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


    private static String weightCategoryText = "";
    private static String fightNumberText = "";
    private static String tournamentText = "";
    private static String cityText = "";
    private static String placeText = "";
    private static String dateText = "";
    private static String nameRedText = "";
    private static String nationalityRedText = "";
    private static String nameBlueText = "";
    private static String nationalityBlueText = "";
    private static String refereeText = "";
    private static String refereeNationText = "";
    private static String judgeText = "";
    private static String judgeNationText = "";
    private static String selectedValue = "";
    private static String selectedValue2 = "";

    public static void makeJudgeList() throws IOException {


        switch (docNumb) {
            case 0: judgeText = firstJudgeList.get(fightNumb); break;
            case 1: judgeText = secondJudgeList.get(fightNumb); break;
            case 2: judgeText = thridJudgeList.get(fightNumb); break;
            default: judgeText = "Ошибка";
        }

        String srcToJudgePdf = System.getProperty("user.home") + "/resources/pdf/judge_list.pdf";
        String destToJudgePdf = System.getProperty("user.home") + "/result/Fight" + "1" + ", judge_list.pdf";

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


        Rectangle dateRct = new Rectangle(55, 477, 200, 40);
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


        Paragraph weightCategoryPrg = new Paragraph(weightCategoryText)
                .setFont(font)
                .setFontSize(27)
                .setTextAlignment(TextAlignment.CENTER);

        Paragraph fightNumberPrg = new Paragraph(fightNumberText)
                .setFont(font)
                .setFontSize(27)
                .setTextAlignment(TextAlignment.CENTER);

        Paragraph judge1Prg = new Paragraph("Judge1")
                .setFont(font)
                .setFontSize(20)
                .setTextAlignment(TextAlignment.LEFT);


        Paragraph judge2Prg = new Paragraph("Judge2")
                .setFont(font)
                .setFontSize(20)
                .setTextAlignment(TextAlignment.LEFT);


        Paragraph judge3Prg = new Paragraph("Judge3")
                .setFont(font)
                .setFontSize(20)
                .setTextAlignment(TextAlignment.LEFT);

        Paragraph fighterRedPrg = new Paragraph("Fighter1")
                .setFont(fightersFont)
                .setFontSize(20)
                .setTextAlignment(TextAlignment.CENTER);

        Paragraph fighterBluePrg = new Paragraph("Fighter2")
                .setFont(fightersFont)
                .setFontSize(20)
                .setTextAlignment(TextAlignment.CENTER);


        Text cityTxt = new Text(cityText + ", ")
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


    }


}
