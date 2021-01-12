package application;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.walkin.*;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import application.walkin.report.WalkInReport;

public class GenerateAT3Controller implements Initializable{
    Report report;

    //visuals
    @FXML private Label title, subtitle;

    @FXML private Line topBorder, bottomBorder;

    @FXML private ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/pictures/safetechlogo1.png")));
    @FXML private ImageView googledriveicon = new ImageView(new Image(getClass().getResourceAsStream("/pictures/googledriveicon.png")));
    @FXML private ImageView wordicon = new ImageView(new Image(getClass().getResourceAsStream("/pictures/wordicon.png")));

    @FXML private Rectangle pcRect, googleDriveRect, emailRect;

    //buttons for changing prompts/ending program
    @FXML private Button backToBasicInfoEditorButton, backToTableEditorButton;

    //text field for showing inputed data to user for confirmation
    @FXML private TextArea allData;

    //file chooser for saving report
    @FXML private Button chooseFileLocationButton;
    @FXML private FileChooser saveReportChooser = new FileChooser();
    @FXML private TextField fileTF;

    //Containers
    @FXML private HBox buttonBox, fileLocationBox;

    //file location
    File fileLocation;

    //string for summarized report info to show to user
    String summarizedReportInfo;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    //gets data from previous prompt
    public void getReport(Report report2) {
        report = report2;
        summarizedReportInfo = setSummary();
        allData.setText(summarizedReportInfo);
        allData.setDisable(true);
        System.out.println("check");
    }

