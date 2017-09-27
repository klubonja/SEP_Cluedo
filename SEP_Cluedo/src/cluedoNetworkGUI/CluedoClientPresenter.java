package cluedoNetworkGUI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import staticClasses.HowToPlay;

public class CluedoClientPresenter {
	
	private CluedoClientGUI view;
	
	private final Stop white = new Stop(0,Color.SNOW);
	private final Stop transparent = new Stop(1, Color.TRANSPARENT);
	private final Stop[] stops = new Stop[] { white, transparent };
	private final LinearGradient lg1 = new LinearGradient(0, 0.75, 0, 0, true, CycleMethod.NO_CYCLE, stops);
	
	private Background shadeBackground;
	private BackgroundFill buttonBackgroundFill;
	private final cluedoClient.Client client;
	
	
	public CluedoClientPresenter(CluedoClientGUI view,cluedoClient.Client client){
		
		this.view = view;
		this.client = client;
		
		bindComponents();
		activateEvents();
		
	}
	
	public void activateEvents(){
		

		view.getExit().setOnMouseClicked(e -> exitGame());
		view.getExitButton().setOnAction(e -> exitGame());
		view.getTestServerMenuItem().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				connectToTestServer();
				
			}
		});
		
		view.getCreateGame().setOnMouseEntered(e -> shadeBackground(view.getCreateGame()));
		view.getCreateGame().setOnMouseExited(e -> defaultBackground(view.getCreateGame()));
		view.getCreateServer().setOnMouseEntered(e -> shadeBackground(view.getCreateServer()));
		view.getCreateServer().setOnMouseExited(e -> defaultBackground(view.getCreateServer()));
		view.getConnectToTestServer().setOnMouseEntered(e -> shadeBackground(view.getConnectToTestServer()));
		view.getConnectToTestServer().setOnMouseExited(e -> defaultBackground(view.getConnectToTestServer()));
		view.getRefreshGamesList().setOnMouseEntered(e -> shadeBackground(view.getRefreshGamesList()));
		view.getRefreshGamesList().setOnMouseExited(e -> defaultBackground(view.getRefreshGamesList()));
		view.getExit().setOnMouseEntered(e -> shadeBackground(view.getExit()));
		view.getExit().setOnMouseExited(e -> defaultBackground(view.getExit()));
		
		view.getGameRules().setOnAction(e -> showRules());
		view.getFaq().setOnAction(e -> showFaq());
		view.getClosePromptArea().setOnAction(e -> hideRules());
		
		view.getFullScreen().setOnAction(e -> setToFullScr());
		view.getMute().setOnAction(e -> muteLobby());
		
		view.getOptions().setOnAction(e -> showOptions());
		view.getCloseOptionsArea().setOnAction(e -> hideOptions());
		
		view.getExitMenu().setOnAction(e -> exitGame());
		
		view.getArgonath().setOnAction(e -> setArgonath());	
		
	}
	
	public void bindComponents(){
		

		view.getInputField().clear();
		view.getInputField().setPromptText("ENTER to send");
	}
	
	public void addGlow(Node nd){
		
		Glow glow = new Glow(0.9);
		nd.setEffect(glow);	
	}
	
	public void removeGlow(Node nd){		
		nd.setEffect(null);	
	}
	
	public void exitGame(){

		client.kill();
		Platform.exit();
		System.exit(0);
	}
	
	public void shadeBackground(Button b){
		
		buttonBackgroundFill = new BackgroundFill(lg1 , new CornerRadii(1) , new Insets(1));
		shadeBackground = new Background(buttonBackgroundFill);
		b.setBackground(shadeBackground);
	}
	
	public void defaultBackground(Button b){
		
		b.setBackground(view.getButtonBackground());
	}
	
	public void showRules(){
		
		view.getPromptArea().getChildren().clear();
		view.getRules().clear();
		view.getRules().setText(HowToPlay.GOAL + HowToPlay.PLAY + HowToPlay.SUGGESTION 
        		+ HowToPlay.ACCUSING);
		view.getPromptArea().getChildren().add(view.getInfoArea());
	}
	
	public void showFaq(){
		
		view.getPromptArea().getChildren().clear();
		view.getRules().clear();
		view.getRules().setText(HowToPlay.CREATE_SERVER + HowToPlay.CREATE_GAME + HowToPlay.LOBBY_CHAT
				+ HowToPlay.PRIVATE_CHAT);
		view.getPromptArea().getChildren().add(view.getInfoArea());
	}

	public void hideRules(){
		
		view.getPromptArea().getChildren().clear();
	}
	
	public void showOptions(){
		
		view.getPromptArea().getChildren().clear();
		view.getPromptArea().getChildren().add(view.getOptionsArea());
	}
	
	public void hideOptions(){
		
		view.getPromptArea().getChildren().clear();
	}
	
	public void setToFullScr(){
		
		view.getPrimaryStage().setFullScreen(true);
		view.getPrimaryStage().setAlwaysOnTop(true);
	}
	

	public void connectToTestServer(){
		client.startTestServerConnection();
	}

	public void muteLobby(){
		
//		view.getAudio().setMute(true);
//		view.getMute().setText("Unmute");
//		view.getMute().setOnAction(e -> unmuteLobby());
	}
	
	public void unmuteLobby(){
		
//		view.getAudio1().setMute(false);
//		view.getMute().setText("Mute");
//		view.getMute().setOnAction(e -> muteLobby());
	}
	
	public void setArgonath(){
		
		BackgroundImage argonath = new BackgroundImage(view.getBackMinas(), BackgroundRepeat.NO_REPEAT , 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, view.getBackgroundSize());
		view.setBackgroundImage(argonath);
		
		Background argonathB = new Background(view.getBackgroundImage());
		view.setClientBackground(argonathB);
		
	}

}
