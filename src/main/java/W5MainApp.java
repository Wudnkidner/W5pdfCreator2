import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by albert on 24.08.16.
 */
public class W5MainApp extends Application {

    public static void main (String[] args) throws IOException {
    Application.launch(args);
    }

    @Override
    public void start (final Stage stage) {
        W5FirstPageJavaFX.firstPage(stage);
    }

}
