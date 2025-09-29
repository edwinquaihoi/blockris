package au.com.blockris.shapes;

import java.awt.Color;

import au.com.blockris.KeyHandler;

public class L1Mino extends Mino {
	
	public L1Mino(KeyHandler kh) {
		super(Color.RED, kh);
	}
	
	@Override
	public void setXY(int x, int y) {
		// o
		// o
		// o o
		
		blocks[0].x = x;
		blocks[0].y = y;
		
		Block ref = blocks[0];
		
		blocks[1].x = ref.x;
		blocks[1].y	= ref.y - Block.SIZE;	
		blocks[2].x = ref.x;
		blocks[2].y = ref.y + Block.SIZE;
		blocks[3].x = ref.x + Block.SIZE;
		blocks[3].y = ref.y + Block.SIZE;
	}

}
