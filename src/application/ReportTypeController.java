package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.*;
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

		table.setRowFactory(tv -> {
			TableRow<ReportType> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if(event.getClickCount() ==2 && (!row.isEmpty())){
					FXMLLoader loader;
					Parent root;
					try {
						switch(table.getSelectionModel().getSelectedItem().getCategory()){
							case "Walk In":
								WalkInReport report2;
								report2= new WalkInReport(table.getSelectionModel().getSelectedItem().getTitle());
								loader = new FXMLLoader(getClass().getResource("/application/walkin/BasicInfoPrompt.fxml"));
								root = loader.load();
								BasicInfoController controller1 = loader.getController();
								controller1.getReport(report2);
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
			});
			return row;
		});
	}
	
	public void getReportTypeList() {
		ReportType type = new ReportType("0	","Walk In","Asbestos","N/A");
		list.add(type);
		type = new ReportType("1","Walk In","Bacteroides","N/A");
		list.add(type);
		type = new ReportType("2","Walk In","Lead","N/A");
		list.add(type);
		type = new ReportType("3","Walk In","Mould","N/A");
		list.add(type);
		type = new ReportType("4","Inspection & Air Testing","Inspection & Air Testing Report - during Type 3","N/A");
		list.add(type);
		type = new ReportType("4","Inspection & Air Testing","Pre-Abatement Inspection Report - Type 3 Formal","N/A");
		list.add(type);
		type = new ReportType("5","Inspection & Air Testing","Type 3 Summary Report - pre during post clearance","N/A");
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
		type = new ReportType("17","Asbestos and Sampling Surveys","Asbestos Survey","");
		list.add(type);
		type = new ReportType("18","Asbestos and Sampling Surveys","Bulk Sample Report","");
		list.add(type);
		type = new ReportType("19","Asbestos and Sampling Surveys","Bulk Sampling History","");
		list.add(type);
		type = new ReportType("20","Asbestos and Sampling Surveys","Bulk Sample Form (Field Form)","");
		list.add(type);
		type = new ReportType("21","Asbestos and Sampling Surveys","Surface Sampling TEM Wipes","");
		list.add(type);
		type = new ReportType("22","Asbestos and Sampling Surveys","Surface Sampling TEM Microvacuum","");
		list.add(type);
		type = new ReportType("23","Asbestos and Sampling Surveys","Asbestos Management Program with Procedures","");
		list.add(type);
		type = new ReportType("24","Asbestos and Sampling Surveys","Asbestos Bulk Sample Report Vermiculite","");
		list.add(type);



		type = new ReportType("27","Method of Procedures (MOP)","Type 2 Drilling with HEPA Dust Collection Device - with enclosure","");
		list.add(type);
		type = new ReportType("28","Method of Procedures (MOP)","Type 2 Drilling with HEPA Dust Collection Device - no enclosure","");
		list.add(type);
		type = new ReportType("29","Method of Procedures (MOP)","Type 3 Removal of Ducting (Vertical) in Building with ACM Fireproofing","");
		list.add(type);
		type = new ReportType("30","Method of Procedures (MOP)","Abatement Plan (Type 1 2 and 3)","");
		list.add(type);
		type = new ReportType("31","Lead","Lead Bulk Sample Report","");
		list.add(type);
		type = new ReportType("32","Lead","Lead and Asbestos Bulk Sample Report","");
		list.add(type);
		type = new ReportType("33","Lead","Post-Abatement Inspection and Surface Sampling Report - Class 2a","");
		list.add(type);
		type = new ReportType("34","Lead","Lead Class 3b Daily Inspection Report","");
		list.add(type);
		type = new ReportType("35","Lead","Lead Class 3b Summary Report","");
		list.add(type);
		type = new ReportType("36","Mould & Water Damage","Water Damage Assessment Report - Mould Air Sampling and Sewage Sampling","");
		list.add(type);
		type = new ReportType("37","Mould & Water Damage","Water Damage Assessment Report - with Sewage Sampling","");
		list.add(type);
		type = new ReportType("38","Mould & Water Damage","Water Damage Assessment Report - with Mould Air Sampling","");
		list.add(type);
		type = new ReportType("39","Mould & Water Damage","Water Damage Assessment Report - No Mould Air Sampling or Sewage Sampling","");
		list.add(type);
		type = new ReportType("40","Mould & Water Damage","Post-Remediation Verification Report - Post Insp and Testing Only","");
		list.add(type);
		type = new ReportType("41","Mould & Water Damage","Mould Remediation Close-Out Report - Full Version","");
		list.add(type);
		type = new ReportType("42","Mould & Water Damage","Determination of Sewage Contamination Report","");
		list.add(type);
		type = new ReportType("43","Mould & Water Damage","Mould and Water Damage Assessment Report","");
		list.add(type);
		type = new ReportType("44","Construction","Dust Control Plan","");
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
					WalkInReport report2;
					report2= new WalkInReport(table.getSelectionModel().getSelectedItem().getTitle());
					loader = new FXMLLoader(getClass().getResource("/application/walkin/BasicInfoPrompt.fxml"));
					root = loader.load();
					BasicInfoController controller1 = loader.getController();
					controller1.getReport(report2);
					break;
				default:
					report = new Report(table.getSelectionModel().getSelectedItem().getTitle());
					setInformation(table.getSelectionModel().getSelectedItem().getTitle());
					loader = new FXMLLoader(getClass().getResource("application/InfoMaker.fxml"));
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
		switch(reportType) {
			case "Inspection & Air Testing Report - during Type 3":
				report.setReportType("AirTesting1");
				report.getInfo().setProjectNumberExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setProjectPostalCodeExist(true);
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientContactExist(true);
				report.getInfo().setBuildingNameExist(true);
				report.getInfo().setSpecificLocationExist(true);
				report.getInfo().setVisualAbatementStartExist(true);
				report.getInfo().setSelRepExist(true);
				report.getInfo().setOnSiteTimeExist(true);
				report.getInfo().setReportDateExist(true);
				break;

			case "Pre-Abatement Inspection Report - Type 3 Formal":
				report.setReportType("AirTesting2");
				report.getInfo().setReportDateExist(true);
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setCompanyAddressExist(true);
				report.getInfo().setCompanyCityExist(true);
				report.getInfo().setCompanyProvinceExist(true);
				report.getInfo().setCompanyPostalCodeExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientPositionExist(true);
				report.getInfo().setProjectNumberExist(true);
				report.getInfo().setSpecificLocationExist(true);
				report.getInfo().setBuildingNameExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setProjectPostalCodeExist(true);
				report.getInfo().setOnSiteTimeExist(true);
				break;

			case "Type 3 Summary Report - pre during post clearance":
				report.setReportType("AirTesting3");
				report.getInfo().setSpecificLocationExist(true);
				report.getInfo().setBuildingNameExist(true);
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
				report.getInfo().setProjectNumberExist(true);
				report.getInfo().setPreAbatementStartDateExist(true);
				report.getInfo().setSiteEndDateExist(true);
				break;

			case "Type 3 Summary Report - pre post clearance":
				report.setReportType("AirTesting4");
				report.getInfo().setProjectNumberExist(true);

			case "Type 3 Close Out Report":
				report.setReportType("AirTesting5");
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
				break;

			case "Post-Abatement Inspection and Air Testing Report - Greenrock Properties":
				report.setReportType("AirTesting6");
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
				break;

			case "Type 2 Summary Report":
				report.setReportType("AirTesting7");
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
				break;

			case "Post-Abatement Inspection Report - Building Demolition Type 3":
				report.setReportType("AirTesting8");
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
				break;

			case "Post-Abatement Inspection Report - Type 2 Visual Only":
				report.setReportType("AirTesting9");
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
				break;

			case "PCM Air Sampling - Residential":
				report.setReportType("AirTesting10");
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
				break;

			case "PCM Air Sampling - Construction Projects":
				report.setReportType("AirTesting11");
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
				break;

			case "Type 3 Clearance Report - Final Visual and Air Sampling":
				report.setReportType("AirTesting12");
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
				break;

			case "TEM Air Sampling During Outdoor Type 3 Operation":
				report.setReportType("AirTesting13");
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
				break;
			case "Inspection and Air Testing during Abatement_Outdoor Type 3":
				report.setReportType("AirTesting14");
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
				break;
			case "Asbestos Survey":

				break;
			case "Bulk Sample Report":
				report.getInfo().setReportDateExist(true);
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientPositionExist(true);
				report.getInfo().setProjectNumberExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setProjectPostalCodeExist(true);
				report.getInfo().setPreAbatementStartDateExist(true);
				report.getInfo().setTechnicianExist(true);
				report.getInfo().setProjectManagerExist(true);
				break;
			case "Surface Sampling TEM Wipes":
				report.getInfo().setProjectNameExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientPositionExist(true);
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setCompanyAddressExist(true);
				report.getInfo().setCompanyCityExist(true);
				report.getInfo().setCompanyProvinceExist(true);
				report.getInfo().setTechnicianExist(true);
				report.getInfo().setProjectManagerExist(true);
				report.getInfo().setProjectNumberExist(true);
				report.getInfo().setReportDateExist(true);
				report.getInfo().setSiteWorkDateExist(true);
				break;
			case "Surface Sampling TEM Microvacuum":
				report.getInfo().setProjectNameExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientPositionExist(true);
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setCompanyAddressExist(true);
				report.getInfo().setCompanyCityExist(true);
				report.getInfo().setCompanyProvinceExist(true);
				report.getInfo().setTechnicianExist(true);
				report.getInfo().setProjectManagerExist(true);
				report.getInfo().setProjectNumberExist(true);
				report.getInfo().setReportDateExist(true);
				report.getInfo().setSiteWorkDateExist(true);
				break;
			case "Asbestos Management Program with Procedures":
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientAddressExist(true);
				report.getInfo().setClientCityExist(true);
				report.getInfo().setClientProvinceExist(true);
				break;
			case "Asbestos Bulk Sample Report Vermiculite":
				report.getInfo().setReportDateExist(true);
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setCompanyAddressExist(true);
				report.getInfo().setCompanyCityExist(true);
				report.getInfo().setCompanyProvinceExist(true);
				report.getInfo().setCompanyPostalCodeExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientPositionExist(true);
				report.getInfo().setBuildingNameExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setSiteWorkDateExist(true);
				report.getInfo().setVisualAbatementStartExist(true);
				break;

			case "Type 2 Drilling with HEPA Dust Collection Device - with enclosure":
				report.getInfo().setProjectNameExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientAddressExist(true);
				report.getInfo().setClientCityExist(true);
				report.getInfo().setClientProvinceExist(true);
				report.getInfo().setClientPostalCodeExist(true);
				report.getInfo().setReportDateExist(true);
				break;

			case "Type 2 Drilling with HEPA Dust Collection Device - no enclosure":
				break;

			case "Type 3 Removal of Ducting (Vertical) in Building with ACM Fireproofing":
				report.getInfo().setProjectNameExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientAddressExist(true);
				report.getInfo().setClientCityExist(true);
				report.getInfo().setClientProvinceExist(true);
				report.getInfo().setClientPostalCodeExist(true);
				report.getInfo().setReportDateExist(true);
				break;

			case "Abatement Plan (Type 1 2 and 3)":
				report.getInfo().setProjectNameExist(true);
				report.getInfo().setSpecificLocationExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientAddressExist(true);
				report.getInfo().setClientCityExist(true);
				report.getInfo().setClientProvinceExist(true);
				report.getInfo().setClientPostalCodeExist(true);
				report.getInfo().setReportDateExist(true);
				break;

			case "Lead Bulk Sample Report":
				report.getInfo().setReportDateExist(true);
				report.getInfo().setCompanyNameExist(true);
				report.getInfo().setCompanyAddressExist(true);
				report.getInfo().setCompanyCityExist(true);
				report.getInfo().setCompanyProvinceExist(true);
				report.getInfo().setCompanyPostalCodeExist(true);
				report.getInfo().setClientNameExist(true);
				report.getInfo().setClientPositionExist(true);
				report.getInfo().setProjectAddressExist(true);
				report.getInfo().setProjectCityExist(true);
				report.getInfo().setProjectProvinceExist(true);
				report.getInfo().setVisualAbatementStartExist(true);
				report.getInfo().setProjectManagerExist(true);
				break;

			case "Lead and Asbestos Bulk Sample Report":
				break;

			case "Post-Abatement Inspection and Surface Sampling Report - Class 2a":
				break;

			case "Lead Class 3b Daily Inspection Report":
				break;

			case "Lead Class 3b Summary Report":
				break;

			case "Water Damage Assessment Report - Mould Air Sampling and Sewage Sampling":
				break;

			case "Water Damage Assessment Report - with Sewage Sampling":
				break;

			case "Water Damage Assessment Report - with Mould Air Sampling":
				break;

			case "Water Damage Assessment Report - No Mould Air Sampling or Sewage Sampling":
				break;

			case "Post-Remediation Verification Report - Post Insp and Testing Only":
				break;

			case "Mould Remediation Close-Out Report - Full Version":
				break;

			case "Determination of Sewage Contamination Report":
				break;

			case "Mould and Water Damage Assessment Report":
				break;

			case "Dust Control Plan":
				break;


		}
	}
}