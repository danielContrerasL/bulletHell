package entity;

import java.awt.Color;
import java.awt.Point;

public class TriangularBullet {
	
	private boolean down;
	private boolean rigth;
	private boolean down1;
	private boolean rigth1;
	private Point positiveShot;
	private Point negativeShot;
	private int width;
	private int height;
	private Color color;
	
	
	public TriangularBullet(int x, int y) {
		positiveShot = new Point(x, y);
		negativeShot = new Point(x, y);
		down = true;
		rigth = true;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public Point getPositiveShot() {
		return positiveShot;
	}
	
	public Point getNegativeShot() {
		return negativeShot;
	}

	public synchronized void positiveMove() {
		if (rigth && positiveShot.x <= (width)) {
			positiveShot.x ++;
		} else
			rigth = false;

		if (!rigth && positiveShot.x >= 0) {
			positiveShot.x --;
		} else
			rigth = true;
		if (down && positiveShot.y <= (height)) {
			positiveShot.y ++;
		} else
			down = false;

		if (!down && positiveShot.y >= 0) {
			positiveShot.y --;
		} else
			down = true;
	}
	
	public synchronized void negativeMove() {
		if (!rigth1 && negativeShot.x >= 0) {
			negativeShot.x --;
		} else
			rigth1 = true;
		
		if (rigth1 && negativeShot.x <= (width)) {
			negativeShot.x++;
		} else
			rigth1 = false;

		if (!down1 && negativeShot.y >= 0) {
			negativeShot.y --;
		} else
			down1 = true;
		
		if (down1 && negativeShot.y <= (height)) {
			negativeShot.y ++;
		} else
			down1 = false;

	}

}
