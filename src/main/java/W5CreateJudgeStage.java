import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class W5CreateJudgeStage {

	public static void setCreateTournamentStage (Stage stage) {

		GridPane gp = new GridPane();
		gp.setPrefSize(1024, 768);
		gp.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		gp.addRow(0, W5Buttons.setBackBtn(stage));
		Scene scene = new Scene(gp);
		stage.setScene(scene);
		stage.show();
		
		
	}
	
}
