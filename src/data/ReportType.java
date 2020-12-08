package data;

import javafx.beans.property.SimpleStringProperty;

public class ReportType {
	SimpleStringProperty category;
	SimpleStringProperty ID;
	SimpleStringProperty title;
	SimpleStringProperty comments;
	
	public ReportType(String ID, String category, String title, String comments){
		this.category = new SimpleStringProperty(category);
		this.ID = new SimpleStringProperty(ID);
		this.title = new SimpleStringProperty(title);
		this.comments = new SimpleStringProperty(comments);
	}

	public String getID() {
		return ID.get();
	}

	public void setID(String ID) {
		this.ID.set(ID);
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public String getComments() {
		return comments.get();
	}

	public void setComments(String comments) {
		this.comments.set(comments);
	}

	public String getCategory() {
		return category.get();
	}

	public void setCategory(String category) {
		this.category.set(category);
	}
	
	
	
}
