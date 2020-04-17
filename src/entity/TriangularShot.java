package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import constant.Constant;
import constant.ConstantGui;
import dobleList.CircularList;
import dobleList.Node;

public class TriangularShot extends Shot {

	private volatile CircularList<TriangularBullet> bulletList;
	private Random r;
	private int x;
	private int y;
	private int nX;
	private int nY;
	private int destroyIn;
	

	public TriangularShot() {
		super();
		r = new Random();
		bulletList = new CircularList<>();
		x = 0;
		y = 0;
		nX = 0;
		nY = 0;
		destroyIn = 0;
	}
	
	public synchronized CircularList<TriangularBullet> getBulletList() {
		return bulletList;
	}

	@Override
	public void drawMe(Graphics g) {
		if ( Math.random() < Constant.PROBABILITY && Math.random() < Constant.PROBABILITY){
			TriangularBullet b = new TriangularBullet(r.nextInt(width), r.nextInt(height));
			b.setWidth(width);
			b.setHeight(height);
			b.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
			bulletList.add(b);
		}
		Node<TriangularBullet> bulletAux = bulletList.getHead();
		while (bulletAux != bulletList.getQueue()) {
			x = bulletAux.getInfo().getPositiveShot().x;
			y = bulletAux.getInfo().getPositiveShot().y;
			nX = bulletAux.getInfo().getNegativeShot().x;
			nY = bulletAux.getInfo().getNegativeShot().y;
			g.setColor(bulletAux.getInfo().getColor());
			g.fillOval(x, y, 10, 10);
			g.fillOval(nX, nY, 10, 10);
			bulletAux.getInfo().positiveMove();
			bulletAux.getInfo().negativeMove();
			if (x > ConstantGui.SHIP_POS_X && x < (ConstantGui.SHIP_POS_X + ConstantGui.SHIP_WIDTH)) {
				if (y > ConstantGui.SHIP_POS_Y && y < (ConstantGui.SHIP_POS_Y + ConstantGui.SHIP_HEIGHT)) {
					bulletList.delete(bulletAux);
					Constant.SHIP_LIFE -= 1;
				}
			} else if (nX > ConstantGui.SHIP_POS_X && nX < (ConstantGui.SHIP_POS_X + ConstantGui.SHIP_WIDTH)) {
				if (nY > ConstantGui.SHIP_POS_Y && nY < (ConstantGui.SHIP_POS_Y + ConstantGui.SHIP_HEIGHT)) {
					bulletList.delete(bulletAux);
					Constant.SHIP_LIFE -= 1;
				}
			}
			if (y <= destroyIn) {
				bulletList.delete(bulletAux);
			}
			if (nY <= destroyIn) {
				bulletList.delete(bulletAux);
			}
			bulletAux = bulletAux.getNext();
		}
	}


}
