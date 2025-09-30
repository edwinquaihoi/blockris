package au.com.blockris;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import au.com.blockris.shapes.BarMino;
import au.com.blockris.shapes.Block;
import au.com.blockris.shapes.JMino;
import au.com.blockris.shapes.LMino;
import au.com.blockris.shapes.Mino;
import au.com.blockris.shapes.SMino;
import au.com.blockris.shapes.SquareMino;
import au.com.blockris.shapes.TMino;
import au.com.blockris.shapes.ZMino;

public class PlayManager {

	final int BORDER_WIDTH = 4;
	final int DOUBLE_BORDER_WIDTH = BORDER_WIDTH*2;
	final String NEXT_LBL = "NEXT";

	private Mino currMino;
	private Mino nextMino;

	private Stroke areaStroke = new BasicStroke(4f);
	private Font defaultFont = new Font("Arial",Font.PLAIN, 30);
	private KeyHandler kh;
	private Random rand = new Random();
	
	private List<Block> staticBlocks = new ArrayList<Block>();
	
	public PlayManager(KeyHandler kh) {
		this.kh = kh;
				
		currMino = randomMino();
		currMino.setXY(Constants.MINO_START_X, Constants.MINO_START_Y);
		
		nextMino = randomMino();
		nextMino.setXY(Constants.NEXT_MINO_START_X, Constants.NEXT_MINO_START_Y);
	}
	
	Mino randomMino() {
		
		var index = rand.nextInt(7);
		
		var mino = switch(index) {
			case 0 -> new LMino(kh);
			case 1 -> new JMino(kh);
			case 2 -> new BarMino(kh);
			case 3 -> new ZMino(kh);
			case 4 -> new SMino(kh);
			case 5 -> new SquareMino(kh);
			case 6 -> new TMino(kh);
			default -> null; // this should not happen
		};
		
		return mino;
	}
	
	public void update() {
		
		if(currMino.isActive()) {
			currMino.update(staticBlocks);
		} else {
			Block[] blocks = currMino.getBlocks();
			for(int i=0; i < blocks.length; i++) {
				staticBlocks.add(blocks[i]);
			}
			
			currMino.setDeactivating(false);
			
			currMino = nextMino;
			currMino.setXY(Constants.MINO_START_X, Constants.MINO_START_Y);
			nextMino = randomMino();
			nextMino.setXY(Constants.NEXT_MINO_START_X, Constants.NEXT_MINO_START_Y);
		}
	}
	
	public void paint(Graphics2D g2) {
		
		//System.out.println("PlayManager:paint");
		
		drawPlayArea(g2);
		drawNextShapeArea(g2);
		
		if(currMino != null) {
			currMino.paint(g2);
		}
		
		if(nextMino != null) {
			nextMino.paint(g2);
		}
		
		for(Block b : staticBlocks) {
			b.paint(g2);
		}
	}
	
	private void drawNextShapeArea(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.setStroke(areaStroke);
		int x = Constants.PA_RIGHT_X + 100;
		int y = Constants.PA_BOTTOM_Y - 196;
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
		g2.drawRect(Constants.PA_LEFT_X - BORDER_WIDTH, Constants.PA_TOP_Y - BORDER_WIDTH, Constants.PLAY_AREA_WIDTH+DOUBLE_BORDER_WIDTH, Constants.PLAY_AREA_HEIGHT+DOUBLE_BORDER_WIDTH);
	}
}
