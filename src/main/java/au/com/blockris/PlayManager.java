package au.com.blockris;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

import au.com.blockris.shapes.Block;
import au.com.blockris.shapes.Mino;
import au.com.blockris.shapes.SMino;

public class PlayManager {

	final int WIDTH = 360;
	final int HEIGHT = 600;
	final int BORDER_WIDTH = 4;
	final int DOUBLE_BORDER_WIDTH = BORDER_WIDTH*2;
	final String NEXT_LBL = "NEXT";
	
	public static int leftX;
	public static int rightX;
	public static int topY;
	public static int bottomY;

	private Mino currMino;
	final int MINO_START_X;
	final int MINO_START_Y;
	
	private Stroke areaStroke = new BasicStroke(4f);
	private Font defaultFont = new Font("Arial",Font.PLAIN, 30);
	private KeyHandler kh;
	
	public PlayManager(KeyHandler kh) {
		this.kh = kh;
		leftX = (Constants.WIDTH/2) - (WIDTH/2);
		rightX = leftX + WIDTH;
		topY = 50;
		bottomY = topY + HEIGHT;
		
		MINO_START_X = leftX + (WIDTH/2) - Block.SIZE;
		MINO_START_Y = topY + Block.SIZE;
		
		currMino = new SMino(kh);
		currMino.setXY(MINO_START_X, MINO_START_Y);
	}
	
	public void update() {
		currMino.update();
	}
	
	public void paint(Graphics2D g2) {
		
		//System.out.println("PlayManager:paint");
		
		drawPlayArea(g2);
		drawNextShapeArea(g2);
		
		if(currMino != null) {
			currMino.paint(g2);
		}
	}
	
	private void drawNextShapeArea(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.setStroke(areaStroke);
		int x = rightX + 100;
		int y = bottomY - 196;
		g2.drawRect(x, y, 200, 200);
		
		g2.setFont(defaultFont);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		FontMetrics metrics = g2.getFontMetrics(defaultFont);
		
		int width = metrics.stringWidth(NEXT_LBL);
		int height = metrics.getHeight();
		
		g2.drawString(NEXT_LBL, (x+100)-(width/2), y+height);
		
	}

	private void drawPlayArea(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.setStroke(areaStroke);
		g2.drawRect(leftX - BORDER_WIDTH, topY - BORDER_WIDTH, WIDTH+DOUBLE_BORDER_WIDTH, HEIGHT+DOUBLE_BORDER_WIDTH);
	}
}
