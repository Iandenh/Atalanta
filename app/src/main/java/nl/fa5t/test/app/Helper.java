package nl.fa5t.test.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ian on 15-9-16.
 */
public class Helper {
    public static String formateDateFromstring(String inputFormat, String outputFormat, String inputDate) {

        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, java.util.Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (ParseException e) {
            //LOGE(TAG, "ParseException - dateFormat");
        }

        return outputDate;

    }
    public static String getDayFromDate(String inputDate) throws ParseException {

        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = inFormat.parse(inputDate);
        SimpleDateFormat outFormat = new SimpleDateFormat("EEEE d MMMM");
        String day = outFormat.format(date);
        return day;
    }
}
