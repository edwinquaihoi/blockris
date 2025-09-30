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
		int margin = 2;
		g.setColor(color);
		g.fillRect(x + 2, y + 2, SIZE-(margin*2), SIZE-(margin*2));
	}
	
	@Override
	public void update() {
	}
	
}
