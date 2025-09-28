package au.com.blockris;

import java.awt.Graphics2D;

import au.com.blockris.commons.DeltaGameLoopPanel;
import lombok.Getter;

public class GamePanel extends DeltaGameLoopPanel {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int FPS = 60;
	
	@Getter
	private PlayManager playManager = new PlayManager();
	
	public GamePanel() {
		super(WIDTH, HEIGHT, FPS);
	}
	
	@Override
	public void update() {
		getPlayManager().update();
	}

	@Override
	public void paint(Graphics2D g2) {
		getPlayManager().paint(g2);
	}
		
}
