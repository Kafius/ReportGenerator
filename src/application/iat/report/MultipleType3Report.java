package application.iat.report;

import application.Info;
import application.Report;
import application.iat.sample.AirTestingSample;
import javafx.collections.FXCollections;

import java.util.List;

public class MultipleType3Report extends Report {
    String name;
    Info info;
    List<String> deficiencies;
    String procedure;
    List<AirTestingSample> table;

    MultipleType3Report(){
        super("MultipleType3");
        this.deficiencies = FXCollections.observableArrayList();
        this.procedure = "";
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

    public List<AirTestingSample> getTable() {
        return table;
    }

    public void setTable(List<AirTestingSample> table) {
        this.table = table;
    }
}
