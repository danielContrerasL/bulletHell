package entity;

import java.awt.Graphics;
import java.awt.Point;

import constant.Constant;
import constant.ConstantGui;
import dobleList.CircularList;
import dobleList.Node;

public class FireShot extends Shot {

	private CircularList<Point> bulletList;
	private Point a;
	private Point b;
	private int y;
	private int x;

	public FireShot() {
		bulletList = new CircularList<>();
		add();
	}

	private void add() {
		b = new Point(ConstantGui.SHIP_POS_X - 15, ConstantGui.SHIP_POS_Y + 30);
		a = new Point(ConstantGui.SHIP_POS_X + 30, ConstantGui.SHIP_POS_Y + 30);
		bulletList.add(a);
		bulletList.add(b);
	}

	@Override
	public void drawMe(Graphics g) {
		add();
		Node<Point> aux = bulletList.getHead();
		g.setColor(ConstantGui.FIRE_BULLET_COLOR);
		while (aux != bulletList.getQueue()) {
			g.fillOval(aux.getInfo().x, aux.getInfo().y, 11, 11);
			y = aux.getInfo().y -= 12;
			if (y <= ConstantGui.ALIEN_POS_Y) {
				x = aux.getInfo().x;
				if (x > ConstantGui.ALIEN_POS_X && x < ConstantGui.ALIEN_BREACK_POINT) {
					Constant.ALIEN_LIFE -= Constant.HIGHT_DAMAGE;
					Constant.SCORE += Constant.MIN_SCORE;
					bulletList.delete(aux);
				}
			} else if (y <= 0)
				bulletList.delete(aux);
			aux = aux.getNext();
		}
	}
}
