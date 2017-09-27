package view.spielfeld;


import java.io.File;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import kacheln.KachelContainer;
import kommunikation.PlayerCircleManager;
import staticClasses.Config;
import staticClasses.Images;
import view.AussergewohnlichesZugfenster;
import view.ChatView;
import view.DiceView;
import view.HandFrameView;
import view.MenuBarView;
import view.NotesView;
import view.StatusView;
import cluedoNetworkLayer.CluedoGameClient;





public class GameFrameView extends GridPane{
	
	MenuBarView menu;
	GridPane rightGrid;
	NotesView notes;
	DiceView dice;
	ChatView chat;
	BoardView board;
	StatusView statusView;
	Slider volume;
	Stage chatStage;
	VBox leftColumn;
	

	//private SpielfeldUndFiguren komplettesFeld;
	//private FigurenView figuren;
	private KrasserStack komplettesFeld;
	BallEbene ballEbene;
	AussergewohnlichesZugfenster zugView;
	

	private HandFrameView hand;
	
	private Stage stage;
	private Scene scene;
	public final PlayerCircleManager pcManager;
	
	private AudioClip audio1 = new AudioClip(getClass().getResource("gameAudio1.mp3").toExternalForm());
	private AudioClip audio2 = new AudioClip(getClass().getResource("gameAudio2.mp3").toExternalForm());
	private AudioClip audio3 = new AudioClip(getClass().getResource("gameAudio3.mp3").toExternalForm());
	private AudioClip audio4 = new AudioClip(getClass().getResource("gameAudio4.mp3").toExternalForm());
	private AudioClip audio5 = new AudioClip(getClass().getResource("gameAudio5.mp3").toExternalForm());
	private AudioClip audio6 = new AudioClip(getClass().getResource("gameAudio6.mp3").toExternalForm());
	private AudioClip audio7 = new AudioClip(getClass().getResource("gameAudio7.mp3").toExternalForm());
//	private Media audioMedia;
//	private MediaPlayer audio;
	

	
	public GameFrameView(PlayerCircleManager pcm, KachelContainer kacheln, CluedoGameClient client){
		pcManager = pcm;

		/*(2 Rows x 2 Columns). */
		this.setAlignment(Pos.CENTER);
		
		RowConstraints row0 = new RowConstraints();
		row0.setPercentHeight(3.34);
		this.getRowConstraints().add(row0);
		
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(100);
		this.getRowConstraints().add(row1);
		
		ColumnConstraints column0 = new ColumnConstraints();
		//column0.setPercentWidth(53.57);
		column0.setPercentWidth(54);
		this.getColumnConstraints().add(column0);
		
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(46.0);
		this.getColumnConstraints().add(column1);
		
		this.setVgap(4);
		this.setHgap(2);
		this.setPadding(new Insets(0, 4, 2, 4));
		this.setGridLinesVisible(false);
		
		
		//Adds the MenuBar and Menus at the top of the screen.
		menu = new MenuBarView();
		
		
		//Adds the Game Board frame.
		board = new BoardView(kacheln);

		//Adds the Notes frame.
		notes = new NotesView(client);
		
		//Adds the Chat frame.
		chat = new ChatView();

		//Adds the Dices frame.
		dice = new DiceView();
		
		
		//Adds the Stackpane with the field 
		statusView = new StatusView();
		ballEbene = new BallEbene(pcm);
		zugView = new AussergewohnlichesZugfenster();
		komplettesFeld = new KrasserStack(ballEbene, board, zugView);
		
		//Adds the volume slider
		volume = new Slider(0, 1, 0.5);
		HBox volumeBox = new HBox(4);
		volumeBox.setPrefWidth(200);
		volumeBox.setMaxWidth(200);
		volumeBox.setPrefHeight(10);
		volumeBox.setMaxHeight(10);
		Label volumeLabel = new Label("Volume");
		volumeBox.getChildren().addAll(volumeLabel, volume);
		
		//Adds the frame for the Cards in hand.
		hand = new HandFrameView();

		/* Left part of the whole GameFrame,
		   only for a better organising of objects. */

		rightGrid = new GridPane();
		
		RowConstraints row00 = new RowConstraints();
		row00.setPercentHeight(50);
		rightGrid.getRowConstraints().add(row00);
		
		RowConstraints row01 = new RowConstraints();
		row01.setPercentHeight(50);
		rightGrid.getRowConstraints().add(row01);
		
		ColumnConstraints column00 = new ColumnConstraints();
		column00.setPercentWidth(53.846);
		rightGrid.getColumnConstraints().add(column00);
		
		ColumnConstraints column01 = new ColumnConstraints();
		column01.setPercentWidth(46.154);
		rightGrid.getColumnConstraints().add(column01);
		
//		rightGrid.getRowConstraints().add(new RowConstraints(450));
//		rightGrid.getColumnConstraints().add(new ColumnConstraints(350));
//		rightGrid.getColumnConstraints().add(new ColumnConstraints(300));
		rightGrid.setVgap(2);
		rightGrid.setHgap(8);

		// VBox and HBox for dividing elements in two columns
		leftColumn = new VBox(10);
		leftColumn.setPadding(new Insets(0, 2, 0, 2));
		leftColumn.setMaxWidth(300);
		leftColumn.setPrefWidth(300);
		VBox rightColumn = new VBox(10);
		leftColumn.getChildren().addAll(statusView, hand, chat);
		
		GridPane.setConstraints(chat,0,1);
		GridPane.setConstraints(hand,0,0);
		
		
		GridPane.setConstraints(notes,1,0);
		GridPane.setRowSpan(notes, 2);
		GridPane.setHalignment(notes, HPos.RIGHT);
		GridPane.setValignment(notes, VPos.CENTER);
		
		//Add elements to rightGrid
		/*GridPane.setConstraints(notes,1,0);
		GridPane.setConstraints(dice,1,1);
		GridPane.setConstraints(chat,0,1);
		GridPane.setConstraints(hand,0,0);*/
		GridPane.setConstraints(rightColumn,1,0);
		GridPane.setRowSpan(rightColumn, 2);
		GridPane.setConstraints(leftColumn,0,0);
		GridPane.setRowSpan(leftColumn, 2);
		GridPane.setHalignment(leftColumn, HPos.CENTER);
		GridPane.setValignment(leftColumn, VPos.CENTER);
		rightGrid.getChildren().addAll(leftColumn, notes);
		
		
		GridPane.setConstraints(menu,0,0);
		GridPane.setColumnSpan(menu, 2);
		GridPane.setValignment(menu, VPos.TOP);
		
		GridPane.setConstraints(volumeBox,1,0);
		GridPane.setValignment(volumeBox, VPos.BOTTOM);
		GridPane.setHalignment(volumeBox, HPos.RIGHT);
		

		GridPane.setConstraints(komplettesFeld,0,1);

		GridPane.setValignment(komplettesFeld, VPos.TOP);
		GridPane.setHalignment(komplettesFeld, HPos.LEFT);
		GridPane.setConstraints(rightGrid, 1,1);
		GridPane.setValignment(rightGrid, VPos.CENTER);
		GridPane.setHalignment(rightGrid, HPos.CENTER);
		this.getChildren().addAll(komplettesFeld, menu, volumeBox, rightGrid);
		
		setAudio();
		this.setBackground(new Background(new BackgroundImage(
				Images.gameFrameBG,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT)));
		
		}
	
	
	public void start(){
		
		scene = new Scene (this, Config.GAMEWINDOW_WIDTH, Config.GAMEWINDOW_HEIGHT);
		stage = new Stage();		
		stage.setScene(scene);
		stage.setResizable(true);
		//stage.setFullScreen(true);
		stage.setTitle("Cluedo");
		stage.show();
		
	}
	
