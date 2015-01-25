/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx.fileIO;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import testfx.model.Sensor;

/**
 *
 * @author krille0x7c2
 */
public class FileIO {

    private static final Logger log = Logger.getLogger(FileIO.class.getName());

    /**
     * Static ObservableList to keep singles and keep the graph up to date
     *
     * @param path
     * @param name
     * @return ObservableList<Sensor>
     */
    public static ObservableList<Sensor> addSensorsFromFile(String path) {
        log.setLevel(Level.FINE);
        ObservableList<Sensor> sensorList = FXCollections.observableArrayList();
        try {
            String content = "";
            /*TODO dangerous loop, the path to file is comming from another thread (FileWatch) using event.context()
             This loop is only here for one reson, wait for the OS to finish copying a big file(tested 500MB).
             Could be excluded if "feature" not needed
             */
            while (!Files.isReadable(Paths.get(path)));
            if (Files.isWritable(Paths.get(path))) {
                content = new String(Files.readAllBytes(Paths.get(path)));
            } else {
                log.log(Level.WARNING, "File not found");
            }
             SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd:HH:mm:ss,SSS"); 
            StringTokenizer st = new StringTokenizer(content);
            if (st.hasMoreTokens()) {
                while (st.hasMoreTokens()) {
                    String isDate = st.nextToken();
                    if (isDate.matches("^(19|20)\\d\\d[\\-\\/.](0[1-9]|1[012])[\\-\\/.](0[1-9]|[12][0-9]|3[01])$")) {//Check if string is date yyyy-mm-dd and Validate year 19|20 month!>12&&month!=0 and day!>31 && day!=0
                        log.log(Level.FINEST, "Date found {0}", isDate);
                        sensorList.add(new Sensor("addlater", //Filename
                                ft.parse(isDate+":"+st.nextToken()), //Time
                                Double.parseDouble(st.nextToken()),//Channel
                                Double.parseDouble(st.nextToken()),//Channel 
                                Double.parseDouble(st.nextToken()),//Channel 
                                Double.parseDouble(st.nextToken()),//Channel 
                                Double.parseDouble(st.nextToken()),//Channel 
                                Double.parseDouble(st.nextToken()),//Channel 
                                Double.parseDouble(st.nextToken()),//Channel
                                Double.parseDouble(st.nextToken())));//Channel
                    }
                }
            } else {
                log.log(Level.WARNING, "Empty file");
            }
        } catch (IOException | ParseException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sensorList;
    }
}
