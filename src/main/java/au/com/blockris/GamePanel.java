package au.com.blockris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	final int FPS = 60;
	
	Thread gameThread;
	PlayManager pm;
	
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.BLACK);
		setLayout(null);
		pm = new PlayManager();
	}
	
	public void launch() {
		System.out.println("GamePanel::launch");
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		// Game Loop
		double drawInterval = 1_000_000_000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		
		while(gameThread != null) {
			
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta >= 1) {
				System.out.println("GamePanel::run  delta");

				update();
				repaint();
				
				delta--;
			}
		}
		
	}
	
	private void update() {
		pm.update();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		pm.paint(g2d);
	}

}
