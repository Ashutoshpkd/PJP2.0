import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class Storage {

    public static void write(Date dt1) throws IOException, ClassNotFoundException {
        ArrayList<Date> array = new ArrayList<Date>();
        array = Storage.read();
        array.add(dt1);
        if(array.size()>100)
        {
            array.remove(0);
            System.out.println("Trimming session storage to 100");
        }
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\HP\\Desktop\\DevOps\\data1.ser");

        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeInt(array.size());
            for(Date train: array) {
                objectOutputStream.writeObject(train);
            }
        }
    }
    public static ArrayList<Date> read() throws IOException, ClassNotFoundException {
        ArrayList<Date> array = new ArrayList<Date>();
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\HP\\Desktop\\DevOps\\data1.ser");

        try(ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            int trainCount = objectInputStream.readInt();
            for (int i = 0; i < trainCount; i++) {
                array.add((Date)objectInputStream.readObject());

            }
            System.out.println("Session storage Memory");
            // DisplayDate.displayArray(array);
            return array;
        }
    }
}