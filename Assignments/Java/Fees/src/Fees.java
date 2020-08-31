import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReader;

public class Fees {

    //checking for intraday transactions..............
    public static ArrayList<ArrayList<String>> checkintra(ArrayList<ArrayList<String>> lr){
        for(int i =0;i<lr.size()-1;i++) {
            ArrayList<String> ll= lr.get(i);
            for(int j=i+1;j<lr.size();j++) {
                ArrayList<String> l2= lr.get(j);
                if(ll.get(0).equals(l2.get(0)) && ll.get(1).equals(l2.get(1)) && ll.get(3).equals(l2.get(3))) {
                    if((ll.get(2).equals("SELL") && l2.get(2).equals("BUY") ) || ((ll.get(2).equals("BUY") && l2.get(2).equals("SELL") ))){

                        int temp= Integer.valueOf(ll.get(6));
                        int temp1= Integer.valueOf(l2.get(6));
                        temp+=10;
                        temp1+=10;
                        ll.set(6,String.valueOf(temp));
                        l2.set(6,String.valueOf(temp1));
                    }
                }
            }
        }
        return lr;
    }

    //sorting the final data.......................
    public static Comparator<ArrayList<String>> fieldcom = new Comparator<ArrayList<String>>() {

        public int compare(ArrayList<String> s1, ArrayList<String> s2) {
            String f1 = s1.get(0);
            String f2 = s2.get(0);
            //ascending order
            if(f1.equals(f2)) {
                if(s1.get(1).equals(s2.get(1))) {
                    try {
                        Date date1=(Date) new SimpleDateFormat("dd/MM/yyyy").parse(s1.get(2));
                        Date date2=(Date) new SimpleDateFormat("dd/MM/yyyy").parse(s2.get(2));
                        if(date1.equals(date2)) {
                            return s1.get(3).compareTo(s2.get(3));
                        }
                        return date1.compareTo(date2);
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                }
                return s1.get(1).compareTo(s2.get(1));
            }
            return f1.compareTo(f2);
        }};
    //main methos starts here...............
    public static void main(String args[]){
        try {

            String file="C:\\Users\\HP\\Desktop\\Avenue Dataset\\restaurantmenu\\fee.csv";
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] data;
            //reading the input csv file and storing the data in to Arraylist of lists...............
            ArrayList<ArrayList<String>> lr = new ArrayList<ArrayList<String>>();
            while ((data = csvReader.readNext()) != null) {
                ArrayList<String> ls= new ArrayList<String>();
                for (String cell : data) {
                    ls.add(cell);
                }
                ls.add("0");
                ls.remove(0);
                lr.add(ls);
            }
            lr.remove(0);

            //calculating the nominal fees.............
            for(ArrayList<String> ls: lr) {
                String tt=ls.get(5);
                if(tt.equals("Y")) {
                    int temp= Integer.valueOf(ls.get(6));
                    temp+=500;
                    ls.set(6,String.valueOf(temp));

                }
                else if(tt.equals("N")) {
                    if(ls.get(2)=="SELL" ||ls.get(2)=="WITHDRAW"  ) {
                        int temp= Integer.valueOf(ls.get(6));
                        temp+=100;
                        ls.set(6,String.valueOf(temp));
                    }
                    else {
                        int temp= Integer.valueOf(ls.get(6));
                        temp+=50;
                        ls.set(6,String.valueOf(temp));
                    }
                }
                else {}

            }
            //updating  the intraday transactions.................
            lr = checkintra(lr);

            //Merging the transactions according to the details (if there are any transactions with same clientId, transaction date and type and priority).....
            Map<String,Integer> mp=new HashMap<String,Integer>();
            for(ArrayList<String> ls: lr) {
                String ss= ls.get(0)+","+ls.get(2)+","+ls.get(3)+","+ls.get(5);
                int fee=Integer.valueOf(ls.get(6));
                if(! mp.containsKey(ss)) {
                    mp.put(ss,fee);
                }
                else {
                    int fees=mp.get(ss);
                    fees+=fee;
                    mp.put(ss,fees);
                }
            }
            //Generating the final report...................
            ArrayList<ArrayList<String>> report = new ArrayList<ArrayList<String>>();

            for(String i : mp.keySet()) {
                String[] finaldata= i.split(",");
                ArrayList<String> tempdata=new ArrayList<String>();
                for(String j: finaldata) {
                    tempdata.add(j);
                }
                tempdata.add(String.valueOf(mp.get(i)));
                report.add(tempdata);
            }

            //call to sorting method ..................
            Collections.sort(report,fieldcom);

            System.out.println("ClientId\tTransactionType\tTransactionDate\t\tPriority\tFees");
            for(ArrayList<String> ls: report) {

                for(String s:ls) {
                    System.out.print(s+"\t\t");
                }
                System.out.println("\n");
            }
            System.out.println("\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}