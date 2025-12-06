package cse213.badc.saad;

import cse213.badc.BADCApplication;
import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class U3ViewNoticeController
{
    @javafx.fxml.FXML
    private ComboBox<String> monthCB;
    @javafx.fxml.FXML
    private TableColumn<Notice, String> titleCol;
    @javafx.fxml.FXML
    private TableColumn<Notice, String> regionCol;
    @javafx.fxml.FXML
    private TableColumn<Notice, LocalDate> dateCol;
    @javafx.fxml.FXML
    private TableView<Notice> noticeTableView;

    @javafx.fxml.FXML
    public void initialize() {
        monthCB.getItems().addAll("JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");

        titleCol.setCellValueFactory(new PropertyValueFactory<>("noticeTitle"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("noticeDate"));
        regionCol.setCellValueFactory(new PropertyValueFactory<>("region"));


    }

    Supplier currentUser;
    public void passViewNoticeInterface(Supplier s){
        currentUser = s;
    }

    @javafx.fxml.FXML
    public void detailsOA(ActionEvent actionEvent) {
        Notice notice = noticeTableView.getSelectionModel().getSelectedItem();
        Helper.showAlert(notice.toString());
    }

    @javafx.fxml.FXML
    public void backOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/U3IESDashboardView.fxml"));
        Scene scene = new Scene(loader.load());

        U3IESDashboardController controller = loader.getController();
        controller.passIESDashboard(currentUser);

        Stage stage =   (Stage) ((Node)  actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("IES Dashboard");
        stage.show();
    }

    @javafx.fxml.FXML
    public void loadOA(ActionEvent actionEvent) throws IOException {
        ArrayList<Notice> nList = new ArrayList<>();
        Helper.loadFrom("allNotices.bin", nList);
        for (Notice n : nList){
            if (n.getMonth().equals(monthCB.getValue())){
                noticeTableView.getItems().add(n);
            }
        }
    }
}