package application.iat.report;

import application.Info;
import application.iat.AirTestingSample;

import java.util.List;

public class BuildDemoType3Report {
    String name;
    Info info;
    BuildDemoType3Report(){
        this.name="";
        this.info = new Info();
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
