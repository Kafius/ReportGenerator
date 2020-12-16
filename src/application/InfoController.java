package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class InfoController implements Initializable {
    Report report;

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
    @FXML ComboBox<String> companyProvinceCB;
    @FXML TextField companyPostalCodeTF;

    @FXML ChoiceBox<String> clientPrefixCB;
    @FXML TextField clientNameTF;
    @FXML TextField clientPositionTF;
    @FXML TextField clientAddressTF;
    @FXML TextField clientCityTF;
    @FXML ChoiceBox<String> clientProvinceCB;
    @FXML TextField clientPostalCodeTF;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        projectNumberTF.setDisable(true);
        SELRepTF.setDisable(true);
        technicianTF.setDisable(true);
        projectManagerTF.setDisable(true);
        projectAddressTF.setDisable(true);
        projectCityTF.setDisable(true);
        projectProvinceCB.setDisable(true);
        projectPostalCodeTF.setDisable(true);

        buildingNameTF.setDisable(true);
        specificLocationTF.setDisable(true);
        locationOfAirSamplesTF.setDisable(true);

        siteWorkDateDP.setDisable(true);
        reportDateDP.setDisable(true);
        onSiteTimeS.setDisable(true);
        samplingDateDP.setDisable(true);
        preAbatementStartDP.setDisable(true);
        visualAbatementStartDP.setDisable(true);
        visualAbatementEndDP.setDisable(true);
        postAbatementDateDP.setDisable(true);
        siteEndDateDP.setDisable(true);
        inspectionStartDateDP.setDisable(true);

        companyNameCB.setDisable(true);
        companyAddressTF.setDisable(true);
        companyCityTF.setDisable(true);
        companyProvinceCB.setDisable(true);
        companyPostalCodeTF.setDisable(true);

        clientPrefixCB.setDisable(true);
        clientNameTF.setDisable(true);
        clientPositionTF.setDisable(true);
        clientAddressTF.setDisable(true);
        clientCityTF.setDisable(true);
        clientProvinceCB.setDisable(true);
        clientPostalCodeTF.setDisable(true);
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
        if(selectedReport.info.isProjectNameExist()) {
            projectNameTF.setDisable(false);
        }
        if(selectedReport.info.isProjectAddressExist()) {
            projectAddressTF.setDisable(false);
        }
        if(selectedReport.info.isProjectCityExist()){
            projectCityTF.setDisable(false);
        }
        if(selectedReport.info.isProjectProvinceExist()){
            projectProvinceCB.setDisable(false);
        }
        if(selectedReport.info.isProjectPostalCodeExist()){
            projectPostalCodeTF.setDisable(false);
        }
        if(selectedReport.info.isTechnicianExist()){
            technicianTF.setDisable(false);
        }
        if(selectedReport.info.isProjectManagerExist()){
            projectManagerTF.setDisable(false);
        }
        if(selectedReport.info.isClientNameExist()){
            clientNameTF.setDisable(false);
            clientPrefixCB.setDisable(false);
        }
        if(selectedReport.info.isClientPositionExist()){
            clientPositionTF.setDisable(false);
        }
        if(selectedReport.info.isCompanyNameExist()){
            companyNameCB.setDisable(false);
        }
        if(selectedReport.info.isCompanyAddressExist()){
            companyAddressTF.setDisable(false);
        }
        if(selectedReport.info.isCompanyCityExist()){
            companyCityTF.setDisable(false);
        }
        if(selectedReport.info.isCompanyProvinceExist()){
            companyProvinceCB.setDisable(false);
        }
        if(selectedReport.info.isCompanyPostalCodeExist()){
            companyPostalCodeTF.setDisable(false);
        }
        if(selectedReport.info.isBuildingNameExist()){
            buildingNameTF.setDisable(false);
        }
        if(selectedReport.info.isSpecificLocationExist()){
            specificLocationTF.setDisable(false);
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
        if(selectedReport.info.isOnSiteTimeExist()){
            onSiteTimeS.setDisable(false);
        }
        if(selectedReport.info.isClientAddressExist()){
            clientAddressTF.setDisable(false);
        }
        if(selectedReport.info.isClientCityExist()){
            clientCityTF.setDisable(false);
        }
        if(selectedReport.info.isClientProvinceExist()){
            clientProvinceCB.setDisable(false);
        }
        if(selectedReport.info.isClientPostalCodeExist()){
            clientPostalCodeTF.setDisable(false);
        }
        if(selectedReport.info.isLocationOfAirSamplesExist()){
            locationOfAirSamplesTF.setDisable(false);
        }
        if(selectedReport.info.isInspectionStartDateExist()){
            inspectionStartDateDP.setDisable(false);
        }
        if(selectedReport.info.isSamplingDateExist()){
            samplingDateDP.setDisable(false);
        }

    }
}
