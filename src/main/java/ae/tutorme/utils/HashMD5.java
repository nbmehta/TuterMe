package ae.tutorme.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by almehairbi on 3/18/17.
 */
public class HashMD5 {

    public static String getHashPath(String fileName) {
        String link = "";
        try {

            Date date = new Date(System.currentTimeMillis());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            String monthName = getMonthForInt(month);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);

            String str = monthName +" "+ day+" "+year+" "+hour+":"+min+":52.454 UTC";

            System.out.println("year " +year +", month "+ monthName +",day " +day + ", hour " +hour +" min "+ min);


            SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
            Date newDate = df.parse(str);
            long epoch = newDate.getTime();


            System.out.println(epoch);
            String input =  epoch +"/hls/"+fileName+" enigma";

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] enc = md.digest();
            String md5Sum = new sun.misc.BASE64Encoder().encode(enc);
            System.out.println(md5Sum);
            String hash = md5Sum.substring( 0,md5Sum.length() -2);
            System.out.println(hash);
            hash = hash.replace("/", "_");
            hash = hash.replace("++", "--");



            link = "http://tutorme.ae/hls/"+fileName+"?md5="+hash+"&expires="+epoch;

            System.out.println(link);


        } catch
                (NoSuchAlgorithmException nsae) {
            System.out.println(nsae.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return link;
    }

    public static  String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }
}
