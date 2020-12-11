package application.iat;

import application.iat.sample.AirTestingSample;
import application.iat.sample.TEMAsbestosFibreSample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class TEMAsbestosFibreController implements Initializable {
    //visuals
    @FXML private Label title;
    @FXML private Label subtitle;
    @FXML private Line topBorder;
    @FXML private Line bottomBorder;
    @FXML private ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/pictures/safetechlogo1.png")));
    @FXML private GridPane entry;
    @FXML private Rectangle inputSection;
    @FXML private HBox buttonSection;

    @FXML private TableView<TEMAsbestosFibreSample> table;
    @FXML private TableColumn<TEMAsbestosFibreSample, String> number;
    @FXML private TableColumn<TEMAsbestosFibreSample, String> location;
    @FXML private TableColumn<TEMAsbestosFibreSample, String> PCMConcentration;
    @FXML private TableColumn<TEMAsbestosFibreSample, String> TEMConcetration;
    @FXML private TableColumn<TEMAsbestosFibreSample, String> asbestosTypes;

    @FXML private TextField numberTF;
    @FXML private TextField locationTF;
    @FXML private TextField PCMConcentrationTF;
    @FXML private TextField TEMConcetrationTF;
    @FXML private ComboBox<String> asbestosTypesCB;

    @FXML private Label numberL;
    @FXML private Label locationL;
    @FXML private Label PCMConcentrationL;
    @FXML private Label TEMConcetrationL;
    @FXML private Label asbestosTypesL;

    @FXML private Button addSample;
    @FXML private Button removeSample;
    @FXML private Button nextButton;
    @FXML private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        number.setCellValueFactory(new PropertyValueFactory<TEMAsbestosFibreSample, String>("number"));
        location.setCellValueFactory(new PropertyValueFactory<TEMAsbestosFibreSample, String>("location"));
        PCMConcentration.setCellValueFactory(new PropertyValueFactory<TEMAsbestosFibreSample, String>("PCMConcentration"));
        TEMConcetration.setCellValueFactory(new PropertyValueFactory<TEMAsbestosFibreSample, String>("TEMConcetration"));
        asbestosTypes.setCellValueFactory(new PropertyValueFactory<TEMAsbestosFibreSample, String>("asbestosTypes"));
    }

    public void addEntry(ActionEvent event) {
        if(checkEntryInputs()) {
            String materialDescriptionFinal;
            TEMAsbestosFibreSample newTEMAsbestosFibreSample = new TEMAsbestosFibreSample(numberTF.getText(),locationTF.getText(), PCMConcentrationTF.getText(), TEMConcetrationTF.getText(), asbestosTypesCB.getValue());
            table.getItems().add(newTEMAsbestosFibreSample);

            numberTF.clear();
            locationTF.clear();
            PCMConcentrationTF.clear();
            TEMConcetrationTF.clear();
            asbestosTypesCB.setValue("");
        }
    }

    public void removeEntry(ActionEvent event) {
        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
    }

    public boolean checkEntryInputs() {
        if(numberTF.getText().equals("")||
                locationTF.getText().equals("")||
                PCMConcentrationTF.getText().equals("")||
                TEMConcetrationTF.getText().equals("")||
                asbestosTypesCB.getValue().equals("")) {
            missingInputError();
            return false;
        }
        else {
            try {
                Double.parseDouble(PCMConcentrationTF.getText());
                Double.parseDouble(TEMConcetrationTF.getText());
            }
            catch(Exception e) {
                invalidInputError();
                return false;
            }
        }
        return true;
    }

    public void missingInputError() {
        Alert invalidInputs = new Alert(Alert.AlertType.ERROR);
        invalidInputs.setTitle("New Sample");
        invalidInputs.setHeaderText("Missing Inputs");
        invalidInputs.setContentText("Complete sample info before submitting.");
        ButtonType okayButton = new ButtonType("Okay");
        invalidInputs.getButtonTypes().setAll(okayButton);
        invalidInputs.show();
        return;
    }

    public void invalidInputError() {
        Alert invalidInputs = new Alert(Alert.AlertType.ERROR);
        invalidInputs.setTitle("New Sample");
        invalidInputs.setHeaderText("Invalid Input");
        invalidInputs.setContentText("Please enter a valid content number");
        ButtonType okayButton = new ButtonType("Okay");
        invalidInputs.getButtonTypes().setAll(okayButton);
        invalidInputs.show();
        return;
    }
}