	public void setWatchersMode(){
		rightGrid.getChildren().remove(0);
		rightGrid.getChildren().remove(0);
		leftColumn.getChildren().remove(chat);
		stage.setWidth(696);
		
		BorderPane borderPane = new BorderPane();
	    borderPane.setTop(chat);
		Scene chatScene = new Scene (borderPane, 560, 220);
		chatStage = new Stage();		
		chatStage.setScene(chatScene);
		chatStage.setResizable(true);
		chatStage.setTitle("Chat");
		chatStage.show();		
	}
	
	public void setStageTitle(String title){
		stage.setTitle(title);
	}
	
	public void close(){
		this.stage.close();
		closeChatStage();
	}
	
	public void setAudio(){
		
		audio1 = new AudioClip(getClass().getResource("gameAudio1.mp3").toExternalForm());
				
//		audio1.play();
		
//		String url = audioFile1.toURI().toString();
//        audioMedia = new Media(url);
//        audio = new MediaPlayer(audioMedia);
////      audio.setStopTime(new Duration(9000));
//        audio.play();
	}
	

	
	//Getter and Setter Methods
	public MenuBarView getMenu() {
		return menu;
	}


	public void setMenu(MenuBarView menu) {
		this.menu = menu;
	}


	public GridPane getRightGrid() {
		return rightGrid;
	}


