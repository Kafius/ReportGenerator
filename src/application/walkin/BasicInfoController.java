package application.walkin;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import application.Report;
import application.walkin.report.WalkInReport;
import javafx.application.Platform;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BasicInfoController implements Initializable{
	WalkInReport newReport;
	
	//Visuals
	@FXML private Label title, subtitle, companyInfoLabel, projectInfoLabel;
	@FXML private Line topBorder, bottomBorder;
	@FXML private Rectangle reportSection, companySection, clientSection;
	@FXML private ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/pictures/safetechlogo1.png")));
	
	//grid for report info
	@FXML private GridPane reportInfoGrid;
	
	ObservableList<String> companyNames = FXCollections.observableArrayList();
	
	//Boxes for organization
	@FXML private VBox companyInfoBox;
	@FXML private HBox companyNameSection;
	@FXML private HBox companyAddressSection;
	
	@FXML private VBox projectInfoBox;
	@FXML private HBox clientNameSection;
	@FXML private HBox clientAddressSection;
	
	@FXML private HBox buttonSection;
	
	//datepickers for report info
	@FXML private DatePicker reportDatePicker, samplingDatePicker;
	
	//buttons for report info
	@FXML private Button changeButton, nextButton;
	
	//Labels for Report Info
	@FXML private Label reportDateL, companyNameL, companyAddressL, companyCityL, companyProvinceL, companyPostalCodeL, clientNameL, projectNumberL, projectAddressL, projectCityL, projectProvinceL, samplingDateL;
	
	//Input fields for Report Info
	@FXML private TextField companyAddressTF, companyCityTF,companyPostalCodeTF,clientNameTF, projectNumberTF, projectAddressTF, projectCityTF;
	@FXML private ComboBox<String> companyNameCB;	
	@FXML private ChoiceBox<String> companyProvinceCB, projectProvinceCB, clientNameTitles;
	
	//sends basic info to next prompt and saves into report
	@FXML
	public void submitBasicInfo(ActionEvent event) {
		if(checkTextFields()) {
			newReport.info.setReportDate(reportDatePicker.getValue().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
			newReport.info.setSamplingDate(samplingDatePicker.getValue().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
			newReport.info.setCompanyName(companyNameCB.getValue());
			newReport.info.setCompanyAddress(companyAddressTF.getText());
			newReport.info.setCompanyCity(companyCityTF.getText());
			newReport.info.setCompanyProvince(companyProvinceCB.getValue());
			newReport.info.setCompanyPostalCode(companyPostalCodeTF.getText());
			newReport.info.setClientName(clientNameTitles.getValue()+" "+clientNameTF.getText());
			if(companyNameCB.getValue().equals("Home Owner")) {
				newReport.info.copyAddresses();
			}
			else {
				newReport.info.setProjectAddress(projectAddressTF.getText());
				newReport.info.setProjectCity(projectCityTF.getText());
				newReport.info.setProjectProvince(projectProvinceCB.getValue());
			}
			newReport.info.setProjectNumber(projectNumberTF.getText());	
		}
		else {
			Alert invalidInputs = new Alert(AlertType.ERROR);
			invalidInputs.setTitle("New Report");
			invalidInputs.setHeaderText("Missing Inputs");
			invalidInputs.setContentText("Fill in all missing entries."); 
			ButtonType okayButton = new ButtonType("Okay");
			invalidInputs.getButtonTypes().setAll(okayButton);
			invalidInputs.show();
			return;
		}
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/walkin/"+newReport.getReportType()+"Maker.fxml"));
			Parent root = (Parent) loader.load();
			if(newReport.getReportType().equals("Asbestos")) {
				AsbestosController table = loader.getController();
				table.getReportData(newReport);
			}
			else if(newReport.getReportType().equals("Bacteroides")) {
				BacteroidesController table = loader.getController();
				table.getReportData(newReport);
			}
			else if(newReport.getReportType().equals("Lead")) {
				LeadController table = loader.getController();
				table.getReportData(newReport);
			}
			else {
				MouldGrowthController table = loader.getController();
				table.getReportData(newReport);
			}
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void changeReports(ActionEvent event) {
		Alert confirmChange = new Alert(AlertType.WARNING);
		confirmChange.setTitle("Report Generator");
		confirmChange.setHeaderText("Change Report Type");
		confirmChange.setContentText("Are you sure you want to change reports? All entered data will be lost"); 
		ButtonType yesButton = new ButtonType("Yes");
		ButtonType cancelButton = new ButtonType("Cancel");
		confirmChange.getButtonTypes().setAll(yesButton, cancelButton);
		Optional<ButtonType> result = confirmChange.showAndWait();
		if(result.get()==yesButton) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ReportTypeMaker.fxml"));
				Parent root = (Parent) loader.load();	
				
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			return;
		}
	}
	
	//gets data from previous prompt
	public void getReport(WalkInReport report) {
		newReport=report;
		System.out.println(newReport.info.getCompanyName());
		companyNameCB.setValue(newReport.info.getCompanyName());
		companyAddressTF.setText(newReport.info.getCompanyAddress());
		companyCityTF.setText(newReport.info.getCompanyCity());
		companyPostalCodeTF.setText(newReport.info.getCompanyPostalCode());
		companyProvinceCB.setValue(newReport.info.getCompanyProvince());
		clientNameTF.setText(newReport.info.getClientName());
		projectNumberTF.setText(newReport.info.getProjectNumber());
		projectAddressTF.setText(newReport.info.getProjectAddress());
		projectCityTF.setText(newReport.info.getProjectCity());
		projectProvinceCB.setValue(newReport.info.getProjectProvince());
	}
	
	//checks textfields for invalid inputs
	public boolean checkTextFields() {
		if(reportDatePicker.getValue()==null||
				samplingDatePicker.getValue()==null||
				companyNameCB.getValue().equals("")||
				companyAddressTF.getText().equals("")||
				companyCityTF.getText().equals("")||
				companyProvinceCB.getValue().equals("")||
				companyPostalCodeTF.getText().equals("")||
				clientNameTF.getText().equals("")||
				(!companyNameCB.getValue().equals("Home Owner")&&
						projectAddressTF.getText().equals("")||
						projectCityTF.getText().equals("")||
						projectProvinceCB.getValue().equals("")))
				 {
			return false;
		}
		return true;
	}
	
	//shuts down 
	public static void shutdown() {
		Platform.exit();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clientNameTitles.getItems().addAll("Mr.","Mrs.","");
		clientNameTitles.setValue("Mr.");

		companyProvinceCB.getItems().addAll("Newfoundland and Labrador","Prince Edward Island","Nova Scotia","New Brunswick","Quebec","Ontario","Manitoba","Saskatchewan","Alberta","British Columbia");
		companyProvinceCB.setValue("--");

		projectProvinceCB.getItems().addAll("Newfoundland and Labrador","Prince Edward Island","Nova Scotia","New Brunswick","Quebec","Ontario","Manitoba","Saskatchewan","Alberta","British Columbia");
		projectProvinceCB.setValue("--");
		
		try {
			Scanner r = new Scanner(getClass().getResourceAsStream("/data/companyNames.txt"));
		    while (r.hasNextLine()) {
		        companyNames.addAll(r.nextLine());
		    }
		    r.close();
		    companyNameCB.setItems(companyNames);
		    companyNameCB.setEditable(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		companyNameCB.valueProperty().addListener((observable,oldValue,newValue)->{
			if(newValue.equals("Home Owner")) {
				projectAddressTF.setDisable(true);
				projectCityTF.setDisable(true);
				projectProvinceCB.setDisable(true);

				companyAddressTF.textProperty().addListener((observable1,oldValue1,newValue1)->{
					projectAddressTF.setText(companyAddressTF.getText());
				});
				companyCityTF.textProperty().addListener((observable2,oldValue2,newValue2)->{
					projectCityTF.setText(companyCityTF.getText());
				});
				companyProvinceCB.valueProperty().addListener((observable3,oldValue3,newValue3)->{
					projectProvinceCB.setValue(companyProvinceCB.getValue());
				});
			}
			else {
				projectAddressTF.setDisable(false);
				projectCityTF.setDisable(false);
				projectProvinceCB.setDisable(false);
			}
		});
	}
	
}