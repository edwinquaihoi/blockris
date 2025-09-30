package au.com.blockris.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import au.com.blockris.Constants;
import au.com.blockris.KeyHandler;
import au.com.blockris.commons.DirectionEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Mino {

	private static final int MARGIN = 2;
	
	protected Block blocks[] = new Block[4];
	protected Block tempBlocks[] = new Block[4];
	protected Color color;
	protected int dropCounter = 0;
	protected KeyHandler kh;
	protected boolean active = true;
	protected boolean bottomCollision = false;
	protected boolean deactivating = false;
	protected int deactivatingCounter = 0;
	
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
	
	/*
	private boolean hasCollidedWithLeft() {
		return Arrays.asList(blocks).stream().anyMatch(b -> b.x == Constants.PA_LEFT_X);
	}
	
	private boolean hasCollidedWithRight() {
		return Arrays.asList(blocks).stream().anyMatch(b -> (b.x + Constants.BLOCK_SIZE) == Constants.PA_RIGHT_X);
	}
	
	private boolean hasCollidedWithBottom() {
		return Arrays.asList(blocks).stream().anyMatch(b -> (b.y + Constants.BLOCK_SIZE) == Constants.PA_BOTTOM_Y);
	}
	*/

	public boolean hasCollided(Predicate<Block> predicate) {
		return Arrays.asList(blocks).stream().anyMatch(b -> predicate.test(b));
	}
	
	public boolean hasCollidedWithBlocks(List<Block> staticBlocks, BiPredicate<Block, Block> predicate) {
		
		// iterate over static blocks and see if this mino has collided with one
		return staticBlocks.stream().anyMatch(target -> {
			return Arrays.asList(blocks).stream().anyMatch(b -> {
				return predicate.test(target, b);
			});
		});
	}
	
	public void moveDown(List<Block> staticBlocks) {
		
		if(!hasCollided(b -> (b.y + Constants.BLOCK_SIZE) == Constants.PA_BOTTOM_Y) 
		&& !hasCollidedWithBlocks(staticBlocks, (target, source) ->  ((source.y + Constants.BLOCK_SIZE) == target.y) && (source.x == target.x))) {
			for(Block b : blocks) {
				b.y += Block.SIZE;
			}
			dropCounter = 0;	
		} else {
			bottomCollision = true;
			deactivating = true;
		}
		
		kh.setDirectionPressed(DirectionEnum.NONE);
	}
	
	public void moveLeft(List<Block> staticBlocks) {
		
		// check left collision
		if(!hasCollided(b -> b.x == Constants.PA_LEFT_X) && !hasCollidedWithBlocks(staticBlocks, (t,b) -> (b.x == t.x + Constants.BLOCK_SIZE) && b.y == t.y )) {
			for(Block b : blocks) {
				b.x -= Block.SIZE;
			}
		}
		
		kh.setDirectionPressed(DirectionEnum.NONE);
	}
	
	public void moveRight(List<Block> staticBlocks) {
		
		if(!hasCollided(b -> (b.x + Constants.BLOCK_SIZE) == Constants.PA_RIGHT_X) && !hasCollidedWithBlocks(staticBlocks, (t,b) -> (b.x + Constants.BLOCK_SIZE == t.x) && (b.y == t.y))) {
			for(Block b : blocks) {
				b.x += Block.SIZE;
			}
		}
		kh.setDirectionPressed(DirectionEnum.NONE);
	}
	
	public void moveUp(List<Block> staticBlocks) {
		if(!deactivating) {
			rotate(staticBlocks);
		}
		kh.setDirectionPressed(DirectionEnum.NONE);
	}
	
	private void copyCoordinates(Block[] source, Block[] target) {
		for(int i=0; i< source.length;i++) {
			target[i].x = source[i].x;
			target[i].y = source[i].y;
		}
	}	

	public void rotate(List<Block> staticBlocks) {
		
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
		
		// only update if no collision
		if(!hasRotationCollided(tempBlocks)) {
			copyCoordinates(tempBlocks, blocks);
		}
	}
	
	private boolean hasRotationCollided(Block[] source) {
		return Arrays.asList(source).stream().anyMatch(b -> {
			return (
				b.x < Constants.PA_LEFT_X ||
				b.x + Constants.BLOCK_SIZE > Constants.PA_RIGHT_X ||
				b.y + Constants.BLOCK_SIZE > Constants.PA_BOTTOM_Y
			);
		});
	}
	
	public void deactivating(List<Block> staticBlocks) {
		deactivatingCounter++;
		
		if(deactivatingCounter == 45) {
			deactivatingCounter = 0;
			
			if(hasCollided(b -> (b.y + Constants.BLOCK_SIZE) == Constants.PA_BOTTOM_Y) 
			|| hasCollidedWithBlocks(staticBlocks, (target, source) ->  ((source.y + Constants.BLOCK_SIZE) == target.y) && (source.x == target.x))) {
				active = false;
				bottomCollision = true;
			}

		}
	}

	public void update(List<Block> staticBlocks) {
		
		if(deactivating) {
			deactivating(staticBlocks);
		}
				
		if(active) {
			
			if(hasCollided(b -> (b.y + Constants.BLOCK_SIZE) == Constants.PA_BOTTOM_Y) 
		    || hasCollidedWithBlocks(staticBlocks, (target, source) ->  ((source.y + Constants.BLOCK_SIZE) == target.y) && (source.x == target.x))) {
				bottomCollision = true;
				deactivating = true;
			}
			
			switch(kh.getDirectionPressed()) {
				case DOWN -> moveDown(staticBlocks);
				case LEFT -> moveLeft(staticBlocks);
				case RIGHT -> moveRight(staticBlocks);
				case UP -> moveUp(staticBlocks);
				default -> kh.setDirectionPressed(DirectionEnum.NONE);
			}
		
			if(bottomCollision) {
				deactivating = true;
			} else {
				dropCounter++;
				if(dropCounter >= Constants.SHAPE_DROP_INTERVAL) {
					
					for(Block b : blocks) {
						b.y += Block.SIZE;
					}
										
					dropCounter = 0;
				}
			}
		}
	}
	
	public void paint(Graphics2D g) {
		
		//System.out.println("Mino:paint");
		
		g.setColor(color);
		
		int blockSize = Block.SIZE - (MARGIN*2);
		
		for(int i=0; i < blocks.length; i++) {
			g.fillRect(blocks[i].x+MARGIN, blocks[i].y+MARGIN, blockSize, blockSize);
		}
	}
}
