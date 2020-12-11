package application.walkin.sample;

import javafx.beans.property.SimpleStringProperty;

public class BacteroidesSample extends Sample {
	private final SimpleStringProperty materialDescription;
	private final SimpleStringProperty bacteroidesConcentration;
	private int varNum = 3;
	private int conclusionCase;
	
	public BacteroidesSample(String sN, String mD, String bC) {
		super(sN);
		this.materialDescription = new SimpleStringProperty(mD);
		if(bC.equals("0")) {
			bC = "None Detected"; 
		}
		this.bacteroidesConcentration = new SimpleStringProperty(bC);
		setConclusionCase();
	}	
	
	public String getSampleNumber() {
		return sampleNumber.get();
	}

	public String getMaterialDescription() {
		return materialDescription.get();
	}

	public String getBacteroidesConcentration(){
		return bacteroidesConcentration.get();
	}
	
	public int getVarNum() {
		return this.varNum;
	}
	
	public void setConclusionCase() {
		if(this.bacteroidesConcentration.get().equals("None Detected")) {
			this.conclusionCase = 0;
		}
		else {
			this.conclusionCase = 1;
		}
	}
	
	public int getConclusionCase() {
		return this.conclusionCase;
	}
	
	public String getArray(int num) {
		switch(num) {
		case 0:
			return this.sampleNumber.get();
		case 1:
			return this.materialDescription.get();
		case 2:
			return this.bacteroidesConcentration.get();
		default:
			return "";
	
		}
	}

}