	public void setRightGrid(GridPane rightGrid) {
		this.rightGrid = rightGrid;
	}


	public NotesView getNotes() {
		return notes;
	}


	public void setNotes(NotesView notes) {
		this.notes = notes;
	}


	public DiceView getDice() {
		return dice;
	}


	public void setDice(DiceView dice) {
		this.dice = dice;
	}


	public ChatView getChat() {
		return chat;
	}


	public void setChat(ChatView chat) {
		this.chat = chat;
	}


	public BoardView getBoard() {
		return board;
	}


	public void setBoard(BoardView board) {
		this.board = board;
	}


	public HandFrameView getHand() {
		return hand;
	}


	public void setHand(HandFrameView hand) {
		this.hand = hand;
	}

	public Stage getStage() {
		return stage;
	}


	public void setStage(Stage stage) {
		this.stage = stage;
	}


	public Scene getOurScene() {
		return scene;
	}


	public void setScene(Scene scene) {
		this.scene = scene;
	}


	public BallEbene getBallEbene() {
		return ballEbene;
	}


	public void setBallEbene(BallEbene ballEbene) {
		this.ballEbene = ballEbene;
	}
	
	public KrasserStack getKomplettesFeld() {
		return komplettesFeld;
	}


	public void setKomplettesFeld(KrasserStack komplettesFeld) {
		this.komplettesFeld = komplettesFeld;
	}


	public AussergewohnlichesZugfenster getZugView() {
		return zugView;
	}


	public void setZugView(AussergewohnlichesZugfenster zugView) {
		this.zugView = zugView;
	}
	
	public StatusView getStatusView() {
		return statusView;
	}


	public Slider getVolume() {
		return volume;
	}


	public void setVolume(Slider volume) {
		this.volume = volume;
	}

//
//	public Media getAudioMedia() {
//		return audioMedia;
//	}
//
//
//	public void setAudioMedia(Media audioMedia) {
//		this.audioMedia = audioMedia;
//	}
//
//
//	public MediaPlayer getAudio() {
//		return audio;
//	}
//
//
//	public void setAudio(MediaPlayer audio) {
//		this.audio = audio;
//	}
//

//	public File getAudioFile1() {
//		return audioFile1;
//	}
//
//
//	public File getAudioFile2() {
//		return audioFile2;
//	}
//
//
//	public File getAudioFile3() {
//		return audioFile3;
//	}
//
//
//	public File getAudioFile4() {
//		return audioFile4;
//	}
//
//
//	public File getAudioFile5() {
//		return audioFile5;
//	}
//
//
//	public File getAudioFile6() {
//		return audioFile6;
//	}
//
//
//	public File getAudioFile7() {
//		return audioFile7;
//	}
	
	public void closeChatStage(){
		if(chatStage != null) chatStage.close();
	}


	public AudioClip getAudio1() {
		return audio1;
	}


	public void setAudio1(AudioClip audio1) {
		this.audio1 = audio1;
	}


	public AudioClip getAudio2() {
		return audio2;
	}


	public void setAudio2(AudioClip audio2) {
		this.audio2 = audio2;
	}


	public AudioClip getAudio3() {
		return audio3;
	}


	public void setAudio3(AudioClip audio3) {
		this.audio3 = audio3;
	}


	public AudioClip getAudio4() {
		return audio4;
	}


	public void setAudio4(AudioClip audio4) {
		this.audio4 = audio4;
	}


	public AudioClip getAudio5() {
		return audio5;
	}


	public void setAudio5(AudioClip audio5) {
		this.audio5 = audio5;
	}


	public AudioClip getAudio6() {
		return audio6;
	}


	public void setAudio6(AudioClip audio6) {
		this.audio6 = audio6;
	}


	public AudioClip getAudio7() {
		return audio7;
	}


	public void setAudio7(AudioClip audio7) {
		this.audio7 = audio7;
	}
	
	
	
}


