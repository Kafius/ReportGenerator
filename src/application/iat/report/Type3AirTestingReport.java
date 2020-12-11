package application.iat.report;

import application.Info;
import application.iat.sample.PostAirTestingSample;
import javafx.collections.FXCollections;

import java.util.List;

public class Type3AirTestingReport {
    String name;
    Info info;
    String scopeOfWork;
    String flowRate;
    String postAbatementInspection;
    List<PostAirTestingSample> table;

    Type3AirTestingReport(){
        this.name = "";
        this.info= new Info();
        this.scopeOfWork= "";
        this.flowRate= "";
        this.postAbatementInspection= "";
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

    public String getScopeOfWork() {
        return scopeOfWork;
    }

    public void setScopeOfWork(String scopeOfWork) {
        this.scopeOfWork = scopeOfWork;
    }

    public String getFlowRate() {
        return flowRate;
    }

    public void setFlowRate(String flowRate) {
        this.flowRate = flowRate;
    }

    public String getPostAbatementInspection() {
        return postAbatementInspection;
    }

    public void setPostAbatementInspection(String postAbatementInspection) {
        this.postAbatementInspection = postAbatementInspection;
    }

    public List<PostAirTestingSample> getTable() {
        return table;
    }

    public void setTable(List<PostAirTestingSample> table) {
        this.table = table;
    }
}
