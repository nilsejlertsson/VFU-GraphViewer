/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

import testfx.model.Sensor;
import testfx.fileIO.FileIO;
import java.io.File;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author krille0x7c2
 */
public class TestFX extends Application {

    private Stage stage;
    private ObservableList<Sensor> dataList = FXCollections.observableArrayList();
    private BorderPane rootLayout;
    private Sensor sensor;
    private List<Sensor> values;
    private Rectangle rect;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.stage.setTitle("GraphViewer");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = (Parent) loader.load();
        FXMLDocumentController c = loader.getController();
        c.setMain(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initFile(File file) {
        setSensorData(FileIO.addSensorsFromFile(file.getAbsolutePath()));
    }

    public void setSensorData(ObservableList<Sensor> dataList) {
        this.dataList = dataList;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return stage;
    }

    public ObservableList<Sensor> getSensorData() {
        return dataList;
    }

}
