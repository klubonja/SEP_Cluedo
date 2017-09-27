package staticClasses;

import java.io.File;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Eine statische Klasse, die die Mehrheit der Audio-Methoden des Spiels enthält
 * 
 * @author Nedko Chulev
 *
 */
public class Sounds {
	/**
	 * Spielt ein Geraeusch, waehrend die Karten verteilt werden.
	 */
	public void cardSound() {
//		String sound = "src/media/sounds/card.wav";
//		Media mediaFile = new Media(new File(sound).toURI().toString());
//		MediaPlayer mediaplayer = new MediaPlayer(mediaFile);
//		mediaplayer.setAutoPlay(true);
//		mediaplayer.setVolume(1);
		AudioClip card = new AudioClip(getClass().getResource("media/sounds/card.wav").toExternalForm());
		card.play();
		card.setVolume(1);
	}

	/**
	 * Sound fur den Gang nehmen Button
	 */
	public void gangSound() {
//		String sound = "src/media/sounds/gang.mp3";
//		Media mediaFile = new Media(new File(sound).toURI().toString());
//		MediaPlayer mediaplayer = new MediaPlayer(mediaFile);
//		mediaplayer.setAutoPlay(true);
//		mediaplayer.setVolume(1);
		AudioClip gang = new AudioClip(getClass().getResource("gang.mp3").toExternalForm());
		gang.play();
		gang.setVolume(1);
	}

	/**
	 * Sound fur die Turkacheln
	 */
	public void doorSound() {
//		String sound = "src/media/sounds/door.mp3";
//		Media mediaFile = new Media(new File(sound).toURI().toString());
//		MediaPlayer mediaplayer = new MediaPlayer(mediaFile);
//		mediaplayer.setAutoPlay(true);
//		mediaplayer.setVolume(1);
		AudioClip door= new AudioClip(getClass().getResource("media/sounds/door.mp3").toExternalForm());
		door.play();
		door.setVolume(1);
	}

	/**
	 * Sound fur das Wurfeln
	 */
	public void diceSound() {
//		String sound = "src/media/sounds/dice.wav";
//		Media mediaFile = new Media(new File(sound).toURI().toString());
//		MediaPlayer mediaplayer = new MediaPlayer(mediaFile);
//		mediaplayer.setAutoPlay(true);
//		mediaplayer.setVolume(1);
		AudioClip dice = new AudioClip(getClass().getResource("media/sounds/dice.mp3").toExternalForm());
		dice.play();
		dice.setVolume(1);
	}
}
