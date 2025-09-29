package au.com.blockris.shapes;

import java.awt.Color;

import au.com.blockris.commons.DirectionEnum;

public class Z1Mino extends Mino {
	
	public Z1Mino() {
		super(Color.PINK);
	}
	
	@Override
	public void setXY(int x, int y) {
		// o o 
		//   o o
		
		blocks[0].x = x;
		blocks[0].y = y;
		
		Block ref = blocks[0];
		
		blocks[1].x = ref.x - Block.SIZE;
		blocks[1].y	= ref.y;	
		blocks[2].x = ref.x;
		blocks[2].y = ref.y + Block.SIZE;
		blocks[3].x = ref.x + Block.SIZE;
		blocks[3].y = ref.y + Block.SIZE;
	}

	@Override
	public void updateXY(DirectionEnum direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
