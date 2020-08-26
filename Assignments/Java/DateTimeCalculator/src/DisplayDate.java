import java.util.ArrayList;

interface printable{
    public  void print(Date dt1);
    //public void printA(ArrayList<Date> al);
}
interface printArray{
    public void print(ArrayList<Date> al);
}
public class DisplayDate {

    public static void dis(Date dt1)
    {
        p1.print(dt1);
    }
    public static void displayArray(ArrayList<Date> al)
    {
        p2.print(al);
    }
    public static printable p1= (dt1)->{
        int d = dt1.getD();
        int m = dt1.getM();
        int y = dt1.getY();
        System.out.println(d + ":" + m + ":" + y);
    };
    public static printArray p2 = (al)->{
        for(int i=0;i<al.size();i++)
        {
            DisplayDate.dis(al.get(i));
        }
    };


}