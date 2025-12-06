package cse213.badc.saad;

import cse213.badc.BADCApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class U4NoticeCreationController
{
    @javafx.fxml.FXML
    private ComboBox<String> regionCB;
    @javafx.fxml.FXML
    private TextArea detailsTA;
    @javafx.fxml.FXML
    private TextField idTF;
    @javafx.fxml.FXML
    private TextField titleTF;

    FieldOfficer currentUser;
    public void passNoticeInterface(FieldOfficer fo){
        currentUser = fo;
    }

    @javafx.fxml.FXML
    public void initialize() {
        regionCB.getItems().addAll("Dhaka", "Chattogram", "Rajshahi", "Khulna", "Sylhet", "Barishal", "Rangpur", "Mymensingh");
    }

    @javafx.fxml.FXML
    public void publishOA(ActionEvent actionEvent) throws IOException {

        File file = new File("allNotices.bin");
        if (file.exists()){
            ArrayList<Notice> nList = new ArrayList<>();
            Helper.loadFrom("allNotices.bin", nList);
            for (Notice n : nList){
                if (n.getNoticeID().equals(idTF.getText())){
                    Helper.showAlert("Cant duplicate notice id");
                    return;
                }
            }
        }


        Notice n = new Notice(
                idTF.getText(),
                detailsTA.getText(),
                titleTF.getText(),
                regionCB.getValue()
        );



        Helper.writeInto("allNotices.bin", n);
        Helper.showAlert(n.toString());
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