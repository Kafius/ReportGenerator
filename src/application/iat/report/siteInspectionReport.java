package application.iat.report;

import application.Info;
import application.Report;
import javafx.collections.FXCollections;

import java.util.List;

public class siteInspectionReport extends Report {
    String reportType;
    int negativePressureDifferential;
    int numberOfCAHU;
    int HEPAvacuums;
    List<String> postObservations;
    List<String> postDeficiencies;

    public siteInspectionReport(){
        super("siteInspection");
        this.name="";
        this.info=new Info();
        this.negativePressureDifferential = 0;
        this.numberOfCAHU = 0;
        this.HEPAvacuums = 0;
        this.postObservations = FXCollections.observableArrayList();
        this.postDeficiencies = FXCollections.observableArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public String getReportType() {
        return reportType;
    }

    @Override
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public int getNegativePressureDifferential() {
        return negativePressureDifferential;
    }

    public void setNegativePressureDifferential(int negativePressureDifferential) {
        this.negativePressureDifferential = negativePressureDifferential;
    }

    public int getNumberOfCAHU() {
        return numberOfCAHU;
    }

    public void setNumberOfCAHU(int numberOfCAHU) {
        this.numberOfCAHU = numberOfCAHU;
    }

    public int getHEPAvacuums() {
        return HEPAvacuums;
    }

    public void setHEPAvacuums(int HEPAvacuums) {
        this.HEPAvacuums = HEPAvacuums;
    }

    public List<String> getPostObservations() {
        return postObservations;
    }

    public void setPostObservations(List<String> postObservations) {
        this.postObservations = postObservations;
    }

    public List<String> getPostDeficiencies() {
        return postDeficiencies;
    }

    public void setPostDeficiencies(List<String> postDeficiencies) {
        this.postDeficiencies = postDeficiencies;
    }
}
