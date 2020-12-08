package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.walkin.BasicInfoController;
import application.walkin.WalkInReport;
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
	WalkInReport walkInReport;
	
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
		ReportType type = new ReportType("Walk In","Walk In","Asbestos","N/A");
		list.add(type);
		type = new ReportType("Walk In","Walk In","Bacteroides","N/A");
		list.add(type);
		type = new ReportType("Walk In","Walk In","Lead","N/A");
		list.add(type);
		type = new ReportType("Walk In","Walk In","Mould","N/A");
		list.add(type);
	}
	
	public void uncheckButton() {
		if(table.getSelectionModel().getSelectedItem()!=null) {
			confirm.setDisable(false);
		}
	}
	
	public void selectReportType(ActionEvent event) {
		walkInReport= new WalkInReport(table.getSelectionModel().getSelectedItem().getTitle());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/walkin/BasicInfoPrompt.fxml"));
	        Parent root = loader.load();
	        BasicInfoController controller = loader.getController();
	        controller.getReport(walkInReport);

	        Stage stage = new Stage();
	        stage.setScene(new Scene(root));
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
}