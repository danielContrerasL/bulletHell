package entity;

import java.awt.Graphics;
import java.awt.Point;

import constant.Constant;
import constant.ConstantGui;
import dobleList.CircularList;
import dobleList.Node;

public class IceShot extends Shot {

	private CircularList<Point> bulletList;
	private Point a;
	private Point b;
	private Point c;
	private Point d;
	private int y;
	private int x;

	public IceShot() {
		bulletList = new CircularList<>();
		add();
	}

	private void add() {
		a = new Point(ConstantGui.SHIP_POS_X - 30, ConstantGui.SHIP_POS_Y - 60);
		b = new Point(ConstantGui.SHIP_POS_X - 15, ConstantGui.SHIP_POS_Y - 30);
		c = new Point(ConstantGui.SHIP_POS_X + 45, ConstantGui.SHIP_POS_Y - 30);
		d = new Point(ConstantGui.SHIP_POS_X + 60, ConstantGui.SHIP_POS_Y - 60);
		bulletList.add(a);
		bulletList.add(b);
		bulletList.add(c);
		bulletList.add(d);
	}

	@Override
	public void drawMe(Graphics g) {
		add();
		Node<Point> aux = bulletList.getHead();
		g.setColor(ConstantGui.ICE_BULLET_COLOR);
		while (aux != bulletList.getQueue()) {
			g.fillOval(aux.getInfo().x, aux.getInfo().y, 8, 8);
			y = aux.getInfo().y -= 12;
			if (y <= ConstantGui.ALIEN_POS_Y) {
				x = aux.getInfo().x;
				if (x > ConstantGui.ALIEN_POS_X && x < ConstantGui.ALIEN_BREACK_POINT) {
				Constant.ALIEN_LIFE -= Constant.MID_DAMAGE;
				Constant.SCORE += Constant.MIN_SCORE;
				bulletList.delete(aux);
				}
			} else if (y <= 0)
				bulletList.delete(aux);
			aux = aux.getNext();
		}
	}

}
