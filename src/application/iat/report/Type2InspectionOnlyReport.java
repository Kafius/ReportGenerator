package application.iat.report;

import application.Info;
import application.Report;
import javafx.collections.FXCollections;

public class Type2InspectionOnlyReport extends Report {
    String name;
    Info info;
    String reportType;

    Type2InspectionOnlyReport(){
        super("Type2InspectionOnly");
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
}
