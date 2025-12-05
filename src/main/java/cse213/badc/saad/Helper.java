package com.example.mainproject.saad;

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



    public static void changeScene(ActionEvent event, String fxmlFile, String title){
        try {
            FXMLLoader loader = new FXMLLoader(Helper.class.getResource(fxmlFile));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle(title);

            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static  <T>void writeInto(String binFile, T data) throws IOException {
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


    public static  <T>void loadFrom(String binFile, ArrayList<T> lst) throws IOException {
        File file = new File(binFile);

        if (!file.exists()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("File not found");
            a.showAndWait();
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
            System.out.println("Sesh");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ois.close();

    }

    public void deleteFile(String binFile){

        File file = new File(binFile);
        if (!file.exists()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("File not found");
            a.showAndWait();
            return;
        } else {
            file.delete();
        }

    }


}
