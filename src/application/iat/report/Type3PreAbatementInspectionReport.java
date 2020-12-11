package application.iat.report;

import application.Info;

public class Type3PreAbatementInspectionReport {
    String name;
    Info info;
    String scopeOfWork;

    Type3PreAbatementInspectionReport(){
        this.name="";
        this.info = new Info();
        this.scopeOfWork = "";
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

    public String getScopeOfWork() {
        return scopeOfWork;
    }

    public void setScopeOfWork(String scopeOfWork) {
        this.scopeOfWork = scopeOfWork;
    }
}
