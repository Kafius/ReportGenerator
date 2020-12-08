package application.iat.report;

import application.Info;
import application.iat.AirTestingSample;
import javafx.collections.FXCollections;

import java.util.List;

public class ASResidentialReport {
    String name;
    Info info;
    List<AirTestingSample> table;
    ASResidentialReport(){
        this.name="";
        this.info=new Info();
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

    public List<AirTestingSample> getTable() {
        return table;
    }

    public void setTable(List<AirTestingSample> table) {
        this.table = table;
    }

    public void addSample(AirTestingSample sample){
        this.table.add(sample);
    }

    public void removeSample(AirTestingSample sample){
        table.remove(table.indexOf(sample));
    }
}

