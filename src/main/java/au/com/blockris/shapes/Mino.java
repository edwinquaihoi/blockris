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
	public void updateXY(DirectionEnum direction) {};


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
		kh.setDirectionPressed(DirectionEnum.NONE);
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
