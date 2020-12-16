package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class InfoController implements Initializable {
    Report report;

    @FXML
    AnchorPane ap;

    //buttons for report info
    @FXML private Button changeButton, nextButton;

    @FXML Label projectNameL;
    @FXML Label projectNumberL;
    @FXML Label SELRepL;
    @FXML Label technicianL;
    @FXML Label projectManagerL;
    @FXML Label projectAddressL;
    @FXML Label projectCityL;
    @FXML Label projectProvinceL;
    @FXML Label projectPostalCodeL;

    @FXML Label buildingNameL;
    @FXML Label specificLocationL;
    @FXML Label locationOfAirSamplesL;

    @FXML Label siteWorkDateL;
    @FXML Label reportDateL;
    @FXML Label onSiteTimeL;
    @FXML Label samplingDateL;
    @FXML Label preAbatementStartL;
    @FXML Label visualAbatementStartL;
    @FXML Label visualAbatementEndL;
    @FXML Label postAbatementDateL;
    @FXML Label siteEndDateL;
    @FXML Label inspectionStartDateL;

    @FXML Label companyNameL;
    @FXML Label companyAddressL;
    @FXML Label companyCityL;
    @FXML Label companyProvinceL;
    @FXML Label companyPostalCodeL;

    @FXML Label clientNameL;
    @FXML Label clientPositionL;
    @FXML Label clientAddressL;
    @FXML Label clientCityL;
    @FXML Label clientProvinceL;
    @FXML Label clientPostalCodeL;

    @FXML TextField projectNameTF;
    @FXML TextField projectNumberTF;
    @FXML TextField SELRepTF;
    @FXML TextField technicianTF;
    @FXML TextField projectManagerTF;
    @FXML TextField projectAddressTF;
    @FXML TextField projectCityTF;
    @FXML ChoiceBox<String> projectProvinceCB;
    @FXML TextField projectPostalCodeTF;

    @FXML TextField buildingNameTF;
    @FXML TextField specificLocationTF;
    @FXML TextField locationOfAirSamplesTF;

    @FXML DatePicker siteWorkDateDP;
    @FXML DatePicker reportDateDP;
    @FXML Spinner<String> onSiteTimeS;
    @FXML DatePicker samplingDateDP;
    @FXML DatePicker preAbatementStartDP;
    @FXML DatePicker visualAbatementStartDP;
    @FXML DatePicker visualAbatementEndDP;
    @FXML DatePicker postAbatementDateDP;
    @FXML DatePicker siteEndDateDP;
    @FXML DatePicker inspectionStartDateDP;

    @FXML ComboBox companyNameCB;
    @FXML TextField companyAddressTF;
    @FXML TextField companyCityTF;
    @FXML ChoiceBox<String> companyProvinceCB;
    @FXML TextField companyPostalCodeTF;

    @FXML ChoiceBox<String> clientPrefixCB;
    @FXML TextField clientNameTF;
    @FXML TextField clientPositionTF;
    @FXML TextField clientAddressTF;
    @FXML TextField clientCityTF;
    @FXML ChoiceBox<String> clientProvinceCB;
    @FXML TextField clientPostalCodeTF;

    ObservableList<String> companyNames = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientProvinceCB.getItems().addAll("NL","PE","NS","NB","QC","ON","MB","SK","AB","BC","YT","NT","NU");
        clientProvinceCB.setValue("--");

        companyProvinceCB.getItems().addAll("NL","PE","NS","NB","QC","ON","MB","SK","AB","BC","YT","NT","NU");
        companyProvinceCB.setValue("--");

        projectProvinceCB.getItems().addAll("NL","PE","NS","NB","QC","ON","MB","SK","AB","BC","YT","NT","NU");
        projectProvinceCB.setValue("--");

        try {
            Scanner r = new Scanner(getClass().getResourceAsStream("/data/companyNames.txt"));
            while (r.hasNextLine()) {
                companyNames.addAll(r.nextLine());
            }
            r.close();
            companyNameCB.setItems(companyNames);
            companyNameCB.setEditable(true);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        companyNameCB.valueProperty().addListener((observable,oldValue,newValue)->{
            if(newValue.equals("Home Owner")) {
                projectAddressTF.setText(companyAddressTF.getText());
                projectCityTF.setText(companyCityTF.getText());
                projectProvinceCB.setValue(companyProvinceCB.getValue());

                projectAddressTF.setDisable(true);
                projectCityTF.setDisable(true);
                projectProvinceCB.setDisable(true);
            }
            else {
                projectAddressTF.setDisable(false);
                projectCityTF.setDisable(false);
                projectProvinceCB.setDisable(false);
            }
        });

    }

    public void getReport(Report selectedReport){
        report = selectedReport;

        projectNumberTF.setText(selectedReport.info.getProjectNumber());
        SELRepTF.setText(selectedReport.info.getSelRep());
        technicianTF.setText(selectedReport.info.getTechnician());
        projectManagerTF.setText(selectedReport.info.getProjectManager());
        projectAddressTF.setText(selectedReport.info.getProjectAddress());
        projectCityTF.setText(selectedReport.info.getProjectCity());
        projectProvinceCB.setValue(selectedReport.info.getProjectProvince());
        projectPostalCodeTF.setText(selectedReport.info.getProjectPostalCode());

        buildingNameTF.setText(selectedReport.info.getBuildingName());
        specificLocationTF.setText(selectedReport.info.getSpecificLocation());
        locationOfAirSamplesTF.setText(selectedReport.info.getLocationOfAirSamples());

        companyNameCB.setValue(selectedReport.info.getCompanyName());
        companyAddressTF.setText(selectedReport.info.getCompanyAddress());
        companyCityTF.setText(selectedReport.info.getCompanyCity());
        companyProvinceCB.setValue(selectedReport.info.getCompanyProvince());
        companyPostalCodeTF.setText(selectedReport.info.getCompanyPostalCode());

        clientNameTF.setText(selectedReport.info.getClientName());
        clientPositionTF.setText(selectedReport.info.getClientPosition());
        clientAddressTF.setText(selectedReport.info.getClientAddress());
        clientCityTF.setText(selectedReport.info.getClientCity());
        clientProvinceCB.setValue(selectedReport.info.getClientProvince());
        clientPostalCodeTF.setText(selectedReport.info.getClientPostalCode());

        if(selectedReport.info.isProjectNumberExist()) {
            projectNumberTF.setDisable(false);
        }
        else{
            projectNumberTF.setText("Not required");
        }

        if(selectedReport.info.isProjectNameExist()) {
            projectNameTF.setDisable(false);
        }
        else{
            projectNameTF.setText("Not required");
        }

        if(selectedReport.info.isProjectAddressExist()) {
            projectAddressTF.setDisable(false);
        }
        else{
            projectAddressTF.setText("Not required");
        }

        if(selectedReport.info.isProjectCityExist()){
            projectCityTF.setDisable(false);
        }
        else{
            projectCityTF.setText("Not required");
        }

        if(selectedReport.info.isProjectProvinceExist()){
            projectProvinceCB.setDisable(false);
        }
        else{
            projectProvinceCB.setValue("Not required");
        }

        if(selectedReport.info.isProjectPostalCodeExist()){
            projectPostalCodeTF.setDisable(false);
        }
        else{
            projectPostalCodeTF.setText("Not required");
        }

        if(selectedReport.info.isTechnicianExist()){
            technicianTF.setDisable(false);
        }
        else{
            technicianTF.setText("Not required");
        }

        if(selectedReport.info.isProjectManagerExist()){
            projectManagerTF.setDisable(false);
        }
        else{
            projectManagerTF.setText("Not required");
        }

        if(selectedReport.info.isClientNameExist()){
            clientNameTF.setDisable(false);
            clientPrefixCB.setDisable(false);
        }
        else{
            clientNameTF.setText("Not required");
        }

        if(selectedReport.info.isClientPositionExist()){
            clientPositionTF.setDisable(false);
        }
        else{
            clientPositionTF.setText("Not required");
        }

        if(selectedReport.info.isCompanyNameExist()){
            companyNameCB.setDisable(false);
        }
        else{
            companyNameCB.setValue("Not Required");
        }

        if(selectedReport.info.isCompanyAddressExist()){
            companyAddressTF.setDisable(false);
        }
        else{
            companyAddressTF.setText("Not required");
        }

        if(selectedReport.info.isCompanyCityExist()){
            companyCityTF.setDisable(false);
        }
        else{
            companyCityTF.setText("Not required");
        }

        if(selectedReport.info.isCompanyProvinceExist()){
            companyProvinceCB.setDisable(false);
        }
        else{
            companyProvinceCB.setValue("Not required");
        }

        if(selectedReport.info.isCompanyPostalCodeExist()){
            companyPostalCodeTF.setDisable(false);
        }
        else{
            companyPostalCodeTF.setText("Not required");
        }

        if(selectedReport.info.isBuildingNameExist()){
            buildingNameTF.setDisable(false);
        }
        else{
            buildingNameTF.setText("Not required");
        }

        if(selectedReport.info.isSpecificLocationExist()){
            specificLocationTF.setDisable(false);
        }
        else{
            specificLocationTF.setText("Not required");
        }

        if(selectedReport.info.isSiteWorkDateExist()){
            siteWorkDateDP.setDisable(false);
        }

        if(selectedReport.info.isReportDateExist()){
            reportDateDP.setDisable(false);
        }

        if(selectedReport.info.isPreAbatementStartDateExist()){
            preAbatementStartDP.setDisable(false);
        }

        if(selectedReport.info.isVisualAbatementStartExist()){
            visualAbatementStartDP.setDisable(false);
        }

        if(selectedReport.info.isVisualAbatementEndExist()){
            visualAbatementEndDP.setDisable(false);
        }

        if(selectedReport.info.isPostAbatementDateExist()){
            postAbatementDateDP.setDisable(false);
        }

        if(selectedReport.info.isSiteEndDateExist()){
            siteEndDateDP.setDisable(false);
        }

        if(selectedReport.info.isSelRepExist()){
            SELRepTF.setDisable(false);
        }
        else{
            SELRepTF.setText("Not required");
        }

        if(selectedReport.info.isOnSiteTimeExist()){
            onSiteTimeS.setDisable(false);
        }

        if(selectedReport.info.isClientAddressExist()){
            clientAddressTF.setDisable(false);
        }
        else{
            clientAddressTF.setText("Not required");
        }

        if(selectedReport.info.isClientCityExist()){
            clientCityTF.setDisable(false);
        }
        else{
            clientCityTF.setText("Not required");
        }

        if(selectedReport.info.isClientProvinceExist()){
            clientProvinceCB.setDisable(false);
        }
        else{
            clientProvinceCB.setValue("Not required");
        }

        if(selectedReport.info.isClientPostalCodeExist()){
            clientPostalCodeTF.setDisable(false);
        }
        else{
            clientPostalCodeTF.setText("Not required");
        }

        if(selectedReport.info.isLocationOfAirSamplesExist()){
            locationOfAirSamplesTF.setDisable(false);
        }
        else{
            locationOfAirSamplesTF.setText("Not required");
        }

        if(selectedReport.info.isInspectionStartDateExist()){
            inspectionStartDateDP.setDisable(false);
        }

        if(selectedReport.info.isSamplingDateExist()){
            samplingDateDP.setDisable(false);
        }

    }
    public void changeReports(ActionEvent event) {
        Alert confirmChange = new Alert(Alert.AlertType.WARNING);
        confirmChange.setTitle("Report Generator");
        confirmChange.setHeaderText("Change Report Type");
        confirmChange.setContentText("Are you sure you want to change reports? All entered data will be lost");
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType cancelButton = new ButtonType("Cancel");
        confirmChange.getButtonTypes().setAll(yesButton, cancelButton);
        Optional<ButtonType> result = confirmChange.showAndWait();
        if(result.get()==yesButton) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ReportTypeMaker.fxml"));
                Parent root = (Parent) loader.load();

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            return;
        }
    }
}
