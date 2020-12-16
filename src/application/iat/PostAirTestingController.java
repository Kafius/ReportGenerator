package application.iat;

import application.iat.sample.PostAirTestingSample;
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

public class PostAirTestingController implements Initializable {
    //visuals
    @FXML private Label title;
    @FXML private Label subtitle;
    @FXML private Line topBorder;
    @FXML private Line bottomBorder;
    @FXML private ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/pictures/safetechlogo1.png")));
    @FXML private GridPane entry;
    @FXML private Rectangle inputSection;
    @FXML private HBox buttonSection;

    @FXML private TableView<PostAirTestingSample> table;
    @FXML private TableColumn<PostAirTestingSample, String> number;
    @FXML private TableColumn<PostAirTestingSample, String> location;
    @FXML private TableColumn<PostAirTestingSample, String> volume;
    @FXML private TableColumn<PostAirTestingSample, String> airborneFibreConc;

    @FXML private TextField numberTF;
    @FXML private TextField locationTF;
    @FXML private TextField volumeTF;
    @FXML private TextField airborneFibreConcTF;

    @FXML private Label numberL;
    @FXML private Label locationL;
    @FXML private Label volumeL;
    @FXML private Label  airborneFibreConcL;

    @FXML private Button addSample;
    @FXML private Button removeSample;
    @FXML private Button nextButton;
    @FXML private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        number.setCellValueFactory(new PropertyValueFactory<PostAirTestingSample, String>("number"));
        location.setCellValueFactory(new PropertyValueFactory<PostAirTestingSample, String>("location"));
        volume.setCellValueFactory(new PropertyValueFactory<PostAirTestingSample, String>("volume"));
        airborneFibreConc.setCellValueFactory(new PropertyValueFactory<PostAirTestingSample, String>("airborneFibreConc"));
    }

    public void addEntry(ActionEvent event) {
        if(checkEntryInputs()) {
            String materialDescriptionFinal;
            PostAirTestingSample newPostAirTestingSample = new PostAirTestingSample(numberTF.getText(), locationTF.getText(), volumeTF.getText(), airborneFibreConcTF.getText());
            table.getItems().add(newPostAirTestingSample);

            numberTF.clear();
            locationTF.clear();
            volumeTF.clear();
            airborneFibreConcTF.clear();
        }
    }

    public void removeEntry(ActionEvent event) {
        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
    }

    public boolean checkEntryInputs() {
        if(numberTF.getText().equals("")||
                locationTF.getText().equals("")||
                volumeTF.getText().equals("")||
                airborneFibreConcTF.getText().equals("")) {
            missingInputError();
            return false;
        }
        else {
            try {
                Double.parseDouble(volumeTF.getText());
                Double.parseDouble(airborneFibreConcTF.getText());
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
