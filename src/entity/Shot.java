package entity;

import constant.ConstantGui;

public abstract class Shot implements IDrawShot{
	
	protected int width;
	protected int height;
	public Shot() {
		setMyHeigth();
		setMyWidth();
	}
	
	public void setMyHeigth() {
		this.height = (int) ConstantGui.SIZE_WINDOW.getHeight();
		
	}

	public void setMyWidth() {
		this.width = (int) (ConstantGui.SIZE_WINDOW.getWidth() * 0.4);
		
	}

	
	
}
