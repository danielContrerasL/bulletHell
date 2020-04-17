package entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import constant.ConstantGui;

public class Tool extends Shot {

	private Image ice;
	private Ice tool;
	private int x;
	private int y;

	public Tool() {
		ice = new ImageIcon(getClass().getResource("/ima/sprite/ice.png")).getImage();
		tool = new Ice(width, height);
		x = 0;
		y = 0;
	}

	@Override
	public void drawMe(Graphics g) {
		if (tool != null) {
			x = tool.getP().x;
			y = tool.getP().y;
			g.drawImage(ice, x, y, ConstantGui.ICE_SIZE, ConstantGui.ICE_SIZE, null);
			tool.move();
			if (x < ConstantGui.SHIP_POS_X && (x + ConstantGui.ICE_SIZE) > ConstantGui.SHIP_POS_X) {
				if (y < ConstantGui.SHIP_POS_Y && (y + ConstantGui.ICE_SIZE) > ConstantGui.SHIP_POS_Y) {
					tool = null;
					ConstantGui.IS_COLD = true;
				}
			} else if (tool.getP().y <= 5) {
				tool = null;
			}

		}
	}

}
