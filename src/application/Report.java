package application;

import application.iat.sample.AirTestingSample;

import java.util.List;

public class Report {
    public String name;
    public Info info;
    public String reportType;
    public Report(String reportType){
        this.name="";
        this.info = new Info();
        this.reportType = reportType;
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
}
