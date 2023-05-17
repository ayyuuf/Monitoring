package backend.sitemanagment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        Date date = new Date();
        date.setHours(date.getHours() + 24);
        System.out.println(date);
        SimpleDateFormat simpDate;
        simpDate = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        System.out.println(simpDate.format(date));
    }
}
