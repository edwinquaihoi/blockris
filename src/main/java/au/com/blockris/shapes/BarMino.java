package au.com.blockris.shapes;

import java.awt.Color;

import au.com.blockris.KeyHandler;

public class BarMino extends Mino {
	
	public BarMino(KeyHandler kh) {
		super(Color.BLUE, kh);
	}
	
	@Override
	public void setXY(int x, int y) {
		// o o o o
		
		blocks[0].x = x;
		blocks[0].y = y;
		
		blocks[1].x = x - (Block.SIZE*2);
		blocks[1].y = y;

		blocks[2].x = x - Block.SIZE;
		blocks[2].y = y;

		blocks[3].x = x + Block.SIZE;
		blocks[3].y = y;

	}

}
