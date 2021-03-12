package Config;

import java.text.SimpleDateFormat;

public class Constants {

    public static final int BATCH_SIZE = 10;
    public transient final SimpleDateFormat ITEM_DATE_FORMAT = new SimpleDateFormat("mm/dd/yyyy");
    public transient final SimpleDateFormat TOKEN_DATE_FORMAT = new SimpleDateFormat("YYYY-MM-DD HH-MM-SS");
}
