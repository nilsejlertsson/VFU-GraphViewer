/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx.database;

import java.sql.Connection;
import java.util.Optional;
import javafx.collections.ObservableList;
import testfx.model.Sensor;

/**
 *
 * @author krille0x7c2
 */
public interface DatabaseHandler {
    public ObservableList<Sensor> getValues();
    public Optional<Connection> setUpConnection();
    public boolean canConnect();
}