package Config;

import java.text.SimpleDateFormat;

public class Constants {

    public static final int BATCH_SIZE = 10;
    public transient static final SimpleDateFormat ITEM_DATE_FORMAT = new SimpleDateFormat("mm/dd/yyyy");
    public transient static final SimpleDateFormat TOKEN_DATE_FORMAT = new SimpleDateFormat("YYYY-MM-DD HH-MM-SS");
    public static final String IMAGE_URL_EXTRA = "IMAGE_URL";
    public static final String GOOGLE_IMAGES_URL = "https://images.google.com/";
    public static final String GOOGLE_URL = "https://www.google.com/";
    public static final String IMAGE_URL_IDENTIFIER = "imgurl=";
    public static final String BOOK_CATEGORY = "BOOK";
    public static final String BOARD_GAME_CATEGORY = "BOARD_GAME";
    public static final String MOVIE_CATEGORY = "MOVIE";
    public static final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static String BOOK_URI = "@drawable/book";
    public static String MOVIE_URI = "@drawable/movie";
    public static String BOARD_GAME_URI = "@drawable/board_game";
    public static String USER_URI = "@drawable/person";
}
