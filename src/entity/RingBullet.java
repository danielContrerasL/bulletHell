package entity;

import java.awt.Color;
import java.util.Random;

public class RingBullet {

	private Color color;
	private double degree;
	private int r;
	private int plussR;
	private int maxR;
	private int minR;
	private double plussDegree;

	public RingBullet() {
		plussDegree = 0.1;
		Random random = new Random();
		r = 12;
		plussR = 1;
		maxR = 45;
		minR = 0;
		this.color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
	}

	public Color getColor() {
		return color;
	}

	public double getDegree() {
		return degree;
	}

	public int getR() {
		return r;
	}

	public void plussDegres() {
		this.degree += plussDegree;
		r += plussR;
		if (this.degree > maxR)
			this.degree = minR;
	}

}
