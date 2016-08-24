import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceCmyk;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.mysql.cj.jdbc.MysqlDataSource;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Example extends Application
{

    static String element = "";
    public static final String SRC = "/home/albert/clear.pdf";
    public static final String SRС_JUDGE_LIST = "/home/albert/Empty/judge_list.pdf";
    public static final String SRC_DIPLOMA = "/home/albert/Empty/diploma.pdf";

    public static String DEST = "/home/albert/result/Clear1.pdf";
    public static String DEST_JUDGE_LIST = "/home/result/judge_list1.pdf";


    public static final String FONT = "/home/albert/Fonts/MyriadPro-BoldCond.otf";
    public static final String FONT_NAME_DIPLOMA = "/home/albert/Fonts/CondaraBold.ttf";
    public static final String FONT_NAME = "/home/albert/Fonts/OpenSans-Regular.ttf";
    public static final String FONT_JUDGE = "/home/albert/Fonts/OpenSans-Regular.ttf";


    public static final Color MYBLUE = new DeviceCmyk(70,30,0,18);
    public static final Color MYRED = new DeviceCmyk(0,83,84,13);

    public static String weightCategoryText = "";
    public static String fightNumberText = "";
    public static String tournamentText = "";
    public static String cityText = "";
    public static String placeText = "";
    public static String dateText = "";
    public static String nameRedText = "";
    public static String nationalityRedText = "";
    public static String nameBlueText = "";
    public static String nationalityBlueText = "";
    public static String refereeText = "";
    public static String refereeNationText = "";
    public static String judgeText = "";
    public static String judgeNationText = "";
    public static String selectedValue = "";
    public static String selectedValue2 = "";

    Button saveFighter = new Button();
    Button saveJudge = new Button();
    Button insertFighter = new Button();
    Button insertJudge = new Button();
    Button createTournament = new Button();
    Button back = new Button();
    Button saveTournament = new Button();
    Button pdfMaker = new Button();
    Button createPdf = new Button();



    // Create the Message Label
    Label messageLbl = new Label("Enter data into the text fields.");
    Label progressCompleteLbl = new Label("ожидание");

    ProgressBar pb = new ProgressBar(0);

    final TextField tournamentFld = new TextField();
    final TextField fightNumberFld = new TextField();
    final TextField nameFld = new TextField();
    final TextField surnameFld = new TextField();
    final TextField nicknameFld = new TextField();
    final TextField countryFld = new TextField();
    final TextField ageFld = new TextField();
    final TextField heightFld = new TextField();
    final TextField fightsFld = new TextField();
    final TextField winFld = new TextField();
    final TextField koFld = new TextField();
    final TextField lossFld = new TextField();
    final TextField drawFld =  new TextField();
    final TextField dateFld = new TextField();
    final TextField cityFld = new TextField();
    final TextField placeFld = new TextField();
    final TextField weight_categoryFld = new TextField();

    ArrayList<String> arrayBox = new ArrayList<String>();
    ArrayList<String> arrayRefereeBox = new ArrayList<String>();
    ArrayList<String> arrayFighters = new ArrayList<String>();
    ArrayList<String> arrayNationality = new ArrayList<String>();
    ArrayList<String> arrayJudge = new ArrayList<String>();
    ArrayList<String> arrayRefereesCountry = new ArrayList<String>();
    ArrayList<String> arrayJudgeCountry = new ArrayList<String>();
    ArrayList<String> arrayListFighters = new ArrayList<String>();
    ArrayList<String> arrayListMedicalFighters = new ArrayList<String>();
    ArrayList<String> arrayListWeightInFighters = new ArrayList<String>();
    ComboBox<String> tournamentsBox = new ComboBox<String>();







    public static void main(String[] args) throws IOException {
        Application.launch(args);
    }

    @Override
    public void start(final Stage stage) {

        insertFighter.setText("Insert fighter");
        insertJudge.setText("Insert judge");
        back.setText("Back");
        saveFighter.setText("Save");
        saveJudge.setText("Save");
        createTournament.setText("Create tournament");
        saveTournament.setText("Save");
        pdfMaker.setText("PDF maker");
        createPdf.setText("Create PDF");
        // Both fields should be wide enough to display 15 chars
        nameFld.setPrefColumnCount(16);
        surnameFld.setPrefColumnCount(16);


        // Set ActionEvent handlers for both fields
        createPdf.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                File file = new File(DEST);
                file.getParentFile().mkdirs();

                try {
                    try {
                        //Progress bar
                        Integer STARTTIME =15;
                        IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
                        pb.progressProperty().bind(timeSeconds.divide(STARTTIME*100.0).subtract(1).multiply(-1));

                        new W5pdfCreator().manipulatePdf(SRC,DEST);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        fightNumberFld.setOnAction((new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

            }
        }));



        createTournament.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Scene scene = createTournament();
                stage.setScene(scene);
            }
        });



        back.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                arrayBox.clear();
                arrayRefereeBox.clear();

                Scene scene = scene1();
                stage.setScene(scene);
            }
        });

        insertFighter.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                GridPane root = new GridPane();
                // Set the horizontal spacing to 10px
                root.setHgap(10);
                // Set the vertical spacing to 5px
                root.setVgap(5);
                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUser("root");
                dataSource.setPassword("root");
                dataSource.setUrl("jdbc:mysql://localhost/w5database?useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow");
                dataSource.setPort(3306);

                Connection conn = null;
                try {
                    conn = dataSource.getConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Statement stmt = null;
                try {
                    stmt = conn.createStatement();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ResultSet rs = null;
                try {
                    rs = stmt.executeQuery("SELECT * FROM tournaments");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    while(rs.next()) {
                        tournamentsBox.getItems().clear();
                        arrayBox.add(rs.getString("tournament"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                tournamentsBox.getItems().addAll(arrayBox);

                tournamentsBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()

                {

                    public void changed(ObservableValue<? extends String> ov, final String oldValue, final String newValue)

                    {
                        selectedValue2 = newValue;

                    }

                });

                //tournamentsBox.getItems().addAll(Box[0]);


                // Add Labels and Fields to the GridPane

                root.addRow(0, messageLbl);
                root.addRow(1, new Label("Tournament:"), tournamentsBox);
                root.addRow(2, new Label("Fight number:"), fightNumberFld);
                root.addRow(3, new Label("Name:"), nameFld);
                root.addRow(4, new Label("Surname:"), surnameFld);
                root.addRow(5, new Label("Nickname:"), nicknameFld);
                root.addRow(6, new Label("Country:"), countryFld);
                root.addRow(7, new Label("Age:"), ageFld);
                root.addRow(8, new Label("Height:"), heightFld);
                root.addRow(9, new Label("Fights:"), fightsFld);
                root.addRow(10, new Label("Win:"), winFld);
                root.addRow(11, new Label("KO:"), koFld);
                root.addRow(12, new Label("Loss:"), lossFld);
                root.addRow(13, new Label("Draw:"), drawFld);
                root.addRow(14, new Label("Weight category:"), weight_categoryFld);
                root.addRow(15, new Label(""), saveFighter);
                root.addRow(16, new Label(""), back);

                root.setMinSize(1028, 720);

                root.setStyle("-fx-padding: 10;"
                );

                Scene scene = new Scene(root);

                stage.setScene(scene);
            }
        });

        insertJudge.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Scene scene = scene2();
                stage.setScene(scene);
            }
        });

        saveJudge.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUser("root");
                dataSource.setPassword("root");
                dataSource.setUrl("jdbc:mysql://localhost/w5database?useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow");
                dataSource.setPort(3306);
                dataSource.setDatabaseName("w5database");

                Connection conn = null;
                try {
                    conn = dataSource.getConnection();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                PreparedStatement insertNewRow = null;
                String insertString =
                        "INSERT INTO referees"+
                                "(tournament,fight_number,name,surname,country)"+
                                "VALUES"+
                                "(?,?,?,?,?)";

                tournamentsBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()

                {

                    public void changed(ObservableValue<? extends String> ov, final String oldValue, final String newValue)

                    {
                        selectedValue = newValue;
                    }

                });

                try {
                    insertNewRow = conn.prepareStatement(insertString);

                    insertNewRow.setString(1,selectedValue);
                    insertNewRow.setInt(2,Integer.parseInt(fightNumberFld.getText()));
                    insertNewRow.setString(3,nameFld.getText());
                    insertNewRow.setString(4,surnameFld.getText());
                    insertNewRow.setString(5,countryFld.getText());
                    insertNewRow.execute();
                    conn.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


                tournamentFld.clear();
                fightNumberFld.clear();
                nameFld.clear();
                surnameFld.clear();
                nicknameFld.clear();
                countryFld.clear();
                ageFld.clear();
                heightFld.clear();
                fightsFld.clear();
                winFld.clear();
                koFld.clear();
                lossFld.clear();
                drawFld.clear();


            }
        });

        saveTournament.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUser("root");
                dataSource.setPassword("root");
                dataSource.setUrl("jdbc:mysql://localhost/w5database?useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow");
                dataSource.setPort(3306);
                dataSource.setDatabaseName("w5database");

                Connection conn = null;
                try {
                    conn = dataSource.getConnection();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                PreparedStatement insertNewRow = null;
                String insertString =
                        "INSERT INTO tournaments"+
                                "(tournament,city,place,date)"+
                                "VALUES"+
                                "(?,?,?,?)";



                try {
                    insertNewRow = conn.prepareStatement(insertString);
                    insertNewRow.setString(1,tournamentFld.getText());
                    insertNewRow.setString(2,cityFld.getText());
                    insertNewRow.setString(3,placeFld.getText());
                    insertNewRow.setString(4,dateFld.getText());


                    insertNewRow.execute();
                    conn.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


                tournamentFld.clear();
                cityFld.clear();
                placeFld.clear();
                dateFld.clear();


            }
        });

        pdfMaker.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stage.setScene(pdfMaker());
                progressCompleteLbl.setText("test");

            }
        });

        saveFighter.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUser("root");
                dataSource.setPassword("root");
                dataSource.setUrl("jdbc:mysql://localhost/w5database?useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow");
                dataSource.setPort(3306);
                dataSource.setDatabaseName("w5database");
                Connection conn = null;
                try {
                    conn = dataSource.getConnection();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                PreparedStatement insertNewRow = null;
                String insertString =
                        "INSERT INTO fighters"+
                                "(tournament,fight_number,name,surname,nickname,country,age,height,fights,win,ko,loss,draw,weight_category)"+
                                "VALUES"+
                                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";



                try {

                    insertNewRow = conn.prepareStatement(insertString);

                    insertNewRow.setString(1,selectedValue2);
                    insertNewRow.setInt(2,Integer.parseInt(fightNumberFld.getText()));
                    insertNewRow.setString(3,nameFld.getText());
                    insertNewRow.setString(4,surnameFld.getText());
                    insertNewRow.setString(5,nicknameFld.getText().length() == 0 ? "" : nicknameFld.getText());
                    insertNewRow.setString(6,countryFld.getText());
                    insertNewRow.setInt(7,Integer.parseInt(ageFld.getText()));
                    insertNewRow.setFloat(8,Float.parseFloat(heightFld.getText()));
                    insertNewRow.setInt(9,fightsFld.getText().length() == 0 ? 0 : Integer.parseInt(fightsFld.getText()));
                    insertNewRow.setInt(10, winFld.getText().length() == 0 ? 0 :Integer.parseInt(winFld.getText()));
                    insertNewRow.setInt(11, koFld.getText().length() == 0 ? 0 :Integer.parseInt(koFld.getText()));
                    insertNewRow.setInt(12, lossFld.getText().length() == 0 ? 0 : Integer.parseInt(lossFld.getText()));
                    insertNewRow.setInt(13, drawFld.getText().length() == 0 ? 0 : Integer.parseInt(drawFld.getText()));
                    insertNewRow.setString(14, weight_categoryFld.getText());
                    insertNewRow.execute();

                    conn.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


                tournamentFld.clear();
                fightNumberFld.clear();
                nameFld.clear();
                surnameFld.clear();
                nicknameFld.clear();
                countryFld.clear();
                ageFld.clear();
                heightFld.clear();
                fightsFld.clear();
                winFld.clear();
                koFld.clear();
                lossFld.clear();
                drawFld.clear();
                weight_categoryFld.clear();


            }
        });

        // Set the Size of the GridPane



		/*
		 * Set the padding of the GridPane
		 * Set the border-style of the GridPane
		 * Set the border-width of the GridPane
		 * Set the border-insets of the GridPane
		 * Set the border-radius of the GridPane
		 * Set the border-color of the GridPane
		*/



        // Create the Scene

        Scene scene = scene1();

        // Add the scene to the Stage


        stage.setScene(scene);

        // Set the title of the Stage
        stage.setTitle("W5 Tournaments");
        // Display the Stage
        stage.show();

    }

    // Helper Method


    public Scene createTournament () {
        GridPane select = new GridPane();


        tournamentFld.setPrefColumnCount(18);
        select.addRow(0, new Label("Tournament name: "), tournamentFld);
        select.addRow(1, new Label("City: "), cityFld);
        select.addRow(2, new Label("Place: "), placeFld);
        select.addRow(3, new Label("Date: "), dateFld);
        select.addRow(4, new Label(""),saveTournament);
        select.addRow(5, new Label(""), back);



        select.setHgap(10);
        select.setVgap(5);
        select.setMinSize(1028,720);

        select.setStyle("-fx-padding: 10;"
        );

        Scene scene = new Scene(select);
        return scene;
    }

    public Scene scene1 () {
        GridPane select = new GridPane();


        select.addRow(0, new Label(""), createTournament);
        select.addRow(1, new Label(""), insertFighter);
        select.addRow(2, new Label(""), insertJudge);
        select.addRow(3, new Label(""), pdfMaker);

        select.setHgap(10);
        select.setVgap(5);
        select.setMinSize(1028,720);

        select.setStyle("-fx-padding: 10;"
        );

        Scene scene = new Scene(select);
        return scene;
    }

    public Scene scene2 () {
        GridPane select = new GridPane();

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost/w5database?useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow");
        dataSource.setPort(3306);

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM tournaments");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while(rs.next()) {
                tournamentsBox.getItems().clear();
                arrayBox.add(rs.getString("tournament"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tournamentsBox.getItems().addAll(arrayBox);

        select.addRow(0, new Label("Tournament: "), tournamentsBox);
        select.addRow(1, new Label("Fight number: "), fightNumberFld);
        select.addRow(2, new Label("Name: "), nameFld);
        select.addRow(3, new Label("Surname: "), surnameFld);
        select.addRow(4, new Label("Country: "), countryFld);
        select.addRow(5, new Label(""), saveJudge);
        select.addRow(6, new Label(""), back);


        select.setHgap(10);
        select.setVgap(5);
        select.setMinSize(1028,720);

        select.setStyle("-fx-padding: 10;"
        );

        Scene scene = new Scene(select);
        return scene;
    }

    public Scene pdfMaker () {
        GridPane select = new GridPane();

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost/w5database?useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow");
        dataSource.setPort(3306);

        Connection conn = null;
        Connection conn2 = null;

        try {
            conn = dataSource.getConnection();
            conn2 = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement stmt = null;
        Statement stmt2 = null;
        try {
            stmt = conn.createStatement();
            stmt2 = conn2.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM tournaments");
            rs2 = stmt2.executeQuery("SELECT * FROM referees");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while(rs.next()) {
                tournamentsBox.getItems().clear();
                arrayBox.add(rs.getString("tournament"));
            }
            while(rs2.next()) {
                tournamentsBox.getItems().clear();
                String nameData = rs2.getString("name") + " " + rs2.getString("surname");
                arrayRefereeBox.add(nameData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tournamentsBox.getItems().addAll(arrayBox);


        tournamentsBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()

        {

            public void changed(ObservableValue<? extends String> ov, final String oldValue, final String newValue)

            {

                selectedValue = newValue;

            }

        });



        //refereeBox.getItems().addAll(arrayRefereeBox);

        select.addRow(0, new Label("Tournament: "), tournamentsBox);
        select.addRow(1, new Label(""), createPdf);
        select.addRow(2, new Label(""), back);
        select.addRow(3, new Label("Статус: "), pb);

        select.setHgap(10);
        select.setVgap(5);
        select.setMinSize(1028,720);

        select.setStyle("-fx-padding: 10;"
        );

        Scene scene = new Scene(select);
        return scene;
    }

    public void manipulatePdf(String src, String dest) throws IOException, SQLException {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost/w5database?useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow");
        dataSource.setPort(3306);
        Connection conn = dataSource.getConnection();

        Statement stmtFighters = conn.createStatement();
        Statement stmtReferees = conn.createStatement();
        Statement stmtTournaments = conn.createStatement();
        Statement stmtJudge = conn.createStatement();

        ResultSet rsFightersCounter = stmtFighters.executeQuery("SELECT * FROM fighters WHERE tournament = " + "\'" + selectedValue + "\'");
        while (rsFightersCounter.next()) {
            arrayListFighters.add(rsFightersCounter.getString("name") + " " + rsFightersCounter.getString("surname"));
        }


        for (int i = 1; i <= arrayListFighters.size() / 2; ) {


            ResultSet rsFighters = stmtFighters.executeQuery("SELECT * FROM fighters WHERE fight_number = " + i + " AND tournament = " + "\'" + selectedValue + "\'");
            ResultSet rsReferees = stmtReferees.executeQuery("SELECT * FROM referees WHERE fight_number = " + i + " AND tournament = " + "\'" + selectedValue + "\'");
            ResultSet rsJudge = stmtJudge.executeQuery("SELECT * FROM referees WHERE fight_number != " + i + " AND tournament = " + "\'" + selectedValue + "\'");
            ResultSet rsTournaments = stmtTournaments.executeQuery("SELECT * FROM tournaments");

            while (rsFighters.next()) {
                arrayFighters.add(rsFighters.getString("name") + " " + rsFighters.getString("surname"));
                arrayNationality.add(rsFighters.getString("country"));
                fightNumberText = rsFighters.getString("fight_number");
                weightCategoryText = rsFighters.getString("weight_category");
            }



            nameRedText = arrayFighters.get(0);
            nationalityRedText = arrayNationality.get(0);
            nameBlueText = arrayFighters.get(1);
            nationalityBlueText = arrayNationality.get(1);


            while (rsReferees.next()) {
                arrayRefereeBox.add(rsReferees.getString("name") + " " + rsReferees.getString("surname"));
                arrayRefereesCountry.add(rsReferees.getString("country"));
            }
            refereeText = arrayRefereeBox.get(0);
            refereeNationText = arrayRefereesCountry.get(0);

            while (rsJudge.next()) {

                arrayJudge.add(rsJudge.getString("name") + " " + rsJudge.getString("surname"));
                arrayJudgeCountry.add(rsJudge.getString("name") + " " + rsJudge.getString("surname")+ " " +rsJudge.getString("country"));

            }

            while (rsTournaments.next()) {
                tournamentText = rsTournaments.getString("tournament");
                cityText = rsTournaments.getString("city");
                placeText = rsTournaments.getString("place");
                dateText = rsTournaments.getString("date");
            }

            //Create diploma

            for (int indexFighter = 0; indexFighter < arrayFighters.size(); ) {

                String srcToDiploma = "/home/albert/Empty/diploma.pdf";
                String destToDiploma = "/home/albert/result/diploma/Fight" + i + ", Diploma: " + arrayFighters.get(indexFighter) + ".pdf";

                final String FONT = "/home/albert/Fonts/CandaraRegular.ttf";
                PdfFont condaraRegularFont = PdfFontFactory.createFont(FONT, "Cp1251", true);
                PdfFont font = PdfFontFactory.createFont(FONT_NAME_DIPLOMA, "cp1251", true);
                final Color MYGRAY = new DeviceCmyk(61,56,64,39);



                File createDiploma = new File(destToDiploma);
                createDiploma.getParentFile().mkdirs();

                PdfReader reader = new PdfReader(srcToDiploma);
                PdfWriter writer = new PdfWriter(destToDiploma);
                PdfDocument pdfDoc = new PdfDocument(reader, writer);

                PdfPage page = pdfDoc.getPage(1);
                PdfCanvas pdfCanvas = new PdfCanvas(page);

                Rectangle nameFighterRct = new Rectangle(260, 500, 300, 40);
                Rectangle tournamentRct = new Rectangle(205,365,400,40);
                Rectangle cityRct = new Rectangle(205,335,400,40);
                Rectangle weightCategoryRct = new Rectangle(205, 307, 400, 40);

                Canvas nameRedCnvs = new Canvas(pdfCanvas, pdfDoc, nameFighterRct);
                Canvas tournamentCnvs = new Canvas(pdfCanvas, pdfDoc, tournamentRct);
                Canvas cityCnvs = new Canvas(pdfCanvas, pdfDoc, cityRct);
                Canvas weightCategoryCnvs = new Canvas(pdfCanvas, pdfDoc, weightCategoryRct);

                //pdfCanvas.rectangle(weightCategoryRct);
                //pdfCanvas.stroke();

                Paragraph nameFigter = new Paragraph(arrayFighters.get(indexFighter))
                        .setFont(font)
                        .setFontColor(MYGRAY)
                        .setFontSize(27)
                        .setTextAlignment(TextAlignment.CENTER);

                Paragraph tournament = new Paragraph(tournamentText)
                        .setFont(font)
                        .setFontColor(MYGRAY)
                        .setFontSize(27)
                        .setTextAlignment(TextAlignment.CENTER);

                Paragraph cityStr = new Paragraph().add(dateText).add(", ").add(cityText).add(", ").add(placeText)
                        .setFont(condaraRegularFont)
                        .setFontColor(MYGRAY)
                        .setFontSize(19)
                        .setTextAlignment(TextAlignment.CENTER);

                Text weightCategoryBeginText = new Text("Fight in up to ")
                        .setFont(condaraRegularFont)
                        .setFontColor(MYGRAY)
                        .setFontSize(19);


                Text weightCategory = new Text(weightCategoryText)
                        .setFont(font)
                        .setFontColor(MYGRAY)
                        .setFontSize(19);

                Text weightCategoryEndText = new Text(" kg weight division")
                        .setFont(condaraRegularFont)
                        .setFontColor(MYGRAY)
                        .setFontSize(19);

                Paragraph weightCategoryStr = new Paragraph().add(weightCategoryBeginText).add(weightCategory).add(weightCategoryEndText)
                        .setTextAlignment(TextAlignment.CENTER);

                nameRedCnvs.add(nameFigter);
                tournamentCnvs.add(tournament);
                cityCnvs.add(cityStr);
                weightCategoryCnvs.add(weightCategoryStr);

                pdfDoc.close();
                indexFighter++;

            }
            Set<String> hs0 = new HashSet<String>();
            hs0.addAll(arrayJudge);
            arrayJudge.clear();
            arrayJudge.addAll(hs0);
            hs0.clear();
            arrayJudge.remove(arrayRefereeBox.get(0));




            Set<String> hs = new HashSet<String>();
            hs.addAll(arrayJudgeCountry);
            arrayJudgeCountry.clear();
            arrayJudgeCountry.addAll(hs);
            hs.clear();
            arrayJudgeCountry.remove(arrayRefereeBox.get(0)+" "+arrayRefereesCountry.get(0));

            //Judge list

            for (int indexJudge = 0; indexJudge < 1;) {



                String srcToJudgePdf = "/home/albert/Empty/judge_list.pdf";
                String destToJudgePdf = "/home/albert/result/Fight" + i + " judge_list.pdf";

                PdfFont font = PdfFontFactory.createFont(FONT, "Cp1251", true);

                final String FONT_FIGTERS = "/home/albert/Fonts/OpenSans-ExtraBold.ttf";
                PdfFont fightersFont = PdfFontFactory.createFont(FONT_FIGTERS, "cp1251", true);


                File judge_list = new File(destToJudgePdf);
                judge_list.getParentFile().mkdirs();

                PdfReader reader = new PdfReader(srcToJudgePdf);

                PdfWriter writer = new PdfWriter(destToJudgePdf);


                PdfDocument pdfDoc = new PdfDocument(reader, writer);

                PdfPage page = pdfDoc.getPage(1);
                PdfCanvas pdfCanvas = new PdfCanvas(page);


                Rectangle dateRct = new Rectangle(55, 477, 200, 40);
                Rectangle tournamentRct = new Rectangle(55, 450, 460, 40);
                Rectangle weightCategoryRct = new Rectangle(609, 476, 78, 40);
                Rectangle fightNumberRct = new Rectangle(750, 476, 60, 40);
                Rectangle judge1 = new Rectangle(55, 352, 282, 40);
                Rectangle judge2 = new Rectangle(55, 328, 282, 40);
                Rectangle judge3 = new Rectangle(55, 304, 282, 40);
                Rectangle fighterRedRct = new Rectangle(342,419,210,40);
                Rectangle fighterBlueRct = new Rectangle(596,419,210,40);

                //pdfCanvas.rectangle(dateRct);
                //pdfCanvas.stroke();

                Canvas dateCnvs = new Canvas(pdfCanvas, pdfDoc, dateRct);
                Canvas tournamentCnvs = new Canvas(pdfCanvas, pdfDoc, tournamentRct);
                Canvas weightCategoryCnvs = new Canvas(pdfCanvas, pdfDoc, weightCategoryRct);
                Canvas fightNumberCnvs = new Canvas(pdfCanvas, pdfDoc, fightNumberRct);
                Canvas judge1Cnvs = new Canvas(pdfCanvas, pdfDoc, judge1);
                Canvas judge2Cnvs = new Canvas(pdfCanvas, pdfDoc, judge2);
                Canvas judge3Cnvs = new Canvas(pdfCanvas, pdfDoc, judge3);
                Canvas fighterRedCnvs = new Canvas(pdfCanvas, pdfDoc,fighterRedRct);
                Canvas fighterBlueCnvs = new Canvas(pdfCanvas, pdfDoc,fighterBlueRct);


                Paragraph weightCategoryPrg = new Paragraph(weightCategoryText)
                        .setFont(font)
                        .setFontSize(27)
                        .setTextAlignment(TextAlignment.CENTER);

                Paragraph fightNumberPrg = new Paragraph(fightNumberText)
                        .setFont(font)
                        .setFontSize(27)
                        .setTextAlignment(TextAlignment.CENTER);

                Paragraph judge1Prg = new Paragraph(arrayJudge.get(0))
                        .setFont(font)
                        .setFontSize(20)
                        .setTextAlignment(TextAlignment.LEFT);


                Paragraph judge2Prg = new Paragraph(arrayJudge.get(1))
                        .setFont(font)
                        .setFontSize(20)
                        .setTextAlignment(TextAlignment.LEFT);


                Paragraph judge3Prg = new Paragraph(arrayJudge.get(2))
                        .setFont(font)
                        .setFontSize(20)
                        .setTextAlignment(TextAlignment.LEFT);

                Paragraph fighterRedPrg = new Paragraph(arrayFighters.get(0))
                        .setFont(fightersFont)
                        .setFontSize(20)
                        .setTextAlignment(TextAlignment.CENTER);

                Paragraph fighterBluePrg = new Paragraph(arrayFighters.get(1))
                        .setFont(fightersFont)
                        .setFontSize(20)
                        .setTextAlignment(TextAlignment.CENTER);


                Text cityTxt = new Text(cityText+", ")
                        .setFont(font)
                        .setFontSize(23);

                Text dateTxt = new Text(dateText)
                        .setFont(font)
                        .setFontSize(23);


                Paragraph datePrg = new Paragraph().add(cityTxt).add(dateTxt)
                        .setFont(font)
                        .setFontSize(27);


                Text tournamentText1 = new Text("W5 ")
                        .setFont(font)
                        .setFontSize(27)
                        .setFontColor(MYRED);

                Text tournamentText2 = new Text(tournamentText)
                        .setFont(font)
                        .setFontSize(27);

                Paragraph tournamentPrg = new Paragraph().add(tournamentText1).add(tournamentText2)
                        .setFont(font)
                        .setFontSize(27);


                dateCnvs.add(datePrg);
                tournamentCnvs.add(tournamentPrg);
                weightCategoryCnvs.add(weightCategoryPrg);
                fightNumberCnvs.add(fightNumberPrg);
                judge1Cnvs.add(judge1Prg);
                judge2Cnvs.add(judge2Prg);
                judge3Cnvs.add(judge3Prg);
                fighterRedCnvs.add(fighterRedPrg);
                fighterBlueCnvs.add(fighterBluePrg);


                pdfDoc.close();
                indexJudge++;
            }

            //Create fightcard
            for (int index = 0; index < arrayJudgeCountry.size(); ) {

                String[] arr = arrayJudgeCountry.get(index).split(" ");


                judgeText = arr[0]+" "+arr[1];
                judgeNationText = arr[2];

                dest = "/home/albert/result/Fight" + i + ", Judge " + index + ".pdf";

                PdfReader reader = new PdfReader(src);
                PdfWriter writer = new PdfWriter(dest);
                PdfDocument pdfDoc = new PdfDocument(reader, writer);

                PdfPage page = pdfDoc.getPage(1);
                PdfCanvas pdfCanvas = new PdfCanvas(page);
                Rectangle weightCategoryRct = new Rectangle(354, 730, 78, 40);
                Rectangle fightNumberRct = new Rectangle(496, 730, 62, 40);
                Rectangle tournamentRct = new Rectangle(125, 681, 188, 40);
                Rectangle cityRct = new Rectangle(350, 683, 84, 40);
                Rectangle dateRct = new Rectangle(470, 682, 91, 40);
                Rectangle nameRedRct = new Rectangle(100, 621, 200, 40);
                Rectangle nationalityRedRct = new Rectangle(100, 594, 200, 40);
                Rectangle nameBlueRct = new Rectangle(318, 621, 200, 40);
                Rectangle nationalityBlueRct = new Rectangle(318, 594, 200, 40);
                Rectangle refereeRct = new Rectangle(120, 360, 258, 40);
                Rectangle refereeNationRct = new Rectangle(430, 360, 122, 40);
                Rectangle judgeRct = new Rectangle(120, 315, 258, 40);
                Rectangle judgeNationRct = new Rectangle(430, 315, 122, 40);

                //pdfCanvas.rectangle(judgeNationRct);
                //pdfCanvas.stroke();

                Canvas weightCategoryCnvs = new Canvas(pdfCanvas, pdfDoc, weightCategoryRct);
                Canvas fightNumberCategoryCnvs = new Canvas(pdfCanvas, pdfDoc, fightNumberRct);
                Canvas tournamentCnvs = new Canvas(pdfCanvas, pdfDoc, tournamentRct);
                Canvas cityCnvs = new Canvas(pdfCanvas, pdfDoc, cityRct);
                Canvas dateCnvs = new Canvas(pdfCanvas, pdfDoc, dateRct);
                Canvas nameRedCnvs = new Canvas(pdfCanvas, pdfDoc, nameRedRct);
                Canvas nationalityRedCnvs = new Canvas(pdfCanvas, pdfDoc, nationalityRedRct);
                Canvas nameBlueCnvs = new Canvas(pdfCanvas, pdfDoc, nameBlueRct);
                Canvas nationalityBlueCnvs = new Canvas(pdfCanvas, pdfDoc, nationalityBlueRct);
                Canvas refereeCnvs = new Canvas(pdfCanvas, pdfDoc, refereeRct);
                Canvas refereeNationCnvs = new Canvas(pdfCanvas, pdfDoc, refereeNationRct);
                Canvas judgeCnvs = new Canvas(pdfCanvas, pdfDoc, judgeRct);
                Canvas judgeNationCnvs = new Canvas(pdfCanvas, pdfDoc, judgeNationRct);

                PdfFont font = PdfFontFactory.createFont(FONT, "cp1251", true);
                PdfFont font_name = PdfFontFactory.createFont(FONT_NAME,"cp1251",true);
                PdfFont font_judge = PdfFontFactory.createFont(FONT_JUDGE, "cp1251", true);

                Paragraph weightCategory = new Paragraph(weightCategoryText)
                        .setFont(font)
                        .setFontSize(27)
                        .setTextAlignment(TextAlignment.CENTER);


                Paragraph fightNumber = new Paragraph(fightNumberText)
                        .setFont(font)
                        .setFontSize(27)
                        .setTextAlignment(TextAlignment.CENTER);


                Paragraph tournament = new Paragraph(tournamentText)
                        .setFont(font)
                        .setFontSize(15)
                        .setTextAlignment(TextAlignment.CENTER);


                Paragraph city = new Paragraph(cityText)
                        .setFont(font)
                        .setFontSize(17)
                        .setTextAlignment(TextAlignment.CENTER);

                Paragraph date = new Paragraph(dateText)
                        .setFont(font)
                        .setFontSize(16)
                        .setTextAlignment(TextAlignment.CENTER);

                Paragraph nameRed = new Paragraph(nameRedText)
                        .setFont(font_name)
                        .setFontSize(17)
                        .setFontColor(MYRED)
                        .setTextAlignment(TextAlignment.CENTER);

                Paragraph nationalityRed = new Paragraph(nationalityRedText)
                        .setFont(font_name)
                        .setFontSize(14)
                        .setFontColor(MYRED)
                        .setTextAlignment(TextAlignment.CENTER);

                Paragraph nameBlue = new Paragraph(nameBlueText)
                        .setFont(font_name)
                        .setFontSize(17)
                        .setFontColor(MYBLUE)
                        .setTextAlignment(TextAlignment.CENTER);

                Paragraph nationalityBlue = new Paragraph(nationalityBlueText)
                        .setFont(font_name)
                        .setFontSize(14)
                        .setFontColor(MYBLUE)
                        .setTextAlignment(TextAlignment.CENTER);

                Paragraph referee = new Paragraph(refereeText)
                        .setFont(font_judge)
                        .setFontSize(19)
                        .setTextAlignment(TextAlignment.LEFT);

                Paragraph refereeNation = new Paragraph(refereeNationText)
                        .setFont(font_judge)
                        .setFontSize(19)
                        .setTextAlignment(TextAlignment.CENTER);

                Paragraph judge = new Paragraph(judgeText)
                        .setFont(font_judge)
                        .setFontSize(19)
                        .setTextAlignment(TextAlignment.LEFT);

                Paragraph judgeNation = new Paragraph(judgeNationText)
                        .setFont(font_judge)
                        .setFontSize(19)
                        .setTextAlignment(TextAlignment.CENTER);


                weightCategoryCnvs.add(weightCategory);
                fightNumberCategoryCnvs.add(fightNumber);
                tournamentCnvs.add(tournament);
                cityCnvs.add(city);
                dateCnvs.add(date);
                nameRedCnvs.add(nameRed);
                nationalityRedCnvs.add(nationalityRed);
                nameBlueCnvs.add(nameBlue);
                nationalityBlueCnvs.add(nationalityBlue);
                refereeCnvs.add(referee);
                refereeNationCnvs.add(refereeNation);
                judgeCnvs.add(judge);
                judgeNationCnvs.add(judgeNation);

                pdfDoc.close();
                dest = "/home/albert/result/Fight" + i + ", Judge " +index + ".pdf";
                index++;

            }

            //medical
            Statement stmtMedicalFighters = conn.createStatement();




            if (i < 2) {


                ResultSet rsMedicalFighters = stmtMedicalFighters.executeQuery("SELECT * FROM fighters");

                while (rsMedicalFighters.next()) {
                    arrayListMedicalFighters.add(rsMedicalFighters.getString("name") + " " + rsMedicalFighters.getString("surname")+" "+"("+rsMedicalFighters.getString("country")+")");
                }

                String srcToMedicalPdf = "/home/albert/Empty/medical.pdf";
                String destToMedicalPdf = "/home/albert/result/medical.pdf";


                File medical = new File(destToMedicalPdf);
                medical.getParentFile().mkdirs();

                PdfReader reader = new PdfReader(srcToMedicalPdf);

                PdfWriter writer = new PdfWriter(destToMedicalPdf);


                PdfDocument pdfDoc = new PdfDocument(reader, writer);

                PdfPage page1 = pdfDoc.getPage(1);
                PdfPage page2 = pdfDoc.getPage(2);
                PdfPage page3 = pdfDoc.getPage(3);

                PdfCanvas pdfCanvas1 = new PdfCanvas(page1);
                PdfCanvas pdfCanvas2 = new PdfCanvas(page2);
                PdfCanvas pdfCanvas3 = new PdfCanvas(page3);

                ArrayList<Rectangle> Page1Rct= new ArrayList<Rectangle>();
                ArrayList<Rectangle> Page2Rct= new ArrayList<Rectangle>();
                ArrayList<Rectangle> Page3Rct= new ArrayList<Rectangle>();

                Rectangle page1CityRct = new Rectangle(100,446,140,20);
                Rectangle page1DateRct = new Rectangle(100,428,65,20);

                float x = 95; float y = 365; float weight = 240; float height = 40;

                for (int index = 0; index < arrayListMedicalFighters.size();index++) {
                    if (index % 14 == 0 ) {
                        x = 95; y = 365; weight = 240; height = 40;
                    }
                    if (index < 14) {
                        if (index != 0) y -= 0.2;
                        Page1Rct.add(new Rectangle(x, y, weight, height));
                        y -= 17;

                    }
                    if (index >= 14 && index < 28) {
                        if (index != 0) y -= 0.2;
                        Page2Rct.add(new Rectangle(x, y, weight, height));
                        y -= 17;
                    }
                    if (index >= 28 && index < 42) {
                        if (index != 0) y -= 0.2;
                        Page3Rct.add(new Rectangle(x, y, weight, height));
                        y -= 17;
                    }

                }

                ArrayList<Canvas> Page1Cnvs = new ArrayList<Canvas>();
                ArrayList<Canvas> Page2Cnvs = new ArrayList<Canvas>();
                ArrayList<Canvas> Page3Cnvs = new ArrayList<Canvas>();

                Canvas page1CityCnvs = new Canvas(pdfCanvas1, pdfDoc, page1CityRct);
                Canvas page1DateCnvs = new Canvas(pdfCanvas1, pdfDoc, page1DateRct);

                Canvas page2CityCnvs = new Canvas(pdfCanvas2, pdfDoc, page1CityRct);
                Canvas page2DateCnvs = new Canvas(pdfCanvas2, pdfDoc, page1DateRct);

                Canvas page3CityCnvs = new Canvas(pdfCanvas3, pdfDoc, page1CityRct);
                Canvas page3DateCnvs = new Canvas(pdfCanvas3, pdfDoc, page1DateRct);

                //pdfCanvas1.rectangle(page1CityRct);
                //pdfCanvas1.stroke();

                for (int index = 0; index < Page1Rct.size(); index++) {
                    Page1Cnvs.add(new Canvas(pdfCanvas1, pdfDoc, Page1Rct.get(index)));
                }

                for (int index = 0; index < Page2Rct.size(); index++) {
                    Page2Cnvs.add(new Canvas(pdfCanvas2, pdfDoc, Page2Rct.get(index)));
                }

                for (int index = 0; index < Page3Rct.size(); index++) {
                    Page3Cnvs.add(new Canvas(pdfCanvas3, pdfDoc, Page3Rct.get(index)));
                }

                PdfFont font = PdfFontFactory.createFont(FONT, "Cp1251", true);

                ArrayList<Paragraph> Page1Prg = new ArrayList<Paragraph>();
                ArrayList<Paragraph> Page2Prg = new ArrayList<Paragraph>();
                ArrayList<Paragraph> Page3Prg = new ArrayList<Paragraph>();

                Paragraph page1CityPrg = new Paragraph(cityText)
                        .setFont(font)
                        .setFontSize(14)
                        .setTextAlignment(TextAlignment.LEFT);
                Paragraph page1DatePrg = new Paragraph(dateText)
                        .setFont(font)
                        .setFontSize(14)
                        .setTextAlignment(TextAlignment.LEFT);

                Paragraph page2CityPrg = new Paragraph(cityText)
                        .setFont(font)
                        .setFontSize(14)
                        .setTextAlignment(TextAlignment.LEFT);
                Paragraph page2DatePrg = new Paragraph(dateText)
                        .setFont(font)
                        .setFontSize(14)
                        .setTextAlignment(TextAlignment.LEFT);

                Paragraph page3CityPrg = new Paragraph(cityText)
                        .setFont(font)
                        .setFontSize(14)
                        .setTextAlignment(TextAlignment.LEFT);
                Paragraph page3DatePrg = new Paragraph(dateText)
                        .setFont(font)
                        .setFontSize(14)
                        .setTextAlignment(TextAlignment.LEFT);

                for (int index = 0; index < arrayListMedicalFighters.size(); index++) {
                    if (index < 14) {
                        Page1Prg.add(new Paragraph(arrayListMedicalFighters.get(index))
                                .setFont(font)
                                .setFontSize(18)
                                .setTextAlignment(TextAlignment.LEFT));
                    }

                    if (index >=14 && index < 28) {
                        Page2Prg.add(new Paragraph(arrayListMedicalFighters.get(index))
                                .setFont(font)
                                .setFontSize(18)
                                .setTextAlignment(TextAlignment.LEFT));
                    }

                    if (index >=28 && index < 42) {
                        Page2Prg.add(new Paragraph(arrayListMedicalFighters.get(index))
                                .setFont(font)
                                .setFontSize(18)
                                .setTextAlignment(TextAlignment.LEFT));
                    }

                }


                for (int index = 0; index < Page1Cnvs.size(); index++){
                    Canvas cnvs = Page1Cnvs.get(index);
                    cnvs.add(Page1Prg.get(index));
                }


                for (int index = 0; index < Page2Cnvs.size(); index++){
                    Canvas cnvs = Page2Cnvs.get(index);
                    cnvs.add(Page2Prg.get(index));
                }

                for (int index = 0; index < Page3Cnvs.size(); index++){
                    Canvas cnvs = Page2Cnvs.get(index);
                    cnvs.add(Page2Prg.get(index));
                }

                page1CityCnvs.add(page1CityPrg);
                page1DateCnvs.add(page1DatePrg);

                page2CityCnvs.add(page2CityPrg);
                page2DateCnvs.add(page2DatePrg);

                page3CityCnvs.add(page3CityPrg);
                page3DateCnvs.add(page3DatePrg);

                pdfDoc.close();
            }

            //WEIGHT_IN
            if (i < 2) {

                String[] arr = {};
                String fighterText;
                String fighterCountryText;

                ResultSet rsWeightIn = stmtMedicalFighters.executeQuery("SELECT * FROM fighters");

                while (rsWeightIn.next()) {
                    arrayListWeightInFighters.add(rsWeightIn.getString("name") + " " + rsWeightIn.getString("surname")+" "+rsWeightIn.getString("country"));
                }

                for (int index = 0; index < arrayListWeightInFighters.size(); index++) {
                    arr = arrayListWeightInFighters.get(index).split(" ");
                    fighterText = arr[0] + arr[1];
                    fighterCountryText = arr[2];
                }





                System.out.println(arrayListWeightInFighters.size());
                System.out.println(arrayListWeightInFighters);

                String srcToMedicalPdf = "/home/albert/Empty/weight.pdf";
                String destToMedicalPdf = "/home/albert/result/weight.pdf";


                File weightIn = new File(destToMedicalPdf);
                weightIn.getParentFile().mkdirs();

                PdfReader reader = new PdfReader(srcToMedicalPdf);

                PdfWriter writer = new PdfWriter(destToMedicalPdf);

                PdfDocument pdfDoc = new PdfDocument(reader, writer);

                PdfPage page1 = pdfDoc.getPage(1);

                PdfCanvas pdfCanvas1 = new PdfCanvas(page1);

                ArrayList<Rectangle> Page1Rct = new ArrayList<Rectangle>();
                for (int index = 0; index < arrayListWeightInFighters.size(); index++ ) {
                    Page1Rct.add(new Rectangle(100,100,100,100));
                }

                ArrayList<Canvas> Page1Cnvs = new ArrayList<Canvas>();

                for (int index = 0; index < arrayListWeightInFighters.size(); index++ ) {

                }

                ArrayList<Paragraph> Page1Prg = new ArrayList<Paragraph>();
                for (int index = 0; index < arrayListWeightInFighters.size(); index++ ) {

                }

                pdfDoc.close();

            }

            arrayRefereeBox.clear();
            arrayFighters.clear();
            arrayRefereesCountry.clear();
            arrayJudge.clear();
            arrayJudgeCountry.clear();
            System.out.println("Документы на " + i + " бой созданы");
            i++;
            System.exit(1);



        }

    }

    public void printMessage (String message) {
        progressCompleteLbl.setText(message);
    }


}













s