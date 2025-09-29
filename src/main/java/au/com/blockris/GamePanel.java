package au.com.blockris;

import java.awt.Graphics2D;

import au.com.blockris.commons.DeltaGameLoopPanel;
import lombok.Getter;

public class GamePanel extends DeltaGameLoopPanel {
	
	@Getter
	private PlayManager playManager = new PlayManager();
	
	public GamePanel() {
		super(Constants.WIDTH, Constants.HEIGHT, Constants.FPS);
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
