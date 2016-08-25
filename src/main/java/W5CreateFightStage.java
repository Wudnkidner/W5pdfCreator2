import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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

	public static void setCreateFightsStage (Stage stage) {

		GridPane gp = new GridPane();
		gp.setPrefSize(1024, 768);
		gp.setVgap(5);
		gp.setHgap(10);
		gp.setPadding(new Insets(10.0));
		gp.setAlignment(Pos.TOP_CENTER);
		gp.addRow(0, new Label("Event name:"), W5CreateFightStage.createTournamentCBox());
		gp.addRow(1, new Label("Place:") ,W5CreateFightStage.createPlaceCBox(),new Label("Date:"), W5CreateFightStage.createDateCBox());
		gp.addRow(2, new Label("Fight number:"), createFightNumCBox(), new Label("Weight:"), createWeightCBox());
		gp.addRow(3, new Label("Fighter red:"), createFighterRedCBox(), new Label("Fighter blue:"), createFighterBlueCBox());
		gp.addRow(4, new Label("Country red:"), createCountryRedCBox(), new Label("Country blue:"), createCountryBlueCBox());
		gp.addRow(5, new Label("First judge:"), createJudge1CBox(), new Label("Second judge:"),createJudge2CBox(), new Label("Third judge: "), createJudge3CBox());
		gp.addRow(6, new Label("Referee:"), createRefereeCBox());

		Scene scene = new Scene(gp);
		stage.setScene(scene);
		stage.show();

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
