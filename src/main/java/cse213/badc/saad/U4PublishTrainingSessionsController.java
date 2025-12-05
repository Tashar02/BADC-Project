package cse213.badc.saad;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class U4PublishTrainingSessionsController
{
    @javafx.fxml.FXML
    private TextField seatsTF;
    @javafx.fxml.FXML
    private TableColumn idCol;
    @javafx.fxml.FXML
    private TableColumn dateCol;
    @javafx.fxml.FXML
    private DatePicker trainingDatePicker;
    @javafx.fxml.FXML
    private TextField trainingIdTF;
    @javafx.fxml.FXML
    private TextArea detailsTA;
    @javafx.fxml.FXML
    private TableView trainingsTableView;
    @javafx.fxml.FXML
    private TextField titleTF;

    @javafx.fxml.FXML
    public void initialize() {
    }

    FieldOfficer currentUser;
    public void passPublishTrainingInterface(FieldOfficer fo){
        currentUser = fo;
    }

    @javafx.fxml.FXML
    public void publishOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void loadTrainingOA(ActionEvent actionEvent) {
    }
}