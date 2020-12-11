package application.walkin.sample;

import javafx.beans.property.SimpleStringProperty;

public class Sample {
	protected final SimpleStringProperty sampleNumber;
	private int varNum = 0;
	private int conclusionCase = 0;
	
	public Sample() {
		this.sampleNumber = new SimpleStringProperty();
	}
	
	public Sample(String sampleNumber){
		this.sampleNumber = new SimpleStringProperty(sampleNumber);
	}
	
	public int getVarNum() {
		return this.varNum;
	}
	
	public String getArray(int num) {
		switch(num) {
		default:
			return this.sampleNumber.get();
		}
	}
	
	public String getSampleNumber() {
		return this.sampleNumber.get();
	}
	
	public int getConclusionCase() {
		return this.conclusionCase;
	}
}
