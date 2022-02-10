package starter.helper;

import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public static void writeToCSV(String allstorename) throws IOException {
        csv ="allfilmnames.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv));
        writer.writeNext(new String[]{allstorename});
        writer.close();
    }
    //Reader to CSV
    public static String readToCSV(String letter) throws IOException, CsvException {
        BufferedReader reader = new BufferedReader(new FileReader(csv));
        List<String> lines = new ArrayList<>();
        String line =null;
        while ((line = reader.readLine()) != null) {
            //no case sensivty
            lines.add(line.toLowerCase());
        }
        List<String> result = new ArrayList<>();
        int order = 0 ;
        for (String s : lines) {
            if (s.startsWith(letter)) {
                order++;
                if(order == 1){
                    result.add(s);
                }
            }
        }
        return result.toString();
    }
}

