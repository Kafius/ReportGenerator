package application.walkin.sample;

import javafx.beans.property.SimpleStringProperty;

public class LeadSample extends Sample {
	private final SimpleStringProperty sampleDescription;
	private final SimpleStringProperty colour;
	private final SimpleStringProperty leadConcentration;
	private int varNum=4;
	private int conclusionCase;
	
	public LeadSample(String sN, String sD, String c, String lC) {
		super(sN);
		this.sampleDescription = new SimpleStringProperty(sD);
		this.colour = new SimpleStringProperty(c);
		this.leadConcentration = new SimpleStringProperty(lC);
	}	
	
	public String getSampleNumber() {
		return sampleNumber.get();
	}

	public String getMaterialDescription() {
		return sampleDescription.get();
	}
	
	public String getColour() {
		return colour.get();
	}

	public String getLeadConcentration(){
		return leadConcentration.get();
	}
	
	public void setConclusionCase() {
		if(Double.parseDouble(this.leadConcentration.get())<=0.1) {
			this.conclusionCase = 0;
		}
		else if(Double.parseDouble(this.leadConcentration.get())<=0.5) {
			this.conclusionCase = 1;
		}
		else {
			this.conclusionCase = 2;
		}
	}
	
	public int getConclusionCase() {
		return this.conclusionCase;
	}
	
	public int getVarNum() {
		return this.varNum;
	}
	
	public String getArray(int num) {
		switch(num) {
		case 0:
			return this.sampleNumber.get();
		case 1:
			return this.sampleDescription.get();
		case 2:
			return this.colour.get();
		case 3:
			return this.leadConcentration.get();
		default:
			return "";
	
		}
	}
}
