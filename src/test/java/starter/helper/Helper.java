package starter.helper;


import com.opencsv.CSVWriter;
import java.io.*;


public class Helper {
    public static String csv;

    //Wait method
    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //Writer to CSV
    public static void writeToCSV(String allfilmname) throws IOException {
        csv = "allfilmnames.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv));
        writer.writeNext(new String[]{allfilmname});
        writer.close();
    }

}

