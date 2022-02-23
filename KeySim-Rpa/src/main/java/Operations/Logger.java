/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 *
 * @author u55369
 */
public class Logger {

    static String logFileName;

    public static void initialize() {

        logFileName = "logs/" + Calendar.getInstance().get(Calendar.DATE)
                + "-" + Calendar.getInstance().get(Calendar.MONTH)
                + "_" + Calendar.getInstance().get(Calendar.HOUR)
                + "-" + Calendar.getInstance().get(Calendar.MINUTE)
                + "_" + "Operationl Log.csv";

        String[] head = {"count", "operation", "data", "log"};
        log(head);
    }

    public static void log(String[] logs) {

        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(new File(logFileName), true));
            csvWriter.writeNext(logs);
            csvWriter.close();
        } catch (IOException ex) {
            System.err.println("Error in writing log");
        }

    }

}
