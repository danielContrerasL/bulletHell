package constant;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class ConstantGui {

	private static final double ALIEN_HEIGHT = 0.3;
	public static final Dimension SIZE_WINDOW = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static final int ICE_SIZE = 50;
	public static final int SHIP_WIDTH = 30;
	public static final int SHIP_HEIGHT = 60;
	public static final String GAME_NAME = "Jinrui o Mamoru";
	public static final String INTRO_BG = "/ima/sprite/history.png";
	public static final String WIN_BG = "/ima/sprite/bg_cold.jpg";
	public static final String LOSE_BG = "/ima/sprite/bg_cold.jpg";
	public static final String INFO = "/ima/sprite/info.png";

	public static final String ICE_BG = "/ima/sprite/bg_cold.jpg";
	public static final String AURA_ICE = "/ima/sprite/ice_bg.png";
	
	public static final String SHIP_PATH = "/ima/sprite/1.png";
	public static final String ALIEN_PATH = "/ima/sprite/alienEnemy.png";
	public static final String MID_ALIEN_PATH = "/ima/sprite/mid_alien.png";
	public static final Color BULLET_COLOR = new Color(160, 61, 204);
	public static final Color ENEMY_BULLET_COLOR = new Color(255, 65, 14);
	
	public static final Color ICE_BULLET_COLOR = new Color(145, 255, 220);
	public static final Color FIRE_BULLET_COLOR = new Color(204, 81, 5);
	
	public static final String DF_PATH = "/ima/sprite/bg_d.jpg";
	public static final String DF_BORDER_PATH = "/ima/sprite/alien.jpg";
	public static final String DF_BORDER_R_PATH = "/ima/sprite/alienR.jpg";
	public static final String DF_ICON_PATH = "/ima/icon/icon.png";


	public static volatile int SHIP_POS_X = 0;
	public static volatile int SHIP_POS_Y = 0;
	public static volatile boolean IS_COLD = false;

	public static volatile int ALIEN_POS_X = 0;
	public static volatile int ALIEN_POS_Y = (int) (SIZE_WINDOW.getHeight() * ALIEN_HEIGHT);
	public static volatile int ALIEN_BREACK_POINT = 0;
	
	public static final int MIN_CORRECTION_DRAW = 11;

}
