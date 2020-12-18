package application;

import application.iat.AirTestingController;
import application.iat.PostAirTestingController;
import application.iat.TEMAsbestosFibreController;
import application.walkin.AsbestosController;
import application.walkin.BacteroidesController;
import application.walkin.LeadController;
import application.walkin.MouldGrowthController;
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
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class InfoController implements Initializable {
    Report report;

    @FXML
    AnchorPane ap;

    //buttons for report info
    @FXML private Button changeButton;
    @FXML private Button nextButton;

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

    @FXML ComboBox<String> companyNameCB;
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

    @FXML
    public void submitBasicInfo(ActionEvent event) {
        if(checkTextFields()) {
            if(report.info.isProjectNumberExist()) {
                report.getInfo().setProjectNumber(projectNumberTF.getText());
            }

            if(report.info.isProjectNameExist()) {
                report.getInfo().setProjectName(projectNameTF.getText());
            }

            if(report.info.isProjectAddressExist()) {
                report.getInfo().setProjectAddress(projectAddressTF.getText());
            }

            if(report.info.isProjectCityExist()){
                report.getInfo().setProjectCity(projectCityTF.getText());
            }

            if(report.info.isProjectProvinceExist()){
                report.getInfo().setProjectProvince(projectProvinceCB.getValue());
            }

            if(report.info.isProjectPostalCodeExist()){
                report.getInfo().setProjectPostalCode(projectPostalCodeTF.getText());
            }

            if(report.info.isTechnicianExist()){
                report.getInfo().setTechnician(technicianTF.getText());
            }

            if(report.info.isProjectManagerExist()){
                report.getInfo().setProjectManager(projectManagerTF.getText());
            }

            if(report.info.isClientNameExist()){
                report.getInfo().setClientName(clientNameTF.getText());
            }

            if(report.info.isClientPositionExist()){
                report.getInfo().setClientPosition(clientPositionTF.getText());
            }

            if(report.info.isCompanyNameExist()){
                report.getInfo().setCompanyName(companyNameCB.getValue());
            }

            if(report.info.isCompanyAddressExist()){
                report.getInfo().setCompanyAddress(companyAddressTF.getText());
            }

            if(report.info.isCompanyCityExist()){
                report.getInfo().setCompanyCity(companyCityTF.getText());
            }

            if(report.info.isCompanyProvinceExist()){
                report.getInfo().setCompanyProvince(companyProvinceCB.getValue());
            }

            if(report.info.isCompanyPostalCodeExist()){
                report.getInfo().setCompanyPostalCode(companyPostalCodeTF.getText());
            }

            if(report.info.isBuildingNameExist()){
                report.getInfo().setBuildingName(buildingNameTF.getText());
            }

            if(report.info.isSpecificLocationExist()){
                report.getInfo().setSpecificLocation(specificLocationTF.getText());
            }

            if(report.info.isSiteWorkDateExist()){
                report.getInfo().setSiteWorkDate(siteWorkDateDP.getValue().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
            }

            if(report.info.isReportDateExist()){
                report.getInfo().setReportDate(reportDateDP.getValue().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
            }

            if(report.info.isPreAbatementStartDateExist()){
                report.getInfo().setPreAbatementStartDate(preAbatementStartDP.getValue().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
            }

            if(report.info.isVisualAbatementStartExist()){
                report.getInfo().setVisualAbatementStart(visualAbatementStartDP.getValue().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
            }

            if(report.info.isVisualAbatementEndExist()){
                report.getInfo().setVisualAbatementEnd(visualAbatementEndDP.getValue().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
            }

            if(report.info.isPostAbatementDateExist()){
                report.getInfo().setPostAbatementDate(postAbatementDateDP.getValue().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
            }

            if(report.info.isSiteEndDateExist()){
                report.getInfo().setSiteEndDate(siteEndDateDP.getValue().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
            }

            if(report.info.isSelRepExist()){
                report.getInfo().setSelRep(SELRepTF.getText());
            }

            if(report.info.isOnSiteTimeExist()){
                report.getInfo().setOnSiteTime(onSiteTimeS.getValue());
            }

            if(report.info.isClientAddressExist()){
                report.getInfo().setClientAddress(clientAddressTF.getText());
            }

            if(report.info.isClientCityExist()){
                report.getInfo().setClientCity(clientCityTF.getText());
            }

            if(report.info.isClientProvinceExist()){
                report.getInfo().setClientProvince(clientProvinceCB.getValue());
            }

            if(report.info.isClientPostalCodeExist()){
                report.getInfo().setClientPostalCode(clientPostalCodeTF.getText());
            }

            if(report.info.isLocationOfAirSamplesExist()){
                report.getInfo().setLocationOfAirSamples(locationOfAirSamplesTF.getText());
            }

            if(report.info.isInspectionStartDateExist()){
                report.getInfo().setInspectionStartDate(inspectionStartDateDP.getValue().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
            }

            if(report.info.isSamplingDateExist()){
                report.getInfo().setSamplingDate(samplingDateDP.getValue().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
            }
        }
        else {
            Alert invalidInputs = new Alert(Alert.AlertType.ERROR);
            invalidInputs.setTitle("New Report");
            invalidInputs.setHeaderText("Missing Inputs");
            invalidInputs.setContentText("Fill in all missing entries.");
            ButtonType okayButton = new ButtonType("Okay");
            invalidInputs.getButtonTypes().setAll(okayButton);
            invalidInputs.show();
            return;
        }

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
            report.interfaceCounter++;
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkTextFields() {

        if(report.info.isProjectNumberExist()&&projectNumberTF.getText().equals("")) {
            return false;
        }

        if(report.info.isProjectNameExist()&&projectNameTF.getText().equals("")) {
            return false;
        }

        if(report.info.isProjectAddressExist()&&projectAddressTF.getText().equals("")) {
            return false;
        }

        if(report.info.isProjectCityExist()&&projectCityTF.getText().equals("")){
            return false;
        }

        if(report.info.isProjectProvinceExist()&&projectProvinceCB.getValue().equals("")){
            return false;
        }

        if(report.info.isProjectPostalCodeExist()&&projectPostalCodeTF.getText().equals("")){
            return false;
        }

        if(report.info.isTechnicianExist()&&technicianTF.getText().equals("")){
            return false;
        }

        if(report.info.isProjectManagerExist()&&projectManagerTF.getText().equals("")){
            return false;
        }

        if(report.info.isClientNameExist()&&clientNameTF.getText().equals("")){
            return false;
        }

        if(report.info.isClientPositionExist()&&clientPositionTF.getText().equals("")){
            return false;
        }

        if(report.info.isCompanyNameExist()&&companyNameCB.getValue().equals("")){
            return false;
        }

        if(report.info.isCompanyAddressExist()&&companyAddressTF.getText().equals("")){
            return false;
        }

        if(report.info.isCompanyCityExist()&&companyCityTF.getText().equals("")){
            return false;
        }

        if(report.info.isCompanyProvinceExist()&&companyProvinceCB.getValue().equals("")){
            return false;
        }

        if(report.info.isCompanyPostalCodeExist()&&companyPostalCodeTF.getText().equals("")){
            return false;
        }

        if(report.info.isBuildingNameExist()&&buildingNameTF.getText().equals("")){
            return false;
        }

        if(report.info.isSpecificLocationExist()&&specificLocationTF.getText().equals("")){
            return false;
        }

        if(report.info.isSiteWorkDateExist()&&siteWorkDateDP.getValue().equals("")){
            return false;
        }

        if(report.info.isReportDateExist()&&reportDateDP.getValue().equals("")){
            return false;
        }

        if(report.info.isPreAbatementStartDateExist()&&preAbatementStartDP.getValue().equals("")){
            return false;
        }

        if(report.info.isVisualAbatementStartExist()&&visualAbatementStartDP.getValue().equals("")){
            return false;
        }

        if(report.info.isVisualAbatementEndExist()&&visualAbatementEndDP.getValue().equals("")){
            return false;
        }

        if(report.info.isPostAbatementDateExist()&&postAbatementDateDP.getValue().equals("")){
            return false;
        }

        if(report.info.isSiteEndDateExist()&&siteEndDateDP.getValue().equals("")){
            return false;
        }

        if(report.info.isSelRepExist()&&SELRepTF.getText().equals("")){
            return false;
        }

        if(report.info.isOnSiteTimeExist()&&onSiteTimeS.getValue().equals("")){
            return false;
        }

        if(report.info.isClientAddressExist()&&clientAddressTF.getText().equals("")){
            return false;
        }

        if(report.info.isClientCityExist()&&clientCityTF.getText().equals("")){
            return false;
        }

        if(report.info.isClientProvinceExist()&&clientProvinceCB.getValue().equals("")){
            return false;
        }

        if(report.info.isClientPostalCodeExist()&&clientPostalCodeTF.getText().equals("")){
            return false;
        }

        if(report.info.isLocationOfAirSamplesExist()&&locationOfAirSamplesTF.getText().equals("")){
            return false;
        }

        if(report.info.isInspectionStartDateExist()&&inspectionStartDateDP.getValue().equals("")){
            return false;
        }

        if(report.info.isSamplingDateExist()&&samplingDateDP.getValue().equals("")){
            return false;
        }
        return true;
    }
}
