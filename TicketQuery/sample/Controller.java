package sample;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import ticket.util.ParseResult;
import ticket.util.SearchJSON;
import ticket.util.SerStat;

public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="x1"
    private Font x1; // Value injected by FXMLLoader

    @FXML // fx:id="x2"
    private Color x2; // Value injected by FXMLLoader

    @FXML // fx:id="dateTxt"
    private DatePicker dateTxt; // Value injected by FXMLLoader

    @FXML // fx:id="fromTxt"
    private TextField fromTxt; // Value injected by FXMLLoader

    @FXML // fx:id="toTxt"
    private TextField toTxt; // Value injected by FXMLLoader

    @FXML // fx:id="searchBtn"
    private Button searchBtn; // Value injected by FXMLLoader

    @FXML // fx:id="table"
    private TableView<TicketInfo> table; // Value injected by FXMLLoader

    @FXML // fx:id="c1"
    private TableColumn<?, ?> c1; // Value injected by FXMLLoader

    @FXML // fx:id="c2"
    private TableColumn<?, ?> c2; // Value injected by FXMLLoader

    @FXML // fx:id="c3"
    private TableColumn<?, ?> c3; // Value injected by FXMLLoader

    @FXML // fx:id="c4"
    private TableColumn<?, ?> c4; // Value injected by FXMLLoader

    @FXML // fx:id="c5"
    private TableColumn<?, ?> c5; // Value injected by FXMLLoader

    @FXML // fx:id="c6"
    private TableColumn<?, ?> c6; // Value injected by FXMLLoader

    @FXML // fx:id="c7"
    private TableColumn<?, ?> c7; // Value injected by FXMLLoader

    @FXML // fx:id="c8"
    private TableColumn<?, ?> c8; // Value injected by FXMLLoader

    @FXML // fx:id="c9"
    private TableColumn<?, ?> c9; // Value injected by FXMLLoader

    @FXML // fx:id="c10"
    private TableColumn<?, ?> c10; // Value injected by FXMLLoader

    @FXML // fx:id="c10"
    private TableColumn<?, ?> c11; // Value injected by FXMLLoader

    @FXML // fx:id="x3"
    private Font x3; // Value injected by FXMLLoader

    @FXML // fx:id="x4"
    private Color x4; // Value injected by FXMLLoader

    @FXML
    private CheckBox isStu;

    HashMap<String, String> stat;
    SearchJSON result ;
    public String[][] info;
    private final String pattern = "yyyy-MM-dd";

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert x1 != null : "fx:id=\"x1\" was not injected: check your FXML file 'check.fxml'.";
        assert x2 != null : "fx:id=\"x2\" was not injected: check your FXML file 'check.fxml'.";
        assert dateTxt != null : "fx:id=\"dateTxt\" was not injected: check your FXML file 'check.fxml'.";
        assert fromTxt != null : "fx:id=\"fromTxt\" was not injected: check your FXML file 'check.fxml'.";
        assert toTxt != null : "fx:id=\"toTxt\" was not injected: check your FXML file 'check.fxml'.";
        assert searchBtn != null : "fx:id=\"searchBtn\" was not injected: check your FXML file 'check.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'check.fxml'.";
        assert c1 != null : "fx:id=\"c1\" was not injected: check your FXML file 'check.fxml'.";
        assert c2 != null : "fx:id=\"c2\" was not injected: check your FXML file 'check.fxml'.";
        assert c3 != null : "fx:id=\"c3\" was not injected: check your FXML file 'check.fxml'.";
        assert c4 != null : "fx:id=\"c4\" was not injected: check your FXML file 'check.fxml'.";
        assert c5 != null : "fx:id=\"c5\" was not injected: check your FXML file 'check.fxml'.";
        assert c6 != null : "fx:id=\"c6\" was not injected: check your FXML file 'check.fxml'.";
        assert c7 != null : "fx:id=\"c7\" was not injected: check your FXML file 'check.fxml'.";
        assert c8 != null : "fx:id=\"c8\" was not injected: check your FXML file 'check.fxml'.";
        assert c9 != null : "fx:id=\"c9\" was not injected: check your FXML file 'check.fxml'.";
        assert c10 != null : "fx:id=\"c10\" was not injected: check your FXML file 'check.fxml'.";
        assert x3 != null : "fx:id=\"x3\" was not injected: check your FXML file 'check.fxml'.";
        assert x4 != null : "fx:id=\"x4\" was not injected: check your FXML file 'check.fxml'.";
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                StringConverter converter = new StringConverter<LocalDate>() {
                    DateTimeFormatter dateFormatter =
                            DateTimeFormatter.ofPattern(pattern);
                    @Override
                    public String toString(LocalDate date) {
                        if (date != null) {
                            return dateFormatter.format(date);
                        } else {
                            return "";
                        }

                    }
                    @Override
                    public LocalDate fromString(String string) {
                        if (string != null && !string.isEmpty()) {
                            return LocalDate.parse(string, dateFormatter);
                        } else {
                            return null;
                        }
                    }
                };
                dateTxt.setConverter(converter);
                dateTxt.setValue(LocalDate.now());

                try {
                    stat = SerStat.statInit();
                }catch(Exception e){
                    fromTxt.setText("err in stat(Hashmap) init");
                }

                File file = new File("locat.dat");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(new FileInputStream(file));
                    result = (SearchJSON) ois.readObject();
                    ois.close();
                    fromTxt.setText(((ArrayList<String>)SearchJSON.getKey(stat,result.from)).get(0));
                    toTxt.setText(((ArrayList<String>)SearchJSON.getKey(stat,result.to)).get(0));
                    int year = Integer.parseInt(result.date.split("\\-")[0]);
                    int month = Integer.parseInt(result.date.split("\\-")[1]);
                    int day = Integer.parseInt(result.date.split("\\-")[2]);
                    dateTxt.setValue(LocalDate.of(year,month,day));
                    isStu.setSelected(!result.method.equals("ADULT"));
                } catch (Exception e) {
                    result = null;
                    e.printStackTrace();
                }
            }
        });
    }
    @FXML
    void search(){
        try {
                String from = stat.get(fromTxt.getText());
                String to = stat.get(toTxt.getText());
                String date = dateTxt.getValue().toString();
                String method = isStu.isSelected()?"0X00":"ADULT";
                result = new SearchJSON(from,to,date,method);

                    result.search();

                info = ParseResult.parseResult(result.result);
                ObservableList<TicketInfo> data = FXCollections.observableArrayList();
                for (int i = 0; i <result.result.size() ; i++) {
                    data.add(new TicketInfo(info[i][3],info[i][8],info[i][9],info[i][10],info[i][31],info[i][30],info[i][23],info[i][28],info[i][29],info[i][26],info[i][21]));
                }
                c1.setCellValueFactory(new PropertyValueFactory<>("train"));
                c2.setCellValueFactory(new PropertyValueFactory<>("fromTime"));
                c3.setCellValueFactory(new PropertyValueFactory<>("toTime"));
                c4.setCellValueFactory(new PropertyValueFactory<>("costTime"));
                c5.setCellValueFactory(new PropertyValueFactory<>("firstClass"));
                c6.setCellValueFactory(new PropertyValueFactory<>("secondClass"));
                c7.setCellValueFactory(new PropertyValueFactory<>("softBed"));
                c8.setCellValueFactory(new PropertyValueFactory<>("hardBed"));
                c9.setCellValueFactory(new PropertyValueFactory<>("hardSeat"));
                c10.setCellValueFactory(new PropertyValueFactory<>("noSeat"));
                c11.setCellValueFactory(new PropertyValueFactory<>("advanceBed"));
                table.setItems(data);

                ObjectOutputStream oos = null;
                try {
                    oos = new ObjectOutputStream(new FileOutputStream("locat.dat"));
                    oos.writeObject(result);
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }catch(Exception e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("err in conn with 12306");
        alert.setContentText(e.getMessage().replaceAll("[^\\u4e00-\\u9fa5]", ""));
        alert.showAndWait();
    }
    }

}

