import java.io.Serializable;

@SuppressWarnings("serial")
public class Date implements Serializable {
    int d, m, y;

    public Date(int d, int m, int y)
    {
        this.d = d;
        this.m = m;
        this.y = y;
    }
    public Date(String s)
    {
        String [] arr = s.split(" ");
        System.out.println(arr.length);
        d = Integer.parseInt(arr[0]);
        m = Integer.parseInt(arr[1]);
        y = Integer.parseInt(arr[2]);
    }
    public Date()
    {
        d =0;
        m=0;
        y=0;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}