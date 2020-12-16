package application.iat.report;

import application.Info;
import application.Report;
import application.iat.sample.AirTestingSample;
import javafx.collections.FXCollections;

import java.util.List;

public class Type3CloseOutReport extends Report {
    String name;
    Info info;
    List<AirTestingSample> table;
    List<String> preDeficiencies;
    String duringAbatementInspection;
    String postAbatementInspection;

    Type3CloseOutReport(){
        super();
        this.table = FXCollections.observableArrayList();
        this.preDeficiencies = FXCollections.observableArrayList();
        this.duringAbatementInspection="";
        this.postAbatementInspection="";
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

    public List<AirTestingSample> getTable1() {
        return table;
    }

    public void setTable(List<AirTestingSample> table1) {
        this.table = table;
    }

    public List<String> getPreDeficiencies() {
        return preDeficiencies;
    }

    public void setPreDeficiencies(List<String> preDeficiencies) {
        this.preDeficiencies = preDeficiencies;
    }

    public String getDuringAbatementInspection() {
        return duringAbatementInspection;
    }

    public void setDuringAbatementInspection(String duringAbatementInspection) {
        this.duringAbatementInspection = duringAbatementInspection;
    }

    public String getPostAbatementInspection() {
        return postAbatementInspection;
    }

    public void setPostAbatementInspection(String postAbatementInspection) {
        this.postAbatementInspection = postAbatementInspection;
    }

    public void addDeficiency(String d){
        this.preDeficiencies.add(d);
    }

    public void addSample(AirTestingSample ATS){
        this.table.add(ATS);
    }
    public void removeSample(AirTestingSample sample) {
        table.remove(table.indexOf(sample));
    }

}
