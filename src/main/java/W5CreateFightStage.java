import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.sql.SQLException;


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

	private static String tournamentText;
	private static String placeText;
	private static String dateText;
	private static String fightNumText;
	private static String weightText;
	private static String fighterRedText;
	private static String fighterBlueText;
	private static String countryRedText;
	private static String countryBlueText;
	private static String judge1Text;
	private static String judge2Text;
	private static String judge3Text;
	private static String refereeText;

	private static final ObservableList<W5FightsData> data =
			FXCollections.observableArrayList(
					/*new W5FightsData(tournamentText,placeText,dateText,fightNumText,weightText,
							fighterRedText,countryRedText,fighterBlueText,countryBlueText,
							judge1Text, judge2Text, judge3Text, refereeText)*/
					new W5FightsData("t1","t2","t3","t4","t5","t6","t7","t8","t9","t10","t11")
			);




	private static GridPane gridPane = new GridPane();
	private static TableView<W5FightsData> tableView  = new TableView<W5FightsData>();

	public static void setCreateFightsStage (Stage stage) throws SQLException {
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
		eventNameTC.setMinWidth(100);
		eventNameTC.setCellValueFactory(
				new PropertyValueFactory<W5FightsData, String>("eventName")
		);
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
		tableView.setItems(data);
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

	private static ComboBox createFighterRedCBox () throws SQLException {
		fighterRedCBox = new ComboBox();
		fighterRedCBox.getItems().addAll(W5MySQLRequests.getFightersList());
		fighterRedCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

			}
		});
		return fighterRedCBox;
	}

	private static ComboBox createFighterBlueCBox () throws SQLException {
		fighterBlueCBox = new ComboBox();
		fighterBlueCBox.getItems().addAll(W5MySQLRequests.getFightersList());
		return fighterBlueCBox;
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
