package gui.mainWindow;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class PanelToDraw extends JPanel{
	
	private Image bg;
	
	public PanelToDraw(String path) {
		bg = new ImageIcon(getClass().getResource(path)).getImage();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	

}
