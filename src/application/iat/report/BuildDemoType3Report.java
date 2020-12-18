package application.iat.report;

import application.Info;
import application.Report;

public class BuildDemoType3Report extends Report {
    String reportType;
    BuildDemoType3Report(){
        super("BuildDemoType3");
        this.name="";
        this.info=new Info();
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
