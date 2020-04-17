package gui.mainWindow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import constant.ConstantGui;

@SuppressWarnings("serial")
public class PanelIntro extends JPanel {

	private Image bg;
	private int x;
	private int y;
	private volatile boolean isUp;

	public PanelIntro() {
		isUp = true;
		setBackground(Color.black);
		bg = new ImageIcon(getClass().getResource(ConstantGui.INTRO_BG)).getImage();
		x = 0;
		y = (int) ConstantGui.SIZE_WINDOW.getHeight();

	}

	public void reactivatePanel(String imagePath) {
		bg = new ImageIcon(getClass().getResource("/ima/sprite/" + imagePath + ".png")).getImage();
		y = (int) ConstantGui.SIZE_WINDOW.getHeight();
		isUp = true;
	}

	public synchronized boolean isUp() {
		return isUp;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bg, x, y, this.getWidth(), this.getHeight(), this);
		y--;
		if (y <= x)
			isUp = false;
	}

}
