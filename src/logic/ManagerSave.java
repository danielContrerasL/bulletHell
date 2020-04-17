package logic;

import java.awt.Point;
import java.io.IOException;

import constant.Constant;
import constant.ConstantGui;
import dobleList.CircularList;
import dobleList.Node;
import entity.RingBullet;
import entity.TriangularBullet;
import persistence.Persistence;

public class ManagerSave {

	private CircularList<TriangularBullet> triangularBulletList;
	private CircularList<RingBullet> ringBulletList;
	private CircularList<Point> linealBulletList;

	public ManagerSave() {
		Persistence.setFileName("coordinates.txt");
		try {
			Persistence.open(false);
			Persistence.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setTriangularBulletList(CircularList<TriangularBullet> triangularBulletList) {
		this.triangularBulletList = triangularBulletList;
	}

	public void setRingBulletList(CircularList<RingBullet> ringBulletList) {
		this.ringBulletList = ringBulletList;
	}

	public void setLinealBulletList(CircularList<Point> linealBulletList) {
		this.linealBulletList = linealBulletList;
	}

	public void save() {
		try {
			Persistence.delete();
			Persistence.open(false);
			saveTriangularPosition();
			saveCircularPosition();
			savelinealPosition();
			saveGameSetings();
			Persistence.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveTriangularPosition() throws IOException {
		String coordinates = "";
		Node<TriangularBullet> aux = triangularBulletList.getHead();
		while (aux != triangularBulletList.getQueue()) {
			coordinates += ("(" + aux.getInfo().getPositiveShot().x + ":" + aux.getInfo().getPositiveShot().y);
			coordinates += (":" + aux.getInfo().getNegativeShot().x + ":" + aux.getInfo().getNegativeShot().y + ");");
			coordinates += ("{" + aux.getInfo().getColor().getRGB() + "},");
			aux = aux.getNext();
		}
		Persistence.writer(coordinates);
	}

	private void saveCircularPosition() throws IOException {
		String coordinates = "";
		Node<RingBullet> aux = ringBulletList.getHead();
		while (aux != ringBulletList.getQueue()) {
			coordinates += ("(" + aux.getInfo().getDegree() + ":" + aux.getInfo().getR() + ":"
					+ aux.getInfo().getColor().getRGB() + "),");
			aux = aux.getNext();
		}
		Persistence.writer(coordinates);
	}

	private void savelinealPosition() throws IOException {
		String coordinates = "";
		Node<Point> aux = linealBulletList.getHead();
		while (aux != linealBulletList.getQueue()) {
			coordinates += ("(" + aux.getInfo().x + ":" + aux.getInfo().y + "),");
			aux = aux.getNext();
		}
		Persistence.writer(coordinates);
	}

	private void saveGameSetings() throws IOException {
		String alienPos = Constant.ALIEN_LIFE + "," + ConstantGui.ALIEN_POS_X + "," + ConstantGui.ALIEN_POS_Y + ":";
		alienPos += Constant.SHIP_LIFE + "," + ConstantGui.SHIP_POS_X + "," + ConstantGui.SHIP_POS_Y + ":";
		alienPos += Constant.SCORE;
		Persistence.writer(alienPos);
	}

}
