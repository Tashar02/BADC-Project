package cse213.badc.saad;

import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;



public class U3SubmitMaintenanceReportController {
    @javafx.fxml.FXML
    private ComboBox<String> outcomeComboBox;
    @javafx.fxml.FXML
    private ComboBox<String> taskComboBox;
    @javafx.fxml.FXML
    private TextArea remarkTA;
    @javafx.fxml.FXML
    private CheckBox correctCheckBox;
    @javafx.fxml.FXML
    private DatePicker workDatePicker;
    @javafx.fxml.FXML
    private ComboBox<String> equipmentIdComboBox;
    @javafx.fxml.FXML
    private TextField idTF;
    @javafx.fxml.FXML
    private TextField costTF;
    @javafx.fxml.FXML
    private TextField runtimeTF;
    @javafx.fxml.FXML
    private ComboBox<String> seasonComboBox;

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        outcomeComboBox.getItems().addAll("Not Started Yet", "Early Stage", "In Progress", "Completed");
        seasonComboBox.getItems().addAll("Summer", "Monsoon", "Winter");

        ArrayList<Task> tasks = new ArrayList<>();
        Helper.loadFrom("allTasks.bin", tasks);
        ArrayList<String> taskIds = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getAssignedSupplier().equals(currentUser.getSupplierID()) && !t.isReported()) {
                taskIds.add(t.getTaskId());
            }
        }
        taskComboBox.getItems().addAll(taskIds);

        ArrayList<Pump> pumps = new ArrayList<>();
        Helper.loadFrom("allPumps.bin", pumps);
        ArrayList<String> pumpIds = new ArrayList<>();
        for (Pump p : pumps) {
            if (p.getSupplierId().equals(currentUser.getSupplierID())) {
                pumpIds.add(p.getEquipmentId());
            }
        }
        equipmentIdComboBox.getItems().addAll(pumpIds);


    }

    Supplier currentUser;

    public void passMainReportInterface(Supplier s) {
        currentUser = s;
    }

    @javafx.fxml.FXML
    // String reportId, String authorId, String summary, String taskId, String equipmentId, String workOutcome, LocalDate workDate, int cost
    public void supplierReportOA(ActionEvent actionEvent) throws IOException {


        if (!correctCheckBox.isSelected()) {
            Helper.showAlert("Agree with the term");
            return;
        }

        if (!currentUser.isVerified()) {
            Helper.showAlert("You are not verified");
            return;
        }

        if (workDatePicker.getValue().isAfter(LocalDate.now())) {
            Helper.showAlert("Date of work cannot be a future date");
            return;
        }

// String reportId, String authorId, String summary, int pumpRuntime, int cost, /
// /LocalDate workDate, String season, String workOutcome, String equipmentId, String taskId

        MaintenanceReport mr = new MaintenanceReport(
                idTF.getText(),
                currentUser.getSupplierID(),
                remarkTA.getText(),
                Integer.parseInt(runtimeTF.getText()),
                Integer.parseInt(costTF.getText()),
                workDatePicker.getValue(),
                seasonComboBox.getValue(),
                outcomeComboBox.getValue(),
                equipmentIdComboBox.getValue(),
                taskComboBox.getValue()
        );

        Helper.writeInto("allMaintenanceReports.bin", mr);
        ArrayList<Task> tasks = new ArrayList<>();
        Helper.loadFrom("allTasks.bin", tasks);
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskComboBox.getValue())) {
                t.setReported(true);
                taskComboBox.getItems().remove(t.getTaskId());
            }
        }


    }

    @javafx.fxml.FXML
    public void cancelSupplierReportOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("saad/U3IEWDashboardView.fxml"));
        Scene scene = new Scene(loader.load());

        U3IESDashboardController controller = loader.getController();
        controller.passIESDashboard(currentUser);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("IES Dashboard");
        stage.setScene(scene);
        stage.show();

    }


}