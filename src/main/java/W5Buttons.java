import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
	public static Button setSaveTournamentBtn(final Stage stage) {
		Button saveTournament = new Button();
		saveTournament.setText("Save");

		saveTournament.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				W5FirstScene.setFirstScene(stage);

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
