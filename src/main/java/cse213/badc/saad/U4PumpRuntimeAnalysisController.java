package cse213.badc.saad;

import cse213.badc.Helper;
import cse213.badc.BADCApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class U4PumpRuntimeAnalysisController
{
    @javafx.fxml.FXML
    private CheckBox includeCheckBox;
    @javafx.fxml.FXML
    private AnchorPane backOA;
    @javafx.fxml.FXML
    private PieChart pieChart;

    private ObservableList<PieChart.Data> list = FXCollections.observableArrayList();

    private double summerACount = 0;
    private double monsoonACount = 0;
    private double winterACount = 0;
    private double summerAllCount = 0;
    private double monsoonAllCount = 0;
    private double winterAllCount = 0;


    @javafx.fxml.FXML
    public void initialize() throws IOException {

        ArrayList<MaintenanceReport> mrList = new ArrayList<>();
        Helper.loadFrom("allMaintenanceReports.bin", mrList);

        // reset counters
        summerACount = 0;
        monsoonACount = 0;
        winterACount = 0;

        summerAllCount = 0;
        monsoonAllCount = 0;
        winterAllCount = 0;

        for (MaintenanceReport mr : mrList) {
            String season = mr.getSeason();
            String status = mr.getStatus();

            if (season == null) continue;

            if ("summer".equalsIgnoreCase(season)) {
                summerAllCount++;
                if ("approved".equalsIgnoreCase(status)) summerACount++;
            }
            else if ("monsoon".equalsIgnoreCase(season)) {
                monsoonAllCount++;
                if ("approved".equalsIgnoreCase(status)) monsoonACount++;
            }
            else if ("winter".equalsIgnoreCase(season)) {
                winterAllCount++;
                if ("approved".equalsIgnoreCase(status)) winterACount++;
            }
        }

        // default load: approved only
        list.clear();
        list.add(new PieChart.Data("Summer", summerACount));
        list.add(new PieChart.Data("Monsoon", monsoonACount));
        list.add(new PieChart.Data("Winter", winterACount));

        pieChart.setData(list);
    }


    FieldOfficer currentUser;
    public void passPumpRuntimeInterface(FieldOfficer fo){
        currentUser = fo;
    }


    @javafx.fxml.FXML
    public void loadOA(ActionEvent actionEvent) {

        list.clear();
        list.add(new PieChart.Data("Summer", summerACount));
        list.add(new PieChart.Data("Monsoon", monsoonACount));
        list.add(new PieChart.Data("Winter", winterACount));

        pieChart.setData(list);
    }


    @javafx.fxml.FXML
    public void checkOA(ActionEvent actionEvent) {

        list.clear();

        if (includeCheckBox.isSelected()){
            list.add(new PieChart.Data("Summer", summerAllCount));
            list.add(new PieChart.Data("Monsoon", monsoonAllCount));
            list.add(new PieChart.Data("Winter", winterAllCount));
        } else {
            list.add(new PieChart.Data("Summer", summerACount));
            list.add(new PieChart.Data("Monsoon", monsoonACount));
            list.add(new PieChart.Data("Winter", winterACount));
        }

        pieChart.setData(list);
    }


    @javafx.fxml.FXML
    public void backOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/U4BFODashboardView.fxml"));
        Scene scene = new Scene(loader.load());

        U4BFODashboardController controller = loader.getController();
        controller.passBFODashboard(currentUser);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("BFO Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
