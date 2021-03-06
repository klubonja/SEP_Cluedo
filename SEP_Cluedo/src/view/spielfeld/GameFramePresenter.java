package view.spielfeld;

import java.io.File;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import kacheln.KachelContainer;
import kommunikation.PlayerCircleManager;
import staticClasses.Images;
import staticClasses.NetworkMessages;
import staticClasses.auxx;
import view.AussergewohnlichesZugfensterPresenter;
import view.DicePresenter;
import view.HandFramePresenter;
import view.MenuBarPresenter;
import view.NotesPresenter;
import animation.DerBeweger;
import animation.RaumBeweger;
import cluedoNetworkLayer.CluedoGameClient;
import cluedoNetworkLayer.CluedoPlayer;
import finderOfPaths.Ausloeser;
import finderOfPaths.Sucher;
import finderOfPaths.Vorschlaege;
import finderOfPaths.WahnsinnigTollerPathfinder;

/**
 * @version 21.07.2015
 * @author YinYanYolos
 * Beinhaltet alle Presenter des Spielfelds sowie alle Teile des Pathfinders
 */
public class GameFramePresenter {
	
	
	private CluedoGameClient networkGame;
	private CluedoPlayer currentPlayer;
	private GameFrameView gfv;
	private Stage stage;
	
	private Ausloeser ausloeser;
	private Sucher sucher;
	private DerBeweger beweger;
	private RaumBeweger raumBeweger;
	private Vorschlaege vorschlager;
	private WahnsinnigTollerPathfinder pathfinder;
	private char [][] anweisungen;
	
	private NotesPresenter notesPresenter;
	private HandFramePresenter handFramePresenter;
	private MenuBarPresenter menuBarPresenter;
	private DicePresenter dicePresenter;
	private AussergewohnlichesZugfensterPresenter zugPresenter;
	public final PlayerCircleManager pcManager;
	private KachelContainer kacheln;
	private int gameid;
	
	public static int audioCounter=1;
	private ArrayList<AudioClip> gameSounds;
	

	/**
	 * Erstellt einen GameFramePresenter und damit alle Presenter des Spielfelds / den Pathfinder
	 * @param gfv
	 * @param game
	 * @param pcManager
	 * @param gameid
	 * @param kacheln
	 */
	public GameFramePresenter(GameFrameView gfv, CluedoGameClient game,PlayerCircleManager pcManager, int gameid, KachelContainer kacheln){
		this.kacheln = kacheln;
		this.gfv = gfv;
		networkGame = game;
		this.gameid = gameid;
		this.pcManager = pcManager;
		this.currentPlayer = pcManager.getCurrentPlayer();
		
		startEvents();
		setHandler();
		initSoundsList();
		adjustAudio(gfv.getAudio1());
		bindComponents();
		checkRooms();
		
	}
	
	/**
	 * Erstellt alle Presenter und alle Teile des Pathfinders
	 */
	public void startEvents(){
		
		gfv.getStage().setOnCloseRequest(e -> closeWindow());
		
		auxx.logsevere("null? : " +currentPlayer.getPosition().getY() +currentPlayer.getPosition().getX());
	
//		notesPresenter = new NotesPresenter(gfv.notes, currentPlayer);
		notesPresenter = new NotesPresenter(gfv.notes);
		handFramePresenter = new HandFramePresenter(gfv.getHand());
		menuBarPresenter = new MenuBarPresenter(gfv.menu, gfv);
		
		zugPresenter = new AussergewohnlichesZugfensterPresenter(gfv.getZugView(), networkGame, gameid);

		raumBeweger = new RaumBeweger(pcManager, kacheln);		
		beweger = new DerBeweger(gfv.getZugView(), gfv.board, raumBeweger,pcManager, kacheln);
		vorschlager = new Vorschlaege(gfv.board,pcManager, kacheln);
		pathfinder = new WahnsinnigTollerPathfinder(pcManager, kacheln);
		
		sucher = new Sucher(vorschlager, pathfinder, pcManager);
		ausloeser = new Ausloeser( gfv.getZugView(), gfv.board, beweger, gfv.ballEbene, pathfinder, pcManager, gameid, networkGame, kacheln);

		dicePresenter = new DicePresenter(gfv.dice, gfv,ausloeser, gfv.board, sucher, pcManager, kacheln);
		
		
		gogogo();
	}
	
