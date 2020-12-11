package application.walkin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.GenerateReportController;
import application.walkin.report.WalkInReport;
import application.walkin.sample.BacteroidesSample;
import application.walkin.sample.Sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BacteroidesController implements Initializable{
		
	WalkInReport newReport2 = new WalkInReport("Bacteroides");
	
	//holds conclusion sentences for all 3 scenarios
	String conclusion1 = "";
	String conclusion2 = ""; 
	String endingConclusion  = "";
	
	//holds list of sample numbers for each scenario
	ObservableList<String> conclusionNumbers1 = FXCollections.observableArrayList();
	ObservableList<String> conclusionNumbers2 = FXCollections.observableArrayList();
	
	//visuals	
	@FXML private Label title, subtitle;
	
	@FXML private Line topBorder, bottomBorder;
	
	@FXML private ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/pictures/safetechlogo1.png")));
	
	@FXML private Rectangle inputSection;
	
	@FXML private HBox buttonSection;
	
	//table data
	@FXML private TableView<Sample> table;
	@FXML private TableColumn<Sample, String> sampleNumber, materialDescription, bacteroidesConcentration;
	
	//entry section
	@FXML private GridPane entry;
	
	//TextFields for Table 1: Bulk Sample Analytical Results for Determination of Asbestos Content
	@FXML private TextField sampleNumberTF, materialDescriptionTF, bacteroidesConcentrationTF;
	
	//Choice Box for Asbestos Forms
	@FXML private CheckBox locationNA;
	
	//Labels for Table 1: Bulk Sample Analytical Results for Determination of Asbestos Content
	@FXML private Label sampleNumberL,materialDescriptionL,bacteroidesConcentrationL;

	//Buttons for Table 1: Bulk Sample Analytical Results for Determination of Asbestos Content
	@FXML private Button addSample, removeSample,nextButton, backButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) { 
		sampleNumber.setCellValueFactory(new PropertyValueFactory<Sample, String>("sampleNumber"));
		materialDescription.setCellValueFactory(new PropertyValueFactory<Sample, String>("materialDescription"));
		bacteroidesConcentration.setCellValueFactory(new PropertyValueFactory<Sample, String>("bacteroidesConcentration"));
	} 	
	
	//collects user inputed data from textfields and creates a sample entry for the table
	public void addEntry(ActionEvent event) {
		if(checkEntryInputs()) {
			BacteroidesSample newBacteroidesSample = new BacteroidesSample(sampleNumberTF.getText(),materialDescriptionTF.getText(),bacteroidesConcentration.getText());
			newBacteroidesSample.setConclusionCase();
			table.getItems().add(newBacteroidesSample);
			newReport2.addSample(newBacteroidesSample);
			addConclusionNumber(newBacteroidesSample);
			sampleNumberTF.clear();
			materialDescriptionTF.clear();
			bacteroidesConcentrationTF.clear();
		}
	}
	
	//removes entry from sample table and walk in report data
	public void removeEntry(ActionEvent event) {
		newReport2.removeSample(table.getSelectionModel().getSelectedItem());
		table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
		if(conclusionNumbers1.indexOf(table.getSelectionModel().getSelectedItem().getSampleNumber())!=-1) {
			conclusionNumbers1.remove(conclusionNumbers1.indexOf(table.getSelectionModel().getSelectedItem().getSampleNumber()));
		}
		else {
			conclusionNumbers2.remove(conclusionNumbers2.indexOf(table.getSelectionModel().getSelectedItem().getSampleNumber()));
		}
	}
	
	//checks entry for invalid inputs
	public boolean checkEntryInputs() {
		boolean isValid = true;
		if(sampleNumberTF.getText().equals("")) {
			isValid = false;
		}
		
		if(materialDescriptionTF.getText().equals("")) {
			isValid = false;
		}
		
		if(Double.valueOf(bacteroidesConcentrationTF.getText())==null) {
			isValid = false;
		}
		return isValid;
	}
	
	//returns to previous prompt
	public void goBack(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/walkin/BasicInfoPrompt.fxml"));
			Parent root = (Parent) loader.load();
			
			GenerateReportController reportVariables = loader.getController();
			reportVariables.getReport(newReport2);
			
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//gets report data from prior prompt
	public void getReportData(WalkInReport report) {
		newReport2 = report;
		newReport2.clearConclusions();
		for(int i=0;i<newReport2.table.size();i++) {
			Sample newBacteroidesSample = newReport2.table.get(i);
			table.getItems().add(newBacteroidesSample);
			addConclusionNumber(newBacteroidesSample);
		}
	}
	
	//submits final table to next prompt
	@FXML
	public void submitTable(ActionEvent event) {
		if(!conclusion1.equals("")) {
			newReport2.addConclusion(conclusion1);
		}
		if(!conclusion2.equals("")) {
			newReport2.addConclusion(conclusion2);
		}
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ReportMaker.fxml"));
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
	
	//turns sorted sample numbers into final conclusions
	public void addConclusions() {
		if(conclusionNumbers1.size()==0) {
			conclusion1="";
		}
		else if(conclusionNumbers1.size()==1) {
			conclusion1 = "Bacteroides was detected from sample "+conclusionNumbers1.get(0)+".";
		}
		else if(conclusionNumbers1.size()==2) {
			conclusion1 = "Bacteroides was detected from samples "+conclusionNumbers1.get(0) + " and "+ conclusionNumbers1.get(1) + ".";
		}
		else {
			conclusion1 = "Bacteroides was detected from samples ";
			for(int i=0;i<conclusionNumbers1.size()-2;i++) {
				conclusion1+=conclusionNumbers1.get(i) + ", ";
			}
			conclusion1+= conclusionNumbers1.get(conclusionNumbers1.size()-2) + " and "+ conclusionNumbers1.get(conclusionNumbers1.size()-1) + ".";
		}
		
		if(conclusionNumbers2.size()==0) {
			conclusion2="";
		}
		else if(conclusionNumbers2.size()==1) {
			conclusion2 = "no Bacteroides was detected from sample "+conclusionNumbers2.get(0)+".";
		}
		else if(conclusionNumbers2.size()==2) {
			conclusion2 = "no Bacteroides was detected from samples "+conclusionNumbers2.get(0) + " and "+ conclusionNumbers2.get(1) + ".";
		}
		else {
			conclusion2 = "no Bacteroides was detected from samples ";
			for(int i=0;i<conclusionNumbers2.size()-2;i++) {
				conclusion2+=conclusionNumbers2.get(i) + ", ";
			}
			conclusion2+= conclusionNumbers2.get(conclusionNumbers2.size()-2) + " and "+ conclusionNumbers2.get(conclusionNumbers2.size()-1) + ".";
		}
		
	}
	
	//adds a sample number to the appropriate list
	public void addConclusionNumber(Sample sample) {
		if(sample.getConclusionCase()==0) {
			conclusionNumbers1.addAll(sample.getSampleNumber());
		}
		else {
			conclusionNumbers2.addAll(sample.getSampleNumber());
		}
	}
	
}
