package application.walkin;

import javafx.beans.property.SimpleStringProperty;

public class MouldSample extends Sample {
	private final SimpleStringProperty sampleDescription;
	private final SimpleStringProperty settledSporeTypes;
	private final SimpleStringProperty mouldGrowthTypes;
	private int varNum=4;
	private int conclusionCase;
	
	public MouldSample(String sN, String sD, String sST, String mGT) {
		super(sN);
		this.sampleDescription = new SimpleStringProperty(sD);
		this.settledSporeTypes = new SimpleStringProperty(sST);
		this.mouldGrowthTypes = new SimpleStringProperty(mGT);
	}	
	
	public String getSampleNumber() {
		return sampleNumber.get();
	}

	public String getMaterialDescription() {
		return sampleDescription.get();
	}
	
	public String getSettledSporeTypes() {
		return settledSporeTypes.get();
	}

	public String getMouldGrowthTypes(){
		return mouldGrowthTypes.get();
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
			return this.settledSporeTypes.get();
		case 3:
			return this.mouldGrowthTypes.get();
		default:
			return "";
	
		}
	}
	
	public void setConclusionCase() {
		//todo
	}
	
	public int getConclusionCase() {
		return this.conclusionCase;
	}
}
