	package view;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Deck;

public class HandFrameView extends BorderPane {

	private Deck deck = new Deck(6);
	private ArrayList<ImageView> hand = new ArrayList<ImageView>();
	private ArrayList<String> handURI = new ArrayList<String>();
	private Label text;
	private Label help;
	private VBox top = new VBox();
	private StackPane stack = new StackPane();
	private HBox cards = new HBox();
	private Group selectedCard = new Group();
	private VBox accusationBox = new VBox();
	private HBox bottom = new HBox();
	private HBox lists = new HBox();
	private Button accuse = new Button("Accuse!");
	private Button endTurn = new Button("End turn");

	private ComboBox<String> persons = new ComboBox<String>();
	private ComboBox<String> weapons = new ComboBox<String>();
	private ComboBox<String> rooms = new ComboBox<String>();

	public HandFrameView() {
	
		this.setMaxSize(350, 350);
		this.setPrefSize(350, 350);
		this.setMinSize(350, 350);
		
		deck.makeDeck();
		
//		happening = new Label("Currently happening: ");
//		text = new Label();
//		top.getChildren().addAll(happening, text);
//		happening = new Label("If somebody disproves you, look here:" + "\n");
		text = new Label();
		top.getChildren().addAll(text);
		
		persons.getItems().addAll("red", "yellow", "white", "green", "blue", "purple");
		persons.setValue("Person");
		persons.setMinWidth(115);
		persons.setMaxWidth(115);
		persons.setStyle("-fx-background-color: black, gold; -fx-font-weight: bold; -fx-text-fill: white;");
		weapons.getItems().addAll(deck.getWeapons());
		weapons.setValue("Weapon");
		weapons.setMinWidth(120);
		weapons.setMaxWidth(120);
		weapons.setStyle("-fx-background-color: black, gold; -fx-font-weight: bold; -fx-text-fill: white;");
		rooms.getItems().addAll(deck.getRooms());
		rooms.setValue("Room");
		rooms.setMinWidth(115);
		rooms.setMaxWidth(115);
		rooms.setStyle("-fx-background-color: black, gold; -fx-font-weight: bold; -fx-text-fill: white;");

		accuse.setMinWidth(175);
		accuse.setMaxWidth(175);
		accuse.setMinHeight(50);
		accuse.setMaxHeight(50);
		accuse.setStyle("-fx-font-weight: bold; -fx-font-size: 15pt;");
		
		endTurn.setMinWidth(175);
		endTurn.setMaxWidth(175);
		endTurn.setMinHeight(50);
		endTurn.setMaxHeight(50);
		endTurn.setStyle("-fx-font-weight: bold; -fx-font-size: 15pt;");
		
		help = new Label("Select a Person, Weapon and Room to accuse.");
		help.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
		
		bottom.getChildren().addAll(accuse, endTurn);
		lists.getChildren().addAll(persons, weapons, rooms);
		accusationBox.getChildren().addAll(help, lists, bottom);

		accusationBox.setAlignment(Pos.CENTER);
		lists.setAlignment(Pos.CENTER);

//		this.setTop(top);
		this.setBottom(accusationBox);
		this.cards.setSpacing(-50);
		this.cards.setAlignment(Pos.CENTER);
		//this.cards.getChildren().addAll(hand);
		stack.getChildren().add(cards);
		this.setCenter(stack);

	}
	
	public void setPlayerCards(ArrayList<String> playerCards){
		for (String card : playerCards){
			String url = "media/cards/" + card + ".png";
			Image img = new Image(url);
			ImageView iv = new ImageView(img);
			iv.setOnMouseEntered(e ->{
				iv.translateYProperty().setValue(iv.getY() - 16);
				iv.setImage(new Image("media/cards/" + card + "_up.png"));
			});
			iv.setOnMouseExited(e -> {
				iv.translateYProperty().setValue(iv.getY());
				iv.setImage(img);
			});
			handURI.add(card);
			hand.add(iv);
		}
		this.cards.getChildren().addAll(hand);
	}
	
	public ArrayList<ImageView> getHand() {
		return hand;
	}

	public void setHand(ArrayList<ImageView> hand) {
		this.hand = hand;
	}

	public HBox getCards() {
		return cards;
	}

	public void setCards(HBox cards) {
		this.cards = cards;
	}

	public Group getSelectedCard() {
		return selectedCard;
	}

	public void setSelectedCard(Group selectedCard) {
		this.selectedCard = selectedCard;
	}

	public StackPane getStack() {
		return stack;
	}

	public void setStack(StackPane stack) {
		this.stack = stack;
	}

	public Button getAccuse() {
		return accuse;
	}

	public void setAccuse(Button accuse) {
		this.accuse = accuse;
	}

	public ComboBox<String> getPersons() {
		return persons;
	}

	public ComboBox<String> getWeapons() {
		return weapons;
	}

	public ComboBox<String> getRooms() {
		return rooms;
	}

	public void setPersons(ComboBox<String> persons) {
		this.persons = persons;
	}

	public void setWeapons(ComboBox<String> weapons) {
		this.weapons = weapons;
	}

	public void setRooms(ComboBox<String> rooms) {
		this.rooms = rooms;
	}

	public Button getEndTurn() {
		return endTurn;
	}

	public void setEndTurn(Button endTurn) {
		this.endTurn = endTurn;
	}

	public ArrayList<String> getHandURI() {
		return handURI;
	}

	public void setHandURI(ArrayList<String> handURI) {
		this.handURI = handURI;
	}

//	public Label getText() {
//		return text;
//	}
//
//	public void setText(Label text) {
//		this.text = text;
//	}

}
