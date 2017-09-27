package view.spielfeld;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import kommunikation.PlayerCircleManager;
import staticClasses.Config;
import view.label.KrassesLabel;
import cluedoNetworkLayer.CluedoPosition;
import cluedoNetworkLayer.CluedoWeapon;
import enums.Weapons;

/**
 * @since ca. 25.06.2015
 * @version 21.07.2015
 * @author Benedikt Mayer
 * 
 * Die Ebene, in welcher die Figuren sich befinden und in welcher die click-Events stattfinden
 *
 */
public class BallEbene extends GridPane{

	private Stage stage;
	private Scene scene;
	
	private KrassesLabel [][] labelArray;
	
	public PlayerCircleManager pcManager;
	
	/**
	 * Hier wird unsere BallEbene erzeugt und layoutStuff() aufgerufen
	 * @param pcManager juchee, der pcManager ist da!
	 */
	public BallEbene(PlayerCircleManager pcManager){
		this.pcManager = pcManager;
		layoutStuff();
	}
	
	/**
	 * Let's start the whole thing
	 */
	public void start(){
		stage = new Stage();
		scene = new Scene(this, 696, 725);
		stage.setScene(scene);
	}
	
	/**
	 * Hier werden die Labels erstellt und hinzugefuegt.
	 */
	public void layoutStuff(){
		
		labelArray = new KrassesLabel [Config.ROWS][Config.COLUMNS];
		
		BackgroundFill fills = new BackgroundFill(Color.TRANSPARENT, null, null);
		Background hintergrund = new Background(fills);
		this.setBackground(hintergrund);
		
		for (int iReihen = 0; iReihen < labelArray.length;iReihen++){
			for (int jSpalten = 0; jSpalten < labelArray[iReihen].length;jSpalten++ ){
				String aufschrift = +iReihen +"" +jSpalten;
				labelArray[iReihen][jSpalten] = new KrassesLabel(aufschrift);
				
				this.add(labelArray[iReihen][jSpalten], jSpalten, iReihen);
				
				labelArray[iReihen][jSpalten].setMinHeight(29);
				labelArray[iReihen][jSpalten].setMinWidth(29);
				labelArray[iReihen][jSpalten].setVisible(false);
				
			}
		}
		int size = pcManager.getSize();
		for (int i = 0; i < size;i++){
			this.add( pcManager.getCharacterByIndex(i), 0, 0);
		}
		for (Weapons waffe : Weapons.values()){
			CluedoWeapon weapon = new CluedoWeapon(waffe, new CluedoPosition(11,11));
			this.add(pcManager.getWeaponImageByWeapon(weapon), 0, 0);
		}
		
	}

	public KrassesLabel[][] getLabelArray() {
		return labelArray;
	}

	public void setLabelArray(KrassesLabel[][] labelArray) {
		this.labelArray = labelArray;
	}
	
	
	
}

