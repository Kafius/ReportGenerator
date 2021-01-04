package application;

import application.iat.sample.AirTestingSample;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Report {
    public String name;
    public Info info;
    public String reportType;
    public List<String> interfaces;
    public int interfaceCounter;
    public Report(String reportType){
        this.name="";
        this.info = new Info();
        this.interfaceCounter = 0;
        this.reportType = reportType;
        this.interfaces = new ArrayList<String>();
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

    public void setFileName(){
        this.name = info.getProjectNumber()+"-Report Final.docx";
    }

    public List<String> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<String> interfaces) {
        this.interfaces = interfaces;
    }

    public void addInterface(String newInterface){
        this.interfaces.add(newInterface);
    }

    public void nextInterface(){
        this.interfaceCounter++;
    }

    public int getInterfaceCounter() {
        return interfaceCounter;
    }

    public void setInterfaceCounter(int interfaceCounter) {
        this.interfaceCounter = interfaceCounter;
    }
}
