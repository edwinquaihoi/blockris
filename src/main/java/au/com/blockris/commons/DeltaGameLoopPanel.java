package au.com.blockris.commons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import lombok.Getter;

@Getter
public abstract class DeltaGameLoopPanel extends JPanel implements Runnable {

	private int width;
	private int height;
	private int fps;
	
	private Thread gameThread;
	
	public DeltaGameLoopPanel(int pWidth, int pHeight, int pFps) {
		this.width = pWidth;
		this.height = pHeight;
		this.fps = pFps;
		
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.BLACK);
		setLayout(null);
	}
	
	public void launch() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		// Game Loop
		double drawInterval = 1_000_000_000 / fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();				
				delta--;
			}
		}	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paint((Graphics2D)g);
	}
	
	public abstract void update();
	
	public abstract void paint(Graphics2D g2);

}
