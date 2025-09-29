package au.com.blockris;

import java.awt.Graphics2D;

import au.com.blockris.commons.DeltaGameLoopPanel;
import lombok.Getter;

public class GamePanel extends DeltaGameLoopPanel {
	
	@Getter
	private PlayManager playManager;
	
	public GamePanel() {
		super(Constants.WIDTH, Constants.HEIGHT, Constants.FPS);
		
		var kh = new KeyHandler();
		addKeyListener(kh);
		setFocusable(true);
		
		playManager = new PlayManager(kh);
	}
	
	@Override
	public void update() {
		getPlayManager().update();
	}

	@Override
	public void paint(Graphics2D g2) {
		
		//System.out.println("GamePanel:paint");
		
		getPlayManager().paint(g2);
	}
		
}
