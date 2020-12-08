package application.walkin;

import java.util.List;

import javafx.collections.FXCollections;

public class WalkInReport {
    String name;
	String reportType;
	BasicInfo info;
	List<Sample> table;
	List<String> conclusions;
	int tableLength;
	String finalConclusion;
	public WalkInReport(String reportType) {
		this.table = FXCollections.observableArrayList();
		this.reportType = reportType;
		this.info = new BasicInfo();
		this.conclusions = FXCollections.observableArrayList();
		this.tableLength = 0;
		this.finalConclusion="";
	}

	public void setInfo(BasicInfo info) {
		this.info=info;
	}
	public void setTable(List<Sample> set) {
		this.table = set;
	}
	public void addSample(Sample sample) {
		table.add(sample);
	}
	
	public void removeSample(Sample sample) {
		table.remove(table.indexOf(sample));
	}
	
	public void addConclusion(String conclusion) {
		this.conclusions.add(conclusion);
	}
	
	public void generateConclusion() {
		if(this.reportType.equals("Asbestos")) {
			for(int i=0;i<this.conclusions.size();i++) {
				this.finalConclusion+=conclusions.get(i) +"\n";
			}
		}
		else if(this.reportType.equals("Bacteroides")) {
			if(this.conclusions.size()==2) {
				this.finalConclusion+=conclusions.get(0) + "while "+ conclusions.get(1);
			}
		}
		else if(this.reportType.equals("Lead")) {
			this.finalConclusion = "Results of analysis indicate that ";
			if(conclusions.size()==1) {
				this.finalConclusion+=conclusions.get(0)+ ".";
			}
			else if(conclusions.size()==2) {
				this.finalConclusion+=conclusions.get(0)+" and "+conclusions.get(1)+ ".";
			}
			else {
				this.finalConclusion+=conclusions.get(0)+", "+conclusions.get(1)+ " and "+ conclusions.get(2) + ".";
			}
		}
		else {
			for(int i=0;i<conclusions.size();i++) {
				this.finalConclusion += conclusions.get(i) + "\n";
			}
		}
	}
	
	public void setFileName() {
		this.name = this.info.getProjectNumber()+"-"+this.info.getProjectAddress()+"-"+this.info.getProjectAddress()+", "+this.info.getProjectCity()+"-Report Final.docx";
	}
	
	public void clearConclusions() {
		this.conclusions= FXCollections.observableArrayList();
	}
	
	public String getReportType() {
		return this.reportType;
	}

	public BasicInfo getInfo(){
		return this.info;
	}

	public List<Sample> getTable(){
		return this.table;
	}

	public List<String> getConclusions(){
		return this.conclusions;
	}

	public String getName(){
		return this.name;
	}
}
