package application.iat.report;

import application.Info;
import application.Report;

public class BuildDemoType3Report extends Report {
    String name;
    Info info;
    BuildDemoType3Report(){
        super();
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
}
