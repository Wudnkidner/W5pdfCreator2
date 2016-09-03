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

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by albert on 02.09.16.
 */
public class W5DiplomaPDF {

    private static final String FONT = System.getProperty("user.home") + "/resources/fonts/MyriadPro-BoldCond.otf";
    private static final String FONT_NAME_DIPLOMA = System.getProperty("user.home") + "/resources/fonts/CondaraBold.ttf";
    private static final String FONT_NAME = System.getProperty("user.home") + "/resources/fonts/OpenSans-Regular.ttf";
    private static final String FONT_JUDGE = System.getProperty("user.home") + "/resources/fonts/OpenSans-Regular.ttf";


    private static final Color MYBLUE = new DeviceCmyk(70, 30, 0, 18);
    private static final Color MYRED = new DeviceCmyk(0, 83, 84, 13);

    private static String weightCategoryText;
    private static String fightNumberText;
    private static String tournamentText;
    private static String cityText;
    private static String placeText;
    private static String dateText;
    private static String fighterName;
    private static String nameRedText;
    private static String nameBlueText;
    private static String selectedValue;
    private static String selectedValue2;

    public static void makeDiploma(int fightNumb) throws IOException, SQLException {
        fightNumb += 1;//Костыль для отображения правильного номера раунда

        for (int docNumb = 0; docNumb < 2; docNumb++) {

            switch (docNumb) {
                case 0: fighterName = cornerRed(fightNumb); break;
                case 1: fighterName = cornerBlue(fightNumb); break;
                default: fighterName = "Error";
            }

            String srcToDiploma = System.getProperty("user.home") + "/resources/pdf/diploma.pdf";
            String destToDiploma = System.getProperty("user.home") + "/result/diploma/Fight" + fightNumb + ", Diploma: " + fighterName + ".pdf";

            final String FONT = System.getProperty("user.home") + "/resources/fonts/CandaraRegular.ttf";
            PdfFont condaraRegularFont = PdfFontFactory.createFont(FONT, "Cp1251", true);
            PdfFont font = PdfFontFactory.createFont(FONT_NAME_DIPLOMA, "cp1251", true);
            final Color MYGRAY = new DeviceCmyk(61, 56, 64, 39);


            File createDiploma = new File(destToDiploma);
            createDiploma.getParentFile().mkdirs();

            PdfReader reader = new PdfReader(srcToDiploma);
            PdfWriter writer = new PdfWriter(destToDiploma);
            PdfDocument pdfDoc = new PdfDocument(reader, writer);

            PdfPage page = pdfDoc.getPage(1);
            PdfCanvas pdfCanvas = new PdfCanvas(page);

            Rectangle nameFighterRct = new Rectangle(260, 500, 300, 40);
            Rectangle tournamentRct = new Rectangle(205, 365, 400, 40);
            Rectangle cityRct = new Rectangle(205, 335, 400, 40);
            Rectangle weightCategoryRct = new Rectangle(205, 307, 400, 40);

            Canvas nameRedCnvs = new Canvas(pdfCanvas, pdfDoc, nameFighterRct);
            Canvas tournamentCnvs = new Canvas(pdfCanvas, pdfDoc, tournamentRct);
            Canvas cityCnvs = new Canvas(pdfCanvas, pdfDoc, cityRct);
            Canvas weightCategoryCnvs = new Canvas(pdfCanvas, pdfDoc, weightCategoryRct);

            //pdfCanvas.rectangle(weightCategoryRct);
            //pdfCanvas.stroke();




            Paragraph nameFigter = new Paragraph(fighterName)
                    .setFont(font)
                    .setFontColor(MYGRAY)
                    .setFontSize(27)
                    .setTextAlignment(TextAlignment.CENTER);

            Paragraph tournament = new Paragraph(tournamentText = eventName(fightNumb))
                    .setFont(font)
                    .setFontColor(MYGRAY)
                    .setFontSize(27)
                    .setTextAlignment(TextAlignment.CENTER);

            Paragraph cityStr =
                    new Paragraph().add(dateText = eventDate(fightNumb))
                            .add(", ").add(cityText = eventCity(tournamentText)).add(", ")
                            .add(placeText = eventPlace(fightNumb))
                    .setFont(condaraRegularFont)
                    .setFontColor(MYGRAY)
                    .setFontSize(19)
                    .setTextAlignment(TextAlignment.CENTER);

            Text weightCategoryBeginText = new Text("Fight in up to ")
                    .setFont(condaraRegularFont)
                    .setFontColor(MYGRAY)
                    .setFontSize(19);


            Text weightCategory = new Text(weightCategoryText = weight(fighterName))
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
        }

    }

    private static String eventName(int fightNumb) throws SQLException {

        Connection connection = W5MySQLConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT eventname FROM Fights WHERE fightnumber = "+"'"+(fightNumb)+"'");
        String eventname = null;
        while(rs.next()) {
            eventname = rs.getString("eventname");
        }
        return eventname;
    }

    private static String eventDate(int fightNumb) throws SQLException {

        Connection connection = W5MySQLConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT date FROM Fights WHERE fightnumber = "+"'"+(fightNumb)+"'");
        String eventDate = null;
        while(rs.next()) {
            eventDate = rs.getString("date");
        }
        return eventDate;
    }

    private static String eventCity(String eventName) throws SQLException {

        Connection connection = W5MySQLConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT city FROM Tournaments WHERE name = "+"'"+eventName+"'");
        String eventCity = null;
        while(rs.next()) {
            eventCity = rs.getString("city");
        }
        return eventCity;
    }

    private static String eventPlace(int fightNumb) throws SQLException {

        Connection connection = W5MySQLConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT place FROM Fights WHERE fightnumber = "+"'"+(fightNumb)+"'");
        String eventPlace = null;
        while(rs.next()) {
            eventPlace = rs.getString("place");
        }
        return eventPlace;
    }

    private static String cornerRed(int fightNumb) throws SQLException {

        Connection connection = W5MySQLConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT cornerred FROM Fights WHERE fightnumber = "+"'"+(fightNumb)+"'");
        String cornerRed = null;
        while(rs.next()) {
            cornerRed = rs.getString("cornerred");
        }
        return cornerRed;
    }

    private static String cornerBlue(int fightNumb) throws SQLException {

        Connection connection = W5MySQLConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT cornerblue FROM Fights WHERE fightnumber = "+"'"+(fightNumb)+"'");
        String cornerBlue = null;
        while(rs.next()) {
            cornerBlue = rs.getString("cornerblue");
        }
        return cornerBlue;
    }

    private static String weight(String name) throws SQLException {
        String[] arr = name.split(" ");

        Connection connection = W5MySQLConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT weight FROM Fighters WHERE firstname = '"+arr[0]+"' AND lastname = '"+arr[1]+"'");
        String weight = null;
        while(rs.next()) {
            weight = rs.getString("weight");
        }
        return weight;
    }

}
