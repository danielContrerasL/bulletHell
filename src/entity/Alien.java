package entity;

import java.awt.Graphics;
import javax.swing.ImageIcon;

import constant.Constant;
import constant.ConstantGui;
import java.awt.Image;

public class Alien extends Shot {

	private Image alien;
	private Image aura;
	private boolean isMove;
	private int w;
	private int h;
	private int min;
	private int max;

	public Alien() {
		isMove = true;
		alien = new ImageIcon(getClass().getResource(ConstantGui.ALIEN_PATH)).getImage();
		aura = new ImageIcon(getClass().getResource(ConstantGui.AURA_ICE)).getImage();
		w = (int) (width * 0.6);
		h = (int) (height * 0.3);
		min = 0;
		max = (int) (height * 0.3);
	}
	
	public void chengeAlien() {
		alien = new ImageIcon(getClass().getResource(ConstantGui.MID_ALIEN_PATH)).getImage();
	}

	@Override
	public void drawMe(Graphics g) {
		g.drawImage(alien, ConstantGui.ALIEN_POS_X, min, w, h, null);
		if (ConstantGui.IS_COLD) {
			g.drawImage(aura, ConstantGui.ALIEN_POS_X, min, w, h, null);
			Constant.CONT_COLD_TIME ++;
			System.out.println(Constant.CONT_COLD_TIME );
			if (Constant.COLD_TIME <= Constant.CONT_COLD_TIME) {
				Constant.CONT_COLD_TIME = 0;
				ConstantGui.IS_COLD = false;
			}
		}
	}
	
	public void move() {
		if (isMove && ConstantGui.ALIEN_POS_X >= min)
			ConstantGui.ALIEN_POS_X--;
		else
			isMove = false;

		if (!isMove && ConstantGui.ALIEN_POS_X < max)
			ConstantGui.ALIEN_POS_X++;
		else
			isMove = true;
		
		
		ConstantGui.ALIEN_BREACK_POINT = ConstantGui.ALIEN_POS_X + h;
	}

}
