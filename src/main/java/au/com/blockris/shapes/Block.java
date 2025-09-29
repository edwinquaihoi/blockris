package au.com.blockris.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import au.com.blockris.commons.INode;
import lombok.Setter;

@Setter
public class Block extends Rectangle implements INode {

	public static final int SIZE = 30;
	
	public int x;
	public int y;
	public Color color;
	
	public Block(Color color) {
		this.color = color;
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(color);
		g.fillRect(x, y, SIZE, SIZE);
	}
	
	@Override
	public void update() {
	}
	
}
