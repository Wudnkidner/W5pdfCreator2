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
import java.util.ArrayList;

/**
 * Created by albert on 02.09.16.
 */
public class W5JudgesListPDF {

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
    private static String nameRedText;
    private static String nameBlueText;
    private static String judge1Text;
    private static String judge2Text;
    private static String judge3Text;

    private static ArrayList<String> eventNameList = new ArrayList<String>();
    private static ArrayList<String> placeList = new ArrayList<String>();
    private static ArrayList<String> dateList = new ArrayList<String>();
    private static ArrayList<String> fightNumbList = new ArrayList<String>();
    private static ArrayList<String> cornerRedList = new ArrayList<String>();
    private static ArrayList<String> countryRedList = new ArrayList<String>();
    private static ArrayList<String> cornerBlueList = new ArrayList<String>();
    private static ArrayList<String> countryBlueList = new ArrayList<String>();
    private static ArrayList<String> firstJudgeList = new ArrayList<String>();
    private static ArrayList<String> secondJudgeList = new ArrayList<String>();
    private static ArrayList<String> thridJudgeList = new ArrayList<String>();
    private static ArrayList<String> refereeList = new ArrayList<String>();


    public static void makeJudgeList(int fightNumb) throws IOException, SQLException {

        fightNumb += 1;//Костыль для нужного номера раунда

        String srcToJudgePdf = System.getProperty("user.home") + "/resources/pdf/judge_list.pdf";
        String destToJudgePdf = System.getProperty("user.home") + "/result/Fight" + fightNumb + ", judge_list.pdf";

        PdfFont font = PdfFontFactory.createFont(FONT, "Cp1251", true);

        final String FONT_FIGTERS = System.getProperty("user.home") + "/resources/fonts/OpenSans-ExtraBold.ttf";
        PdfFont fightersFont = PdfFontFactory.createFont(FONT_FIGTERS, "cp1251", true);


        File judge_list = new File(destToJudgePdf);
        judge_list.getParentFile().mkdirs();

        PdfReader reader = new PdfReader(srcToJudgePdf);

        PdfWriter writer = new PdfWriter(destToJudgePdf);


        PdfDocument pdfDoc = new PdfDocument(reader, writer);

        PdfPage page = pdfDoc.getPage(1);
        PdfCanvas pdfCanvas = new PdfCanvas(page);


        Rectangle dateRct = new Rectangle(55, 477, 450, 40);
        Rectangle tournamentRct = new Rectangle(55, 450, 460, 40);
        Rectangle weightCategoryRct = new Rectangle(609, 476, 78, 40);
        Rectangle fightNumberRct = new Rectangle(750, 476, 60, 40);
        Rectangle judge1 = new Rectangle(55, 352, 282, 40);
        Rectangle judge2 = new Rectangle(55, 328, 282, 40);
        Rectangle judge3 = new Rectangle(55, 304, 282, 40);
        Rectangle fighterRedRct = new Rectangle(342, 419, 210, 40);
        Rectangle fighterBlueRct = new Rectangle(596, 419, 210, 40);

        //pdfCanvas.rectangle(dateRct);
        //pdfCanvas.stroke();

        Canvas dateCnvs = new Canvas(pdfCanvas, pdfDoc, dateRct);
        Canvas tournamentCnvs = new Canvas(pdfCanvas, pdfDoc, tournamentRct);
        Canvas weightCategoryCnvs = new Canvas(pdfCanvas, pdfDoc, weightCategoryRct);
        Canvas fightNumberCnvs = new Canvas(pdfCanvas, pdfDoc, fightNumberRct);
        Canvas judge1Cnvs = new Canvas(pdfCanvas, pdfDoc, judge1);
        Canvas judge2Cnvs = new Canvas(pdfCanvas, pdfDoc, judge2);
        Canvas judge3Cnvs = new Canvas(pdfCanvas, pdfDoc, judge3);
        Canvas fighterRedCnvs = new Canvas(pdfCanvas, pdfDoc, fighterRedRct);
        Canvas fighterBlueCnvs = new Canvas(pdfCanvas, pdfDoc, fighterBlueRct);


        Paragraph judge1Prg = new Paragraph(judge1Text = firstJudge(fightNumb))
                .setFont(font)
                .setFontSize(20)
                .setTextAlignment(TextAlignment.LEFT);


        Paragraph judge2Prg = new Paragraph(judge2Text = secondJudge(fightNumb))
                .setFont(font)
                .setFontSize(20)
                .setTextAlignment(TextAlignment.LEFT);


        Paragraph judge3Prg = new Paragraph(judge3Text = thridJudge(fightNumb))
                .setFont(font)
                .setFontSize(20)
                .setTextAlignment(TextAlignment.LEFT);

        Paragraph fighterRedPrg = new Paragraph(nameRedText = cornerRed(fightNumb))
                .setFont(fightersFont)
                .setFontSize(20)
                .setTextAlignment(TextAlignment.CENTER);

        Paragraph fighterBluePrg = new Paragraph(nameBlueText = cornerBlue(fightNumb))
                .setFont(fightersFont)
                .setFontSize(20)
                .setTextAlignment(TextAlignment.CENTER);

        Paragraph weightCategoryPrg = new Paragraph(weightCategoryText = weight(nameRedText))
                .setFont(font)
                .setFontSize(27)
                .setTextAlignment(TextAlignment.CENTER);

        Paragraph fightNumberPrg = new Paragraph(fightNumberText = Integer.toString(fightNumb))
                .setFont(font)
                .setFontSize(27)
                .setTextAlignment(TextAlignment.CENTER);

        Text cityTxt = new Text(cityText = eventPlace(fightNumb) + ", ")
                .setFont(font)
                .setFontSize(23);

        Text dateTxt = new Text(dateText = eventDate(fightNumb))
                .setFont(font)
                .setFontSize(23);


        Paragraph datePrg = new Paragraph().add(cityTxt).add(dateTxt)
                .setFont(font)
                .setFontSize(27);


        Text tournamentText1 = new Text("W5 ")
                .setFont(font)
                .setFontSize(27)
                .setFontColor(MYRED);

        Text tournamentText2 = new Text(tournamentText = eventName(fightNumb))
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

    private static String firstJudge(int fightNumb) throws SQLException {

        Connection connection = W5MySQLConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT firstjudge FROM Fights WHERE fightnumber = "+"'"+(fightNumb)+"'");
        String judgeName = null;
        while(rs.next()) {
            judgeName = rs.getString("firstjudge");
        }
        return judgeName;
    }

    private static String secondJudge(int fightNumb) throws SQLException {

        Connection connection = W5MySQLConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT secondjudge FROM Fights WHERE fightnumber = "+"'"+(fightNumb)+"'");
        String judgeName = null;
        while(rs.next()) {
            judgeName = rs.getString("secondjudge");
        }
        return judgeName;
    }

    private static String thridJudge(int fightNumb) throws SQLException {

        Connection connection = W5MySQLConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT thridjudge FROM Fights WHERE fightnumber = "+"'"+(fightNumb)+"'");
        String judgeName = null;
        while(rs.next()) {
            judgeName = rs.getString("thridjudge");
        }
        return judgeName;
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
