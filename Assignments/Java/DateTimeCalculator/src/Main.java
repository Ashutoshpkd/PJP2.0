import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub
        System.out.println("Loading....");
        Date d1 = new Date("14 11 2020");
        Date d2 = new Date(1, 4, 2007);
        Calculations c = new Calculations();
        String x =c.dayofweek(d1);
        System.out.println(x);
        // System.out.println(x);
        int y = c.getWeekNumber(d1);
        System.out.println(y);
        DisplayDate.dis(d1);
        Language.tomorrow(d1);
        DisplayDate.dis(d1);

        //p1.print(d1);
    }


}