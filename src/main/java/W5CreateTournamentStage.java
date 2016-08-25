import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class W5CreateTournamentStage {

	private static TextField tournamentFld = new TextField();
	private static TextField cityFld = new TextField();
	private static TextField placeFld = new TextField();
	private static TextField dateFld = new TextField();


	public static void setCreateTournamentStage (Stage stage) {

		GridPane gp = new GridPane();
		gp.setPrefSize(1024, 768);

		gp.addRow(0, new Label("Tournament name: "), tournamentFld);
		gp.addRow(1, new Label("City: "), cityFld);
		gp.addRow(2, new Label("Place: "), placeFld);
		gp.addRow(3, new Label("Date: "), dateFld);
		gp.addRow(4, new Label(""),W5Buttons.setSaveTournamentBtn(stage));
		gp.addRow(5, new Label(""),W5Buttons.setBackBtn(stage));


		gp.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(5);
		gp.setMinSize(1024,768);

		gp.setStyle("-fx-padding: 10;"
		);


		Scene scene = new Scene(gp);
		stage.setScene(scene);
		stage.show();
		
		
	}
	
}
