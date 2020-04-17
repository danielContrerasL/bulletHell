package gui.mainWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import constant.Constant;
import constant.ConstantGui;
import controller.Controller;
import entity.Alien;
import entity.Shot;
import entity.Tool;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private PanelDraw panelDraw;
	private PanelLeft panelLeft;
	private PanelRight panelRight;
	private PanelIntro panelInfo;
	private JPanel center;
	private boolean isReady;

	public MainWindow(Controller controller) {
		initSizeWindow();
		setExtendedState(MAXIMIZED_BOTH);
		setTitle(ConstantGui.GAME_NAME);
		setIconImage(new ImageIcon(getClass().getResource(ConstantGui.DF_ICON_PATH)).getImage());
		setUndecorated(true);
		setResizable(false);
		isReady =false;
		initPanelGame(controller);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initPanelGame(Controller controller) {
		panelInfo = new PanelIntro();
		center = new JPanel();
		center.setBackground(Color.PINK);
		center.setLayout(new BorderLayout());
		center.setPreferredSize(
				new Dimension((int) (ConstantGui.SIZE_WINDOW.width * 0.4), ConstantGui.SIZE_WINDOW.height));
		center.setSize(center.getPreferredSize());
		
		panelDraw = new PanelDraw(controller);
		panelDraw.setPreferredSize(
				new Dimension((int) (ConstantGui.SIZE_WINDOW.width * 0.4), ConstantGui.SIZE_WINDOW.height));
		panelDraw.setSize(panelDraw.getPreferredSize());

		panelLeft = new PanelLeft(controller);
		
		panelLeft.setPreferredSize(
				new Dimension((int) (ConstantGui.SIZE_WINDOW.width * 0.3), ConstantGui.SIZE_WINDOW.height));
		panelRight = new PanelRight(controller);
		panelRight.setPreferredSize(
				new Dimension((int) (ConstantGui.SIZE_WINDOW.width * 0.3), ConstantGui.SIZE_WINDOW.height));
		
		center.add(panelInfo, BorderLayout.CENTER);
		add(center, BorderLayout.CENTER);
		add(panelLeft, BorderLayout.WEST);
		add(panelRight, BorderLayout.EAST);
		updateLife();
		
	}
	
	public void addPanelGame() {
		center.remove(panelInfo);
		panelDraw.initThread();
		center.add(panelDraw, BorderLayout.CENTER);
		center.revalidate();
	}
	
	public void reactivatePanelInfo(String imagePath) {
		panelInfo.reactivatePanel(imagePath);
		center.remove(panelDraw);
		center.add(panelInfo, BorderLayout.CENTER);
		center.revalidate();
	}

	private void initSizeWindow() {
		setSize(ConstantGui.SIZE_WINDOW);
		setMinimumSize(getSize());
	}

	public void turnFire() {
		panelDraw.turnFire();
	}

	public void updateShot(Shot shot) {
		panelDraw.updateShot(shot);
	}
	
	public void setEnemy(Alien alien) {
		panelDraw.setEnemy(alien);
	}
//	
	public void setTool(Tool tool) {
		panelDraw.setTool(tool);
	}
	public boolean isReady() {
		return isReady;
	}

	
	public void repaintPanelInfo() {
		SwingWorker<Void, Void> intro = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				while (panelInfo.isUp()) {
					panelInfo.repaint();
					Thread.sleep(15);
				}
				Thread.sleep(5500);
				return null;
			}

			@Override
			protected void done() {
				super.done();
				isReady = true;
			}
		};
		intro.execute();
	}
	
	private void updateLife() {
		SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				while (!Constant.IS_WIN) {
					panelRight.updateProgress();
					Thread.sleep(Constant.TIME_TO_SEELP);
				}
				panelRight.setProgress();
				Thread.sleep(5000);
				String name = "dead";
				if (Constant.ALIEN_LIFE <= 0 && Constant.SHIP_LIFE >= 0)
					name = "win";
				reactivatePanelInfo(name);
				while (panelInfo.isUp()) {
					panelInfo.repaint();
					Thread.sleep(18);
				}
				return null;
			}

		};
		sw.execute();
	}


}
