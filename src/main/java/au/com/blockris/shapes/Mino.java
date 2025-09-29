package au.com.blockris.shapes;

import java.awt.Color;
import java.awt.Graphics2D;

import au.com.blockris.Constants;
import au.com.blockris.KeyHandler;
import au.com.blockris.commons.DirectionEnum;
import au.com.blockris.commons.INode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Mino implements INode {

	private static final int MARGIN = 2;
	
	protected Block blocks[] = new Block[4];
	protected Block tempBlocks[] = new Block[4];
	protected Color color;
	protected int dropCounter = 0;
	protected KeyHandler kh;
	
	public abstract void setXY(int x, int y);

	public Mino(Color color, KeyHandler keyHandler) {
		this.kh = keyHandler;
		this.color = color;
		
		// initialise array of blocks
		for(int i=0;i < 4;i++ ) {
			blocks[i] = new Block(color);
			tempBlocks[i] = new Block(color);
		}
	}
	
	public void moveDown() {
		for(Block b : blocks) {
			b.y += Block.SIZE;
		}
		dropCounter = 0;
		
		kh.setDirectionPressed(DirectionEnum.NONE);
	}
	
	public void moveLeft() {
		for(Block b : blocks) {
			b.x -= Block.SIZE;
		}
		kh.setDirectionPressed(DirectionEnum.NONE);
	}
	
	public void moveRight() {
		for(Block b : blocks) {
			b.x += Block.SIZE;
		}
		kh.setDirectionPressed(DirectionEnum.NONE);
	}
	
	public void moveUp() {
		rotate();
		kh.setDirectionPressed(DirectionEnum.NONE);
	}
	
	private void copyCoordinates(Block[] source, Block[] target) {
		for(int i=0; i< source.length;i++) {
			target[i].x = source[i].x;
			target[i].y = source[i].y;
		}
	}
	
	public void updateCoordinates() {
		copyCoordinates(tempBlocks, blocks);
	};
	

	public void rotate() {
		
		// store current position into temp
		copyCoordinates(blocks, tempBlocks);
		
		// index 0 is always the center point we will use for rotation
		var px = tempBlocks[0].x;
		var py = tempBlocks[0].y;
		
		for(int i=1;i < tempBlocks.length;i++) {
			/*
			 * Clockwise rotation: The new coordinates (x2, y2) can be found using the formula: 
			 * x2 = px - (y1 - py) and y2 = py + (x1 - px)
			 */
			var x2 = px - (tempBlocks[i].y - py);
			var y2 = py + (tempBlocks[i].x - px);
			
			tempBlocks[i].x = x2;
			tempBlocks[i].y = y2;
		}
		
		updateCoordinates();
	}

	@Override
	public void update() {
		dropCounter++;
		
		switch(kh.getDirectionPressed()) {
			case DOWN -> moveDown();
			case LEFT -> moveLeft();
			case RIGHT -> moveRight();
			case UP -> moveUp();
			default -> kh.setDirectionPressed(DirectionEnum.NONE);
		}
		
		if(dropCounter >= Constants.SHAPE_DROP_INTERVAL) {
			
			for(Block b : blocks) {
				b.y += Block.SIZE;
			}
			
			dropCounter = 0;
		}
	}
	
	@Override
	public void paint(Graphics2D g) {
		
		//System.out.println("Mino:paint");
		
		g.setColor(color);
		
		int blockSize = Block.SIZE - (MARGIN*2);
		
		for(int i=0; i < blocks.length; i++) {
			g.fillRect(blocks[i].x+MARGIN, blocks[i].y+MARGIN, blockSize, blockSize);
		}
	}
}
