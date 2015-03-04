/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx.database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import testfx.model.Sensor;

/**
 *
 * @author krille0x7c2
 */
public class Database implements DatabaseHandler {

    private final Logger log = Logger.getLogger(this.getClass().getPackage().getName());
    private final String password;
    private final String userName;
    private final String databaseName;
    private final String host;
    private final String port;
    private final String tableName;
    private PreparedStatement preparedStatement;
    private Statement stmt;
    private final int maxBatch;
    private volatile int counter = 0;
    private double progress;
    private final ReentrantLock lock;
    private String fileName;
    private Optional<Connection> connection;

    public Database(String password, String userName,
            String host, String port, String databaseName,
            String tableName) {

        this.maxBatch = 1000;
        log.setLevel(Level.FINE);
        this.lock = new ReentrantLock();
        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;
        this.port = port;
        this.host = host;
        this.tableName = tableName;
    }

    @Override
    public boolean canConnect() {
        if ((connection = setUpConnection()).isPresent()) {
            try {
                connection.get().close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * Connect to database
     *
     * @return Optional
     */
    @Override
    public Optional<Connection> setUpConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties myProp = new Properties();
            myProp.put("user", userName);
            myProp.put("password", password);
            myProp.setProperty("rewriteBatchedStatements", "true");
            Connection c = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + databaseName, myProp);
            log.log(Level.INFO, "Connected database successfully");
            return Optional.of(c);

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        log.log(Level.SEVERE, "Failed to connect");
        return Optional.empty();
    }

    @Override
    public ObservableList<Sensor> getValues() {
        try {
            ObservableList<Sensor> sensorList = FXCollections.observableArrayList();
            String query = "select * from val";
            connection = setUpConnection();
            stmt = connection.get().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss,SSS");
            while (rs.next()) {
                    sensorList.add(new Sensor("addlater", //Filename
                            ft.parse(rs.getString("DateInTime")+":"+rs.getString("TimeOfMesure")), //Time
                            rs.getDouble("Sensor0"),//Channel
                            rs.getDouble("Sensor1"),//Channel
                            rs.getDouble("Sensor2"),//Channel
                            rs.getDouble("Sensor3"),//Channel
                            rs.getDouble("Sensor4"),//Channel
                            rs.getDouble("Sensor5"),//Channel
                            rs.getDouble("Sensor6"),//Channel
                            rs.getDouble("Sensor7")));//Channel
                
            }
            rs.close();
      stmt.close();
      connection.get().close();
            return sensorList;
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
