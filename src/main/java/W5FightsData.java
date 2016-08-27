import javafx.beans.property.SimpleStringProperty;

/**
 * Created by albert on 27.08.16.
 */
public class W5FightsData {



    private final SimpleStringProperty eventName;
    private final SimpleStringProperty place;
    private final SimpleStringProperty date;
    private final SimpleStringProperty fightNumb;
    private final SimpleStringProperty cornerRed;
    private final SimpleStringProperty countryRed;
    private final SimpleStringProperty cornerBlue;
    private final SimpleStringProperty countryBlue;
    private final SimpleStringProperty firstJudge;
    private final SimpleStringProperty secondJudge;
    private final SimpleStringProperty thridJudge;

    private W5FightsData (String eventName, String place, String date,
                          String fightNumb, String cornerRed, String countryRed,
                          String cornerBlue, String countryBlue, String firstJudge,
                          String secondJudge,String thridJudge) {
        this.eventName = new SimpleStringProperty(eventName);
        this.place = new SimpleStringProperty(place);
        this.date = new SimpleStringProperty(date);
        this.fightNumb = new SimpleStringProperty(fightNumb);
        this.cornerRed = new SimpleStringProperty(cornerRed);
        this.countryRed = new SimpleStringProperty(countryRed);
        this.cornerBlue = new SimpleStringProperty(cornerBlue);
        this.countryBlue = new SimpleStringProperty(countryBlue);
        this.firstJudge = new SimpleStringProperty(firstJudge);
        this.secondJudge = new SimpleStringProperty(secondJudge);
        this.thridJudge = new SimpleStringProperty(thridJudge);

    }

}
