package au.com.blockris.shapes;

import java.awt.Color;

import au.com.blockris.commons.DirectionEnum;

public class BarMino extends Mino {
	
	public BarMino() {
		super(Color.BLUE);
	}
	
	@Override
	public void setXY(int x, int y) {
		// o o o o
		
		for(int i = 0;i < blocks.length; i++) {
			blocks[i].x = x + (i*Block.SIZE);
			blocks[i].y = y;
		}
	}

}
