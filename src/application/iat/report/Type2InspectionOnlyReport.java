package application.iat.report;

import application.Info;
import javafx.collections.FXCollections;

public class Type2InspectionOnlyReport {
    String name;
    Info info;

    Type2InspectionOnlyReport(){
        this.name="";
        this.info= new Info();
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
