public class  Language {
    static Calculations dcalc = new Calculations();
    public static Date tomorrow(Date dt1)
    {
        return dcalc.addDate(dt1,1);
    }
    public static Date Dayaftertomorrow(Date dt1)
    {

        return dcalc.addDate(dt1,2);
    }
    public static Date yesterday(Date dt1)
    {
        return dcalc.subDate(dt1, 1);
    }
    public static Date  Daybeforeyesterday(Date dt1)
    {
        return dcalc.subDate(dt1, 2);
    }
    public static Date lastWeek(Date dt1)
    {
        return dcalc.subDate(dt1, 7);
    }
    public static Date nextWeek(Date dt1)
    {
        return dcalc.addDate(dt1, 7);
    }
    public static Date nextMonth(Date dt1)
    {
        return dcalc.addDate(dt1, 30);
    }
    public static Date nextYear(Date dt1)
    {
        return dcalc.addDate(dt1, 365);
    }
    public static Date lastMonth(Date dt1)
    {
        return dcalc.subDate(dt1, 30);
    }
    public static Date lastYear(Date dt1)
    {
        return dcalc.subDate(dt1, 365);
    }
    public static Date nweeksafter(Date dt1,int n)
    {
        return dcalc.addDate(dt1, n*7);
    }
    public static Date nDaysAfter(Date dt1,int n)
    {
        return dcalc.addDate(dt1, n);
    }
    public static Date nMonthsAfter(Date dt1,int n)
    {
        return dcalc.addDate(dt1, 30*n);
    }
    public static Date nYearsAfter(Date dt1,int n)
    {
        return dcalc.addDate(dt1, 365*n);
    }
    public static Date nweeksBefore(Date dt1,int n)
    {
        return dcalc.subDate(dt1, n*7);
    }
    public static Date nDaysBefore(Date dt1,int n)
    {
        return dcalc.subDate(dt1, n);
    }
    public static Date nMonthsBefore(Date dt1,int n)
    {
        return dcalc.subDate(dt1, 30*n);
    }
    public static Date nYearsBefore(Date dt1,int n)
    {
        return dcalc.subDate(dt1, 365*n);
    }

}