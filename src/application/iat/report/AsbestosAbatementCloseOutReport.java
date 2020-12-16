package application.iat.report;

import application.Info;
import application.Report;
import application.iat.sample.AirTestingSample;
import javafx.collections.FXCollections;

import java.util.List;

public class AsbestosAbatementCloseOutReport extends Report {
    String name;
    Info info;
    String reportType;
    List<String> deficiencies;
    String procedure;
    String specifications;
    List<AirTestingSample> table;

    public AsbestosAbatementCloseOutReport(){
        super("AsbestosAbatementCloseOut");
        this.deficiencies = FXCollections.observableArrayList();
        this.procedure = "";
        this.specifications = "";
        this.table = FXCollections.observableArrayList();
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

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public List<String> getDeficiencies() {
        return deficiencies;
    }

    public void setDeficiencies(List<String> deficiencies) {
        this.deficiencies = deficiencies;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public List<AirTestingSample> getTable() {
        return table;
    }

    public void setTable(List<AirTestingSample> table) {
        this.table = table;
    }
}
