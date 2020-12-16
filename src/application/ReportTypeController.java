package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.iat.report.*;
import application.walkin.BasicInfoController;
import application.walkin.report.WalkInReport;
import data.ReportType;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class ReportTypeController implements Initializable{
	Report report;
	
	//Visuals
	@FXML private Label mainTitle;
	@FXML private Label subtitle;
	@FXML private Line top;
	@FXML private Line bottom;
	@FXML private ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/pictures/safetechlogo1.png")));
		
	//table data
	@FXML private TableView<ReportType> table;
	@FXML private TableColumn<ReportType, String> title;
	@FXML private TableColumn<ReportType, String> comments;
	
	@FXML private TextField searchField;
	@FXML private ChoiceBox<String> categoryField;
	
	@FXML private Button confirm;
		
	public ObservableList<ReportType> list = FXCollections.observableArrayList();
	ObservableList<ReportType> filteredList;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) { 
		
		confirm.setDisable(true);
		getReportTypeList();
		
		title.setCellValueFactory(new PropertyValueFactory<ReportType, String>("title"));
		comments.setCellValueFactory(new PropertyValueFactory<ReportType, String>("comments"));
		table.setItems(list);
		
		categoryField.getItems().addAll("All","Inspection & Air Testing","Asbestos Sampling & Surveys","Designated Substance Surveys","Method of Procedures (MOP)","Lead","Mould & Water Damage","Construction","Walk In");
		categoryField.setValue("All");
		
		searchField.textProperty().addListener((observable, oldText, newText) -> {
			narrow();
        });
		
		categoryField.getSelectionModel().selectedItemProperty().addListener( (observableValue,oldValue,newValue) -> {
			narrow();
		});
	}
	
	public void getReportTypeList() {
		ReportType type = new ReportType("0","Walk In","Asbestos","N/A");
		list.add(type);
		type = new ReportType("1","Walk In","Bacteroides","N/A");
		list.add(type);
		type = new ReportType("2","Walk In","Lead","N/A");
		list.add(type);
		type = new ReportType("3","Walk In","Mould","N/A");
		list.add(type);
		type = new ReportType("4","Inspection & Air Testing","Inspection & Air Testing Report - during Type 3","N/A");
		list.add(type);
		type = new ReportType("4","Inspection & Air Testing","Pre-Abatement Inspection Report_Type 3_Formal","N/A");
		list.add(type);
		type = new ReportType("5","Inspection & Air Testing","Type 3 Summary Report_Pre, During, Post, Clearance","N/A");
		list.add(type);
		type = new ReportType("6","Inspection & Air Testing","Type 3 Summary Report_Pre, Post, Clearance","N/A");
		list.add(type);
		type = new ReportType("7","Inspection & Air Testing","Type 3 Close Out Report","N/A");
		list.add(type);
		type = new ReportType("8","Inspection & Air Testing","Post-Abatement Inspection & Air Testing Report - Greenrock Properties","N/A");
		list.add(type);
		type = new ReportType("9","Inspection & Air Testing","Type 2 Summary Report","N/A");
		list.add(type);
		type = new ReportType("10","Inspection & Air Testing","Post-Abatement Inspection Report_Building Demolition_Type 3","N/A");
		list.add(type);
		type = new ReportType("11","Inspection & Air Testing","Post-Abatement Inpsection Report_Type 2 visual only","N/A");
		list.add(type);
		type = new ReportType("12","Inspection & Air Testing","PCM Air Sampling - Residential","N/A");
		list.add(type);
		type = new ReportType("13","Inspection & Air Testing","PCM Air Sampling - Construction Projects","N/A");
		list.add(type);
		type = new ReportType("14","Inspection & Air Testing","Type 3 Clearance Report_Final Visual & Air Sampling","N/A");
		list.add(type);
		type = new ReportType("15","Inspection & Air Testing","TEM Air Sampling_during outdoor Type 3 opeation","N/A");
		list.add(type);
		type = new ReportType("16","Inspection & Air Testing","Inspection and Air Testing during Abatement_Outdoor Type 3","N/A");
		list.add(type);

	}
	
	public void uncheckButton() {
		if(table.getSelectionModel().getSelectedItem()!=null) {
			confirm.setDisable(false);
		}
	}
	
	public void selectReportType(ActionEvent event) {
		FXMLLoader loader;
		Parent root;
		try {
			switch(table.getSelectionModel().getSelectedItem().getCategory()){
				case "Walk In":
					report= new WalkInReport(table.getSelectionModel().getSelectedItem().getTitle());
					loader = new FXMLLoader(getClass().getResource("/application/walkin/BasicInfoPrompt.fxml"));
					root = loader.load();
					BasicInfoController controller1 = loader.getController();
					controller1.getReport(report);
					break;
				default:
					report = new Report(table.getSelectionModel().getSelectedItem().getTitle());
					setInformation(table.getSelectionModel().getSelectedItem().getTitle());
					loader = new FXMLLoader(getClass().getResource("/application/InfoMaker.fxml"));
					root = loader.load();
					InfoController controller = loader.getController();
					controller.getReport(report);
					break;
			}


	        Stage stage = new Stage();
	        stage.setScene(new Scene(root));
	        stage.setResizable(false);
	        stage.show();
	        
	        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
	        thisStage.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void narrow(){
		filteredList = FXCollections.observableArrayList();
		if(!categoryField.getValue().equals("All")) {
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getCategory().equals(categoryField.getValue())) {
					if(list.get(i).getTitle().toUpperCase().startsWith(searchField.getText().toUpperCase())) {
						filteredList.add(list.get(i));
					}
					else if(list.get(i).getTitle().toUpperCase().contains(searchField.getText().toUpperCase())) {
						filteredList.add(list.get(i));
					}
				}
			}
			table.setItems(filteredList);
		}
		else if(!searchField.getText().trim().isEmpty()){
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getTitle().toUpperCase().startsWith(searchField.getText().toUpperCase())) {
					filteredList.add(list.get(i));
				}
				else if(list.get(i).getTitle().toUpperCase().contains(searchField.getText().toUpperCase())) {
					filteredList.add(list.get(i));
				}
			}
			table.setItems(filteredList);
		}
		else {
			table.setItems(list);
		}
	}

	public void setInformation(String reportType){
		switch(reportType){
			case "Inspection & Air Testing Report - during Type 3":
				report = new siteInspectionReport();
				report.getInfo().setProjectNumberExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setProjectPostalCodeExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setSpecificLocationExist(true);
				report.getInfo().setSiteWorkDateExist(true);
				report.getInfo().setSelRepExist(true);
				break;

			case "Pre-Abatement Inspection Report - Type 3 Formal":
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setCompanyAddressExist(true);
				report.getInfo().setCompanyCityExist(true);
				report.getInfo().setCompanyProvinceExist(true);
				report.getInfo().setCompanyPostalCodeExist(true);
				report.getInfo().setProjectNumberExist(true);
				report.getInfo().setSpecificLocationExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setOnSiteTimeExist(true);

			case "Type 3 Summary Report - pre during post clearance":
				report = new AsbestosAbatementCloseOutReport();
				report.getInfo().setSpecificLocationExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectPostalCodeExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientPositionExist(true);
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setCompanyAddressExist(true);
				report.getInfo().setCompanyCityExist(true);
				report.getInfo().setCompanyProvinceExist(true);
				report.getInfo().setCompanyPostalCodeExist(true);
				report.getInfo().setTechnicianExist(true);
				report.getInfo().setProjectManagerExist(true);
				report.getInfo().setProjectNumberExist(true);
				report.getInfo().setPreAbatementStartDateExist(true);
				report.getInfo().setVisualAbatementStartExist(true);
				report.getInfo().setVisualAbatementEndExist(true);
				report.getInfo().setPostAbatementDateExist(true);
				report.getInfo().setSiteEndDateExist(true);

			case "Type 3 Summary Report - pre post clearance":
				report.getInfo().setProjectNumberExist(true);

			case "Type 3 Close Out Report":
				report = new Type3CloseOutReport();
				report.getInfo().setSpecificLocationExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientPositionExist(true);
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setCompanyAddressExist(true);
				report.getInfo().setCompanyCityExist(true);
				report.getInfo().setCompanyProvinceExist(true);
				report.getInfo().setCompanyPostalCodeExist(true);
				report.getInfo().setTechnicianExist(true);
				report.getInfo().setProjectManagerExist(true);
				report.getInfo().setProjectNumberExist(true);
				report.getInfo().setPreAbatementStartDateExist(true);
				report.getInfo().setVisualAbatementStartExist(true);
				report.getInfo().setVisualAbatementEndExist(true);
				report.getInfo().setPostAbatementDateExist(true);
				report.getInfo().setSiteWorkDateExist(true);

			case "Post-Abatement Inspection and Air Testing Report - Greenrock Properties":
				report = new IATGreenrockReport();
				report.getInfo().setProjectNumberExist(true);
				report.getInfo().setSpecificLocationExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectPostalCodeExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientPositionExist(true);
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setCompanyAddressExist(true);
				report.getInfo().setCompanyCityExist(true);
				report.getInfo().setCompanyProvinceExist(true);
				report.getInfo().setCompanyPostalCodeExist(true);
				report.getInfo().setTechnicianExist(true);
				report.getInfo().setProjectManagerExist(true);
				report.getInfo().setPreAbatementStartDateExist(true);
				report.getInfo().setVisualAbatementStartExist(true);
				report.getInfo().setVisualAbatementEndExist(true);
				report.getInfo().setSiteWorkDateExist(true);

			case "Type 2 Summary Report":

				report.getInfo().setSpecificLocationExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setProjectPostalCodeExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientPositionExist(true);
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setCompanyAddressExist(true);
				report.getInfo().setCompanyCityExist(true);
				report.getInfo().setCompanyProvinceExist(true);
				report.getInfo().setCompanyPostalCodeExist(true);
				report.getInfo().setTechnicianExist(true);
				report.getInfo().setProjectManagerExist(true);
				report.getInfo().setProjectNumberExist(true);
				report.getInfo().setPreAbatementStartDateExist(true);
				report.getInfo().setVisualAbatementStartExist(true);
				report.getInfo().setVisualAbatementEndExist(true);
				report.getInfo().setSiteWorkDateExist(true);

			case "Post-Abatement Inspection Report - Building Demolition Type 3":
				report.getInfo().setSpecificLocationExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setProjectPostalCodeExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientPositionExist(true);
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setCompanyAddressExist(true);
				report.getInfo().setCompanyCityExist(true);
				report.getInfo().setCompanyProvinceExist(true);
				report.getInfo().setCompanyPostalCodeExist(true);
				report.getInfo().setTechnicianExist(true);
				report.getInfo().setProjectManagerExist(true);
				report.getInfo().setProjectNumberExist(true);
				report.getInfo().setVisualAbatementEndExist(true);
				report.getInfo().setSiteWorkDateExist(true);

			case "Post-Abatement Inspection Report - Type 2 Visual Only":
				report.getInfo().setSpecificLocationExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setProjectPostalCodeExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientPositionExist(true);
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setCompanyAddressExist(true);
				report.getInfo().setCompanyCityExist(true);
				report.getInfo().setCompanyProvinceExist(true);
				report.getInfo().setCompanyPostalCodeExist(true);
				report.getInfo().setTechnicianExist(true);
				report.getInfo().setProjectManagerExist(true);
				report.getInfo().setProjectNumberExist(true);
				report.getInfo().setVisualAbatementEndExist(true);
				report.getInfo().setSiteWorkDateExist(true);

			case "PCM Air Sampling - Residential":
				report = new ASResidentialReport();
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientAddressExist(true);
				report.getInfo().setClientCityExist(true);
				report.getInfo().setClientProvinceExist(true);
				report.getInfo().setClientPostalCodeExist(true);
				report.getInfo().setProjectNumberExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setProjectPostalCodeExist(true);
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setSiteEndDateExist(true);

			case "PCM Air Sampling - Construction Projects":
				report = new ASConstructingReport();
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientAddressExist(true);
				report.getInfo().setClientCityExist(true);
				report.getInfo().setClientProvinceExist(true);
				report.getInfo().setClientPostalCodeExist(true);
				report.getInfo().setProjectNumberExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setProjectPostalCodeExist(true);
				report.getInfo().setSiteWorkDateExist(true);

			case "Type 3 Clearance Report - Final Visual and Air Sampling":
				report.getInfo().setSpecificLocationExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectPostalCodeExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientPositionExist(true);
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setCompanyAddressExist(true);
				report.getInfo().setCompanyCityExist(true);
				report.getInfo().setCompanyProvinceExist(true);
				report.getInfo().setCompanyPostalCodeExist(true);
				report.getInfo().setTechnicianExist(true);
				report.getInfo().setProjectManagerExist(true);
				report.getInfo().setProjectNumberExist(true);
				report.getInfo().setInspectionStartDateExist(true);

			case "TEM Air Sampling During Outdoor Type 3 Operation":
				report.getInfo().setReportDateExist(true);
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setCompanyAddressExist(true);
				report.getInfo().setCompanyCityExist(true);
				report.getInfo().setCompanyProvinceExist(true);
				report.getInfo().setCompanyPostalCodeExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectPostalCodeExist(true);
				report.getInfo().setInspectionStartDateExist(true);
				report.getInfo().setProjectManagerExist(true);
			case "Inspection and Air Testing during Abatement_Outdoor Type 3":
				report.getInfo().setReportDateExist(true);
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setCompanyAddressExist(true);
				report.getInfo().setCompanyCityExist(true);
				report.getInfo().setCompanyProvinceExist(true);
				report.getInfo().setCompanyPostalCodeExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientPositionExist(true);
				report.getInfo().setProjectNameExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setProjectPostalCodeExist(true);
				report.getInfo().setVisualAbatementStartExist(true);
				report.getInfo().setTechnicianExist(true);
				report.getInfo().setProjectManagerExist(true);
		}
	}
}