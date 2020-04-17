package logic;

import java.awt.Point;
import java.util.Random;

import constant.Constant;
import dobleList.CircularList;
import entity.Alien;
import entity.FireShot;
import entity.IceShot;
import entity.LinealShot;
import entity.RingBullet;
import entity.RingShot;
import entity.Shot;
import entity.Tool;
import entity.TriangularBullet;
import entity.TriangularShot;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class Logic {

	private LinealShot linealShot;
	private RingShot ringShot;
	private TriangularShot triangularShot;
	private Random random;
	private Shot actualShot;
	private byte idSong;
	private boolean isPause;
	private Tool tool;

	private BasicPlayer player;
	private MusicManager manager;

	public Logic() {
		isPause = false;
		idSong = 0;
		random = new Random();
		player = new BasicPlayer();// getclassLoader().getResourceAsStream("src/music/button.wav")
		// manager = new
		// MusicManager(getclassLoader().getResourceAsStream("src/music/button.wav"));
		// manager = new
		// MusicManager(getClass().getResourceAsStream("src/music/button.wav"));
		// manager = new MusicManager("src/music/button.wav");
		manager = new MusicManager("/music/button.wav");
		this.linealShot = new LinealShot();
		this.ringShot = new RingShot();
		this.triangularShot = new TriangularShot();
		initMusic();
		isWin();
	}

	public IceShot getIceShot() {
		return new IceShot();
	}

	public FireShot getFireShot() {
		return new FireShot();
	}

	public CircularList<TriangularBullet> getTriangularBullet() {
		return triangularShot.getBulletList();
	}

	public CircularList<RingBullet> getRingBullet() {
		return ringShot.getBulletList();
	}

	public CircularList<Point> getBulletList() {
		return linealShot.getBulletList();
	}

	public Alien getAlien() {
		return new Alien();
	}

	public Tool getTool() {
		tool = null;
		tool = new Tool();
		return tool;
	}

	public synchronized void playbutton() {
		manager.play();
	}

	public void pauseAndPlay() {
		if (!isPause)
			pause();
		else
			resume();
		isPause = !isPause;
	}

	private void initMusic() {
		// open(getClass().getResource("/music/1.mp3").toString().replaceAll("bin",
		// "src"));
		// open("src/music/intro.mp3");
		// open("E:/uptc/programacion_lll/bulletHell/src/music/" + idSong +
		// ".mp3");
		open("/music/intro.mp3");
		play();
	}

	public void playFinishMusic(String song) {
		open("/music/" + song + ".mp3");
		play();
	}

	public void next() {
		idSong++;
		open("/music/" + idSong + ".mp3");
		play();
		if (idSong == 3)
			idSong = 0;
	}

	public void previous() {
		idSong--;
		if (idSong == 0)
			idSong = 3;
		open("/music/" + idSong + ".mp3");
		play();
	}

	private void open(String file) {
		try {
			player.open((getClass().getResource(file)));
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	private void play() {
		try {
			player.play();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	private void pause() {
		try {
			player.pause();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	private void resume() {
		try {
			player.resume();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void stop() {
		try {
			player.stop();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	private void isWin() {
		new MyThread() {

			@Override
			protected int getSleep() {
				return 0;
			}

			@Override
			protected void executeTask() {
				if (!(Constant.ALIEN_LIFE >= 0 && Constant.SHIP_LIFE >= 0)){
					String name = "dead";
					if (Constant.ALIEN_LIFE <= 0 && Constant.SHIP_LIFE >= 0)
						name = "win";
					playFinishMusic(name);
					Constant.IS_WIN = true;
					stop();
				}
			}
		}.start();
	}

	public Shot getRandomShot() {
		switch (random.nextInt(3)) {
		case 0:
			actualShot = linealShot;
			break;
		case 1:
			actualShot = triangularShot;
			break;
		case 2:
			actualShot = ringShot;
			break;

		default:
			break;
		}
		return actualShot;

	}

}
