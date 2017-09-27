package cluedoNetworkGUI;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PrivateChat extends GridPane {
	private TextField input;
	private Label title;
	private Button close;
	
	private Stage stage;
	
	
	public PrivateChat(){
		
		input = new TextField();
		title = new Label();
		close = new Button("Close");
		
		GridPane.setValignment(input, VPos.CENTER);
		GridPane.setHalignment(input, HPos.CENTER);
		GridPane.setConstraints(input, 0, 1);
		
		GridPane.setValignment(title, VPos.BOTTOM);
		GridPane.setHalignment(title, HPos.CENTER);
		GridPane.setConstraints(title, 0, 0);
		
		GridPane.setValignment(close, VPos.CENTER);
		GridPane.setHalignment(close, HPos.CENTER);
		GridPane.setConstraints(close, 0, 2);
		
		BorderStroke stroke = new BorderStroke(Color.DARKSLATEGRAY, BorderStrokeStyle.SOLID, 
				new CornerRadii(15), new BorderWidths(1));
		Border border = new Border(stroke);
		this.setBorder(border);
		
		Rectangle rect = new Rectangle(300, 200);
		rect.setFill(Color.WHITE);
		rect.setArcHeight(20);
		rect.setArcWidth(20);

		
		ColumnConstraints col0 = new ColumnConstraints();
	    col0.setPercentWidth(100);
	    this.getColumnConstraints().add(col0);
	    
	    RowConstraints row0 = new RowConstraints();
//	    row0.setMaxHeight(400);
//	    row0.setPrefHeight(400);
	    row0.setPercentHeight(15);
	    this.getRowConstraints().add(row0);
	    
	    RowConstraints row1 = new RowConstraints();
//	    row1.setMaxHeight(400);
//	    row1.setPrefHeight(400);
	    row1.setPercentHeight(70);
	    this.getRowConstraints().add(row1);
	    
	    RowConstraints row2 = new RowConstraints();
//	    row2.setMaxHeight(400);
//	    row2.setPrefHeight(400);
	    row2.setPercentHeight(15);
	    this.getRowConstraints().add(row2);
		
	    this.setClip(rect);
		this.setPadding(new Insets(10, 15, 10, 15));
		this.setVgap(10);
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(title, input, close);
		
		close.setOnMouseClicked(e -> close());
		
	}
	
	
	public void start(){
		Scene scene = new Scene(this, 300, 200);
		scene.setFill(Color.TRANSPARENT);
		stage = new Stage(StageStyle.TRANSPARENT);
		stage.setScene(scene);
		stage.setAlwaysOnTop(true);
		stage.setOpacity(0.9);
		stage.centerOnScreen();
		
		stage.show();
	}
	
	public void close(){
		
		stage.close();
	}


	public TextField getInput() {
		return input;
	}


	public void setInput(TextField input) {
		this.input = input;
	}





	public Stage getStage() {
		return stage;
	}


	public void setStage(Stage stage) {
		this.stage = stage;
	}


	public Label getTitle() {
		return title;
	}


	public void setTitle(Label title) {
		this.title = title;
	}

}
