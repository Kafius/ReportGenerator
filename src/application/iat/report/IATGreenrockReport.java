package application.iat.report;

import application.Info;
import application.Report;
import application.iat.sample.PostAirTestingSample;
import javafx.collections.FXCollections;

import java.util.List;

public class IATGreenrockReport extends Report {
    String name;
    Info info;
    List<PostAirTestingSample> table;

    IATGreenrockReport(){
        super();
        this.table= FXCollections.observableArrayList();
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

    public List<PostAirTestingSample> getTable() {
        return table;
    }

    public void setTable(List<PostAirTestingSample> table) {
        this.table = table;
    }

    public void addSample(PostAirTestingSample sample){
        this.table.add(sample);
    }

    public void removeSample(PostAirTestingSample sample){
        table.remove(table.indexOf(sample));
    }
}
