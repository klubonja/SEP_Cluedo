package view;


import java.util.ArrayList;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import staticClasses.Config;


public class ShowKarten extends GridPane{
	
	private Stage parent;
	private Button closeButton;
	private String karte;
	private String ueberschrift;
	private String suspecter;
	private ArrayList<String> karten;
	private ArrayList<String> suspecterKarten;

	
	public ShowKarten(Stage stage, String karte){
		this.parent = stage;
		this.ueberschrift = "Disprove";
		this.karte = karte;
		
		setHandlerDisprove();
		layoutGrid();
		setHandler();
	}
	
	public ShowKarten(Stage stage, ArrayList<String> karten){
		this.parent = stage;
		this.ueberschrift = "Poolcards";
		this.karten = karten;
		setHandlerPoolcards();
		layoutGrid();
		setHandler();
	}
	
	public ShowKarten(Stage stage, ArrayList<String> suspecterKarten, String suspecter){
		
		this.parent = stage;
		this.suspecter = suspecter;
		this.suspecterKarten = suspecterKarten;
		setHandlerSuspectCards();
		layoutGrid();
		setHandler();
		
	}
	
	private void setHandlerDisprove(){
		closeButton = new Button("Close Disprove");
//		Text status = new Text(ueberschrift);
//		status.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
//		status.setFill(Color.LIGHTGREY);
//		GridPane.setValignment(status, VPos.CENTER);
//		GridPane.setHalignment(status, HPos.CENTER);
//		GridPane.setConstraints(status, 0, 0);
		
		Image card = new Image ("media/cards/" + karte + ".png");
		ImageView cardView = new ImageView(card);
		GridPane.setValignment(cardView, VPos.CENTER);
		GridPane.setHalignment(cardView, HPos.CENTER);
		GridPane.setConstraints(cardView, 0, 1);
		
		GridPane.setValignment(closeButton, VPos.CENTER);
		GridPane.setHalignment(closeButton, HPos.CENTER);
		GridPane.setConstraints(closeButton, 0, 2);
		this.getChildren().addAll(cardView, closeButton);
		
		ColumnConstraints col0 = new ColumnConstraints();
        col0.setHgrow(Priority.ALWAYS);
        
	}
	
	private void setHandlerPoolcards(){
		
		
		closeButton = new Button("Close");
//		Text status = new Text(ueberschrift);
//		status.setFill(Color.LIGHTGREY);
//		status.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
//		GridPane.setValignment(status, VPos.CENTER);
//		GridPane.setHalignment(status, HPos.CENTER);
//		GridPane.setConstraints(status, 0, 0);
//		this.getChildren().add(status);
		
		HBox poolCards = new HBox(10);
		for (String s : karten){
			Image card = new Image ("media/cards/" + s + ".png");
			ImageView cardView = new ImageView(card);
			poolCards.getChildren().add(cardView);
			
		}
		
		GridPane.setValignment(poolCards, VPos.CENTER);
		GridPane.setHalignment(poolCards, HPos.CENTER);
		GridPane.setConstraints(poolCards, 0, 1);
		this.getChildren().add(poolCards);
		GridPane.setValignment(closeButton, VPos.CENTER);
		GridPane.setHalignment(closeButton, HPos.CENTER);
		GridPane.setConstraints(closeButton, 0, 2);
		this.getChildren().add(closeButton);
	}
	
	
	public void setHandlerSuspectCards(){
		closeButton = new Button("Close");
		closeButton.autosize();
		final Stop[] redlist = new Stop[] { new Stop(0, Color.TRANSPARENT), new Stop(1, Color.TOMATO)};
		final RadialGradient redGrad = new RadialGradient(1, 1, 0, 1, 1, true, CycleMethod.NO_CYCLE, redlist);
		final BackgroundFill redFill = new BackgroundFill(redGrad, 
				new CornerRadii(4), new Insets(0.2));
		final Background red = new Background(redFill);
		Label status = new Label(suspecter + "hat eine Vermutung gemacht:");
		status.setPrefWidth(350);
		status.setMinWidth(400);
		
		status.setBackground(red);
		status.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
		status.setAlignment(Pos.CENTER);
		status.setTextFill(Color.WHITESMOKE);
		GridPane.setValignment(status, VPos.CENTER);
		GridPane.setHalignment(status, HPos.CENTER);
		GridPane.setConstraints(status, 0, 0);
		this.getChildren().add(status);
		
		HBox cards = new HBox(10);
		for (String s : suspecterKarten){
			Image card = new Image ("media/cards/" + s + ".png");
			ImageView cardView = new ImageView(card);
			cards.getChildren().add(cardView);
		}
		GridPane.setValignment(cards, VPos.CENTER);
		GridPane.setHalignment(cards, HPos.CENTER);
		GridPane.setConstraints(cards, 0, 1);
		this.getChildren().add(cards);
		
		
		GridPane.setValignment(closeButton, VPos.CENTER);
		GridPane.setHalignment(closeButton, HPos.CENTER);
		GridPane.setConstraints(closeButton, 0, 2);
		this.getChildren().add(closeButton);

	}
	
	
	public void layoutGrid(){
		 
		this.setPadding(new Insets(15));
		this.setHgap(25);
		this.setVgap(10);
	    this.setGridLinesVisible(false);
	    this.setAlignment(Pos.CENTER);
	      
	    Rectangle rect = new Rectangle(400, 300);
	    rect.setFill(Color.WHITE);
	    rect.setArcHeight(10);
	    rect.setArcWidth(10);

	    this.setClip(rect);
	    
	    Image lotr = new Image("media/ringBack.jpg");
		BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, 
				false, false, false, true);
		BackgroundImage backgroundImage = new BackgroundImage(lotr, BackgroundRepeat.NO_REPEAT , 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
	    Background background = new Background(backgroundImage);
	    
	    this.setBackground(background);
	        
	    ColumnConstraints col0 = new ColumnConstraints();
	    col0.setMaxWidth(200);
	    col0.setPrefWidth(100);
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
	    
	    BorderStroke backgroundStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, 
				new CornerRadii(10), new BorderWidths(1.3));
		Border backgroundBorder = new Border(backgroundStroke);
		this.setBorder(backgroundBorder);
	}
	
	public void setHandler() {
		closeButton.setOnMouseClicked(e -> {
			parent.close();
		});
	}
	
	
	
	
	
}
