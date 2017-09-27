package view;

import staticClasses.Images;
import staticClasses.NetworkMessages;
import staticClasses.Sounds;
import cluedoNetworkLayer.CluedoGameClient;

/**
 * Die Presenter Klasse für das ZugFenster, in dem man die Möglichkeiten
 * in einem Zug auswählen darf.
 * 
 * @author Nedko Chulev
 *
 */
public class AussergewohnlichesZugfensterPresenter {

	private AussergewohnlichesZugfenster gameView;
	private CluedoGameClient network;
	private int id;

	/**
	 * Constructor für den Presenter von der ZugFenster View
	 * 
	 * @param gameView die ZugFenster View
	 * @param pcManager Der Verwalter von den Spielern und den Spielfiguren
	 * @param network Der Client
	 * @param id Die ID des Spiels
	 */
	public AussergewohnlichesZugfensterPresenter(
			AussergewohnlichesZugfenster gameView, CluedoGameClient network, int id) {
		this.gameView = gameView;
		this.network = network;
		this.id = id;

		zugFensterButtonManager();
		vermutungButtonManager();
	}

	/**
	 * Arrangiert die Buttons im Zugfenster.
	 */
	public void zugFensterButtonManager() {
		// Der Button für die Äusserung der Anklage
		Images.suspectNOW.setOnMouseExited(e -> {
			gameView.getBottomBox().getChildren().removeAll(
					Images.suspectLATER,
					Images.suspectNOW);
			gameView.getBottomBox().getChildren().addAll(
					Images.suspectLATER);
		});

		//WE HAVE TO GO BACK!
		gameView.getBackButton().setOnMouseClicked(e -> {
			gameView.getOrganizer().getChildren().remove(gameView.getVermuten());
			gameView.getBottomBox().getChildren().remove(Images.suspectNOW);
			gameView.getBottomBox().getChildren().remove(Images.suspectLATER);
			gameView.getOrganizer().getChildren().remove(gameView.getBottomBox());
			gameView.getOrganizer().getChildren().remove(gameView.getBackButton());
			gameView.getOrganizer().getChildren().add(gameView.getButtonsBox());
		});
	}

	/**
	 * Arrangiert was der "Scroll"-Button im Zugfenster tut.
	 */
	public void vermutungButtonManager() {
		// Der Button fuer die aeusserung der Vermutung
		Images.suspectLATER.setOnMouseEntered(e -> {
			gameView.getBottomBox().getChildren().remove(Images.suspectLATER);
			gameView.getBottomBox().getChildren().remove(Images.suspectNOW);
			gameView.getBottomBox().getChildren()
					.addAll(Images.suspectNOW);
		});

		// Der Button fuer die aeusserung der Anklage
		Images.suspectNOW.setOnMouseExited(e -> {
			gameView.getBottomBox().getChildren().remove(Images.suspectNOW);
			gameView.getBottomBox().getChildren().remove(Images.suspectLATER);
			gameView.getBottomBox().getChildren()
					.addAll(Images.suspectLATER);
		});
	}

	/**
	 * Verbietet das Verwenden von dem Suspect-Button
	 */
	public void disableSuspect(){
		Images.suspect.setImage(Images.disabled);
		Images.suspect.setOnMouseClicked(e -> {});
		Images.suspect.setOnMouseEntered(e -> {});
		Images.suspect.setOnMouseExited(e -> {});
	}
	
	/**
	 * Verbietet das Verwenden von dem Roll-Button
	 */
	public void disableRoll(){
		Images.roll.setImage(Images.disabled);
		Images.roll.setOnMouseClicked(e -> {});
		Images.roll.setOnMouseEntered(e -> {});
		Images.roll.setOnMouseExited(e -> {});
	}
	
	/**
	 * Verbietet das Verwenden von dem Use-Secret-Passage-Button
	 */
	public void disablePassage(){
		Images.passage.setImage(Images.disabled);
		Images.passage.setOnMouseClicked(e -> {});
		Images.passage.setOnMouseEntered(e -> {});
		Images.passage.setOnMouseExited(e -> {});
	}
	
	/**
	 * Erlaubt das Verwenden von dem Suspect-Button
	 */
	public void enableSuspect(){
		Images.suspect.setImage(Images.enabled);
		
		// Vermuten
				Images.suspect.setOnMouseClicked(e -> {
					gameView.getOrganizer().getChildren().remove(gameView.getButtonsBox());
					gameView.getBottomBox().getChildren().add(Images.suspectLATER);
					gameView.getOrganizer().getChildren().add(gameView.getVermuten());
					gameView.getOrganizer().getChildren().add(gameView.getBottomBox());
					gameView.getOrganizer().getChildren().add(gameView.getBackButton());
				});
				
		// Button Animation
		Images.suspect.setOnMouseEntered(e -> {
			Images.suspect.setImage(Images.suspectImage);
		});
		
		Images.suspect.setOnMouseExited(e -> {
			Images.suspect.setImage(Images.enabled);
		});
	}
	
	/**
	 * Erlaubt das Verwenden von dem Roll-Button
	 */
	public void enableRoll(){
		Images.roll.setImage(Images.enabled);
		
		Images.roll.setOnMouseClicked(e -> {
			iWantToRollTheDice();
		});
		
		// Button Animation
		Images.roll.setOnMouseEntered(e -> {
			Images.roll.setImage(Images.rollImage);
		});
			Images.roll.setOnMouseExited(e -> {
			Images.roll.setImage(Images.enabled);
		});
	}
	
	/**
	 * Erlaubt das Verwenden von dem Use-Secret-Passage-Button
	 */
	public void enablePassage(){
		Images.passage.setImage(Images.enabled);
		
		// Gang nehmen
		Images.passage.setOnMouseClicked(e -> {
			Sounds sounds = new Sounds();
			sounds.gangSound();
			network.sendMsgToServer(NetworkMessages.secret_passageMsg(id));
		});
		
		// Button Animation
		Images.passage.setOnMouseEntered(e -> {
			Images.passage.setImage(Images.passageImage);
		});
			Images.passage.setOnMouseExited(e -> {
			Images.passage.setImage(Images.enabled);
		});
	}

	/**
	 * Schickt eine roll_dice Nachricht an den Server
	 */
	public void iWantToRollTheDice(){
		network.sendMsgToServer(NetworkMessages.roll_diceMsg(id));
	}
	
	/**
	 * Getter für die View
	 * @return die Fenster View
	 */
	public AussergewohnlichesZugfenster getGameView() {
		return gameView;
	}
}
