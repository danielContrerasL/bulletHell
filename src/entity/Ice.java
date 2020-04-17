package entity;

import java.awt.Point;
import java.util.Random;

public class Ice {
	
	private boolean down;
	private boolean rigth;
	private Point p;
	private int width;
	private int height;
	
	public Ice(int width, int height) {
		rigth = true;
		down = true;
		Random r = new Random();
		p = new Point(r.nextInt(width), r.nextInt(height));
		this.height = height;
		this.width = width;
	}
	
	public Point getP() {
		return p;
	}
	
	public void move() {
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
