import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


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

	private static Label statusLbl = new Label("waiting...");


	private static final ObservableList<W5FightsData> data =
			FXCollections.observableArrayList();





	private static GridPane gridPane;
	private static TableView<W5FightsData> tableView;

	public static void setCreateFightsStage (Stage stage) throws SQLException {
		data.clear();
		gridPane = new GridPane();
		tableView = new TableView<W5FightsData>();
		data.addAll(W5MySQLRequests.getFightsList());



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
		gridPane.addRow(7, new Label(""),createAddBtn(), createDeleteBtn(), W5Buttons.setBackBtn(stage));
		gridPane.addRow(8, new Label("Status: "), statusLbl);
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
		placeTC.setMinWidth(100);
		placeTC.setCellValueFactory(
				new PropertyValueFactory<W5FightsData, String>("place")
		);

		TableColumn dateTC = new TableColumn("Date");
		dateTC.setMinWidth(100);
		dateTC.setCellValueFactory(
				new PropertyValueFactory<W5FightsData, String>("date")
		);

		TableColumn fightNumbTC = new TableColumn("Fight №");
		fightNumbTC.setMinWidth(100);
		fightNumbTC.setCellValueFactory(
				new PropertyValueFactory<W5FightsData, String>("fightNumb")
		);

		TableColumn cornerRedTC = new TableColumn("Corner red");
		cornerRedTC.setMinWidth(100);
		cornerRedTC.setCellValueFactory(
				new PropertyValueFactory<W5FightsData, String>("cornerRed")
		);

		TableColumn countryRedTC = new TableColumn("Country red");
		countryRedTC.setMinWidth(100);
		countryRedTC.setCellValueFactory(
				new PropertyValueFactory<W5FightsData, String>("countryRed")
		);

		TableColumn cornerBlueTC = new TableColumn("Corner blue");
		cornerBlueTC.setMinWidth(100);
		cornerBlueTC.setCellValueFactory(
				new PropertyValueFactory<W5FightsData, String>("cornerBlue")
		);

		TableColumn countryBlueTC = new TableColumn("Country blue");
		countryBlueTC.setMinWidth(100);
		countryBlueTC.setCellValueFactory(
				new PropertyValueFactory<W5FightsData, String>("countryBlue")
		);

		TableColumn firstJudgeTC = new TableColumn("First judge");
		firstJudgeTC.setMinWidth(100);
		firstJudgeTC.setCellValueFactory(
				new PropertyValueFactory<W5FightsData, String>("firstJudge")
		);

		TableColumn secondJudgeTC = new TableColumn("Second judge");
		secondJudgeTC.setMinWidth(100);
		secondJudgeTC.setCellValueFactory(
				new PropertyValueFactory<W5FightsData, String>("secondJudge")
		);
		TableColumn thridJudgeTC = new TableColumn("Thrid judge");
		thridJudgeTC.setMinWidth(100);
		thridJudgeTC.setCellValueFactory(
				new PropertyValueFactory<W5FightsData, String>("thridJudge")
		);
		TableColumn refereeTC = new TableColumn("Referee");
		refereeTC.setMinWidth(100);
		refereeTC.setCellValueFactory(
				new PropertyValueFactory<W5FightsData, String>("referee")
		);
		tableView.setItems(data);
		tableView.getColumns().addAll(eventNameTC, placeTC, dateTC, fightNumbTC, cornerRedTC,
				countryRedTC, cornerBlueTC, countryBlueTC, firstJudgeTC,
				secondJudgeTC,thridJudgeTC,refereeTC);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(gridPane, tableView);

		((Group) scene.getRoot()).getChildren().addAll(vbox);
		stage.setX((primaryScreenBounds.getMaxX()/2) - 640);
		stage.setScene(scene);
		stage.show();

	}

	private static Button createAddBtn () throws SQLException {
		Button addBtn = new Button();
		addBtn.setPrefWidth(156);
		addBtn.setText("Add");
		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event)  {
				try {
					data.clear();//"обнуляю" ячейки в tableview
					W5MySQLRequests.insertRow(tournamentText, placeText, dateText,
                            fightNumText, fighterRedText, countryRedText,
                            fighterBlueText, countryBlueText, judge1Text,
                            judge2Text, judge3Text, refereeText);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					data.addAll(W5MySQLRequests.getFightsList());
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}) ;

		return addBtn;
	}

	private static Button createDeleteBtn () {
		Button addBtn = new Button("Delete");
		addBtn.setPrefWidth(156);
		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				W5FightsData selectedItem = tableView.getSelectionModel().getSelectedItem();
				tableView.getItems().remove(selectedItem);
				try {
					W5MySQLRequests.deleteRow(selectedItem.getFightNumb());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					data.clear();
					tableView.refresh();
					data.addAll(W5MySQLRequests.getFightsList());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		return addBtn;
	}

	private static ComboBox createTournamentCBox () throws SQLException {
		tournamentCBox = new ComboBox();
		tournamentCBox.getItems().addAll(W5MySQLRequests.getTournamentsList());
		tournamentCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				tournamentText = newValue;
			}
		});
		return tournamentCBox;
	}

	private static ComboBox createPlaceCBox () throws SQLException {
		placeCBox = new ComboBox();
		placeCBox.getItems().addAll(W5MySQLRequests.getPlaceList());
		placeCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				placeText = newValue;
			}
		});
		return placeCBox;
	}

	private static ComboBox createDateCBox () throws SQLException {
		dateCBox = new ComboBox();
		dateCBox.getItems().addAll(W5MySQLRequests.getDateList());
		dateCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				dateText = newValue;
			}
		});
		return dateCBox;
	}

	private static ComboBox createFightNumCBox () throws SQLException {
		fightNumCBox = new ComboBox();
		fightNumCBox.getItems().addAll(W5MySQLRequests.getFightNumList());
		fightNumCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				fightNumText = newValue;
			}
		});
		return fightNumCBox;
	}

	private static ComboBox createWeightCBox () throws SQLException {
		weightCBox = new ComboBox();
		weightCBox.getItems().addAll(W5MySQLRequests.getWeightList());
		weightCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				weightText = newValue;
			}
		});
		return weightCBox;
	}

	private static ComboBox createFighterRedCBox () throws SQLException {
		fighterRedCBox = new ComboBox();
		fighterRedCBox.getItems().addAll(W5MySQLRequests.getFightersList());
		fighterRedCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				fighterRedText = newValue;
			}
		});
		return fighterRedCBox;
	}

	private static ComboBox createFighterBlueCBox () throws SQLException {
		fighterBlueCBox = new ComboBox();
		fighterBlueCBox.getItems().addAll(W5MySQLRequests.getFightersList());
		fighterBlueCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				fighterBlueText = newValue;
			}
		});
		return fighterBlueCBox;
	}

	private static ComboBox createCountryRedCBox () throws SQLException {
		countryRedCBox = new ComboBox();
		countryRedCBox.getItems().addAll(W5MySQLRequests.getCountryList());
		countryRedCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				countryRedText = newValue;
			}
		});
		return countryRedCBox;
	}

	private static ComboBox createCountryBlueCBox () throws SQLException {
		countryBlueCBox = new ComboBox();
		countryBlueCBox.getItems().addAll(W5MySQLRequests.getCountryList());
		countryBlueCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				countryBlueText = newValue;
			}
		});
		return countryBlueCBox;
	}

	private static ComboBox createJudge1CBox () throws SQLException {
		judge1CBox = new ComboBox();
		judge1CBox.getItems().addAll(W5MySQLRequests.getJudgeList());
		judge1CBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				judge1Text = newValue;
			}
		});
		return judge1CBox;
	}

	private static ComboBox createJudge2CBox () throws SQLException {
		judge2CBox = new ComboBox();
		judge2CBox.getItems().addAll(W5MySQLRequests.getJudgeList());
		judge2CBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				judge2Text = newValue;
			}
		});
		return judge2CBox;
	}

	private static ComboBox createJudge3CBox () throws SQLException {
		judge3CBox = new ComboBox();
		judge3CBox.getItems().addAll(W5MySQLRequests.getJudgeList());
		judge3CBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				judge3Text = newValue;
			}
		});
		return judge3CBox;
	}

	private static ComboBox createRefereeCBox () throws SQLException {
		refereeCBox = new ComboBox();
		refereeCBox.getItems().addAll(W5MySQLRequests.getRefereeList());
		refereeCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				refereeText = newValue;
			}
		});
		return refereeCBox;
	}

	public static void setStatus (final String status) {
		statusLbl.setText(status);
		statusLbl.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY.EMPTY,Insets.EMPTY)));
		Task<Void> sleeper = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				return null;
			}
		};
		sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				statusLbl.setText("waiting...");
				statusLbl.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY.EMPTY,Insets.EMPTY)));
			}
		});
		new Thread(sleeper).start();
	}

}
