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


public class W5PDFCreator {

    private static String element = "";
    private static final String SRС_JUDGE_LIST = System.getProperty("user.home") + "/resources/pdf/judge_list.pdf";
    private static final String SRC_DIPLOMA = System.getProperty("user.home") + "/resources/pdf/diploma.pdf";

    private static String DEST = System.getProperty("user.home") + "/result/Clear1.pdf";
    private static String DEST_JUDGE_LIST = System.getProperty("user.home") + "/judge_list1.pdf";


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
    private static String nationalityRedText;
    private static String nameBlueText;
    private static String nationalityBlueText;
    private static String refereeText;
    private static String refereeNationText;
    private static String judgeText;
    private static String judgeNationText;
    private static String selectedValue;
    private static String selectedValue2;

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

		/*
		 * Set the padding of the GridPane
		 * Set the border-style of the GridPane
		 * Set the border-width of the GridPane
		 * Set the border-insets of the GridPane
		 * Set the border-radius of the GridPane
		 * Set the border-color of the GridPane
		*/

    /*Не работает
    public static void makeWeightIn() throws IOException {

        PdfFont font = PdfFontFactory.createFont(FONT, "Cp1251", true);
        PdfFont candaraRegularFont = PdfFontFactory.createFont(FONT, "Cp1251", true);

        String[] arr = {};
        String fighterText;
        String fighterCountryText;

        ArrayList<String> arrayListFighterText = new ArrayList<String>();
        arrayListFighterText.add("TEST1");
        ArrayList<String> arrayListFighterCountryText = new ArrayList<String>();
        arrayListFighterCountryText.add("TEST2");






        String srcToMedicalPdf = System.getProperty("user.home") + "/resources/pdf/weight.pdf";
        String destToMedicalPdf = System.getProperty("user.home") + "/result/weight.pdf";


        File weightIn = new File(destToMedicalPdf);
        weightIn.getParentFile().mkdirs();

        PdfReader reader = new PdfReader(srcToMedicalPdf);
        PdfWriter writer = new PdfWriter(destToMedicalPdf);
        PdfDocument pdfDoc = new PdfDocument(reader, writer);

        PdfPage page1 = pdfDoc.getPage(1);
        PdfPage page2 = pdfDoc.getPage(2);

        PdfCanvas pdfCanvas1 = new PdfCanvas(page1);
        PdfCanvas pdfCanvas2 = new PdfCanvas(page2);

        Rectangle dateRct = new Rectangle(55, 718, 260, 40);
        Rectangle tournamentRct = new Rectangle(55, 702, 260, 40);

        int x = 78, y = 650, weight = 98, height = 18;
        ArrayList<Rectangle> Page1NameRedRct = new ArrayList<Rectangle>();
        ArrayList<Rectangle> Page1CountryRedRct = new ArrayList<Rectangle>();
        ArrayList<Rectangle> Page1NameBlueRct = new ArrayList<Rectangle>();
        ArrayList<Rectangle> Page1CountryBlueRct = new ArrayList<Rectangle>();

        ArrayList<Rectangle> Page2NameRedRct = new ArrayList<Rectangle>();
        ArrayList<Rectangle> Page2CountryRedRct = new ArrayList<Rectangle>();
        ArrayList<Rectangle> Page2NameBlueRct = new ArrayList<Rectangle>();
        ArrayList<Rectangle> Page2CountryBlueRct = new ArrayList<Rectangle>();


        for (int index = 0; index < arrayListWeightInFighters.size() / 2; index++) {
            if (index < 12) {
                if (index != 0) y += 0.2;
                Page1NameRedRct.add(new Rectangle(x, y, weight, height));
                Page1CountryRedRct.add(new Rectangle(x + 100, y, weight - 42, height));
                Page1NameBlueRct.add(new Rectangle(x + 303, y, weight - 2, height));
                Page1CountryBlueRct.add(new Rectangle(x + 246, y, weight - 42, height));
                y -= 17;
            }

            if (index >= 12 && index < 24) {
                if (index != 12) y += 0.2;
                Page2NameRedRct.add(new Rectangle(x, y, weight, height));
                Page2CountryRedRct.add(new Rectangle(x + 100, y, weight - 42, height));
                Page2NameBlueRct.add(new Rectangle(x + 303, y, weight - 2, height));
                Page2CountryBlueRct.add(new Rectangle(x + 246, y, weight - 42, height));
                y -= 17;
            }

        }


        //Table2
        int x2 = 78, y2 = 290, weight2 = 98, height2 = 18;
        ArrayList<Rectangle> Page1NameRed2Rct = new ArrayList<Rectangle>();
        ArrayList<Rectangle> Page1CountryRed2Rct = new ArrayList<Rectangle>();
        ArrayList<Rectangle> Page1NameBlue2Rct = new ArrayList<Rectangle>();
        ArrayList<Rectangle> Page1CountryBlue2Rct = new ArrayList<Rectangle>();

        ArrayList<Rectangle> Page2NameRed2Rct = new ArrayList<Rectangle>();
        ArrayList<Rectangle> Page2CountryRed2Rct = new ArrayList<Rectangle>();
        ArrayList<Rectangle> Page2NameBlue2Rct = new ArrayList<Rectangle>();
        ArrayList<Rectangle> Page2CountryBlue2Rct = new ArrayList<Rectangle>();

        for (int index = 0; index < arrayListWeightInFighters.size() / 2; index++) {
            if (index < 12) {
                if (index != 0) y2 += 0.2;
                Page1NameRed2Rct.add(new Rectangle(x2, y2, weight2 + 5, height2));
                Page1CountryRed2Rct.add(new Rectangle(x2 + 105, y2, weight2 - 53, height2));
                Page1NameBlue2Rct.add(new Rectangle(x2 + 226, y2, weight2 + 8, height2));
                Page1CountryBlue2Rct.add(new Rectangle(x2 + 178, y2, weight2 - 53, height2));
                y2 -= 17;
            }

            if (index >= 12 && index < 24) {
                if (index != 12) y2 += 0.2;
                Page2NameRed2Rct.add(new Rectangle(x2, y2, weight2 + 5, height2));
                Page2CountryRed2Rct.add(new Rectangle(x2 + 105, y2, weight2 - 53, height2));
                Page2NameBlue2Rct.add(new Rectangle(x2 + 226, y2, weight2 + 8, height2));
                Page2CountryBlue2Rct.add(new Rectangle(x2 + 178, y2, weight2 - 53, height2));
                y2 -= 17;
            }

        }

        //pdfCanvas1.rectangle(Page1NameRed2Rct.get(0));
        //pdfCanvas1.stroke();

        Canvas dateCnvs = new Canvas(pdfCanvas1, pdfDoc, dateRct);
        Canvas tourCanvas = new Canvas(pdfCanvas1, pdfDoc, tournamentRct);

        Canvas Page2dateCnvs = new Canvas(pdfCanvas2, pdfDoc, dateRct);
        Canvas Page2tourCanvas = new Canvas(pdfCanvas2, pdfDoc, tournamentRct);

        ArrayList<Canvas> Page1NameRedCnvs = new ArrayList<Canvas>();
        ArrayList<Canvas> Page1CountryRedCnvs = new ArrayList<Canvas>();
        ArrayList<Canvas> Page1NameBlueCnvs = new ArrayList<Canvas>();
        ArrayList<Canvas> Page1CountryBlueCnvs = new ArrayList<Canvas>();

        ArrayList<Canvas> Page2NameRedCnvs = new ArrayList<Canvas>();
        ArrayList<Canvas> Page2CountryRedCnvs = new ArrayList<Canvas>();
        ArrayList<Canvas> Page2NameBlueCnvs = new ArrayList<Canvas>();
        ArrayList<Canvas> Page2CountryBlueCnvs = new ArrayList<Canvas>();

        for (int index = 0; index < arrayListWeightInFighters.size() / 2; index++) {
            if (index < 12) {
                Page1NameRedCnvs.add(new Canvas(pdfCanvas1, pdfDoc, Page1NameRedRct.get(index)));
                Page1CountryRedCnvs.add(new Canvas(pdfCanvas1, pdfDoc, Page1CountryRedRct.get(index)));
                Page1NameBlueCnvs.add(new Canvas(pdfCanvas1, pdfDoc, Page1NameBlueRct.get(index)));
                Page1CountryBlueCnvs.add(new Canvas(pdfCanvas1, pdfDoc, Page1CountryBlueRct.get(index)));
            }

            if (index >= 12 && index < 24) {
                Page2NameRedCnvs.add(new Canvas(pdfCanvas2, pdfDoc, Page2NameRedRct.get(index)));
                Page2CountryRedCnvs.add(new Canvas(pdfCanvas2, pdfDoc, Page2CountryRedRct.get(index)));
                Page2NameBlueCnvs.add(new Canvas(pdfCanvas2, pdfDoc, Page2NameBlueRct.get(index)));
                Page2CountryBlueCnvs.add(new Canvas(pdfCanvas2, pdfDoc, Page2CountryBlueRct.get(index)));
            }

        }

        //Table2
        ArrayList<Canvas> Page1NameRed2Cnvs = new ArrayList<Canvas>();
        ArrayList<Canvas> Page1CountryRed2Cnvs = new ArrayList<Canvas>();
        ArrayList<Canvas> Page1NameBlue2Cnvs = new ArrayList<Canvas>();
        ArrayList<Canvas> Page1CountryBlue2Cnvs = new ArrayList<Canvas>();

        ArrayList<Canvas> Page2NameRed2Cnvs = new ArrayList<Canvas>();
        ArrayList<Canvas> Page2CountryRed2Cnvs = new ArrayList<Canvas>();
        ArrayList<Canvas> Page2NameBlue2Cnvs = new ArrayList<Canvas>();
        ArrayList<Canvas> Page2CountryBlue2Cnvs = new ArrayList<Canvas>();

        for (int index = 0; index < arrayListWeightInFighters.size() / 2; index++) {
            if (index < 12) {
                Page1NameRed2Cnvs.add(new Canvas(pdfCanvas1, pdfDoc, Page1NameRed2Rct.get(index)));
                Page1CountryRed2Cnvs.add(new Canvas(pdfCanvas1, pdfDoc, Page1CountryRed2Rct.get(index)));
                Page1NameBlue2Cnvs.add(new Canvas(pdfCanvas1, pdfDoc, Page1NameBlue2Rct.get(index)));
                Page1CountryBlue2Cnvs.add(new Canvas(pdfCanvas1, pdfDoc, Page1CountryBlue2Rct.get(index)));
            }

            if (index >= 12 && index < 24) {
                Page2NameRed2Cnvs.add(new Canvas(pdfCanvas2, pdfDoc, Page2NameRed2Rct.get(index)));
                Page2CountryRed2Cnvs.add(new Canvas(pdfCanvas2, pdfDoc, Page2CountryRed2Rct.get(index)));
                Page2NameBlue2Cnvs.add(new Canvas(pdfCanvas2, pdfDoc, Page2NameBlue2Rct.get(index)));
                Page2CountryBlue2Cnvs.add(new Canvas(pdfCanvas2, pdfDoc, Page2CountryBlue2Rct.get(index)));
            }

        }


        ArrayList<Paragraph> Page1NameRedPrg = new ArrayList<Paragraph>();
        ArrayList<Paragraph> Page1CountryRedPrg = new ArrayList<Paragraph>();
        ArrayList<Paragraph> Page1NameBluePrg = new ArrayList<Paragraph>();
        ArrayList<Paragraph> Page1CountryBluePrg = new ArrayList<Paragraph>();

        ArrayList<Paragraph> Page2NameRedPrg = new ArrayList<Paragraph>();
        ArrayList<Paragraph> Page2CountryRedPrg = new ArrayList<Paragraph>();
        ArrayList<Paragraph> Page2NameBluePrg = new ArrayList<Paragraph>();
        ArrayList<Paragraph> Page2CountryBluePrg = new ArrayList<Paragraph>();

        for (int index = 0; index < arrayListWeightInFighters.size(); index++) {
            if (index < 24) {
                Page1NameRedPrg.add(new Paragraph(arrayListFighterText.get(index)).setFontSize(12).setFont(font));
                Page1CountryRedPrg.add(new Paragraph(arrayListFighterCountryText.get(index)).setTextAlignment(TextAlignment.CENTER).setFontSize(12).setFont(font));
                Page1NameBluePrg.add(new Paragraph(arrayListFighterText.get(index)).setFontSize(12).setFont(font));
                Page1CountryBluePrg.add(new Paragraph(arrayListFighterCountryText.get(index)).setTextAlignment(TextAlignment.CENTER).setFontSize(12).setFont(font));
            }

            if (index >= 24 && index < 48) {
                Page2NameRedPrg.add(new Paragraph(arrayListFighterText.get(index)).setFontSize(12).setFont(font));
                Page2CountryRedPrg.add(new Paragraph(arrayListFighterCountryText.get(index)).setTextAlignment(TextAlignment.CENTER).setFontSize(12).setFont(font));
                Page2NameBluePrg.add(new Paragraph(arrayListFighterText.get(index)).setFontSize(12).setFont(font));
                Page2CountryBluePrg.add(new Paragraph(arrayListFighterCountryText.get(index)).setTextAlignment(TextAlignment.CENTER).setFontSize(12).setFont(font));
            }

        }

        ArrayList<Paragraph> Page1NameRed2Prg = new ArrayList<Paragraph>();
        ArrayList<Paragraph> Page1CountryRed2Prg = new ArrayList<Paragraph>();
        ArrayList<Paragraph> Page1NameBlue2Prg = new ArrayList<Paragraph>();
        ArrayList<Paragraph> Page1CountryBlue2Prg = new ArrayList<Paragraph>();

        ArrayList<Paragraph> Page2NameRed2Prg = new ArrayList<Paragraph>();
        ArrayList<Paragraph> Page2CountryRed2Prg = new ArrayList<Paragraph>();
        ArrayList<Paragraph> Page2NameBlue2Prg = new ArrayList<Paragraph>();
        ArrayList<Paragraph> Page2CountryBlue2Prg = new ArrayList<Paragraph>();

        for (int index = 0; index < arrayListWeightInFighters.size(); index++) {
            if (index < 24) {
                Page1NameRed2Prg.add(new Paragraph(arrayListFighterText.get(index)).setFontSize(12).setFont(font));
                Page1CountryRed2Prg.add(new Paragraph(arrayListFighterCountryText.get(index)).setTextAlignment(TextAlignment.CENTER).setFontSize(10).setFont(font));
                Page1NameBlue2Prg.add(new Paragraph(arrayListFighterText.get(index)).setFontSize(12).setFont(font));
                Page1CountryBlue2Prg.add(new Paragraph(arrayListFighterCountryText.get(index)).setTextAlignment(TextAlignment.CENTER).setFontSize(10).setFont(font));
            }

            if (index >= 24 && index < 48) {
                Page2NameRed2Prg.add(new Paragraph(arrayListFighterText.get(index)).setFontSize(12).setFont(font));
                Page2CountryRed2Prg.add(new Paragraph(arrayListFighterCountryText.get(index)).setTextAlignment(TextAlignment.CENTER).setFontSize(10).setFont(font));
                Page2NameBlue2Prg.add(new Paragraph(arrayListFighterText.get(index)).setFontSize(12).setFont(font));
                Page2CountryBlue2Prg.add(new Paragraph(arrayListFighterCountryText.get(index)).setTextAlignment(TextAlignment.CENTER).setFontSize(10).setFont(font));
            }

        }


        Paragraph datePrg = new Paragraph().add(dateText).add(" (").add(placeText).add(",").add(cityText).add(")")
                .setFont(candaraRegularFont)
                .setFontSize(19);

        Text tournamentText1 = new Text("W5 ")
                .setFont(font)
                .setFontSize(20)
                .setFontColor(MYRED);

        Text tournamentText2 = new Text(tournamentText)
                .setFont(font)
                .setFontSize(20);

        Paragraph tournamentPrg = new Paragraph().add(tournamentText1).add(tournamentText2)
                .setFont(font)
                .setFontSize(20);


        dateCnvs.add(datePrg);
        tourCanvas.add(tournamentPrg);
        Page2dateCnvs.add(datePrg);
        Page2tourCanvas.add(tournamentPrg);


        for (int index = 0; index < arrayListWeightInFighters.size() / 2; index++) {

            int counterEven = countEven(index);
            int counterOdd = counterEven + 1;
            if (index < 12) {
                Page1NameRedCnvs.get(index).add(Page1NameRedPrg.get(counterEven));
                Page1CountryRedCnvs.get(index).add(Page1CountryRedPrg.get(counterEven));
                Page1NameBlueCnvs.get(index).add(Page1NameBluePrg.get(counterOdd));
                Page1CountryBlueCnvs.get(index).add(Page1CountryBluePrg.get(counterOdd));

                Page1NameRed2Cnvs.get(index).add(Page1NameRed2Prg.get(counterEven));
                Page1CountryRed2Cnvs.get(index).add(Page1CountryRed2Prg.get(counterEven));
                Page1NameBlue2Cnvs.get(index).add(Page1NameBlue2Prg.get(counterOdd));
                Page1CountryBlue2Cnvs.get(index).add(Page1CountryBlue2Prg.get(counterOdd));
            }

            if (index >= 12 && index < 24) {
                Page2NameRedCnvs.get(index).add(Page2NameRedPrg.get(counterEven));
                Page2CountryRedCnvs.get(index).add(Page2CountryRedPrg.get(counterEven));
                Page2NameBlueCnvs.get(index).add(Page2NameBluePrg.get(counterOdd));
                Page2CountryBlueCnvs.get(index).add(Page2CountryBluePrg.get(counterOdd));

                Page2NameRed2Cnvs.get(index).add(Page2NameRed2Prg.get(counterEven));
                Page2CountryRed2Cnvs.get(index).add(Page2CountryRed2Prg.get(counterEven));
                Page2NameBlue2Cnvs.get(index).add(Page2NameBlue2Prg.get(counterOdd));
                Page2CountryBlue2Cnvs.get(index).add(Page2CountryBlue2Prg.get(counterOdd));
            }

        }


        pdfDoc.close();

    }
    */

}

class TestDrivePDF {

    public static void main(String[] args) throws IOException, SQLException {
        for (int i = 0; i < 2; i++) {
            //W5FightCardPDF.makeFightCard(i);
            //W5JudgesListPDF.makeJudgeList(i);
            //W5DiplomaPDF.makeDiploma(i);
            W5MedicalPDF.makeMedical(i);
        }
    }

}