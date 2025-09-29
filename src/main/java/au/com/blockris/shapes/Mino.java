package au.com.blockris.shapes;

import java.awt.Color;
import java.awt.Graphics2D;

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
	
	public abstract void setXY(int x, int y);
	public abstract void updateXY(DirectionEnum direction);
	public abstract void update();
	

	public Mino(Color color) {
		this.color = color;
		
		// initialise array of blocks
		for(int i=0;i < 4;i++ ) {
			blocks[i] = new Block(color);
			tempBlocks[i] = new Block(color);
		}
	}
	
	@Override
	public void paint(Graphics2D g) {
		g.setColor(color);
		
		int blockSize = Block.SIZE - (MARGIN*2);
		
		for(int i=0; i < blocks.length; i++) {
			g.fillRect(blocks[i].x+MARGIN, blocks[i].y+MARGIN, blockSize, blockSize);
		}
	}
}
