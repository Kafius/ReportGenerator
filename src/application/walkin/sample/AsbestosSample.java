package application.walkin.sample;

import javafx.beans.property.SimpleStringProperty;

public class AsbestosSample extends Sample {
	private final SimpleStringProperty materialDescription;
	private final SimpleStringProperty sampleLocation;
	private final SimpleStringProperty asbestosContent;
	private final SimpleStringProperty asbestosForm;
	private int varNum = 4;
	private int conclusionCase;
	
	public AsbestosSample() {
		super();
		this.materialDescription = new SimpleStringProperty();
		this.sampleLocation = new SimpleStringProperty();
		this.asbestosContent = new SimpleStringProperty();
		this.asbestosForm = new SimpleStringProperty();
	}
	
	public AsbestosSample(String sN, String mD, String sL, String aC,String aF) {
		super(sN);
		this.materialDescription = new SimpleStringProperty(mD);
		if(sL.equals("")) {
			sL = "Not Specified";
		}
		this.sampleLocation = new SimpleStringProperty(sL);
		if(aC.equals("0")) {
			aC = "None Detected"; 
		}
		
		if(!aC.contains(".")&&!aC.equals("None Detected")) {
			aC+=".0";
		}
		
		this.asbestosContent = new SimpleStringProperty(aC);
		this.asbestosForm = new SimpleStringProperty(aF);
	}
	
	public String getSampleNumber() {
		return sampleNumber.get();
	}

	public String getMaterialDescription() {
		return materialDescription.get();
	}

	public String getSampleLocation() {
		return sampleLocation.get();
	}

	public String getAsbestosContent() {
		return asbestosContent.get();
	}
	
	public String getAsbestosForm() {
		return asbestosForm.get();
	}
	
	public String getAsbestosInfo() {
		return asbestosContent.get() + "% "+ asbestosForm.get();
	}
	
	public int getVarNum() {
		return this.varNum;
	}
	
	public void setConclusionCase() {
		if(this.asbestosContent.get().equals("None Detected")) {
			this.conclusionCase = 0;
		}
		else if(Double.parseDouble(this.asbestosContent.get())<0.5) {
			this.conclusionCase = 1;
		}
		else {
			this.conclusionCase = 2;
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
			return this.sampleLocation.get();
		case 3:
			return this.asbestosContent.get()+"% "+this.asbestosForm.get();
		default:
			return "";
	
		}
	}

}
