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
        System.out.println("check");
    }

    //saves completed document in a set folder
    private void saveWord(XWPFDocument doc, File fileLocation) throws FileNotFoundException, IOException{
        report.setFileName();
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
            System.out.print(p.getRuns());
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
        for(XWPFFooter f : doc.getFooterList()) {
            for(XWPFTable t : f.getTables()) {
                for(XWPFTableRow tr : t.getRows()) {
                    for(XWPFTableCell c : tr.getTableCells()) {
                        for(XWPFParagraph p : c.getParagraphs()) {
                            for(XWPFRun r : p.getRuns()) {
                                String text  = r.getText(0);
                                System.out.println(text);
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
        if(report.info.isProjectNumberExist()) {
            replaceTextInFooter(document,"$PROJECTNUMBER",report.getInfo().getProjectNumber());
        }

        if(report.info.isProjectNameExist()) {
            replaceTextInFooter(document,"$PROJECTNAME",report.getInfo().getProjectName());
        }

        if(report.info.isProjectAddressExist()) {
            replaceTextInFooter(document,"$PROJECTADDRESS",report.getInfo().getProjectAddress());
        }

        if(report.info.isProjectCityExist()){
            replaceTextInFooter(document,"$PROJECTCITY",report.getInfo().getProjectCity());
        }

        if(report.info.isProjectProvinceExist()){
            replaceTextInFooter(document,"$PROJECTPROVINCE",report.getInfo().getProjectProvince());
        }

        if(report.info.isProjectPostalCodeExist()){
            replaceTextInFooter(document,"$PROJECTPOSTALCODE",report.getInfo().getProjectPostalCode());
        }

        if(report.info.isTechnicianExist()){
            replaceTextInFooter(document,"$TECHNICIAN",report.getInfo().getTechnician());
        }

        if(report.info.isProjectManagerExist()){
            replaceTextInFooter(document,"$PROJECTMANAGER",report.getInfo().getProjectManager());
        }

        if(report.info.isClientNameExist()){
            replaceTextInFooter(document,"$CLIENTNAME",report.getInfo().getClientName());
        }

        if(report.info.isClientPositionExist()){
            replaceTextInFooter(document,"$CLIENTPOSITION",report.getInfo().getClientPosition());
        }

        if(report.info.isCompanyNameExist()){
            replaceTextInFooter(document,"$COMPANYNAME",report.getInfo().getCompanyName());
        }

        if(report.info.isCompanyAddressExist()){
            replaceTextInFooter(document,"$COMPANYADDRESS",report.getInfo().getCompanyAddress());
        }

        if(report.info.isCompanyCityExist()){
            replaceTextInFooter(document,"$COMPANYCITY",report.getInfo().getCompanyCity());
        }

        if(report.info.isCompanyProvinceExist()){
            replaceTextInFooter(document,"$COMPANYPROVINCE",report.getInfo().getCompanyProvince());
        }

        if(report.info.isCompanyPostalCodeExist()){
            replaceTextInFooter(document,"$COMPANYPOSTALCODE",report.getInfo().getCompanyPostalCode());
        }

        if(report.info.isBuildingNameExist()){
            replaceTextInFooter(document,"$BUILDINGNAME",report.getInfo().getBuildingName());
        }

        if(report.info.isSpecificLocationExist()){
            replaceTextInFooter(document,"$SPECIFICLOCATION",report.getInfo().getSpecificLocation());
        }

        if(report.info.isSiteWorkDateExist()){
            replaceTextInFooter(document,"$SITEWORKDATE",report.getInfo().getSiteWorkDate());
        }

        if(report.info.isReportDateExist()){
            replaceTextInFooter(document,"$REPORTDATE",report.getInfo().getReportDate());
        }

        if(report.info.isPreAbatementStartDateExist()){
            replaceTextInFooter(document,"$PREABATEMENTSTARTDATE",report.getInfo().getPreAbatementStartDate());
        }

        if(report.info.isVisualAbatementStartExist()){
            replaceTextInFooter(document,"$VISUALABATEMENTSTART",report.getInfo().getVisualAbatementStart());
        }

        if(report.info.isVisualAbatementEndExist()){
            replaceTextInFooter(document,"$VISUALABATEMENTEND",report.getInfo().getVisualAbatementEnd());
        }

        if(report.info.isPostAbatementDateExist()){
            replaceTextInFooter(document,"$POSTINSPECTIONDATE",report.getInfo().getPostAbatementDate());
        }

        if(report.info.isSiteEndDateExist()){
            replaceTextInFooter(document,"$SITEENDDATE",report.getInfo().getSiteEndDate());
        }

        if(report.info.isSelRepExist()){
            replaceTextInFooter(document,"$SELREP",report.getInfo().getSelRep());
        }

        if(report.info.isOnSiteTimeExist()){
            replaceTextInFooter(document,"$ONSITETIME",report.getInfo().getOnSiteTime());
        }

        if(report.info.isClientAddressExist()){
            replaceTextInFooter(document,"$CLIENTADDRESS",report.getInfo().getClientAddress());
        }

        if(report.info.isClientCityExist()){
            replaceTextInFooter(document,"$CLIENTCITY",report.getInfo().getClientCity());
        }

        if(report.info.isClientProvinceExist()){
            replaceTextInFooter(document,"$CLIENTPROVINCE", report.getInfo().getClientProvince());
        }

        if(report.info.isClientPostalCodeExist()){
            replaceTextInFooter(document,"$CLIENTPOSTALCODE",report.getInfo().getClientPostalCode());
        }

        if(report.info.isInspectionStartDateExist()){
            replaceTextInFooter(document, "$INSPECTIONSTARTDATE",report.getInfo().getInspectionStartDate());
        }

        if(report.info.isSamplingDateExist()){
            replaceTextInFooter(document,"$SAMPLING_DATE",report.getInfo().getSamplingDate());
        }
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

        if(report.info.isTechnicianExist()){
            summary+=report.getInfo().getTechnician()+"\n\n";
        }

        if(report.info.isProjectManagerExist()){
            summary+=report.getInfo().getProjectManager()+"\n\n";
        }

        if(report.info.isClientNameExist()){
            summary+=report.getInfo().getClientName()+"\n\n";
        }

        if(report.info.isClientPositionExist()){
            summary+=report.getInfo().getClientPosition()+"\n\n";
        }

        if(report.info.isCompanyNameExist()){
            summary+=report.getInfo().getCompanyName()+"\n\n";
        }

        if(report.info.isCompanyAddressExist()){
            summary+=report.getInfo().getCompanyAddress()+"\n\n";
        }

        if(report.info.isCompanyCityExist()){
            summary+=report.getInfo().getCompanyCity()+"\n\n";
        }

        if(report.info.isCompanyProvinceExist()){
            summary+=report.getInfo().getCompanyProvince()+"\n\n";
        }

        if(report.info.isCompanyPostalCodeExist()){
            summary+=report.getInfo().getCompanyPostalCode()+"\n\n";
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

        if(report.info.isPreAbatementStartDateExist()){
            summary+=report.getInfo().getPreAbatementStartDate()+"\n\n";
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

        if(report.info.isSiteEndDateExist()){
            summary+=report.getInfo().getSiteEndDate()+"\n\n";
        }

        if(report.info.isSelRepExist()){
            summary+=report.getInfo().getSelRep()+"\n\n";
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

        summary+="File Name: "+report.getName()+"\n";

        return summary;
    }
}