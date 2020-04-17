package gui.mainWindow;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import constant.Constant;
import constant.ConstantGui;
import controller.Controller;
import dobleList.CircularList;
import dobleList.Node;
import entity.Alien;
import entity.FireShot;
import entity.IceShot;
import entity.Shot;
import entity.Tool;
import logic.MyThread;

@SuppressWarnings("serial")
public class PanelDraw extends JPanel {

	private Image ship;
	private Image spaceBg;
	private Image iceBg;
	private Image bg;

	private MyThread myThread;
	private CircularList<Point> bulletList;
	private boolean isFire;

	private Shot shot;

	private Alien enemy;

	private Tool tool;

	private MyThread moveEnemy;

	public PanelDraw(Controller controller) {
		bulletList = new CircularList<>();
		ship = new ImageIcon(getClass().getResource(ConstantGui.SHIP_PATH)).getImage();
		spaceBg = new ImageIcon(getClass().getResource(ConstantGui.DF_PATH)).getImage();
		iceBg = new ImageIcon(getClass().getResource(ConstantGui.ICE_BG)).getImage();
		addMouseMotionListener(controller);
		addMouseListener(controller);
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		this.setCursor(blankCursor);
		// initThread();
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public void setEnemy(Alien alien) {
		this.enemy = alien;
	}

	public void initThread() {
		myThread = new MyThread() {
			@Override
			protected void executeTask() {
				synchronized (bulletList) {
					if (isFire) {
						bulletList.add(new Point(ConstantGui.SHIP_POS_X + ConstantGui.MIN_CORRECTION_DRAW,
								ConstantGui.SHIP_POS_Y));
					}
					Node<Point> bulletAux = bulletList.getHead();
					while (bulletAux != bulletList.getQueue()) {
						bulletAux.getInfo().y -= ConstantGui.MIN_CORRECTION_DRAW;
						bulletAux = bulletAux.getNext();
					}
					repaint();
				}
				if (Constant.IS_WIN)
					stop();
			}

			@Override
			protected int getSleep() {
				return Constant.TIME_TO_SEELP;
			}
		};
		myThread.start();

		MyThread managerMove = new MyThread() {

			@Override
			protected int getSleep() {
				return Constant.TIME_TO_SEELP;
			}

			@Override
			protected void executeTask() {
				if (!ConstantGui.IS_COLD) {
					moveEnemy.resume();
					pause();
				}

			}
		};
		moveEnemy = new MyThread() {
			@Override
			protected int getSleep() {
				return Constant.TIME_TO_SEELP;
			}

			@Override
			protected void executeTask() {
				enemy.move();
				if (ConstantGui.IS_COLD) {
					pause();
					managerMove.resume();
				}
				if (Constant.ALIEN_MIN_LIFE > Constant.ALIEN_LIFE) {
					stop();
					managerMove.stop();
					enemy.chengeAlien();
				}
			}
		};

		managerMove.pause();
		managerMove.start();
		moveEnemy.start();
//		shotAuxuliarBullet();
		drawAxiliarShot(this.getGraphics());
	}
	private void shotAuxuliarBullet() {
//		SwingWorker<Void, Void> iceBullet = new SwingWorker<Void, Void>() {
//			Shot iceShot = new IceShot();
//			@Override
//			protected Void doInBackground() throws Exception {
//				Graphics g2g = PanelDraw.this.getGraphics();
//				while (Constant.MAX_BULLET_ICE_POWER >= 0) {
//					iceShot.drawMe(g2g);
//					Thread.sleep(Constant.TIME_TO_SEELP);
//					Constant.MAX_BULLET_ICE_POWER--;
//					if(Constant.IS_WIN)
//						Constant.MAX_BULLET_ICE_POWER =0;
//				}
//				return null;
//			}
//
//		};
//
//		SwingWorker<Void, Void> fireBullet = new SwingWorker<Void, Void>() {
//			Shot fireShot = new FireShot();
//			@Override
//			protected Void doInBackground() throws Exception {
//				Graphics g2g = PanelDraw.this.getGraphics();
//				while (Constant.MAX_BULLET_FIRE_POWER >= 0) {
//					fireShot.drawMe(g2g);
//					Thread.sleep(Constant.TIME_TO_SEELP);
//					Constant.MAX_BULLET_FIRE_POWER--;
//					if(Constant.IS_WIN)
//						Constant.MAX_BULLET_FIRE_POWER =0;
//				}
//				return null;
//			}
//
//			@Override
//			protected void done() {
//				super.done();
//				iceBullet.execute();
//			}
//		};
//		fireBullet.execute();
	}

	public synchronized void updateShot(Shot shot) {
		this.shot = shot;
	}

	public synchronized void turnFire() {
		isFire = !isFire;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		bg = ConstantGui.IS_COLD ? iceBg : spaceBg;
		g.drawImage(this.bg, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawEnemy(g);
		drawShip(g);
		if (tool != null)
			drawTool(g);

	}

	private void drawTool(Graphics g) {
		tool.drawMe(g);
	}

	private void drawShip(Graphics g) {

		g.drawImage(ship, ConstantGui.SHIP_POS_X, ConstantGui.SHIP_POS_Y, 30, 60, this);
		g.setColor(ConstantGui.BULLET_COLOR);
		Node<Point> bulletAux = bulletList.getHead();

		while (bulletAux != bulletList.getQueue()) {// 5-30
			if (bulletAux.getInfo().y < ConstantGui.ALIEN_POS_Y) {
				if (bulletAux.getInfo().x > ConstantGui.ALIEN_POS_X
						&& bulletAux.getInfo().x < ConstantGui.ALIEN_BREACK_POINT) {
					bulletList.delete(bulletAux);
					Constant.ALIEN_LIFE -= Constant.MIN_DAMAGE;
					Constant.SCORE += Constant.MIN_SCORE;
				}
			}
			g.fillOval((bulletAux.getInfo().x), (bulletAux.getInfo().y), 8, 8);
			bulletAux = bulletAux.getNext();
		}
	}

	private void drawEnemy(Graphics g) {
		synchronized (shot) {
			this.shot.drawMe(g);
		}
		enemy.drawMe(g);
	}
	
	private void drawAxiliarShot(Graphics g) {
			SwingWorker<Void, Void> iceBullet = new SwingWorker<Void, Void>() {
				Shot iceShot = new IceShot();
				@Override
				protected Void doInBackground() throws Exception {
					while (Constant.MAX_BULLET_ICE_POWER >= 0) {
						iceShot.drawMe(g);
						Thread.sleep(Constant.TIME_TO_SEELP);
						Constant.MAX_BULLET_ICE_POWER--;
						if(Constant.IS_WIN)
							Constant.MAX_BULLET_ICE_POWER =0;
					}
					return null;
				}

			};

			SwingWorker<Void, Void> fireBullet = new SwingWorker<Void, Void>() {
				Shot fireShot = new FireShot();
				@Override
				protected Void doInBackground() throws Exception {
					while (Constant.MAX_BULLET_FIRE_POWER >= 0) {
						fireShot.drawMe(g);
						Thread.sleep(Constant.TIME_TO_SEELP);
						Constant.MAX_BULLET_FIRE_POWER--;
						if(Constant.IS_WIN)
							Constant.MAX_BULLET_FIRE_POWER =0;
					}
					return null;
				}

				@Override
				protected void done() {
					super.done();
					iceBullet.execute();
				}
			};
			fireBullet.execute();
	}

}
