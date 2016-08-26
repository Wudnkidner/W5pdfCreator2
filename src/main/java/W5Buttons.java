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
	public static Button setSaveTournamentBtn() {
		final Button saveTournament = new Button();
		saveTournament.setText("Save");

		saveTournament.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (W5CreateTournamentStage.getTournamentNameText().length() > 0 && W5CreateTournamentStage.getTournamentCityText().length() > 0 && W5CreateTournamentStage.getTournamentPlaceText().length() > 0 && W5CreateTournamentStage.getTournamentDateText().length() > 0) {
				Connection conn = W5MySQLConnection.getConnection();
				PreparedStatement insertNewRow;
				String insertString =
						"INSERT INTO Tournaments"+
								"(name,city,place,date)"+
								"VALUES"+
								"(?,?,?,?)";
				try {

						insertNewRow = conn.prepareStatement(insertString);
						insertNewRow.setString(1, W5CreateTournamentStage.getTournamentNameText());
						insertNewRow.setString(2, W5CreateTournamentStage.getTournamentCityText());
						insertNewRow.setString(3, W5CreateTournamentStage.getTournamentPlaceText());
						insertNewRow.setString(4, W5CreateTournamentStage.getTournamentDateText());

						insertNewRow.execute();
						conn.close();
						W5CreateTournamentStage.clearFlds();

					}catch(SQLException e){
						e.printStackTrace();
					}
				} else {
					System.out.println("Пожалуйста заполните поля");
				}

			}
		});

		return saveTournament;
	}

	public static Button setSaveFighterBtn() {
		Button saveFighter = new Button();
		saveFighter.setText("Save");

		saveFighter.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {


			}
		});

		return saveFighter;
	}

	public static Button setSaveJudgeBtn() {
		Button saveJudge = new Button();
		saveJudge.setText("Save");

		saveJudge.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {


			}
		});

		return saveJudge;
	}
}
