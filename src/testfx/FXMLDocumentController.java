/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import extfx.scene.chart.DateAxis;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author krille0x7c2
 */
public class FXMLDocumentController implements Initializable {

    private static final String CHART_SERIES_DEFAULT_COLOR_0_FX8 = "#000000";
    private static final String CHART_SERIES_DEFAULT_COLOR_1_FX8 = "#FF0000";
    private static final String CHART_SERIES_DEFAULT_COLOR_2_FX8 = "#0015FF";
    private static final String CHART_SERIES_DEFAULT_COLOR_3_FX8 = "#FFFF00";
    private static final String CHART_SERIES_DEFAULT_COLOR_4_FX8 = "#27C400";
    private static final String CHART_SERIES_DEFAULT_COLOR_5_FX8 = "#860061";
    private static final String CHART_SERIES_DEFAULT_COLOR_6_FX8 = "#c62b00";
    private static final String CHART_SERIES_DEFAULT_COLOR_7_FX8 = "#ff5700";
    private static final int RGB_MAX = 255;
    private final ObservableList<Color> seriesColors = FXCollections.observableArrayList();
    private final ObservableList<Integer> samples = FXCollections.observableArrayList();
    private final List<ColorPicker> colorPickers = new ArrayList<>();
    private List<XYChart.Series> seriesCollection = new ArrayList<>();
    //private Series series;
    private XYChart.Series series;
    private XYChart.Series series1;
    private XYChart.Series series2;
    private XYChart.Series series3;
    private XYChart.Series series4;
    private XYChart.Series series5;
    private XYChart.Series series6;
    private XYChart.Series series7;
    private XYChart.Series series8;

    private TestFX testFX;
    @FXML
    private CheckBox c8;
    @FXML
    private CheckBox c7;
    @FXML
    private CheckBox c6;
    @FXML
    private CheckBox c5;
    @FXML
    private CheckBox c4;
    @FXML
    private CheckBox c3;
    @FXML
    private CheckBox c2;
    @FXML
    private CheckBox c1;
    @FXML
    private ColorPicker colorPicker8;
    @FXML
    private ColorPicker colorPicker7;
    @FXML
    private ColorPicker colorPicker6;
    @FXML
    private ColorPicker colorPicker5;
    @FXML
    private ColorPicker colorPicker4;
    @FXML
    private ColorPicker colorPicker3;
    @FXML
    private ColorPicker colorPicker2;
    @FXML
    private ColorPicker colorPicker1;
    @FXML
    private Button resetButton;
    @FXML
    private Button zoomButton;
    @FXML
    private Button setScale;
    @FXML
    private RadioButton manualScale;
    @FXML
    private MenuButton menuButton;
    @FXML
    private MenuItem mc1;
    @FXML
    private MenuItem mc2;
    @FXML
    private MenuItem mc3;
    @FXML
    private MenuItem mc4;
    @FXML
    private MenuItem mc5;
    @FXML
    private MenuItem mc6;
    @FXML
    private MenuItem mc7;
    @FXML
    private MenuItem mc8;
    @FXML
    private TextField ystop;
    @FXML
    private TextField ystart;
    @FXML
    private TextField xstop;
    @FXML
    private TextField xstart;
    @FXML
    private LineChart<Date, Number> lineChart;
    @FXML
    private NumberAxis yAxis;
    double scaleFactorNew = 0;
    @FXML
    private DateAxis dAxis;
    @FXML
    private Menu openFile1;
    @FXML
    private Rectangle zoomRect;
    SimpleDoubleProperty rectinitX = new SimpleDoubleProperty();
    SimpleDoubleProperty rectinitY = new SimpleDoubleProperty();
    SimpleDoubleProperty rectX = new SimpleDoubleProperty();
    SimpleDoubleProperty rectY = new SimpleDoubleProperty();
    LineChart lineChart2 = null;
    Date initXLowerBound, initXUpperBound;
    double initYLowerBound, initYUpperBound;

