package application.walkin;

import java.util.List;
import application.Info;

import javafx.collections.FXCollections;

public class WalkInReport {
    String name;
	String reportType;
	Info info;
	List<Sample> table;
	List<String> conclusions;
	int tableLength;
	String finalConclusion;
	public WalkInReport(String reportType) {
		this.table = FXCollections.observableArrayList();
		this.reportType = reportType;
		this.info = new Info();
		this.conclusions = FXCollections.observableArrayList();
		this.tableLength = 0;
		this.finalConclusion="";
	}

	public void setInfo(Info info) {
		this.info=info;
	}
	public void setTable(List<Sample> set) {
		this.table = set;
	}
	public void addSample(Sample sample) {
		table.add(sample);
	}
	
	public void removeSample(Sample sample) {
		table.remove(sample);
	}
	
	public void addConclusion(String conclusion) {
		this.conclusions.add(conclusion);
	}
	
	public void generateConclusion() {
		switch (this.reportType) {
			case "Asbestos":
				for (String conclusion : this.conclusions) this.finalConclusion += conclusion + "\n";
				break;
			case "Bacteroides":
				if (this.conclusions.size() == 2) {
					this.finalConclusion += conclusions.get(0) + "while " + conclusions.get(1);
				}
				break;
			case "Lead":
				this.finalConclusion = "Results of analysis indicate that ";
				if (conclusions.size() == 1) {
					this.finalConclusion += conclusions.get(0) + ".";
				} else if (conclusions.size() == 2) {
					this.finalConclusion += conclusions.get(0) + " and " + conclusions.get(1) + ".";
				} else {
					this.finalConclusion += conclusions.get(0) + ", " + conclusions.get(1) + " and " + conclusions.get(2) + ".";
				}
				break;
			default:
				for (String conclusion : conclusions) {
					this.finalConclusion += conclusion + "\n";
				}
				break;
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

	public Info getInfo(){
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
