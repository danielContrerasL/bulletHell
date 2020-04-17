package gui.mainWindow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import constant.Constant;
import constant.ConstantGui;
import controller.Controller;
import controller.MyAction;

@SuppressWarnings("serial")
public class PanelRight extends JPanel {

	private Image bg;
	private GridBagConstraints c;
	private JProgressBar progressAlienLife;
	private JProgressBar progressShipLife;
	private JProgressBar progressIceFire;
	private JProgressBar progressFireFire;
	private JLabel jLScore;
	private JLabel jLAlienLife;
	private JLabel jLShipLife;
	private JButton exitButton;

	public PanelRight(Controller controller) {
		setOpaque(false);
		c = new GridBagConstraints();
		bg = new ImageIcon(getClass().getResource(ConstantGui.DF_BORDER_R_PATH)).getImage();
		setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0.01;
		c.gridheight = 1;
		for (int i = 0; i < 12; i++) {
			c.gridx = i;
			c.gridy = i;
			add(new JLabel(), c);
		}
		initLabel();
		initProgresAlienLife();
		initProgresShipLife();
		initProgressIcePower();
		initProgressFirePower();
		initThread();
		initButton(controller);
	}

	private void initLabel() {
		jLScore = new JLabel("Score 0");
		modifiC(1, 1, 10, 1);
		add(jLScore, c);
		jLAlienLife = new JLabel("Alien " + Constant.ALIEN_LIFE);
		modifiC(1, 3, 10, 1);
		add(jLAlienLife, c);
		jLShipLife = new JLabel("Ship " + Constant.SHIP_LIFE);
		modifiC(1, 6, 10, 1);
		add(jLShipLife, c);
	}
	
	private void initThread() {
		SwingWorker<Void, Void> update = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				while (Constant.MAX_BULLET_ICE_POWER >= 0) {
					progressFireFire.setValue(Constant.MAX_BULLET_FIRE_POWER);
					progressIceFire.setValue(Constant.MAX_BULLET_ICE_POWER);
				}
				return null;
			}
			
			@Override
			protected void done() {
				super.done();
				progressFireFire.setValue(0);
				progressIceFire.setValue(0);
			}
			
		};
		update.execute();
	}

	private void initButton(Controller controller) {
		exitButton = new JButton("Salir");
		exitButton.addActionListener(controller);
		exitButton.setActionCommand(MyAction.EXIT.toString());
		exitButton.addMouseListener(controller);
		exitButton.setBackground(Color.RED);
		c.fill = GridBagConstraints.HORIZONTAL;
		modifiC(4, 11, 4, 1);
		add(exitButton, c);
	}

	private void initProgresAlienLife() {
		progressAlienLife = new JProgressBar();
		progressAlienLife.setStringPainted(true);
		progressAlienLife.setMaximum((int) Constant.ALIEN_LIFE);
		progressAlienLife.setBackground(new Color(93, 188, 210));
		progressAlienLife.setForeground(new Color(101, 12, 153));
		modifiC(1, 4, 10, 1);
		c.fill = GridBagConstraints.HORIZONTAL;
		add(progressAlienLife, c);
	}
	
	private void initProgressIcePower() {
		progressIceFire = new JProgressBar();
		progressIceFire.setStringPainted(true);
		progressIceFire.setMaximum(2500);
		progressIceFire.setBackground(Color.GRAY);
		progressIceFire.setForeground(ConstantGui.ICE_BULLET_COLOR);
		modifiC(1, 9, 10,1 );
		c.fill = GridBagConstraints.HORIZONTAL;
		add(progressIceFire, c);
	}
	
	private void initProgressFirePower() {
		progressFireFire = new JProgressBar();
		progressFireFire.setStringPainted(true);
		progressFireFire.setMaximum(1500);
		progressFireFire.setBackground(Color.GRAY);
		progressFireFire.setForeground(ConstantGui.FIRE_BULLET_COLOR);
		modifiC(1, 8, 10,1 );
		c.fill = GridBagConstraints.HORIZONTAL;
		add(progressFireFire, c);
	}

	private void initProgresShipLife() {
		progressShipLife = new JProgressBar();
		progressShipLife.setStringPainted(true);
		progressShipLife.setMaximum((int) Constant.SHIP_LIFE);
		progressShipLife.setBackground(new Color(93, 188, 210));
		progressShipLife.setForeground(new Color(101, 12, 153));
		modifiC(1, 7, 10, 1);
		c.fill = GridBagConstraints.HORIZONTAL;
		add(progressShipLife, c);
	}

	public void updateProgress() {
		progressAlienLife.setValue((int) Constant.ALIEN_LIFE);
		jLAlienLife.setText("Alien " + (int) Constant.ALIEN_LIFE);
		progressShipLife.setValue((int) Constant.SHIP_LIFE);
		jLShipLife.setText("Ship " + (int) Constant.SHIP_LIFE);
		jLScore.setText("Score " + Constant.SCORE);
	}

	public void setProgress() {
		if (Constant.ALIEN_LIFE > 0) {
			jLShipLife.setText("Ship " + 0);
			progressShipLife.setValue(0);
		} else {
			jLAlienLife.setText("Alien " + 0);
			progressAlienLife.setValue(0);
		}
		revalidate();
	}

	private void modifiC(int x, int y, int w, int h) {
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = w;
		c.gridheight = h;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g2);
		g2.drawImage(this.bg, 0, 0, this.getWidth(), this.getHeight(), this);
		g2.setColor(new Color(1, 1, 1, 0.3f));
		g2.fillRoundRect(jLScore.getX(), jLScore.getY(), jLScore.getWidth(), jLScore.getHeight(), 20, 20);
		g2.fillRoundRect(jLAlienLife.getX(), jLAlienLife.getY(), jLAlienLife.getWidth(), jLAlienLife.getHeight(), 20, 20);
		g2.fillRoundRect(jLShipLife.getX(), jLShipLife.getY(), jLShipLife.getWidth(), jLShipLife.getHeight(), 20, 20);
	}

}
