package entity;

import java.awt.Graphics;
import java.awt.Point;

import constant.Constant;
import constant.ConstantGui;
import dobleList.CircularList;
import dobleList.Node;

public class RingShot extends Shot {

	private volatile CircularList<RingBullet> bulletList;
	private boolean rigth;
	private boolean down;
	private Point p;
	private int x = 0;
	private int y = 0;
	private double r = 0;
	private int w;
	private int h;
	private double deegre;

	public RingShot() {
		super();
		bulletList = new CircularList<>();
		p = new Point(width / 2, (int) (height * 0.35));
		rigth = true;
		down = true;
		w = p.x;
		h = p.y;
	}
	
	public synchronized CircularList<RingBullet> getBulletList() {
		return bulletList;
	}

	@Override
	public void drawMe(Graphics g) {
		w = p.x;
		h = p.y;
		bulletList.add(new RingBullet());
		Node<RingBullet> bulletAux = bulletList.getHead();
		while (bulletAux != bulletList.getQueue()) {
			r = bulletAux.getInfo().getR();
			deegre = bulletAux.getInfo().getDegree();
			x = (int) (((Math.cos(deegre)) * r) + w);
			y = (int) (((Math.sin(deegre)) * r) + h);
			g.setColor(bulletAux.getInfo().getColor());
			bulletAux.getInfo().plussDegres();
			g.fillOval(x, y, 15, 15);
			if (x > ConstantGui.SHIP_POS_X && x < (ConstantGui.SHIP_POS_X + 30)) {
				if (y > ConstantGui.SHIP_POS_Y && y < (ConstantGui.SHIP_POS_Y + 60)) {
					bulletList.delete(bulletAux);
					Constant.SHIP_LIFE -= 1.5;
				}
			}
			if (p.x < (width / 2)) {
				if (x > width)
					bulletList.delete(bulletAux);
			} else {
				if (x < 0)
					bulletList.delete(bulletAux);
			}
			bulletAux = bulletAux.getNext();
		}
		getPoint();

	}

	public synchronized void getPoint() {
		if (rigth && p.x <= (width)) {
			p.x++;
		} else
			rigth = false;

		if (!rigth && p.x >= 0) {
			p.x--;
		} else
			rigth = true;
		if (down && p.y <= (height)) {
			p.y++;
		} else
			down = false;

		if (!down && p.y >= 0) {
			p.y--;
		} else
			down = true;
	}

}
