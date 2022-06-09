package dev.jorik.checksaver.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Utils {
    public final static Locale ruLocale = new Locale("ru", "RU");

    public final static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", ruLocale);
    public final static DateFormat timeFormat = new SimpleDateFormat("HH:mm", ruLocale);
}