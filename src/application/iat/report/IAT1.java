package application.iat.report;

import application.Info;
import application.Report;
import application.iat.sample.TEMAsbestosFibreSample;
import application.walkin.sample.Sample;
import javafx.collections.FXCollections;

import java.util.List;

public class IAT1 extends Report {
    public List<TEMAsbestosFibreSample> table;
    int tableLength;

    public IAT1(String reportType){
        super(reportType);
        this.table = FXCollections.observableArrayList();
        this.tableLength = 0;
    }

    public void setInfo(Info info) {
        this.info=info;
    }
    public void setTable(List<TEMAsbestosFibreSample> set) {
        this.table = set;
    }
    public void addSample(TEMAsbestosFibreSample sample) {
        table.add(sample);
    }

    public void removeSample(Sample sample) {
        table.remove(sample);
    }

    public void setFileName() {
        this.name = this.info.getProjectNumber()+"-"+this.info.getCompanyName()+"-"+this.info.getProjectAddress()+"-Report Final.docx";
    }

    public String getReportType() {
        return this.reportType;
    }

    public Info getInfo(){
        return this.info;
    }

    public List<TEMAsbestosFibreSample> getTable(){
        return this.table;
    }

    public String getName(){
        return this.name;
    }
}
