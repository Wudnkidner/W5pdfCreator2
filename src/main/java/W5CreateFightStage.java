import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class W5CreateFightStage {
	private static ComboBox tournamentCBox;
	private static ComboBox placeCBox;
	private static ComboBox dateCBox;
	private static ComboBox fightNumCBox;
	private static ComboBox weightCBox;
	private static ComboBox fighterRedCBox;
	private static ComboBox fighterBlueCBox;
	private static ComboBox countryRedCBox;
	private static ComboBox countryBlueCBox;
	private static ComboBox judge1CBox;
	private static ComboBox judge2CBox;
	private static ComboBox judge3CBox;
	private static ComboBox refereeCBox;

	private static GridPane gridPane = new GridPane();
	private static TableView tableView  = new TableView();

	public static void setCreateFightsStage (Stage stage) {
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		Scene scene = new Scene(new Group());
		//GridPane
		gridPane.setPrefSize(1280, 200);
		gridPane.setVgap(5);
		gridPane.setHgap(10);
		gridPane.setPadding(new Insets(10.0));
		gridPane.setAlignment(Pos.TOP_CENTER);
		gridPane.addRow(0, new Label("Event name:"), W5CreateFightStage.createTournamentCBox());
		gridPane.addRow(1, new Label("Place:") ,W5CreateFightStage.createPlaceCBox(),new Label("Date:"), W5CreateFightStage.createDateCBox());
		gridPane.addRow(2, new Label("Fight number:"), createFightNumCBox(), new Label("Weight:"), createWeightCBox());
		gridPane.addRow(3, new Label("Fighter red:"), createFighterRedCBox(), new Label("Fighter blue:"), createFighterBlueCBox());
		gridPane.addRow(4, new Label("Country red:"), createCountryRedCBox(), new Label("Country blue:"), createCountryBlueCBox());
		gridPane.addRow(5, new Label("First judge:"), createJudge1CBox(), new Label("Second judge:"),createJudge2CBox(), new Label("Third judge: "), createJudge3CBox());
		gridPane.addRow(6, new Label("Referee:"), createRefereeCBox());

		//TableView
		tableView.setPrefSize(1280, 300);
		tableView.setEditable(true);

		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		TableColumn eventNameTC = new TableColumn("Event name");
		TableColumn placeTC = new TableColumn("Place");
		TableColumn dateTC = new TableColumn("Date");
		TableColumn fightNumbTC = new TableColumn("Fight №");
		TableColumn cornerRedTC = new TableColumn("Corner red");
		TableColumn countryRedTC = new TableColumn("Country red");
		TableColumn cornerBlueTC = new TableColumn("Corner blue");
		TableColumn countryBlueTC = new TableColumn("Country blue");
		TableColumn firstJudgeTC = new TableColumn("First judge");
		TableColumn secondJudgeTC = new TableColumn("Second judge");
		TableColumn thridJudgeTC = new TableColumn("Thrid judge");
		tableView.getColumns().addAll(eventNameTC, placeTC, dateTC, fightNumbTC, cornerRedTC,
				countryRedTC, cornerBlueTC, countryBlueTC, firstJudgeTC,
				secondJudgeTC,thridJudgeTC);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(gridPane, tableView);

		((Group) scene.getRoot()).getChildren().addAll(vbox);
		stage.setX((primaryScreenBounds.getMaxX()/2) - 640);
		stage.setScene(scene);
		stage.show();

	}

	private static Button createAddBtn () {
		Button addBtn = new Button();
		addBtn.setText("Add");
		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

			}
		});

		return addBtn;
	}

	private static ComboBox createTournamentCBox () {
		tournamentCBox = new ComboBox();
		tournamentCBox.setPrefSize(100,20);
		return tournamentCBox;
	}

	private static ComboBox createPlaceCBox () {
		placeCBox = new ComboBox();

		return placeCBox;
	}

	private static ComboBox createDateCBox () {
		dateCBox = new ComboBox();

		return dateCBox;
	}

	private static ComboBox createFightNumCBox () {
		fightNumCBox = new ComboBox();

		return fightNumCBox;
	}

	private static ComboBox createWeightCBox () {
		weightCBox = new ComboBox();

		return weightCBox;
	}

	private static ComboBox createFighterRedCBox () {
		fighterRedCBox = new ComboBox();

		return fighterRedCBox;
	}

	private static ComboBox createFighterBlueCBox () {
		fighterRedCBox = new ComboBox();

		return fighterRedCBox;
	}

	private static ComboBox createCountryRedCBox () {
		fighterRedCBox = new ComboBox();

		return fighterRedCBox;
	}

	private static ComboBox createCountryBlueCBox () {
		fighterRedCBox = new ComboBox();

		return fighterRedCBox;
	}

	private static ComboBox createJudge1CBox () {
		fighterRedCBox = new ComboBox();

		return fighterRedCBox;
	}

	private static ComboBox createJudge2CBox () {
		fighterRedCBox = new ComboBox();

		return fighterRedCBox;
	}

	private static ComboBox createJudge3CBox () {
		fighterRedCBox = new ComboBox();

		return fighterRedCBox;
	}

	private static ComboBox createRefereeCBox () {
		refereeCBox = new ComboBox();

		return refereeCBox;
	}

}