    //saves completed document in a set folder
    private void saveWord(XWPFDocument doc, File fileLocation) throws FileNotFoundException, IOException{
        report.setFileName();
        FileOutputStream out = new FileOutputStream(fileLocation);
        doc.write(out);
    }
    public void openWord() {
        try {
            XWPFDocument document = new XWPFDocument(getClass().getResourceAsStream("/templates/AirTesting3TemplateReport.docx"));
            document=generateInfo(document);
            File tempLocation = new File("/temp");
            FileOutputStream out = new FileOutputStream(tempLocation+"/temp.docx");
            document.write(out);
            if(Desktop.isDesktopSupported()) {
                Desktop.getDesktop().edit(new File(tempLocation+"/temp.docx"));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //replaces text in word document
    private static XWPFDocument replaceText(XWPFDocument doc, String findText, String replaceText){
        for (XWPFParagraph p : doc.getParagraphs()) {
            for (XWPFRun r : p.getRuns()) {
                String text = r.getText(0);
                System.out.println(text);
                if(text!=null&&text.contains(findText)) {
                    text= text.replace(findText, replaceText);
                    r.setText(text,0);
                }
            }
        }
        return doc;
    }

    //replace text within a word document footer (uses replaceText)

    private static XWPFDocument replaceTextInFooter(XWPFDocument doc, String findText, String replaceText) {
        for(XWPFFooter f : doc.getFooterList()){
            for(XWPFTable t : f.getTables()){
                for(XWPFTableRow tr : t.getRows()){
                    for(XWPFTableCell tc : tr.getTableCells()){
                        for(XWPFParagraph p : tc.getParagraphs()){
                            for(XWPFRun r : p.getRuns()){
                                String text = r.getText(0);
                                if(text!=null&&text.contains(findText)) {
                                    text = text.replace(findText,replaceText);
                                    r.setText(text,0);
                                }
                            }
                        }
                    }
                }
            }

        }
        return doc;
    }

    //sets run with multiple characteristics
    private static void setRun (XWPFRun run , String fontFamily , int fontSize , String colorRGB , String text , boolean bold , boolean addBreak) {
        run.setFontFamily(fontFamily);
        run.setFontSize(fontSize);
        run.setColor(colorRGB);
        run.setText(text);
        run.setBold(bold);
        if (addBreak) run.addBreak();
    }

    //returns to basic info prompt for editing
    public void goToInfo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/walkin/InfoMaker.fxml"));
            Parent root = (Parent) loader.load();

            InfoController reportVariables = loader.getController();
            reportVariables.getReport(report);

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //main function for generating report. goes through all functions
    public void generateReport(MouseEvent event) {
        try {
            XWPFDocument document = new XWPFDocument(getClass().getResourceAsStream("/templates/AirTesting2TemplateReport.docx"));
            document=generateInfo(document);
            saveReportChooser.setInitialFileName(report.getName());
            File directory = new File(System.getProperty("user.home"));
            saveReportChooser.setInitialDirectory(directory);
            fileLocation = saveReportChooser.showSaveDialog(((Node) event.getTarget()).getScene().getWindow());
            if(fileLocation!=null) {
                saveWord(document,fileLocation);
                completePrompt(event);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    //informs user of file's completion
    public void completePrompt(MouseEvent event) {
        Alert invalidInputs = new Alert(AlertType.CONFIRMATION);
        invalidInputs.setTitle("Report Generator");
        invalidInputs.setHeaderText("Report Complete");
        invalidInputs.setContentText("The completed report has been added to the selected folder.");
        ButtonType finishButton = new ButtonType("Finish");
        ButtonType newButton = new ButtonType("New Report");
        invalidInputs.getButtonTypes().setAll(finishButton, newButton);
        Optional<ButtonType> result = invalidInputs.showAndWait();
        if(result.get()==finishButton) {
            System.exit(0);
        }
        else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ReportMaker.fxml"));
                Parent root = (Parent) loader.load();

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //uploads basic info to document
    public XWPFDocument generateInfo(XWPFDocument document) {
        replaceTextInFooter(document,"$PROJECTNUMBER",report.getInfo().getProjectNumber());
        replaceText(document,"$PROJECTNUMBER",report.getInfo().getProjectNumber());

        replaceTextInFooter(document,"$PROJECTADDRESS",report.getInfo().getProjectAddress());
        replaceText(document,"$PROJECTADDRESS",report.getInfo().getProjectAddress());

        replaceTextInFooter(document,"$PROJECTCITY",report.getInfo().getProjectCity());
        replaceText(document,"$PROJECTCITY",report.getInfo().getProjectCity());

        replaceTextInFooter(document,"$PROJECTPROVINCE",report.getInfo().getProjectProvince());
        replaceText(document,"$PROJECTPROVINCE",report.getInfo().getProjectProvince());

        replaceTextInFooter(document,"$PROJECTPOSTALCODE",report.getInfo().getProjectPostalCode());
        replaceText(document,"$PROJECTPOSTALCODE",report.getInfo().getProjectPostalCode());

        replaceTextInFooter(document,"$CLIENTNAME",report.getInfo().getClientName());
        replaceText(document,"$CLIENTNAME",report.getInfo().getClientName());

        replaceTextInFooter(document,"$CLIENTPOSITION",report.getInfo().getClientContact());
        replaceText(document,"$CLIENTPOSITION",report.getInfo().getClientContact());

        replaceTextInFooter(document,"$BUILDINGNAME",report.getInfo().getBuildingName());
        replaceText(document,"$BUILDINGNAME",report.getInfo().getBuildingName());

        replaceTextInFooter(document,"$SPECIFICLOCATION",report.getInfo().getSpecificLocation());
        replaceText(document,"$SPECIFICLOCATION",report.getInfo().getSpecificLocation());

        replaceTextInFooter(document,"$REPORTDATE",report.getInfo().getReportDate());
        replaceText(document,"$REPORTDATE",report.getInfo().getReportDate());

        replaceTextInFooter(document,"$VISUALABATEMENTSTART",report.getInfo().getVisualAbatementStart());
        replaceText(document,"$VISUALABATEMENTSTART",report.getInfo().getVisualAbatementStart());

        replaceTextInFooter(document,"$SELREP",report.getInfo().getSelRep());
        replaceText(document,"$SELREP",report.getInfo().getSelRep());

        replaceText(document,"$ONSITETIME",report.getInfo().getOnSiteTime());

        replaceText(document,"$COMPANYADDRESS",report.getInfo().getCompanyAddress());
        replaceText(document,"$COMPANYCITY",report.getInfo().getCompanyCity());
        replaceText(document,"$COMPANYPROVINCE",report.getInfo().getCompanyProvince());
        replaceText(document,"$COMPANYPOSTALCODE",report.getInfo().getCompanyPostalCode());

        replaceText(document,"$COMPANYNAME",report.getInfo().getCompanyName());

        replaceText(document, "$PREABATEMENTSTARTDATE", report.getInfo().getPreAbatementStartDate());
        return document;
    }

    //creates a summary text area to show to user for confirmation
    public String setSummary() {
        String summary = "";
        if(report.info.isProjectNumberExist()) {
            summary+=report.getInfo().getProjectNumber()+"\n\n";
        }

        if(report.info.isProjectNameExist()) {
            summary+=report.getInfo().getProjectName()+"\n\n";
        }

        if(report.info.isProjectAddressExist()) {
            summary+=report.getInfo().getProjectAddress()+"\n\n";
        }

        if(report.info.isProjectCityExist()){
            summary+=report.getInfo().getProjectCity()+"\n\n";
        }

        if(report.info.isProjectProvinceExist()){
            summary+=report.getInfo().getProjectProvince()+"\n\n";
        }

        if(report.info.isProjectPostalCodeExist()){
            summary+=report.getInfo().getProjectPostalCode()+"\n\n";
        }

        if(report.info.isClientNameExist()){
            summary+=report.getInfo().getClientName()+"\n\n";
        }

        if(report.info.isClientPositionExist()){
            summary+=report.getInfo().getClientPosition()+"\n\n";
        }

        if(report.info.isBuildingNameExist()){
            summary+=report.getInfo().getBuildingName()+"\n\n";
        }

        if(report.info.isSpecificLocationExist()){
            summary+=report.getInfo().getSpecificLocation()+"\n\n";
        }

        if(report.info.isSiteWorkDateExist()){
            summary+=report.getInfo().getSiteWorkDate()+"\n\n";
        }

        if(report.info.isReportDateExist()){
            summary+=report.getInfo().getReportDate()+"\n\n";
        }

        if(report.info.isVisualAbatementStartExist()){
            summary+=report.getInfo().getVisualAbatementStart()+"\n\n";
        }

        if(report.info.isVisualAbatementEndExist()){
            summary+=report.getInfo().getVisualAbatementEnd()+"\n\n";
        }

        if(report.info.isPostAbatementDateExist()){
            summary+=report.getInfo().getPostAbatementDate()+"\n\n";
        }

        if(report.info.isOnSiteTimeExist()){
            summary+=report.getInfo().getOnSiteTime()+"\n\n";
        }

        if(report.info.isClientAddressExist()){
            summary+=report.getInfo().getClientAddress()+"\n\n";
        }

        if(report.info.isClientCityExist()){
            summary+=report.getInfo().getClientCity()+"\n\n";
        }

        if(report.info.isClientProvinceExist()){
            summary+=report.getInfo().getClientProvince()+"\n\n";
        }

        if(report.info.isClientPostalCodeExist()){
            summary+=report.getInfo().getClientPostalCode()+"\n\n";
        }

        if(report.info.isInspectionStartDateExist()){
            summary+=report.getInfo().getInspectionStartDate()+"\n\n";
        }

        if(report.info.isSamplingDateExist()){
            summary+=report.getInfo().getSamplingDate()+"\n\n";
        }
        report.setFileName();
        summary+="File Name: "+report.getName()+"\n";

        return summary;
    }
}