    public void setMain(TestFX testFX) {
        this.testFX = testFX;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        changeColor();

        setupColorPickers();

        disableScaleFields();
        disableMenuButton();

        disableCheckboxes();
        disableColorpickers();
//        final double SCALE_DELTA = 1.1;
//        lineChart.setOnScroll((ScrollEvent event) -> {
//            event.consume();
//            
//            if (event.getDeltaY() == 0) {
//                return;
//            }
//            
//            double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;
//            //TODO implement zoom
//            System.out.println(scaleFactor>=1.1?"Zoom out":"Zoom in");
//        });
//
//        lineChart.setOnMousePressed((MouseEvent event) -> {
//            if (event.getClickCount() == 2) {
//                System.out.println("gay");
//            }
//        });
        

        
        zoomRect.setFill(Color.web("blue", 0.1));
        zoomRect.setStroke(Color.BLUE);
        zoomRect.setStrokeDashOffset(50);

        zoomRect.widthProperty().bind(rectX.subtract(rectinitX));
        zoomRect.heightProperty().bind(rectY.subtract(rectinitY));
       
    }

    private static double getSceneShiftX(Node node) {
        double shift = 0;
        do {
            shift += node.getLayoutX();
            node = node.getParent();
        } while (node != null);
        return shift;
    }

