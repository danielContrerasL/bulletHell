package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import constant.Constant;
import constant.ConstantGui;
import gui.mainWindow.MainWindow;
import logic.Logic;
import logic.ManagerSave;
import logic.MyThread;

public class Controller implements ActionListener, MouseMotionListener, MouseListener {

	private MainWindow mainWindow;
	private Logic logic;
	private ManagerSave managerSave;
	private MyThread managerShot;
	private MyThread gameController;

	public Controller() {
		managerSave = new ManagerSave();
		logic = new Logic();
		mainWindow = new MainWindow(this);
		mainWindow.repaintPanelInfo();
		startGameController();
		mainWindow.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (MyAction.valueOf(e.getActionCommand())) {
		case NEXT:
			logic.next();
			break;
		case PLAY:
			logic.pauseAndPlay();
			break;
		case PREVIOUS:
			logic.previous();
			break;
		case EXIT:
			System.exit(0);
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		ConstantGui.SHIP_POS_X = (e.getX() - 15);
		ConstantGui.SHIP_POS_Y = (e.getY() - 30);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		ConstantGui.SHIP_POS_X = (e.getX() - 15);
		ConstantGui.SHIP_POS_Y = (e.getY() - 30);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			mainWindow.turnFire();
		} catch (Exception e2) {
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		try {
			mainWindow.turnFire();
		} catch (Exception e2) {
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		try {
			if (((JButton) e.getSource()).isEnabled())
				logic.playbutton();
		} catch (Exception e2) {
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		try {
			if (((JButton) e.getSource()).isEnabled())
				logic.playbutton();
		} catch (Exception e2) {
		}
	}

	private void startGameController() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!mainWindow.isReady()) {
					System.out.println("Sin este Syso no funciona, Perdon :D");
				}
				logic.next();
				startThread();
				mainWindow.setEnemy(logic.getAlien());
				mainWindow.addPanelGame();
				gameController.start();
			}
		}).start();
		gameController = new MyThread() {
			@Override
			protected int getSleep() {
				return Constant.TIME_TO_SAVE;
			}

			@Override
			protected void executeTask() {
				managerSave.setLinealBulletList(logic.getBulletList());
				managerSave.setRingBulletList(logic.getRingBullet());
				managerSave.setTriangularBulletList(logic.getTriangularBullet());
				managerSave.save();
				// Para hacer mas dificil quitar el if del comentario
				// if (Math.random() < Constant.PROBABILITY && Math.random() <
				// Constant.PROBABILITY
				// && Math.random() < Constant.PROBABILITY) {
				if (!(Constant.ALIEN_MIN_LIFE > Constant.ALIEN_LIFE)) {
					mainWindow.setTool((logic.getTool()));
				}
				// }

			}
		};

	}

	private void startThread() {
		managerShot = new MyThread() {
			@Override
			protected void executeTask() {
				mainWindow.updateShot(logic.getRandomShot());
				if (Constant.IS_WIN )
					stop();
			}

			@Override
			protected int getSleep() {
				return (int) (Math.random() * Constant.TIME_TO_CHANGE_BULLET);
			}

		};
		managerShot.start();
	}

}
