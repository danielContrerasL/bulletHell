package gui.mainWindow;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constant.ConstantGui;
import controller.Controller;
import controller.MyAction;

@SuppressWarnings("serial")
public class PanelLeft extends JPanel{
	
	private Image bg;
	private JButton jBNext;
	private JButton jBPrevious;
	private JButton jBPlayPause;
	private JPanel jPMusic;
	private PanelToDraw panelToDraw;
	private GridBagConstraints c;

	public PanelLeft(Controller controller) {
		c = new GridBagConstraints();
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
		jPMusic = new JPanel();
		jPMusic.setOpaque(false);
		initButton(controller);
		panelToDraw = new PanelToDraw("/ima/sprite/info.png");
		modifiC(1,0,8,7);
		add(panelToDraw, c);
		modifiC(3,11,6,1);
		add(jPMusic, c);
		bg = new ImageIcon(getClass().getResource(ConstantGui.DF_BORDER_PATH)).getImage();
	}

	private void initButton(Controller controller) {
		jBPrevious = new JButton("<--");
		fixButton(jBPrevious);
		jBPrevious.setActionCommand(MyAction.PREVIOUS.toString());
		jBPrevious.addActionListener(controller);
		jBPrevious.addMouseListener(controller);
		jPMusic.add(jBPrevious);
		
		jBPlayPause = new JButton("Play/Pause");
		fixButton(jBPlayPause);
		jBPlayPause.setActionCommand(MyAction.PLAY.toString());
		jBPlayPause.addActionListener(controller);
		jBPlayPause.addMouseListener(controller);
		jPMusic.add(jBPlayPause);
		
		jBNext = new JButton("-->");
		fixButton(jBNext);
		jBNext.setActionCommand(MyAction.NEXT.toString());
		jBNext.addActionListener(controller);
		jBNext.addMouseListener(controller);
		jPMusic.add(jBNext);
	}


	private void fixButton(JButton button) {
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setBorderPainted(false);
		button.setFont(new Font("Calibri", Font.PLAIN ,35));
		button.setForeground(Color.WHITE);
	}
	
	private void modifiC(int x, int y, int w, int h) {
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = w;
		c.gridheight = h;
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.bg, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