    private static double getSceneShiftY(Node node) {
        double shift = 0;
        do {
            shift += node.getLayoutY();
            node = node.getParent();
        } while (node != null);
        return shift;
    }
    EventHandler<MouseEvent> mouseHandler = (MouseEvent mouseEvent) -> {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                zoomRect.setX(mouseEvent.getX());
                zoomRect.setY(mouseEvent.getY());
                rectinitX.set(mouseEvent.getX());
                rectinitY.set(mouseEvent.getY());
                System.out.println("rect:" + zoomRect);
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                rectX.set(mouseEvent.getX());
                rectY.set(mouseEvent.getY());
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
                if ((rectinitX.get() >= rectX.get()) && (rectinitY.get() >= rectY.get())) {
                    //Condizioni Iniziali
                    LineChart lineChart1 = lineChart2;
                    dAxis.setLowerBound(initXLowerBound);
                    dAxis.setUpperBound(initXUpperBound);
                    ((NumberAxis) lineChart1.getYAxis()).setLowerBound(initYLowerBound);
                    ((NumberAxis) lineChart1.getYAxis()).setUpperBound(initYUpperBound);
                } else {
                    //Zoom In

                    double Tgap = 0;
                    double newLowerBound, newUpperBound, axisShift;
                    double xScaleFactor, yScaleFactor;
                    double xaxisShift, yaxisShift;
                    LineChart lineChart3 = lineChart2;
                    // Zoom in Y-axis by changing bound range.
                    NumberAxis yAxis1 = (NumberAxis) lineChart3.getYAxis();
                    Tgap = yAxis1.getHeight() / (yAxis1.getUpperBound() - yAxis1.getLowerBound());
                    axisShift = getSceneShiftY(yAxis1);
                    yaxisShift = axisShift;
                    System.out.println("yAxisUPPER:" + yAxis1.getUpperBound() + "yAxisLOWER:" + yAxis1.getLowerBound());
                    newUpperBound = yAxis1.getUpperBound() - ((rectinitY.getValue() - axisShift) / Tgap);
                    newLowerBound = yAxis1.getUpperBound() - ((rectY.getValue()- axisShift) / Tgap);
                    if (newUpperBound > yAxis1.getUpperBound()) {
                        newUpperBound = yAxis1.getUpperBound();
                    }
                    yScaleFactor = (yAxis1.getUpperBound() - yAxis1.getLowerBound()) / (newUpperBound - newLowerBound);
                    yAxis1.setLowerBound(newLowerBound);
                    yAxis1.setUpperBound(newUpperBound);
                    XYChart.Series series9 = (XYChart.Series) lineChart3.getData().get(0);
                    if (!series9.getData().isEmpty()) {
                        series9.getData().remove(0);
                        series9.getData().remove(series9.getData().size() - 1);
                    }
                    DateAxis xAxis = (DateAxis) lineChart3.getXAxis();
                }
                // Hide the rectangle
                rectX.set(0);
                rectY.set(0);
            }
        } // end if (mouseEvent.getButton() == MouseButton.PRIMARY)
    };

    private void initChartLines() {
        lineChart.setOnMouseClicked(mouseHandler);
        lineChart.setOnMouseDragged(mouseHandler);
        lineChart.setOnMouseEntered(mouseHandler);
        lineChart.setOnMouseExited(mouseHandler);
        lineChart.setOnMouseMoved(mouseHandler);
        lineChart.setOnMousePressed(mouseHandler);
        lineChart.setOnMouseReleased(mouseHandler);
        lineChart2 = lineChart;
        //yAxis.setAutoRanging(false);
        yAxis = new NumberAxis();
        

        series1 = new XYChart.Series();
        series2 = new XYChart.Series();
        series3 = new XYChart.Series();
        series4 = new XYChart.Series();
        series5 = new XYChart.Series();
        series6 = new XYChart.Series();
        series7 = new XYChart.Series();
        series8 = new XYChart.Series();

        testFX.getSensorData().stream().forEach((x) -> {
            series1.getData().add(new XYChart.Data(x.getDate(), x.getChannel1()));
            series2.getData().add(new XYChart.Data(x.getDate(), x.getChannel2()));
            series3.getData().add(new XYChart.Data(x.getDate(), x.getChannel3()));
            series4.getData().add(new XYChart.Data(x.getDate(), x.getChannel4()));
            series5.getData().add(new XYChart.Data(x.getDate(), x.getChannel5()));
            series6.getData().add(new XYChart.Data(x.getDate(), x.getChannel6()));
            series7.getData().add(new XYChart.Data(x.getDate(), x.getChannel7()));
            series8.getData().add(new XYChart.Data(x.getDate(), x.getChannel8()));

        });
        seriesCollection.addAll(Arrays.asList(series1, series2, series3, series4, series5, series6, series7, series8));
//        dAxis.setUpperBound(testFX.getSensorData().get(testFX.getSensorData().size()-1).getDate());
//        dAxis.setUpperBound(testFX.getSensorData().get(testFX.getSensorData().size()-1).getDate());
//        initXLowerBound = dAxis.getLowerBound();
//        initXUpperBound = dAxis.getUpperBound();
//        initYLowerBound = ((NumberAxis) lineChart.getYAxis()).getLowerBound();
//        initYUpperBound = ((NumberAxis) lineChart.getYAxis()).getUpperBound();

    }

    @FXML
    private void openFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        // Show save file dialog
        File file = fileChooser.showOpenDialog(testFX.getPrimaryStage());
        if (file != null) {
            testFX.initFile(file);
            enableCheckboxes();
            initChartLines();

        } else {
            Dialogs.create()
                    .owner(testFX.getPrimaryStage())
                    .title("Information Dialog")
                    .masthead(null)
                    .message("Please select a file")
                    .showInformation();

        }
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    private void onCheckButton(ActionEvent event) {
        final Object source = event.getSource();
        if (source instanceof CheckBox) {
            final CheckBox chb = (CheckBox) source;

            if (chb.isSelected() && chb.equals(c1)) {
                setLineChart("Channel 1", chb);

                colorPicker1.setValue(Color.BLACK);
                colorPicker1.setDisable(false);
            }
            if (chb.isSelected() && chb.equals(c2)) {
                setLineChart("Channel 2", chb);
                colorPicker2.setValue(Color.RED);
                colorPicker2.setDisable(false);
            }
            if (chb.isSelected() && chb.equals(c3)) {
                setLineChart("Channel 3", chb);
                colorPicker3.setValue(Color.BLUE);
                colorPicker3.setDisable(false);
            }
            if (chb.isSelected() && chb.equals(c4)) {
                setLineChart("Channel 4", chb);
                colorPicker4.setValue(Color.YELLOW);
                colorPicker4.setDisable(false);
            }
            if (chb.isSelected() && chb.equals(c5)) {
                setLineChart("Channel 5", chb);
                colorPicker5.setValue(Color.GREEN);
                colorPicker5.setDisable(false);
            }
            if (chb.isSelected() && chb.equals(c6)) {
                setLineChart("Channel 6", chb);
                colorPicker6.setDisable(false);
            }
            if (chb.isSelected() && chb.equals(c7)) {
                setLineChart("Channel 7", chb);
                colorPicker7.setDisable(false);
            }
            if (chb.isSelected() && chb.equals(c8)) {
                setLineChart("Channel 8", chb);
                colorPicker8.setDisable(false);
            } else if (!chb.isSelected()) {
//               lineChart.legendVisibleProperty().setValue(false);

                if (chb.equals(c1)) {
                    colorPicker1.setDisable(true);

                    lineChart.getData().remove(series1);

                }
                if (chb.equals(c2)) {
                    colorPicker2.setDisable(true);
                    lineChart.getData().remove(series2);

                }
                if (chb.equals(c3)) {
                    colorPicker3.setDisable(true);
                    lineChart.getData().remove(series3);
                }
                if (chb.equals(c4)) {
                    colorPicker4.setDisable(true);
                    lineChart.getData().remove(series4);
                }
                if (chb.equals(c5)) {
                    colorPicker5.setDisable(true);
                    lineChart.getData().remove(series5);
                }
                if (chb.equals(c6)) {
                    colorPicker6.setDisable(true);
                    lineChart.getData().remove(series6);
                }
                if (chb.equals(c7)) {
                    colorPicker7.setDisable(true);
                    lineChart.getData().remove(series7);
                }
                if (chb.equals(c8)) {
                    colorPicker8.setDisable(true);
                    lineChart.getData().remove(series8);
                }
            }
        }
    }

    //TODO fix a more genric soulution, e.g don't get index
    public void setLineChart(String chs, CheckBox c) {

        //populating the series with data
        if (c.equals(c1)) {
            lineChart.getData().add(series1);

            final XYChart.Series<Date, Number> seriesT = lineChart.getData().get(0);
            seriesT.getNode().setStyle("-fx-stroke: #000000");

            seriesT.getData().stream().forEach((data) -> {
                data.getNode().setStyle("-fx-background-color: #000000, white;");
            });

        }
        if (c.equals(c2)) {
            lineChart.getData().add(series2);
            final XYChart.Series<Date, Number> seriesT = lineChart.getData().get(1);
            seriesT.getNode().setStyle("-fx-stroke: #FF0000");

            seriesT.getData().stream().forEach((data) -> {
                data.getNode().setStyle("-fx-background-color: #FF0000, white;");
            });
        }
        if (c.equals(c3)) {
            lineChart.getData().add(series3);
            final XYChart.Series<Date, Number> seriesT = lineChart.getData().get(2);
            seriesT.getNode().setStyle("-fx-stroke: #0015FF");

            seriesT.getData().stream().forEach((data) -> {
                data.getNode().setStyle("-fx-background-color: #0015FF, white;");
            });
        }
        if (c.equals(c4)) {
            lineChart.getData().add(series4);
            final XYChart.Series<Date, Number> seriesT = lineChart.getData().get(3);
            seriesT.getNode().setStyle("-fx-stroke: #FFFF00");

            seriesT.getData().stream().forEach((data) -> {
                data.getNode().setStyle("-fx-background-color: #FFFF00, white;");
            });
        }
        if (c.equals(c5)) {
            lineChart.getData().add(series5);
            final XYChart.Series<Date, Number> seriesT = lineChart.getData().get(4);
            seriesT.getNode().setStyle("-fx-stroke: #27C400");

            seriesT.getData().stream().forEach((data) -> {
                data.getNode().setStyle("-fx-background-color: #27C400, white;");
            });
        }
        if (c.equals(c6)) {
            lineChart.getData().add(series6);
        }
        if (c.equals(c7)) {
            lineChart.getData().add(series7);
        }
        if (c.equals(c8)) {
            lineChart.getData().add(series8);
        }

    }

    @FXML
    private void setColor(ActionEvent event) {
        final Object source = event.getSource();
        if (source instanceof ColorPicker) {
            final ColorPicker picker = (ColorPicker) source;
            final Color newColor = picker.getValue();

            if (newColor == null) {
                return;
            }
            seriesColors.set(colorPickers.indexOf(picker), newColor);

        }
    }

    @FXML
    private void onSetScale(ActionEvent event) {
        final Object source = event.getSource();
        if (source instanceof RadioButton) {
            final RadioButton rbtn = (RadioButton) source;
            if (rbtn.isSelected()) {
                enableScaleFields();

            } else {
                disableScaleFields();
                enableScaleButton();

            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Enable/disable methods">
    private void disableMenuButton() {
        Arrays.asList(mc1, mc2, mc3, mc4, mc5, mc6, mc7, mc8).stream().forEach((b) -> {
            b.setOnAction(menuButtonChildEvent);
        });
    }

    private void disableColorpickers() {
        Arrays.asList(colorPicker1, colorPicker2, colorPicker3, colorPicker4, colorPicker5, colorPicker6, colorPicker7, colorPicker8).stream().forEach((x) -> {
            x.setDisable(true);
        });
    }

    private void disableCheckboxes() {
        Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8).stream().forEach((x) -> {
            x.setDisable(true);
        });
    }

    private void disableScaleFields() {
        Arrays.asList(xstart, xstop, ystart, ystop).stream().forEach((b) -> {
            b.setEditable(false);
        });
    }

    private void enableScaleFields() {
        Arrays.asList(xstart, xstop, ystart, ystop).stream().forEach((b) -> {
            b.setEditable(true);
        });
    }

    private void enableScaleButton() {
        setScale.setDisable(false);
    }

    private void disableScaleButton() {
        setScale.setDisable(true);
    }

    private void enableCheckboxes() {
        Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8).stream().forEach((x) -> {
            x.setDisable(false);
        });
    }
    // </editor-fold>

    EventHandler toggleButtonEvent = (EventHandler<ActionEvent>) (ActionEvent event) -> {
        if (event.getSource() instanceof ToggleButton) {
            ToggleButton cp = (ToggleButton) event.getSource();
            System.out.println("Action performed manual scale " + cp.isSelected());

            Arrays.asList(xstart, xstop, ystart, ystop).stream().forEach((b) -> {
                b.setEditable(true);
            });

        }
    };

    EventHandler menuButtonChildEvent = (EventHandler<ActionEvent>) (ActionEvent event) -> {
        if (event.getSource() instanceof MenuItem) {
            MenuItem mi = (MenuItem) event.getSource();
            System.out.println("Action performed main channel " + mi.getText());

        }
    };
//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Color metods">
    private void setupColorPickers() {
        colorPickers.add(colorPicker1);
        colorPickers.add(colorPicker2);
        colorPickers.add(colorPicker3);
        colorPickers.add(colorPicker4);
        colorPickers.add(colorPicker5);
        colorPickers.add(colorPicker6);
        colorPickers.add(colorPicker7);
        colorPickers.add(colorPicker8);

        colorPicker1.setValue(seriesColors.get(0));
        colorPicker2.setValue(seriesColors.get(1));
        colorPicker3.setValue(seriesColors.get(2));
        colorPicker4.setValue(seriesColors.get(3));
        colorPicker5.setValue(seriesColors.get(4));
        colorPicker6.setValue(seriesColors.get(5));
        colorPicker7.setValue(seriesColors.get(6));
        colorPicker8.setValue(seriesColors.get(7));

    }

    private static String toRGBCode(final Color color) {
        return String.format("#%02X%02X%02X", (int) (color.getRed() * RGB_MAX), (int) (color.getGreen() * RGB_MAX),
                (int) (color.getBlue() * RGB_MAX));
    }

    private void changeColor() {
        setupColors();

        seriesColors.addListener((final ListChangeListener.Change<? extends Color> change) -> {
            change.next();
            if (change.wasAdded()) {
                //TODO fix a more genric soulution e.g don't limit to index
                int index = change.getFrom();
                final List<? extends Color> addedSubList = change.getAddedSubList();
                for (final Color color : addedSubList) {
                    final XYChart.Series<Date, Number> seriesT = lineChart.getData().get(index);
                    final String newWebColor = toRGBCode(color);
                    final String strokeStyle = "-fx-stroke: " + newWebColor + ";";
                    final String backgroundColorStyle = "-fx-background-color: " + newWebColor + ", white;";
                    // set line color
                    seriesT.getNode().setStyle(strokeStyle);
                    // set data point color
                    seriesT.getData().stream().forEach((data) -> {
                        data.getNode().setStyle(backgroundColorStyle);
                    });
                    // set legend item color
                    final Set<Node> nodes = lineChart.lookupAll(".chart-legend-item-symbol.default-color" + index);
                    nodes.stream().forEach((n) -> {
                        n.setStyle(backgroundColorStyle);
                    });
                    index++;
                }
            }
        });

    }

    private void setupColors() {
        seriesColors.add(Color.web(CHART_SERIES_DEFAULT_COLOR_0_FX8));
        seriesColors.add(Color.web(CHART_SERIES_DEFAULT_COLOR_1_FX8));
        seriesColors.add(Color.web(CHART_SERIES_DEFAULT_COLOR_2_FX8));
        seriesColors.add(Color.web(CHART_SERIES_DEFAULT_COLOR_3_FX8));
        seriesColors.add(Color.web(CHART_SERIES_DEFAULT_COLOR_4_FX8));
        seriesColors.add(Color.web(CHART_SERIES_DEFAULT_COLOR_5_FX8));
        seriesColors.add(Color.web(CHART_SERIES_DEFAULT_COLOR_6_FX8));
        seriesColors.add(Color.web(CHART_SERIES_DEFAULT_COLOR_7_FX8));
    }
    // </editor-fold>

}
