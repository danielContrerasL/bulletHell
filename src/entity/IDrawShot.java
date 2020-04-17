package entity;

import java.awt.Graphics;

public interface IDrawShot {
	
	public void setMyWidth();
	public void setMyHeigth();
	public abstract void drawMe(Graphics g);
}
