package au.com.blockris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import au.com.blockris.commons.DirectionEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeyHandler implements KeyListener {

	private DirectionEnum directionPressed = DirectionEnum.NONE;
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();

		directionPressed = switch(code) {
			case KeyEvent.VK_UP -> DirectionEnum.UP;
			case KeyEvent.VK_DOWN -> DirectionEnum.DOWN;
			case KeyEvent.VK_LEFT -> DirectionEnum.LEFT;
			case KeyEvent.VK_RIGHT -> DirectionEnum.RIGHT;
			default -> DirectionEnum.NONE;
		};
		
	}



}
