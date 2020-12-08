package application.walkin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.GenerateReportController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AsbestosController implements Initializable{
	WalkInReport newReport2 = new WalkInReport("Asbestos");
	
	//holds conclusion sentences for all 3 scenarios
	String conclusion1 = "";
	String conclusion2 = ""; 
	String conclusion3 = "";
	
	//holds list of sample numbers for each scenario
	ObservableList<String> conclusionNumbers1 = FXCollections.observableArrayList();
	ObservableList<String> conclusionNumbers2 = FXCollections.observableArrayList();
	ObservableList<String> conclusionNumbers3 = FXCollections.observableArrayList();

	//visuals
	@FXML private Label title, subtitle;

	@FXML private Line topBorder, bottomBorder;

	@FXML private ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/pictures/safetechlogo1.png")));

	@FXML private Rectangle inputSection;

	@FXML private HBox buttonSection;
	
	//table data
	@FXML private TableView<Sample> table;
	@FXML private TableColumn<Sample, String> sampleNumber, materialDescription, sampleLocation, asbestosContent, asbestosForm;

	//entry section
	@FXML private GridPane entry;

	//TextFields for Table 1: Bulk Sample Analytical Results for Determination of Asbestos Content
	@FXML private TextField sampleNumberTF, asbestosContentTF;
	
	@FXML private ComboBox<String> sampleLocationCB, materialDescriptionCB, colourDescriptionCB;
	
	//Choice Box for Asbestos Forms
	@FXML private ChoiceBox<String> asbestosForms;

	//Labels for Table 1: Bulk Sample Analytical Results for Determination of Asbestos Content
	@FXML private Label sampleNumberL, materialDescriptionL, sampleLocationL, asbestosContentL;

	//Buttons for Table 1: Bulk Sample Analytical Results for Determination of Asbestos Content
	@FXML private Button addSample, removeSample, nextButton, backButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) { 
		sampleNumber.setCellValueFactory(new PropertyValueFactory<Sample, String>("sampleNumber"));
		materialDescription.setCellValueFactory(new PropertyValueFactory<Sample, String>("materialDescription"));
		sampleLocation.setCellValueFactory(new PropertyValueFactory<Sample, String>("sampleLocation"));
		asbestosContent.setCellValueFactory(new PropertyValueFactory<Sample, String>("asbestosContent"));
		asbestosForm.setCellValueFactory(new PropertyValueFactory<Sample, String>("asbestosForm"));
		asbestosForms.getItems().addAll("Chrysotile","Actinolite","Amosite","Crocidolite","Anthophyllite","Tremolite");
		
		sampleLocationCB.getItems().addAll("None Specified");
		materialDescriptionCB.getItems().addAll("N/A");
		colourDescriptionCB.getItems().addAll("N/A");
		
		sampleLocationCB.setValue("None Specified");
		materialDescriptionCB.setValue("Material");
		colourDescriptionCB.setValue("Colour");
		
		sampleLocationCB.setEditable(true);
		materialDescriptionCB.setEditable(true);
		colourDescriptionCB.setEditable(true);
	} 	
	
	//collects user inputed data from text fields and creates a sample entry for the table
	public void addEntry(ActionEvent event) {
		if(checkEntryInputs()) {
			String materialDescriptionFinal;
			if(colourDescriptionCB.getValue().equals("N/A")&&!materialDescriptionCB.getValue().equals("N/A")) {
				materialDescriptionFinal = colourDescriptionCB.getValue();
			}
			else if(materialDescriptionCB.getValue().equals("N/A")&&!colourDescriptionCB.getValue().equals("N/A")) {
				materialDescriptionFinal = materialDescriptionCB.getValue();
			}
			else if(!colourDescriptionCB.getValue().equals("N/A")&&!materialDescriptionCB.getValue().equals("N/A")) {
				materialDescriptionFinal = colourDescriptionCB.getValue() + " - " + materialDescriptionCB.getValue();
			}
			else {
				materialDescriptionFinal = "No Description";
			}
			AsbestosSample newAsbestosSample = new AsbestosSample(sampleNumberTF.getText(),materialDescriptionFinal,sampleLocationCB.getValue(),asbestosContentTF.getText(),asbestosForms.getValue());
			newAsbestosSample.setConclusionCase();
			table.getItems().add(newAsbestosSample);
			newReport2.addSample(newAsbestosSample);
			sampleNumberTF.clear();
			materialDescriptionCB.setValue("Material");
			colourDescriptionCB.setValue("Colour");
			sampleLocationCB.setValue("None Specified");
			asbestosContentTF.clear();
		}
	}
	
	//removes entry from sample table and walk in report data
	public void removeEntry(ActionEvent event) {
		newReport2.removeSample(table.getSelectionModel().getSelectedItem());
		table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
	}
	
	//checks entry for invalid inputs
	public boolean checkEntryInputs() {
		if(
				sampleNumberTF.getText().equals("")||
				materialDescriptionCB.getValue().equals("")||
				materialDescriptionCB.getValue().equals("Material")||
				colourDescriptionCB.getValue().equals("Colour")||
				(!asbestosContentTF.getText().equals("")&&asbestosForms.getValue()==null)) {
			missingInputError();
			return false;
		}
		else {
			try {
				Double.parseDouble(asbestosContentTF.getText());
			}
			catch(Exception e) {
				invalidInputError();
				return false;
			}
		}
		
		return true;
	}
	
	public void missingInputError() {
		Alert invalidInputs = new Alert(AlertType.ERROR);
		invalidInputs.setTitle("New Sample");
		invalidInputs.setHeaderText("Missing Inputs");
		invalidInputs.setContentText("Complete sample info before submitting."); 
		ButtonType okayButton = new ButtonType("Okay");
		invalidInputs.getButtonTypes().setAll(okayButton);
		invalidInputs.show();
		return;
	}
	
	public void invalidInputError() {
		Alert invalidInputs = new Alert(AlertType.ERROR);
		invalidInputs.setTitle("New Sample");
		invalidInputs.setHeaderText("Invalid Input");
		invalidInputs.setContentText("Please enter a valid content number"); 
		ButtonType okayButton = new ButtonType("Okay");
		invalidInputs.getButtonTypes().setAll(okayButton);
		invalidInputs.show();
		return;
	}
	
	//gets report data from prior prompt
	public void getReportData(WalkInReport report) {
		newReport2 = report;
		newReport2.clearConclusions();
		for(int i=0;i<newReport2.table.size();i++) {
			Sample newAsbestosSample = newReport2.table.get(i);
			table.getItems().add(newAsbestosSample);
			addConclusionNumber(newAsbestosSample);
		}
	}
	
	//submits final table to next prompt
	@FXML
	public void submitTable(ActionEvent event) {
		if(table.getItems().size()==0) {
			Alert invalidInputs = new Alert(AlertType.ERROR);
			invalidInputs.setTitle("Submit Table");
			invalidInputs.setHeaderText("No Samples");
			invalidInputs.setContentText("Unable to submit empty table."); 
			ButtonType okayButton = new ButtonType("Okay");
			invalidInputs.getButtonTypes().setAll(okayButton);
			invalidInputs.show();
			return;
		}
		else {
			addConclusions();
			if(!conclusion1.equals("")) {
				newReport2.addConclusion(conclusion1);;
			}
			if(!conclusion2.equals("")) {
				newReport2.addConclusion(conclusion2);;
			}
			if(!conclusion3.equals("")) {
				newReport2.addConclusion(conclusion3);
			}
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/"+"ReportMaker.fxml"));
				Parent root = (Parent) loader.load();
				GenerateReportController finalReport = loader.getController();
				finalReport.getReport(newReport2);
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//returns to previous prompt
	public void goBack(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/walkin/BasicInfoPrompt.fxml"));
			Parent root = (Parent) loader.load();
			
			BasicInfoController basicInfoReport = loader.getController();
			basicInfoReport.getReport(newReport2);
			
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//turns sorted sample numbers into final conclusions
	public void addConclusions() {
		for(int i=0;i<newReport2.table.size();i++) {
			addConclusionNumber(newReport2.table.get(i));
		}
			
		if(conclusionNumbers1.size()==0) {
			conclusion1="";
		}
		else if(conclusionNumbers1.size()==1) {
			conclusion1 = "No asbestos was detected in sample "+conclusionNumbers1.get(0)+". ";
		}
		else if(conclusionNumbers1.size()==2) {
			conclusion1 = "No asbestos was detected in samples "+conclusionNumbers1.get(0) + " and "+ conclusionNumbers1.get(1) + ". ";
		}
		else {
			conclusion1 = "No asbestos was detected in samples ";
			for(int i=0;i<conclusionNumbers1.size()-2;i++) {
				conclusion1+=conclusionNumbers1.get(i) + ", ";
			}
			conclusion1+= conclusionNumbers1.get(conclusionNumbers1.size()-2) + " and "+ conclusionNumbers1.get(conclusionNumbers1.size()-1) + ". ";
		}
		
		if(conclusionNumbers2.size()==0) {
			conclusion2="";
		}
		else if(conclusionNumbers2.size()==1) {
			conclusion2 = "Asbestos was detected at a concentration below that required by the regulation to be defined as an ACM in sample "+conclusionNumbers2.get(0)+". ";
		}
		else if(conclusionNumbers2.size()==2) {
			conclusion2 = "Asbestos was detected at a concentration below that required by the regulation to be defined as an ACM in samples "+conclusionNumbers2.get(0) + " and "+ conclusionNumbers2.get(1) + ". ";
		}
		else {
			conclusion2 = "Asbestos was detected at a concentration below that required by the regulation to be defined as an ACM in samples ";
			for(int i=0;i<conclusionNumbers2.size()-2;i++) {
				conclusion2+=conclusionNumbers2.get(i) + ", ";
			}
			conclusion2+= conclusionNumbers2.get(conclusionNumbers2.size()-2) + " and "+ conclusionNumbers2.get(conclusionNumbers2.size()-1) + ". ";
		}
		
		if(conclusionNumbers3.size()==0) {
			conclusion3="";
		}
		else if(conclusionNumbers3.size()==1) {
			conclusion3 = "Asbestos was detected in sample " +conclusionNumbers3.get(0)+" at a concentration of >0.5%. This sample is therefore classified as ACM and are subject to the requirements set forth in O.Reg. 278/05. ";
		}
		else if(conclusionNumbers2.size()==2) {
			conclusion3 = "Asbestos was detected in samples " +conclusionNumbers3.get(0)+" and "+conclusionNumbers3.get(1)+ "at a concentration of >0.5%. These samples are therefore classified as ACM and are subject to the requirements set forth in O.Reg. 278/05. ";
		}
		else {
			conclusion3 = "Asbestos was detected in samples ";
			for(int i=0;i<conclusionNumbers3.size()-2;i++) {
				conclusion3+=conclusionNumbers3.get(i) + ", ";
			}
			conclusion3+= conclusionNumbers3.get(conclusionNumbers3.size()-2) + " and "+ conclusionNumbers3.get(conclusionNumbers3.size()-1) +" ";
			
			conclusion3+= " at a concentration of >0.5%. These samples are therefore classified as ACM and are subject to the requirements set forth in O.Reg. 278/05. ";
		}
	}
	
	//adds a sample number to the appropriate list
	public void addConclusionNumber(Sample sample) {
		if(sample.getConclusionCase()==0) {
			conclusionNumbers1.addAll(sample.getSampleNumber());
		}
		else if(sample.getConclusionCase()==1) {
			conclusionNumbers2.addAll(sample.getSampleNumber());
		}
		else {
			conclusionNumbers3.addAll(sample.getSampleNumber());
		}
	}
	
	
	
	
	
}