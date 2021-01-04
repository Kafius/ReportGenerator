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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

    final int textfieldSize = 240;
    final int translateX = 10;

    Label projectNameL = new Label("Project Name");

    Label projectNumberL= new Label("Project Number");
    Label SELRepL= new Label("SEL Rep");
    Label technicianL= new Label("Technician");
    Label projectManagerL= new Label("Project Manager");
    Label projectAddressL= new Label("Address");
    Label projectCityL= new Label("City");
    Label projectProvinceL= new Label("Province");
    Label projectPostalCodeL= new Label("Postal Code");

    Label buildingNameL= new Label("Building Name");
    Label specificLocationL= new Label("Specific Location");

    Label siteWorkDateL= new Label("Site Work Date");
    Label reportDateL= new Label("Report Date");
    Label onSiteTimeL= new Label("On Site Time");
    Label samplingDateL= new Label("Sampling Date");
    Label preAbatementStartL= new Label("Pre Abatement Start Date");
    Label visualAbatementStartL= new Label("Visual Abatement Start Date");
    Label visualAbatementEndL= new Label("Visual Abatement End Date");
    Label postAbatementDateL= new Label("Post Abatement Date");
    Label siteEndDateL= new Label("Site End Date");
    Label inspectionStartDateL= new Label("Inspection Start Date");

    Label companyNameL= new Label("Name");
    Label companyAddressL= new Label("Address");
    Label companyCityL= new Label("City");
    Label companyProvinceL= new Label("Province");
    Label companyPostalCodeL= new Label("Postal Code");

    Label clientNameL= new Label("Name");
    Label clientPositionL= new Label("Position");
    Label clientAddressL= new Label("Address");
    Label clientCityL= new Label("City");
    Label clientProvinceL= new Label("Province");
    Label clientPostalCodeL= new Label("Postal Code");

    TextField projectNameTF = new TextField();
    TextField projectNumberTF= new TextField();
    TextField SELRepTF= new TextField();
    TextField technicianTF= new TextField();
    TextField projectManagerTF= new TextField();
    TextField projectAddressTF= new TextField();
    TextField projectCityTF= new TextField();
    ChoiceBox<String> projectProvinceCB = new ChoiceBox<>();
    TextField projectPostalCodeTF= new TextField();

    TextField buildingNameTF= new TextField();
    TextField specificLocationTF= new TextField();

    DatePicker siteWorkDateDP = new DatePicker();
    DatePicker reportDateDP = new DatePicker();
    Spinner<String> onSiteTimeS = new Spinner<>(0,100,0,1);
    DatePicker samplingDateDP = new DatePicker();
    DatePicker preAbatementStartDP = new DatePicker();
    DatePicker visualAbatementStartDP = new DatePicker();
    DatePicker visualAbatementEndDP = new DatePicker();
    DatePicker postAbatementDateDP = new DatePicker();
    DatePicker siteEndDateDP = new DatePicker();
    DatePicker inspectionStartDateDP = new DatePicker();

    ComboBox<String> companyNameCB = new ComboBox<>();
    TextField companyAddressTF= new TextField();
    TextField companyCityTF= new TextField();
    ChoiceBox<String> companyProvinceCB = new ChoiceBox<>();
    TextField companyPostalCodeTF= new TextField();

    ChoiceBox<String> clientPrefixCB = new ChoiceBox<>();
    TextField clientNameTF= new TextField();
    TextField clientPositionTF= new TextField();
    TextField clientAddressTF= new TextField();
    TextField clientCityTF= new TextField();
    ChoiceBox<String> clientProvinceCB = new ChoiceBox<>();
    TextField clientPostalCodeTF= new TextField();

    @FXML GridPane projectInfoBox;
    int projectRow = 0;
    int projectColumn = 0;

    @FXML GridPane locationInfoBox;
    int locationRow = 0;
    int locationColumn = 0;

    @FXML GridPane timeDateInfoBox;
    int timeDateRow = 0;
    int timeDateColumn = 0;
    @FXML GridPane companyInfoBox;
    int companyRow = 0;
    int companyColumn = 0;

    @FXML GridPane clientInfoBox;
    int clientRow = 0;
    int clientColumn = 0;

    ObservableList<String> companyNames = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        projectNameTF.setMaxWidth(textfieldSize);
        projectNumberTF.setMaxWidth(textfieldSize);
        SELRepTF.setMaxWidth(textfieldSize);
        technicianTF.setMaxWidth(textfieldSize);
        projectManagerTF.setMaxWidth(textfieldSize);
        projectAddressTF.setMaxWidth(textfieldSize);
        projectCityTF.setMaxWidth(textfieldSize);
        projectProvinceCB.setMaxWidth(textfieldSize);
        projectPostalCodeTF.setMaxWidth(textfieldSize);

        buildingNameTF.setMaxWidth(textfieldSize);
        specificLocationTF.setMaxWidth(textfieldSize);

        siteWorkDateDP.setMaxWidth(textfieldSize);
        reportDateDP.setMaxWidth(textfieldSize);
        samplingDateDP.setMaxWidth(textfieldSize);
        preAbatementStartDP.setMaxWidth(textfieldSize);
        visualAbatementStartDP.setMaxWidth(textfieldSize);
        visualAbatementEndDP.setMaxWidth(textfieldSize);
        postAbatementDateDP.setMaxWidth(textfieldSize);
        siteEndDateDP.setMaxWidth(textfieldSize);
        inspectionStartDateDP.setMaxWidth(textfieldSize);

        companyNameCB.setMaxWidth(textfieldSize);
        companyAddressTF.setMaxWidth(textfieldSize);
        companyCityTF.setMaxWidth(textfieldSize);
        companyProvinceCB.setMaxWidth(textfieldSize);
        companyPostalCodeTF.setMaxWidth(textfieldSize);

        clientPrefixCB.setMaxWidth(textfieldSize);
        clientNameTF.setMaxWidth(textfieldSize);
        clientPositionTF.setMaxWidth(textfieldSize);
        clientAddressTF.setMaxWidth(textfieldSize);
        clientCityTF.setMaxWidth(textfieldSize);
        clientProvinceCB.setMaxWidth(textfieldSize);
        clientPostalCodeTF.setMaxWidth(textfieldSize);

        projectNameL.setTranslateX(translateX);

        projectNumberL.setTranslateX(translateX);
        SELRepL.setTranslateX(translateX);
        technicianL.setTranslateX(translateX);
        projectManagerL.setTranslateX(translateX);
        projectAddressL.setTranslateX(translateX);
        projectCityL.setTranslateX(translateX);
        projectProvinceL.setTranslateX(translateX);
        projectPostalCodeL.setTranslateX(translateX);

        buildingNameL.setTranslateX(translateX);
        specificLocationL.setTranslateX(translateX);

        siteWorkDateL.setTranslateX(translateX);
        reportDateL.setTranslateX(translateX);
        onSiteTimeL.setTranslateX(translateX);
        samplingDateL.setTranslateX(translateX);
        preAbatementStartL.setTranslateX(translateX);
        visualAbatementStartL.setTranslateX(translateX);
        visualAbatementEndL.setTranslateX(translateX);
        postAbatementDateL.setTranslateX(translateX);
        siteEndDateL.setTranslateX(translateX);
        inspectionStartDateL.setTranslateX(translateX);

        companyNameL.setTranslateX(translateX);
        companyAddressL.setTranslateX(translateX);
        companyCityL.setTranslateX(translateX);
        companyProvinceL.setTranslateX(translateX);
        companyPostalCodeL.setTranslateX(translateX);

        clientNameL.setTranslateX(translateX);
        clientPositionL.setTranslateX(translateX);
        clientAddressL.setTranslateX(translateX);
        clientCityL.setTranslateX(translateX);
        clientProvinceL.setTranslateX(translateX);
        clientPostalCodeL.setTranslateX(translateX);

        clientProvinceCB.getItems().addAll("Newfoundland and Labrador","Prince Edward Island","Nova Scotia","New Brunswick","Quebec","Ontario","Manitoba","Saskatchewan","Alberta","British Columbia");
        clientProvinceCB.setValue("--");

        companyProvinceCB.getItems().addAll("Newfoundland and Labrador","Prince Edward Island","Nova Scotia","New Brunswick","Quebec","Ontario","Manitoba","Saskatchewan","Alberta","British Columbia");
        companyProvinceCB.setValue("--");

        projectProvinceCB.getItems().addAll("Newfoundland and Labrador","Prince Edward Island","Nova Scotia","New Brunswick","Quebec","Ontario","Manitoba","Saskatchewan","Alberta","British Columbia");
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
        if(report.info.isProjectNumberExist()){
            projectNumberTF.setText(selectedReport.info.getProjectNumber());
        }
        if(report.info.isSelRepExist()){
            SELRepTF.setText(selectedReport.info.getSelRep());
        }
        if(report.info.isTechnicianExist()) {
            technicianTF.setText(selectedReport.info.getTechnician());
        }
        if(report.info.isProjectManagerExist()){
            projectManagerTF.setText(selectedReport.info.getProjectManager());
        }

        if(report.info.isProjectAddressExist()){
            projectAddressTF.setText(selectedReport.info.getProjectAddress());
        }

        if(report.info.isProjectCityExist()){
            projectCityTF.setText(selectedReport.info.getProjectCity());
        }

        if(report.info.isProjectProvinceExist()){
            projectProvinceCB.setValue(selectedReport.info.getProjectProvince());
        }

        if(report.info.isProjectPostalCodeExist()){
            projectPostalCodeTF.setText(selectedReport.info.getProjectPostalCode());
        }

        if(report.info.isBuildingNameExist()){
            buildingNameTF.setText(selectedReport.info.getBuildingName());
        }

        if(report.info.isSpecificLocationExist()){
            specificLocationTF.setText(selectedReport.info.getSpecificLocation());
        }

        if(report.info.isCompanyNameExist()){
            companyNameCB.setValue(selectedReport.info.getCompanyName());
        }

        if(report.info.isCompanyAddressExist()){
            companyAddressTF.setText(selectedReport.info.getCompanyAddress());
        }

        if(report.info.isCompanyCityExist()){
            companyCityTF.setText(selectedReport.info.getCompanyCity());
        }

        if(report.info.isCompanyProvinceExist()){
            companyProvinceCB.setValue(selectedReport.info.getCompanyProvince());
        }

        if(report.info.isCompanyPostalCodeExist()){
            companyPostalCodeTF.setText(selectedReport.info.getCompanyPostalCode());
        }

        if(report.info.isClientNameExist()){
            clientNameTF.setText(selectedReport.info.getClientName());
        }

        if(report.info.isClientPositionExist()){
            clientPositionTF.setText(selectedReport.info.getClientPosition());
        }
        if(report.info.isClientAddressExist()) {
            clientAddressTF.setText(selectedReport.info.getClientAddress());
        }
        if(report.info.isClientCityExist()) {
            clientCityTF.setText(selectedReport.info.getClientCity());
        }
        if(report.info.isClientPositionExist()) {
            clientProvinceCB.setValue(selectedReport.info.getClientProvince());
        }
        if(report.info.isClientPositionExist()) {
            clientPostalCodeTF.setText(selectedReport.info.getClientPostalCode());
        }
        if(report.info.isProjectNumberExist()) {
            projectInfoBox.add(projectNumberL,projectColumn,projectRow);
            projectColumn++;

            projectInfoBox.add(projectNumberTF,projectColumn,projectRow);
            projectColumn++;

            if(projectColumn==4){
                projectColumn=0;
                projectRow++;
            }

        }

        if(report.info.isProjectNameExist()) {
            projectInfoBox.add(projectNameL,projectColumn,projectRow);
            projectColumn++;

            projectInfoBox.add(projectNameTF,projectColumn,projectRow);
            projectColumn++;

            if(projectColumn==4){
                projectColumn=0;
                projectRow++;
            }
        }

        if(report.info.isProjectAddressExist()) {
            projectInfoBox.add(projectAddressL,projectColumn,projectRow);
            projectColumn++;

            projectInfoBox.add(projectAddressTF,projectColumn,projectRow);
            projectColumn++;

            if(projectColumn==4){
                projectColumn=0;
                projectRow++;
            }
        }

        if(report.info.isProjectCityExist()){
            projectInfoBox.add(projectCityL,projectColumn,projectRow);
            projectColumn++;

            projectInfoBox.add(projectCityTF,projectColumn,projectRow);
            projectColumn++;

            if(projectColumn==4){
                projectColumn=0;
                projectRow++;
            }
        }

        if(report.info.isProjectProvinceExist()){
            projectInfoBox.add(projectProvinceL,projectColumn,projectRow);
            projectColumn++;

            projectInfoBox.add(projectProvinceCB,projectColumn,projectRow);
            projectColumn++;

            if(projectColumn==4){
                projectColumn=0;
                projectRow++;
            }
        }

        if(report.info.isProjectPostalCodeExist()){
            projectInfoBox.add(projectPostalCodeL,projectColumn,projectRow);
            projectColumn++;

            projectInfoBox.add(projectPostalCodeTF,projectColumn,projectRow);
            projectColumn++;

            if(projectColumn==4){
                projectColumn=0;
                projectRow++;
            }
        }

        if(report.info.isTechnicianExist()){
            projectInfoBox.add(technicianL,projectColumn,projectRow);
            projectColumn++;

            projectInfoBox.add(technicianTF,projectColumn,projectRow);
            projectColumn++;

            if(projectColumn==4){
                projectColumn=0;
                projectRow++;
            }
        }

        if(report.info.isProjectManagerExist()){
            projectInfoBox.add(projectManagerL,projectColumn,projectRow);
            projectColumn++;

            projectInfoBox.add(projectManagerTF,projectColumn,projectRow);
            projectColumn++;

            if(projectColumn==4){
                projectColumn=0;
                projectRow++;
            }
        }

        if(report.info.isClientNameExist()){
            clientInfoBox.add(clientNameL,clientColumn,clientRow);
            clientColumn++;

            clientInfoBox.add(clientNameTF,clientColumn,clientRow);
            clientColumn++;

            if(clientColumn==4){
                clientColumn=0;
                clientRow++;
            }
        }

        if(report.info.isClientPositionExist()){
            clientInfoBox.add(clientPositionL,clientColumn,clientRow);
            clientColumn++;

            clientInfoBox.add(clientPositionTF,clientColumn,clientRow);
            clientColumn++;

            if(clientColumn==4){
                clientColumn=0;
                clientRow++;
            }
        }

        if(report.info.isCompanyNameExist()){
            companyInfoBox.add(companyNameL,companyColumn,companyRow);
            companyColumn++;

            companyInfoBox.add(companyNameCB,companyColumn,companyRow);
            companyColumn++;

            if(companyColumn==4){
                companyColumn=0;
                companyRow++;
            }
        }

        if(report.info.isCompanyAddressExist()){
            companyInfoBox.add(companyAddressL,companyColumn,companyRow);
            companyColumn++;

            companyInfoBox.add(companyAddressTF,companyColumn,companyRow);
            companyColumn++;

            if(companyColumn==4){
                companyColumn=0;
                companyRow++;
            }
        }

        if(report.info.isCompanyCityExist()){
            companyInfoBox.add(companyCityL,companyColumn,companyRow);
            companyColumn++;

            companyInfoBox.add(companyCityTF,companyColumn,companyRow);
            companyColumn++;

            if(companyColumn==4){
                companyColumn=0;
                companyRow++;
            }
        }

        if(report.info.isCompanyProvinceExist()){
            companyInfoBox.add(companyProvinceL,companyColumn,companyRow);
            companyColumn++;

            companyInfoBox.add(companyProvinceCB,companyColumn,companyRow);
            companyColumn++;

            if(companyColumn==4){
                companyColumn=0;
                companyRow++;
            }
        }

        if(report.info.isCompanyPostalCodeExist()){
            companyInfoBox.add(companyPostalCodeL,companyColumn,companyRow);
            companyColumn++;

            companyInfoBox.add(companyPostalCodeTF,companyColumn,companyRow);
            companyColumn++;

            if(companyColumn==4){
                companyColumn=0;
                companyRow++;
            }
        }

        if(report.info.isBuildingNameExist()){
            locationInfoBox.add(buildingNameL,locationColumn,locationRow);
            locationColumn++;

            locationInfoBox.add(buildingNameTF,locationColumn,locationRow);
            locationColumn++;

            if(locationColumn==4){
                locationColumn=0;
                locationRow++;
            }
        }

        if(report.info.isSpecificLocationExist()){
            locationInfoBox.add(specificLocationL,locationColumn,locationRow);
            locationColumn++;

            locationInfoBox.add(specificLocationTF,locationColumn,locationRow);
            locationColumn++;

            if(locationColumn==4){
                locationColumn=0;
                locationRow++;
            }
        }

        if(report.info.isSiteWorkDateExist()){
            timeDateInfoBox.add(siteWorkDateL,timeDateColumn,timeDateRow);
            timeDateColumn++;

            timeDateInfoBox.add(siteWorkDateDP,timeDateColumn,timeDateRow);
            timeDateColumn++;

            if(timeDateColumn==4){
                timeDateColumn=0;
                timeDateRow++;
            }
        }

        if(report.info.isReportDateExist()){
            timeDateInfoBox.add(reportDateL,timeDateColumn,timeDateRow);
            timeDateColumn++;

            timeDateInfoBox.add(reportDateDP,timeDateColumn,timeDateRow);
            timeDateColumn++;

            if(timeDateColumn==4){
                timeDateColumn=0;
                timeDateRow++;
            }
        }

        if(report.info.isPreAbatementStartDateExist()){
            timeDateInfoBox.add(preAbatementStartL,timeDateColumn,timeDateRow);
            timeDateColumn++;

            timeDateInfoBox.add(preAbatementStartDP,timeDateColumn,timeDateRow);
            timeDateColumn++;

            if(timeDateColumn==4){
                timeDateColumn=0;
                timeDateRow++;
            }
        }

        if(report.info.isVisualAbatementStartExist()){
            timeDateInfoBox.add(visualAbatementStartL,timeDateColumn,timeDateRow);
            timeDateColumn++;

            timeDateInfoBox.add(visualAbatementStartDP,timeDateColumn,timeDateRow);
            timeDateColumn++;

            if(timeDateColumn==4){
                timeDateColumn=0;
                timeDateRow++;
            }
        }

        if(selectedReport.info.isVisualAbatementEndExist()){
            timeDateInfoBox.add(visualAbatementEndL,timeDateColumn,timeDateRow);
            timeDateColumn++;

            timeDateInfoBox.add(visualAbatementEndDP,timeDateColumn,timeDateRow);
            timeDateColumn++;

            if(timeDateColumn==4){
                timeDateColumn=0;
                timeDateRow++;
            }
        }

        if(report.info.isPostAbatementDateExist()){
            timeDateInfoBox.add(postAbatementDateL,timeDateColumn,timeDateRow);
            timeDateColumn++;

            timeDateInfoBox.add(postAbatementDateDP,timeDateColumn,timeDateRow);
            timeDateColumn++;

            if(timeDateColumn==4){
                timeDateColumn=0;
                timeDateRow++;
            }
        }

        if(report.info.isSiteEndDateExist()){
            timeDateInfoBox.add(siteEndDateL,timeDateColumn,timeDateRow);
            timeDateColumn++;

            timeDateInfoBox.add(siteEndDateDP,timeDateColumn,timeDateRow);
            timeDateColumn++;

            if(timeDateColumn==4){
                timeDateColumn=0;
                timeDateRow++;
            }
        }

        if(report.info.isSelRepExist()){
            projectInfoBox.add(SELRepL,projectColumn,projectRow);
            projectColumn++;

            projectInfoBox.add(SELRepTF,projectColumn,projectRow);
            projectColumn++;

            if(projectColumn==4){
                projectColumn=0;
                projectRow++;
            }
        }

        if(report.info.isOnSiteTimeExist()){
            timeDateInfoBox.add(onSiteTimeL,timeDateColumn,timeDateRow);
            timeDateColumn++;

            timeDateInfoBox.add(onSiteTimeS,timeDateColumn,timeDateRow);
            timeDateColumn++;
            if(clientColumn==4){
                clientColumn=0;
                clientRow++;
            }
        }

        if(report.info.isClientAddressExist()){
            clientInfoBox.add(clientAddressL,clientColumn,clientRow);
            clientColumn++;

            clientInfoBox.add(clientAddressTF,clientColumn,clientRow);
            clientColumn++;

            if(clientColumn==4){
                clientColumn=0;
                clientRow++;
            }
        }

        if(report.info.isClientCityExist()){
            clientInfoBox.add(clientCityL,clientColumn,clientRow);
            clientColumn++;

            clientInfoBox.add(clientCityTF,clientColumn,clientRow);
            clientColumn++;

            if(clientColumn==4){
                clientColumn=0;
                clientRow++;
            }
        }

        if(report.info.isClientProvinceExist()){
            clientInfoBox.add(clientProvinceL,clientColumn,clientRow);
            clientColumn++;

            clientInfoBox.add(clientProvinceCB,clientColumn,clientRow);
            clientColumn++;

            if(clientColumn==4){
                clientColumn=0;
                clientRow++;
            }
        }

        if(report.info.isClientPostalCodeExist()){
            clientInfoBox.add(clientPostalCodeL,clientColumn,clientRow);
            clientColumn++;

            clientInfoBox.add(clientPostalCodeTF,clientColumn,clientRow);
            clientColumn++;

            if(clientColumn==4){
                clientColumn=0;
                clientRow++;
            }
        }

        if(selectedReport.info.isInspectionStartDateExist()){
            timeDateInfoBox.add(inspectionStartDateL,timeDateColumn,timeDateRow);
            timeDateColumn++;

            timeDateInfoBox.add(inspectionStartDateDP,timeDateColumn,timeDateRow);
            timeDateColumn++;

            if(timeDateColumn==4){
                timeDateColumn=0;
                timeDateRow++;
            }
        }

        if(selectedReport.info.isSamplingDateExist()){
            timeDateInfoBox.add(samplingDateL,timeDateColumn,timeDateRow);
            timeDateColumn++;

            timeDateInfoBox.add(samplingDateDP,timeDateColumn,timeDateRow);
            timeDateColumn++;

            if(timeDateColumn==4){
                timeDateColumn=0;
                timeDateRow++;
            }
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
            if(report.info.isCompanyNameExist()){
                if(companyNameCB.getValue().equals("Home Owner")){
                    if(report.info.isCompanyAddressExist()&&report.info.isProjectAddressExist()){
                        projectAddressTF.setText(companyAddressTF.getText());
                    }
                    if(report.info.isCompanyCityExist()&&report.info.isProjectCityExist()){
                        projectCityTF.setText(companyAddressTF.getText());
                    }
                    if(report.info.isCompanyProvinceExist()&&report.info.isProjectProvinceExist()){
                        projectProvinceCB.setValue(companyProvinceCB.getValue());
                    }
                }
            }

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
        if(report.getReportType().equals("AirTesting1")){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AT1Maker.fxml"));
                Parent root = (Parent) loader.load();

                GenerateAT1Controller controller = loader.getController();
                controller.getReport(report);

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ReportMaker.fxml"));
                Parent root = (Parent) loader.load();

                GenerateReportController controller = loader.getController();
                controller.getReport(report);

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
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
            if((!companyNameCB.getValue().equals("Home Owner")&&
                    (report.info.isProjectAddressExist()&&projectAddressTF.getText().equals(""))||
                    (report.info.isProjectCityExist()&&projectCityTF.getText().equals(""))||
                    (report.info.isProjectProvinceExist()&&projectProvinceCB.getValue().equals("")))){
                return false;
            }
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

        if(report.info.isSiteWorkDateExist()&&siteWorkDateDP.getValue()==null){
            return false;
        }

        if(report.info.isReportDateExist()&&reportDateDP.getValue()==null){
            return false;
        }

        if(report.info.isPreAbatementStartDateExist()&&preAbatementStartDP.getValue()==null){
            return false;
        }

        if(report.info.isVisualAbatementStartExist()&&visualAbatementStartDP.getValue()==null){
            return false;
        }

        if(report.info.isVisualAbatementEndExist()&&visualAbatementEndDP.getValue()==null){
            return false;
        }

        if(report.info.isPostAbatementDateExist()&&postAbatementDateDP.getValue()==null){
            return false;
        }

        if(report.info.isSiteEndDateExist()&&siteEndDateDP.getValue()==null){
            return false;
        }

        if(report.info.isSelRepExist()&&SELRepTF.getText().equals("")){
            return false;
        }

        if(report.info.isOnSiteTimeExist()&&onSiteTimeS.getValue()==null){
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

        if(report.info.isInspectionStartDateExist()&&inspectionStartDateDP.getValue()==null){
            return false;
        }

        if(report.info.isSamplingDateExist()&&samplingDateDP.getValue()==null){
            return false;
        }

        return true;
    }
}
