package cse213.badc;

import cse213.badc.saad.AOOStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Helper {
    public static void showAlert(String s){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(s);
        a.showAndWait();
    }

    public static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static <T> void writeInto(String binFile, T data) throws IOException {
        File file = new File(binFile);
        FileOutputStream fos;
        ObjectOutputStream oos;

        if (file.exists()) {
            fos = new FileOutputStream(file, true);
            oos = new AOOStream(fos);
        } else {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
        }

        oos.writeObject(data);
        oos.close();
    }

    public static <T> void loadFrom(String binFile, ArrayList<T> lst) throws IOException {
        File file = new File(binFile);

        if (!file.exists()) {
            showAlert("File Error", "File not found");
            return;
        }

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            while (true) {
                T obj = (T) ois.readObject();
                lst.add(obj);
            }
        } catch (EOFException e) {
            System.out.println("We have reached the end of file");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ois.close();
    }

    public static Object loadFromSingleObject(String fileName) {
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                showAlert("File Error", "File not found");
                return null;
            }

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Object data = ois.readObject();
            ois.close();
            fis.close();

            return data;
        } catch (Exception e) {
            showAlert("File Error", "Could not read from " + fileName);
            return null;
        }
    }

    public static void deleteFile(String binFile){
        File file = new File(binFile);
        if (!file.exists()){
            showAlert("File Error", "File not found");
        } else {
            file.delete();
        }
    }

    public static boolean appendTextFile(String fileName, String content) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(content + "\n");
            fw.close();
            return true;
        } catch (Exception e) {
            showAlert("File Error", "Could not append to " + fileName);
            return false;
        }
    }

    public static void closeWindow(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            Helper.showAlert("Error", "Could not close window");
        }
    }

    public static void setScene(ActionEvent actionEvent, Scene scene) {
        try {
            javafx.stage.Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            showAlert("Error", "Could not load scene");
        }
    }

    public static void backToDashboardU7(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("rhythm/U7_BADCJobApplicantDashboardView.fxml"));
        Scene scene = new Scene(loader.load());
        setScene(actionEvent, scene);
    }

    public static void backToDashboardU8(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("rhythm/U8_DevelopmentPartnerDashboardView.fxml"));
        Scene scene = new Scene(loader.load());
        setScene(actionEvent, scene);
    }
}
