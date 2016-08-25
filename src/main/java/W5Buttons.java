import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class W5Buttons {
	
	
	//Create buttons
	public static Button setCreateTournamentBtn (final Stage stage) {
		Button createTournament = new Button();
		createTournament.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				W5CreateTournamentStage.setCreateTournamentStage(stage);

			}
		});
		
		createTournament.setText("Create tournament");
		return createTournament;
	}
	
	public static Button setCreateFighterBtn (final Stage stage) {
		Button createFighter = new Button();
		createFighter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				W5CreateFighterStage.setCreateFighterStage(stage);
			}
		});
		createFighter.setText("Create fighter");
		return createFighter;
	}
	
	public static Button setCreateJudgeBtn (final Stage stage) {
		Button createJudge = new Button();

		createJudge.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				W5CreateJudgeStage.setCreateJudgeStage(stage);
			}
		});

		createJudge.setText("Create judge");
		return createJudge;
	}

	static Button setCreateFightBtn (final Stage stage) {
		Button createFight = new Button();

		createFight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				W5CreateFightStage.setCreateFightsStage(stage);
			}
		});

		createFight.setText("Create fight");
		return createFight;
	}
	
	public static Button setBackBtn(final Stage stage) {
		Button backButton = new Button();
		backButton.setText("Back");
		
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				W5FirstScene.setFirstScene(stage);
				
			}
		});
		
		return backButton;
	}


	//Save buttons
	public static Button setSaveTournamentBtn(final String tournament, final String city, final String place, final String date) {
		final Button saveTournament = new Button();
		saveTournament.setText("Save");

		saveTournament.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			Connection conn = W5MySQLConnection.getConnection();
				PreparedStatement insertNewRow;
				String insertString =
						"INSERT INTO Tournaments"+
								"(name,city,place,date)"+
								"VALUES"+
								"(?,?,?,?)";

				try {
					insertNewRow = conn.prepareStatement(insertString);
					insertNewRow.setString(1,tournament);
					insertNewRow.setString(2,city);
					insertNewRow.setString(3,place);
					insertNewRow.setString(4,date);
					System.out.println("1"+tournament+" "+"2"+city+"3"+place+"4"+date);
					insertNewRow.execute();
					conn.close();
					System.out.println("Карамба!");
				} catch (SQLException e) {
					e.printStackTrace();
				}


			}
		});

		return saveTournament;
	}

	public static Button setSaveFighterBtn(final Stage stage) {
		Button saveFighter = new Button();
		saveFighter.setText("Save");

		saveFighter.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {


			}
		});

		return saveFighter;
	}

	public static Button setSaveJudgeBtn(final Stage stage) {
		Button saveJudge = new Button();
		saveJudge.setText("Save");

		saveJudge.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				W5FirstScene.setFirstScene(stage);

			}
		});

		return saveJudge;
	}
}