	public void bindComponents(){
		
		gfv.getAudio1().volumeProperty().bind(gfv.getVolume().valueProperty());
	}
	
	public void setHandler(){
		setStyleChatField(false);
		EventHandler<KeyEvent> listenForEnter = new EventHandler<KeyEvent> (){
			@Override
			public void handle(KeyEvent e) {
			        if (e.getCode() == KeyCode.ENTER){
			        	networkGame.sendMsgToServer(
			        			NetworkMessages.chatMsg(
			        					gfv.getChat().getChatField().getText(), 
			        					networkGame.getGameId(), 
			        					networkGame.getMyNick(),
			        					auxx.now()
			        			)
			        	);	
			        	setStyleChatField(true);
			        }
			    }
			};	
			gfv.getChat().getChatField().focusedProperty().addListener(new ChangeListener<Boolean>(){				
			    @Override
			    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean hasFocus){		    			
			    	if (hasFocus){ 
			    		gfv.getChat().getChatField().addEventHandler(KeyEvent.KEY_PRESSED,listenForEnter );  
			    	}
			    	else {
			    		gfv.getChat().getChatField().removeEventHandler(KeyEvent.KEY_PRESSED,listenForEnter );  
			    	}
			    	setStyleChatField(hasFocus);
			    }
			});
	}
	
	public void setStyleChatField(boolean focused){
		if (focused){
			gfv.getChat().getChatField().setText("");
    		gfv.getChat().getChatField().setStyle("-fx-text-fill: #000000;-fx-font-style: normal;");
		}
		else {

			gfv.getChat().getChatField().setText("Confirm with ENTER");
	    	gfv.getChat().getChatField().setStyle("-fx-text-fill: #999999;-fx-font-style: italic;");

		}
		
	}
	
	/**
	 * Hier geht's los!
	 */
	public void gogogo(){
		beweger.anfangsPositionSetzen(0);
//		Platform.runLater( () -> {
//			beweger.waffenAnfangsPositionenSetzen(0);
//		});
		
		System.out.println("test");
		ausloeser.zuweisung(pcManager);
	}
	
	public void initSoundsList(){
		
		gameSounds = new ArrayList<AudioClip>();
		gameSounds.add(gfv.getAudio1());
		gameSounds.add(gfv.getAudio2());
		gameSounds.add(gfv.getAudio3());
		gameSounds.add(gfv.getAudio4());
		gameSounds.add(gfv.getAudio5());
		gameSounds.add(gfv.getAudio6());
		gameSounds.add(gfv.getAudio7());
	}
	
	public void adjustAudio(AudioClip audio){
		
//		if(!audio.isPlaying()){
//			if(audio.equals(gfv.getAudio1())){
//				gfv.getAudio2().play();
//				adjustAudio(gfv.getAudio2());
//			}
//			if(audio.equals(gfv.getAudio2())){
//				gfv.getAudio2().play();
//				adjustAudio(gfv.getAudio3());
//			}
//			if(audio.equals(gfv.getAudio3())){
//				gfv.getAudio4().play();
//				adjustAudio(gfv.getAudio4());
//			}
//			if(audio.equals(gfv.getAudio4())){
//				gfv.getAudio5().play();
//				adjustAudio(gfv.getAudio5());
//			}
//			if(audio.equals(gfv.getAudio5())){
//				gfv.getAudio6().play();
//				adjustAudio(gfv.getAudio6());
//			}
//			if(audio.equals(gfv.getAudio6())){
//				gfv.getAudio7().play();
//				adjustAudio(gfv.getAudio7());
//			}
//			if(audio.equals(gfv.getAudio7())){
//				gfv.getAudio1().play();
//				adjustAudio(gfv.getAudio1());
//			}
			
		}
