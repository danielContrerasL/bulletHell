package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import constant.Constant;
import constant.ConstantGui;
import dobleList.CircularList;
import dobleList.Node;

public class LinealShot extends Shot {

	private volatile CircularList<Point> bulletList;
	private Random r;
	private int a;
	private int b;
	private int c;
	private int y;

	public LinealShot() {
		bulletList = new CircularList<>();
		r = new Random();
		a = (int) (width * 0.3);
		b = (int) (width * 0.5);
		c = (int) (width * 0.7);
		y = (int) (height * 0.15);
	}
	
	public synchronized CircularList<Point> getBulletList() {
		return bulletList;
	}

	@Override
	public void drawMe(Graphics g) {
		if (Math.random() < 0.5 && Math.random() < 0.5 && Math.random() < 0.5) {
			a = (int) (width * Math.random());
			b = (int) (width * Math.random());
			c = (int) (width * Math.random());
			y = (int) (height * Math.random());
		}

		bulletList.add(new Point(a, y));
		bulletList.add(new Point(b, y));
		bulletList.add(new Point(c, y));
		Node<Point> bulletAux = bulletList.getHead();
		g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
		while (bulletAux != bulletList.getQueue()) {// 5-30
			g.fillOval((bulletAux.getInfo().x - 5), (bulletAux.getInfo().y - 30), 8, 8);
			bulletAux.getInfo().y += 12;

			if (bulletAux.getInfo().x > ConstantGui.SHIP_POS_X && bulletAux.getInfo().x < (ConstantGui.SHIP_POS_X + 30)) {
				if (bulletAux.getInfo().y > ConstantGui.SHIP_POS_Y && bulletAux.getInfo().y < (ConstantGui.SHIP_POS_Y + 60)) {
					bulletList.delete(bulletAux);
					Constant.SHIP_LIFE -= 1.5;
				}
			}

			if (bulletAux.getInfo().y > height) {
				bulletList.delete(bulletAux);
			}
			bulletAux = bulletAux.getNext();
		}

	}

}
