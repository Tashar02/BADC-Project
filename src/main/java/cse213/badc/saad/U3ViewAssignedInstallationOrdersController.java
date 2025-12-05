package cse213.badc.saad;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class U3ViewAssignedInstallationOrdersController
{
    @javafx.fxml.FXML
    private TableColumn<Task, LocalDate> dateCol;
    @javafx.fxml.FXML
    private TableView<Task> taskTableView;
    @javafx.fxml.FXML
    private TableColumn<Task, String> idCol;
    @javafx.fxml.FXML
    private Label detailsLabel;

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        idCol.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("taskDate"));

        ArrayList<Task> tasks = new ArrayList<>();
        Helper.loadFrom("allTasks.bin", tasks);

        for (Task t : tasks){
            if (t.getAssignedSupplier().equals(currentUser.getSupplierID())){
                taskTableView.getItems().add(t);
            }
        }
    }

    Supplier currentUser;
    public void passAssignedOrderInterface(Supplier s){
        currentUser = s;
    }

    @javafx.fxml.FXML
    public void cancelOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("saad/U3IESDashboardView.fxml"));

        Scene scene = new Scene(loader.load());

        U3IESDashboardController controller = loader.getController();
        controller.passIESDashboard(currentUser);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.setTitle("IES Dashboard");
        stage.setScene(scene);
        stage.show();
    }


    @javafx.fxml.FXML
    public void loadOA(ActionEvent actionEvent) {
        Task t = taskTableView.getSelectionModel().getSelectedItem();
        detailsLabel.setText(t.toString());
    }
}