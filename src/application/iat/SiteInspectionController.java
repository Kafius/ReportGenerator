package application.iat;

import application.InfoController;
import application.Report;
import application.iat.report.siteInspectionReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SiteInspectionController implements Initializable {
    siteInspectionReport report;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void goBack(ActionEvent event) {
        if(report.interfaceCounter==1){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/InfoPrompt.fxml"));
                Parent root = (Parent) loader.load();

                InfoController infoReport = loader.getController();
                infoReport.getReport(report);

                report.interfaceCounter--;
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(report.getInterfaces().get(report.interfaceCounter)));
                Parent root = (Parent) loader.load();
                if(report.getInterfaces().get(report.interfaceCounter).equals("/application/iat/AirTestingMaker.fxml")) {
                    AirTestingController table = loader.getController();
                    table.getReport(report);
                }
                else if(report.getInterfaces().get(report.interfaceCounter).equals("/application/iat/PostAirTestingMaker.fxml")) {
                    PostAirTestingController table = loader.getController();
                    table.getReport(report);
                }
                else if(report.getInterfaces().get(report.interfaceCounter).equals("/application/iat/TEMAsbestosFibreMaker.fxml")) {
                    TEMAsbestosFibreController table = loader.getController();
                    table.getReport(report);
                }

                report.interfaceCounter--;
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}


