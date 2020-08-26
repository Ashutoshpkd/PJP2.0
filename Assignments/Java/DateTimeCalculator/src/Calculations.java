import java.util.HashMap;

public class Calculations {
    static int monthDays[] = {31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31};
    static HashMap<Integer,String> dayWeek = new HashMap<Integer,String>();
    public Calculations() {
        dayWeek.put(1,"Monday");
        dayWeek.put(2,"Tuesday");
        dayWeek.put(3,"Wednesday");
        dayWeek.put(4,"Thursday");
        dayWeek.put(5,"Friday");
        dayWeek.put(6,"Saturday");
        dayWeek.put(7,"Sunday");
    }
    static int countLeapYears(Date d)
    {
        int years = d.y;
        if (d.m <= 2)
        {
            years--;
        }
        return years / 4 - years / 100 + years / 400;
    }
    int getDifference(Date dt1, Date dt2)
    {
        int n1 = dt1.y * 365 + dt1.d;
        for (int i = 0; i < dt1.m - 1; i++)
        {
            n1 += monthDays[i];
        }
        n1 += countLeapYears(dt1);
        int n2 = dt2.y * 365 + dt2.d;
        for (int i = 0; i < dt2.m - 1; i++)
        {
            n2 += monthDays[i];
        }
        n2 += countLeapYears(dt2);
        return (n2 - n1);
    }

    Date addDate(Date dt1, int n)
    {
        int y = n/365;
        Date dt2 = dt1;
        dt2.y = dt2.y + y;
        n = n%365;
        int j = dt2.m;
        while(n>0)
        {
            n = n-monthDays[j-1];
            if(n>0)
            {
                dt2.m = dt2.m+1;
                j = j+1;
                if((j-1)>11)
                {
                    j=0;
                    dt2.setM(1);
                    dt2.y = dt2.y+1;
                }
            }
        }
        n = n+monthDays[j-1];
        dt2.d = dt2.d+n;
        if(dt2.d> monthDays[j-1])
        {
            dt2.d = dt2.d- monthDays[j-1];
            if(dt2.m != 12)
                dt2.m = dt2.m+1;
            else
            {
                dt2.m = 1;
                dt2.y = dt2.y+1;
            }
        }
        return dt1;

    }
    Date subDate(Date dt1, int n)
    {
        int y = n/365;
        Date dt2 = dt1;
        dt2.y = dt2.y - y;
        n = n%365;
        int j = dt2.m;
        while(n>0)
        {
            n = n-monthDays[j-1];
            if(n>0)
            {
                dt2.m = dt2.m-1;
                j = j-1;
                if(j<0) j=11;
            }
        }
        n = n+monthDays[j-1];
        dt2.d = dt2.d-n;
        if(dt2.d<=0)
        {
            dt2.d = dt2.d+ monthDays[j-2];
            if(dt2.m != 0)
            {
                dt2.m = dt2.m-1;
            }
            else
            {
                dt2.m = 12;
                dt2.y = dt2.y-1;
            }

        }
        return dt2;

    }
    int conversion(String t)
    {
        String [] arr = t .split(" ");
        if(arr.length == 2)
        {
            if(arr[1] == "year" ) return 365*Integer.parseInt(arr[0]);
            else if(arr[1] == "month" ) return 30*Integer.parseInt(arr[0]);
            else  return 1*Integer.parseInt(arr[0]);
        }
        if(arr.length == 6)
        {
            return 365*Integer.parseInt(arr[0]) + 30*Integer.parseInt(arr[2])+ Integer.parseInt(arr[4]);
        }
        return 0;

    }
    String dayofweek(Date dt1)
    {
        int y = dt1.getY();
        int m = dt1.getM();
        int d = dt1.getD();
        int t[] = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
        y -= (m < 3) ? 1 : 0;
        int x = ( y + y/4 - y/100 + y/400 + t[m-1] + d) % 7;
        return dayWeek.get(x);
    }

    int getWeekNumber(Date dt1)
    {
        int y = dt1.getY();
        int m = dt1.getM();
        int d1 = dt1.getD();
        int d =1;
        int t[] = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
        y -= (m < 3) ? 1 : 0;
        int x = ( y + y/4 - y/100 + y/400 + t[m-1] + d) % 7;
        int x1 = d1+x;
        return (x1/7 + 1);

    }

}