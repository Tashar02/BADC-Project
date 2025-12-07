package cse213.badc;

import cse213.badc.rhythm.BADCJobApplicant;
import cse213.badc.rhythm.DevelopmentPartner;
import cse213.badc.saad.FieldOfficer;
import cse213.badc.saad.Supplier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class BADCApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        initializeCredentials();

        FXMLLoader fxmlLoader = new FXMLLoader(BADCApplication.class.getResource("LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("BADC");
        stage.setScene(scene);
        stage.show();
    }

    private void initializeCredentials() throws IOException {
        FieldOfficer fo = (new FieldOfficer("1000", "1000officer", "Saad Islam", "2432029@iub.edu.bd", "Dhaka"));
        Helper.writeInto("allFieldOfficers.bin", fo);
//        File badcFieldOfficerFile = new File("allFieldOfficers.bin");
//        if (!badcFieldOfficerFile.exists()) {
//            ArrayList<FieldOfficer> officers = new ArrayList<>();
//            officers.add(new FieldOfficer("1000", "1000officer", "Saad Islam", "2432029@iub.edu.bd", "Dhaka"));
//
//            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("allFieldOfficers.bin"))) {
//                oos.writeObject(officers);
//                System.out.println("BADC Field Officer credentials created successfully");
//            } catch (Exception e) {
//                Helper.showAlert("File error", "Could not write credentials for BADC Field Officer");
//            }
//        }

        Supplier s = new Supplier("0001", "0001reshma", "Reshma Sultana", "reshma@gmail.com", "0123456780");
        Helper.writeInto("allSuppliers.bin", s);
//       File supplierFile = new File("allSuppliers.bin");
//        if (!supplierFile.exists()) {
//            ArrayList<Supplier> suppliers = new ArrayList<>();
//            suppliers.add(new Supplier("0001", "0001reshma", "Reshma Sultana", "reshma@gmail.com", "0123456780"));
//
//            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("allSuppliers.bin"))) {
//                oos.writeObject(suppliers);
//                System.out.println("Irrigation Equipment Supplier credentials created successfully");
//            } catch (Exception e) {
//                Helper.showAlert("File error", "Could not write credentials for BADC Job Applicant");
//            }
//        }

        File badcjobApplicantsFile = new File("BADCJobApplicant.bin");
        if (!badcjobApplicantsFile.exists()) {
            ArrayList<BADCJobApplicant> applicants = new ArrayList<>();
            applicants.add(new BADCJobApplicant("1234", "1234badcjobapplicant", "Tashfin Shakeer Rhythm", "tashfinshakeerrhythm@gmail.com"));

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BADCJobApplicant.bin"))) {
                oos.writeObject(applicants);
                System.out.println("Job Applicant credentials created successfully");
            } catch (Exception e) {
                Helper.showAlert("File error", "Could not write credentials for Development Partner");
            }
        }

        File devPartnerFile = new File("developmentPartner.bin");
        if (!devPartnerFile.exists()) {
            ArrayList<DevelopmentPartner> partners = new ArrayList<>();
            partners.add(new DevelopmentPartner("5678", "5678developmentpartner", "Md. Abdur Rahim", "partner@badc.gov.bd"));

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("developmentPartner.bin"))) {
                oos.writeObject(partners);
            } catch (Exception e) {
                Helper.showAlert("File error", "Could not write credentials for Development Partner");
            }
        }
    }
}
