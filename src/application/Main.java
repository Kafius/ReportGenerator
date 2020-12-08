package application;
	
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws InvalidFormatException, IOException {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/ReportTypeMaker.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("New Report");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
