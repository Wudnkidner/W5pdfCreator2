import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by albert on 24.08.16.
 */
public class W5FirstPageJavaFX {

    private static Button createTournament = new Button();
    private static Button createFighter = new Button();
    private static Button createJudge = new Button();

    public static void firstPage (Stage stage) {
        createTournament.setText("Create tournament");
        createFighter.setText("Create fighter");
        createJudge.setText("Create judge");

        GridPane gp = new GridPane();

        gp.addRow(0,createTournament);


        Scene scene = new Scene(gp);
        stage.setScene(scene);
        stage.show();
    }

}
