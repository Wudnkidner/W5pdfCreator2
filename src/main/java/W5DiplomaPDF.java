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
public class W5DiplomaPDF {

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

    public static void makeDiploma() throws IOException {

        for (int i = 0; i < 2; i++) {
            String srcToDiploma = System.getProperty("user.home") + "/resources/pdf/diploma.pdf";
            String destToDiploma = System.getProperty("user.home") + "/result/diploma/Fight" + i + ", Diploma: " + "TEST___" + ".pdf";

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

            Paragraph nameFigter = new Paragraph(Integer.toString(i))
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
            System.out.println("Программа завершилась без ошибок!");
        }

    }

}
