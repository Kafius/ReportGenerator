package application.iat.report;

import application.Info;
import application.Report;
import application.iat.sample.TEMAsbestosFibreSample;
import javafx.collections.FXCollections;

import java.util.List;

public class OutdoorTEMReport extends Report {
    String name;
    Info info;
    String reportType;
    List<TEMAsbestosFibreSample> table;
    OutdoorTEMReport(){
        super("OutdoorTEM");
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

    public List<TEMAsbestosFibreSample> getTable() {
        return table;
    }

    public void setTable(List<TEMAsbestosFibreSample> table) {
        this.table = table;
    }

    public void addSample(TEMAsbestosFibreSample sample){
        this.table.add(sample);
    }

    public void removeSample(TEMAsbestosFibreSample sample){
        table.remove(table.indexOf(sample));
    }
}
