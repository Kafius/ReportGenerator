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
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
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

public class GenerateReportController implements Initializable{
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
    }

    //saves completed document in a set folder
    private void saveWord(XWPFDocument doc, File fileLocation) throws FileNotFoundException, IOException{
        FileOutputStream out = new FileOutputStream(fileLocation);
        doc.write(out);
    }

    /*for testing. checks if data has been transfered through prompts correctly (unused)
    public void checkTransfer() {
        for(int i=0;i<report.getTable().size();i++) {
            for(int j=0;j<report.getTable().get(i).getVarNum();j++) {
                System.out.print(report.getTable().get(i).getArray(j));
            }
            System.out.println("");
        }
    }*/

    public void openWord() {
        try {
            XWPFDocument document = new XWPFDocument(getClass().getResourceAsStream("/templates/"+report.getReportType()+"TemplateReport.docx"));
            //document=generateSampleTable(document);
            document=generateInfo(document);
            //document=generateConclusions(document);
            document=generateHeaderFooter(document);
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
        for(XWPFFooter f : doc.getFooterList()) {
            for(XWPFTable t : f.getTables()) {
                for(XWPFTableRow tr : t.getRows()) {
                    for(XWPFTableCell c : tr.getTableCells()) {
                        for(XWPFParagraph p : c.getParagraphs()) {
                            for(XWPFRun r : p.getRuns()) {
                                String text  = r.getText(0);
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

    /*uploads sample table data to word document
    public XWPFDocument generateSampleTable(XWPFDocument document) {
        XWPFTable table = document.getTableArray(0);
        XWPFTableRow SampleTableRows[] = new XWPFTableRow[report.getTable().size()];
        XWPFParagraph cellOne;

        for(int i=0;i<report.getTable().size();i++) {
            SampleTableRows[i]= table.createRow();
            for(int j=0;j<report.getTable().get(i).getVarNum();j++) {
                cellOne = SampleTableRows[i].getCell(j).addParagraph();
                setRun(cellOne.createRun() , "Arial" , 10, "000000" , report.getTable().get(i).getArray(j), false, false);
                cellOne.setAlignment(ParagraphAlignment.CENTER);
                cellOne = SampleTableRows[i].getCell(j).addParagraph();
                setRun(cellOne.createRun() , "Arial" , 10, "000000" , "\n", false, false);
                cellOne.setAlignment(ParagraphAlignment.CENTER);
            }
        }
        return document;
    } */

    //main function for footer
    public XWPFDocument generateHeaderFooter(XWPFDocument document) {
        replaceTextInFooter(document, "$PROJECT_NUMBER",report.getInfo().getProjectNumber());
        replaceTextInFooter(document, "$PROJECT_ADDRESS",report.getInfo().getProjectAddress());
        replaceTextInFooter(document,"$PROJECT_CITY",report.getInfo().getProjectCity());
        replaceTextInFooter(document,"$PROJECT_PROVINCE",report.getInfo().getProjectProvince());
        return document;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/walkin/InfoPrompt.fxml"));
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

    /* public void goToTable(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/"+report.getReportType()+"Maker.fxml"));
            Parent root = (Parent) loader.load();
            if(report.getReportType().equals("Asbestos")) {
                AsbestosController table = loader.getController();
                table.getReportData(report);
            }
            else if(report.getReportType().equals("Bacteroides")) {
                BacteroidesController table = loader.getController();
                table.getReportData(report);
            }
            else if(report.getReportType().equals("Lead")) {
                LeadController table = loader.getController();
                table.getReportData(report);
            }
            else {
                MouldGrowthController table = loader.getController();
                table.getReportData(report);
            }
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    } */

    /* uploads conclusions to word doc
    private XWPFDocument generateConclusions(XWPFDocument document) throws XmlException {
        XmlCursor cursor = null;
        BigInteger bi= null;

        for (XWPFParagraph p : document.getParagraphs()) {
            for (XWPFRun r : p.getRuns()) {
                String text = r.getText(0);
                if(text!=null&&text.equals("$CONCLUSIONS")) {
                    bi = p.getNumID();
                    cursor = p.getCTP().newCursor();
                    r.setText(report.getConclusions().get(report.getConclusions().size()-1),0);
                }
            }
        }
        XWPFParagraph new_par;
        for (int i = 0; i < report.getConclusions().size()-1; i++) {
            new_par = document.insertNewParagraph(cursor);
            new_par.setNumID(bi);
            XWPFRun run = new_par.createRun();
            setRun(run,"Arial" , 12, "000000" , report.getConclusions().get(i), false, false);
            cursor.toNextToken();
        }
        return document;
    } */

    //main function for generating report. goes through all functions
    public void generateReport(MouseEvent event) {
        try {
            XWPFDocument document = new XWPFDocument(getClass().getResourceAsStream("/templates/"+report.getReportType()+"TemplateReport.docx"));
            //document=generateSampleTable(document);
            document=generateInfo(document);
            //document=generateConclusions(document);
            document=generateHeaderFooter(document);
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/walkin/BasicInfoPrompt.fxml"));
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
        if(report.info.isProjectNumberExist()) {
            replaceText(document,"$PROJECTNUMBER",report.getInfo().getProjectNumber());
        }

        if(report.info.isProjectNameExist()) {
            replaceText(document,"$PROJECTNAME",report.getInfo().getProjectName());
        }

        if(report.info.isProjectAddressExist()) {
            replaceText(document,"$PROJECTADDRESS",report.getInfo().getProjectAddress());
        }

        if(report.info.isProjectCityExist()){
            replaceText(document,"$PROJECTCITY",report.getInfo().getProjectCity());
        }

        if(report.info.isProjectProvinceExist()){
            replaceText(document,"$PROJECTPROVINCE",report.getInfo().getProjectProvince());
        }

        if(report.info.isProjectPostalCodeExist()){
            replaceText(document,"$PROJECTPOSTALCODE",report.getInfo().getProjectPostalCode());
        }

        if(report.info.isTechnicianExist()){
            replaceText(document,"$TECHNICIAN",report.getInfo().getTechnician());
        }

        if(report.info.isProjectManagerExist()){
            replaceText(document,"$PROJECTMANAGER",report.getInfo().getProjectManager());
        }

        if(report.info.isClientNameExist()){
            replaceText(document,"$CLIENTNAME",report.getInfo().getClientName());
        }

        if(report.info.isClientPositionExist()){
            replaceText(document,"$CLIENTPOSITION",report.getInfo().getClientPosition());
        }

        if(report.info.isCompanyNameExist()){
            replaceText(document,"$COMPANYNAME",report.getInfo().getCompanyName());
        }

        if(report.info.isCompanyAddressExist()){
            replaceText(document,"$COMPANYADDRESS",report.getInfo().getCompanyAddress());
        }

        if(report.info.isCompanyCityExist()){
            replaceText(document,"$COMPANYCITY",report.getInfo().getCompanyCity());
        }

        if(report.info.isCompanyProvinceExist()){
            replaceText(document,"$COMPANYPROVINCE",report.getInfo().getCompanyProvince());
        }

        if(report.info.isCompanyPostalCodeExist()){
            replaceText(document,"$COMPANYPOSTALCODE",report.getInfo().getCompanyPostalCode());
        }

        if(report.info.isBuildingNameExist()){
            replaceText(document,"$BUILDINGNAME",report.getInfo().getBuildingName());
        }

        if(report.info.isSpecificLocationExist()){
            replaceText(document,"$SPECIFICLOCATION",report.getInfo().getSpecificLocation());
        }

        if(report.info.isSiteWorkDateExist()){
            replaceText(document,"$SITEWORKDATE",report.getInfo().getSiteWorkDate());
        }

        if(report.info.isReportDateExist()){
            replaceText(document,"$REPORTDATE",report.getInfo().getReportDate());
        }

        if(report.info.isPreAbatementStartDateExist()){
            replaceText(document,"$PREABATEMENTSTARTDATE",report.getInfo().getPreAbatementStartDate());
        }

        if(report.info.isVisualAbatementStartExist()){
            replaceText(document,"$VISUALABATEMENTSTART",report.getInfo().getVisualAbatementStart());
        }

        if(report.info.isVisualAbatementEndExist()){
            replaceText(document,"$VISUALABATEMENTEND",report.getInfo().getVisualAbatementEnd());
        }

        if(report.info.isPostAbatementDateExist()){
            replaceText(document,"$POSTINSPECTIONDATE",report.getInfo().getPostAbatementDate());
        }

        if(report.info.isSiteEndDateExist()){
            replaceText(document,"$SITEENDDATE",report.getInfo().getSiteEndDate());
        }

        if(report.info.isSelRepExist()){
            replaceText(document,"$SELREP",report.getInfo().getSelRep());
        }

        if(report.info.isOnSiteTimeExist()){
            replaceText(document,"$ONSITETIME",report.getInfo().getOnSiteTime());
        }

        if(report.info.isClientAddressExist()){
            replaceText(document,"$CLIENTADDRESS",report.getInfo().getClientAddress());
        }

        if(report.info.isClientCityExist()){
            replaceText(document,"$CLIENTCITY",report.getInfo().getClientCity());
        }

        if(report.info.isClientProvinceExist()){
            replaceText(document,"$CLIENTPROVINCE", report.getInfo().getClientProvince());
        }

        if(report.info.isClientPostalCodeExist()){
            replaceText(document,"$CLIENTPOSTALCODE",report.getInfo().getClientPostalCode());
        }

        if(report.info.isInspectionStartDateExist()){
            replaceText(document, "$INSPECTIONSTARTDATE",report.getInfo().getInspectionStartDate());
        }

        if(report.info.isSamplingDateExist()){
            replaceText(document,"$SAMPLING_DATE",report.getInfo().getSamplingDate());
        }
        return document;
    }

    //creates a summary text area to show to user for confirmation
    public String setSummary() {
        String summary = "";
        summary+="Report Date: "+report.getInfo().getReportDate()+"\n\n";
        summary+="Sampling Date: "+report.getInfo().getSamplingDate()+"\n\n";
        summary+="Company Name: "+report.getInfo().getCompanyName()+"\n\n";
        summary+="Company Full Address: "+report.getInfo().getCompanyAddress()+", "+report.getInfo().getCompanyCity()+", "+report.getInfo().getCompanyProvince()+", "+report.getInfo().getCompanyPostalCode()+"\n\n";
        summary+="Client Name: "+report.getInfo().getClientName()+"\n\n";
        summary+="Project Number: "+report.getInfo().getProjectNumber()+"\n\n";
        summary+="Project Address: "+report.getInfo().getProjectAddress()+"\n\n";
        summary+="File Name: "+report.getName()+"\n";

        return summary;
    }
}