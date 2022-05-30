package dev.kirillbalanov.check_sample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Const {
    public static final Locale ruLocale = new Locale("ru", "RU");

    public static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", ruLocale);
    public static final DateFormat timeFormat = new SimpleDateFormat("HH:mm", ruLocale);
}
