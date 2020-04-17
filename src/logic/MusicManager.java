package logic;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class MusicManager {
	
	private BasicPlayer buttonPlayer;
	
	
	
	public MusicManager(String path) {
		buttonPlayer = new BasicPlayer();
		open(path);
	}
	
	public void open(String file) {
		try {
			buttonPlayer.open(getClass().getResource(file));
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			buttonPlayer.play();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	public void pause() {
		try {
			buttonPlayer.pause();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	public void resume() {
		try {
			buttonPlayer.resume();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			buttonPlayer.stop();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
}
