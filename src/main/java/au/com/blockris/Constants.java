package au.com.blockris;

public class Constants {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int FPS = 60;
	public static final int SHAPE_DROP_INTERVAL = 60;
	
	public static final int PLAY_AREA_WIDTH = 300;
	public static final int PLAY_AREA_HEIGHT = 600;

	public static final int BLOCK_SIZE = 30;
	public static final int PA_LEFT_X = (WIDTH/2) - (PLAY_AREA_WIDTH/2);
	public static final int PA_RIGHT_X = PA_LEFT_X + PLAY_AREA_WIDTH;
	public static final int PA_TOP_Y = 50;
	public static final int PA_BOTTOM_Y = PA_TOP_Y + PLAY_AREA_HEIGHT;
	
	public static final int MINO_START_X = PA_LEFT_X + (PLAY_AREA_WIDTH/2) - BLOCK_SIZE;
	public static final int MINO_START_Y = PA_TOP_Y + BLOCK_SIZE;

	
}
