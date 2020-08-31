import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Income {

    public static float curr(float f,String code) {
        if(code.equals("INR")){
            return f/66;
        }
        if(code.equals("HKD")){
            return f/8;
        }
        if(code.equals("SGP")){
            return f/(float)1.5;
        }
        if(code.equals("GBP")){
            return f/(float)0.67;
        }
        return f;
    }


    //sorting the final data.......................
    public static Comparator< String [] > fieldcom = new Comparator< String [] >() {

        public int compare(String [] s1, String [] s2) {
            String f1 = s1[0];
            String f2 = s2[0];
            //ascending order
            if(f1.equals(f2)) {
                if(s1[1].equals(s2[1])) {

                    return s1[2].compareTo(s2[2]);
                }
                return s1[1].compareTo(s2[1]);
            }
            return f1.compareTo(f2);
        }};
    //main method starts here...............
    public static void main(String args[]){
        try {

            String file="C:\\Users\\HP\\Desktop\\Avenue Dataset\\restaurantmenu\\income.csv;
            String outfile="C:\\Users\\HP\\Desktop\\Avenue Dataset\\restaurantmenu\\output.csv";
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

                lr.add(ls);
            }
            lr.remove(0);



            Map<String,Float> avgincome= new HashMap<String,Float>();
            Map<String,Integer> count= new HashMap<String,Integer>();
            for(ArrayList<String> ls: lr) {
                String keygen="";
                if(ls.get(1).equals("")) {
                    keygen=ls.get(0)+","+ls.get(2);
                }
                else {
                    keygen=ls.get(1)+","+ls.get(2);
                }
                if(avgincome.containsKey(keygen)) {
                    float indinc=avgincome.get(keygen);
                    indinc+=curr(Float.valueOf(ls.get(4)),ls.get(3));
                    avgincome.put(keygen, indinc);
                    if(count.containsKey(keygen)) {
                        int temp=count.get(keygen);
                        temp+=1;
                        count.put(keygen, temp);
                    }

                }
                else {
                    avgincome.put(keygen, curr(Float.valueOf(ls.get(4)),ls.get(3)));
                    count.put(keygen, 1);
                }
            }

            for(String i: avgincome.keySet()) {
                avgincome.put(i, avgincome.get(i)/count.get(i));
            }



            FileWriter outputfile = new FileWriter(outfile);

            CSVWriter writer = new CSVWriter(outputfile);
            ArrayList<String []> report = new ArrayList<String []>();

            for(String i: avgincome.keySet()) {
                String inc=i+","+String.valueOf(avgincome.get(i));
                String[] finaldata= inc.split(",");

                report.add(finaldata);
            }
            Collections.sort(report,fieldcom);

            for(String[] ls: report) {

                for(String s:ls) {
                    System.out.print(s+"\t\t");
                }
                System.out.println("\n");
            }
            System.out.println("\n");
            writer.writeAll(report);
            writer.close();



        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}