package by.step.thoughts;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Converters {

    private static SimpleDateFormat dateFormatter =
            new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

    @TypeConverter
    public static Date fromString(String value) {
        try {
            return dateFormatter.parse(value);
        } catch (Exception e) {
            return new Date();
        }
    }

    @TypeConverter
    public static String dateToString(Date date) {
        return date == null ? null : dateFormatter.format(date);
    }
}