//		gfv.getAudio().setOnEndOfMedia(() -> {
//			String url = gameSounds.get(audioCounter).toURI().toString();
//	        gfv.setAudioMedia(new Media(url));
//	        gfv.setAudio(new MediaPlayer(gfv.getAudioMedia()));
////	        gfv.getAudio().setStopTime(new Duration(9000));;
//	        gfv.getAudio().play();
//	        
//	        gfv.getAudio().volumeProperty().bind(gfv.getVolume().valueProperty());
//	        
//	        audioCounter++;
//	        if(audioCounter > 6){
//	        	
//	        	audioCounter = 0;
//	        }
//	        
//	        adjustAudio();
//	});
//	}
	
	public void checkRooms(){
		gfv.getKomplettesFeld().getBallEbene().setOnMouseMoved(e ->{
			double x = e.getX();
			double y = e.getY();
			
			if(x < 203 && y < 116){ //study
				Images.bg.setImage(Images.study);
			} else 
			if(x > 261 && x < 435 && y < 203){ //hall
				Images.bg.setImage(Images.hall);
			} else
			if(x > 493 && y < 174){ //lounge
				Images.bg.setImage(Images.lounge);
			} else
			if(x < 203 && y > 174 && y < 319){ //library
				Images.bg.setImage(Images.library);
			} else
			if(x < 174 && y > 384 && y < 522){ //billiard
				Images.bg.setImage(Images.billiard);
			} else
			if(x > 232 && x < 464 && y > 261 && y < 435){ //pool
				Images.bg.setImage(Images.pool);
			} else
			if(x > 464 && y > 261 && y < 464){ //diningroom
				Images.bg.setImage(Images.diningroom);
			} else
			if(x < 174 && y > 551){ //conservatory
				Images.bg.setImage(Images.conservatory);
			} else
			if(x > 232 && x < 493 && y > 493 && y < 696){ //ballroom
				Images.bg.setImage(Images.ballroom);
			} else
			if(x > 522 && y > 522){ //kitchen
				Images.bg.setImage(Images.kitchen);
			} else {
				Images.bg.setImage(Images.field);
			}
		});
	}
	
	//Getter and Setters
	public GameFrameView getGfv() {
		return gfv;
	}

	public void setGfv(GameFrameView gfv) {
		this.gfv = gfv;
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Ausloeser getAusloeser() {
		return ausloeser;
	}

	public void setAusloeser(Ausloeser ausloeser) {
		this.ausloeser = ausloeser;
	}

	public Sucher getSucher() {
		return sucher;
	}

	public void setSucher(Sucher sucher) {
		this.sucher = sucher;
	}

	public DerBeweger getBeweger() {
		return beweger;
	}

	public void setBeweger(DerBeweger beweger) {
		this.beweger = beweger;
	}

	public Vorschlaege getVorschlager() {
		return vorschlager;
	}

	public void setVorschlager(Vorschlaege vorschlager) {
		this.vorschlager = vorschlager;
	}

	public WahnsinnigTollerPathfinder getPathfinder() {
		return pathfinder;
	}

	public void setPathfinder(WahnsinnigTollerPathfinder pathfinder) {
		this.pathfinder = pathfinder;
	}

	public NotesPresenter getNotesPresenter() {
		return notesPresenter;
	}

	public void setNotesPresenter(NotesPresenter notesPresenter) {
		this.notesPresenter = notesPresenter;
	}

	public HandFramePresenter getHandFramePresenter() {
		return handFramePresenter;
	}

	public void setHandFramePresenter(HandFramePresenter handFramePresenter) {
		this.handFramePresenter = handFramePresenter;
	}

	public MenuBarPresenter getMenuBarPresenter() {
		return menuBarPresenter;
	}

	public void setMenuBarPresenter(MenuBarPresenter menuBarPresenter) {
		this.menuBarPresenter = menuBarPresenter;
	}

	public DicePresenter getDicePresenter() {
		return dicePresenter;
	}

	public void setDicePresenter(DicePresenter dicePresenter) {
		this.dicePresenter = dicePresenter;
	}

	public RaumBeweger getRaumBeweger() {
		return raumBeweger;
	}

	public void setRaumBeweger(RaumBeweger raumBeweger) {
		this.raumBeweger = raumBeweger;
	}
	
	public AussergewohnlichesZugfensterPresenter getZugPresenter() {
		return zugPresenter;
	}

	public void setZugPresenter(AussergewohnlichesZugfensterPresenter zugPresenter) {
		this.zugPresenter = zugPresenter;
	}
	
	public void closeWindow(){
		gfv.getAudio1().stop();
		gfv.getStage().close();
	}
	
	
	
}
