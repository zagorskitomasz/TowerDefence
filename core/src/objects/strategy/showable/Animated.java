package objects.strategy.showable;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import map.Point;
import objects.api.Showable;
import objects.util.Direction;

// Tę klasę użyjemy w obiektach animowanych

public class Animated implements Showable {

	private Animation<?> animation;
	
	public Animated(Animation<?> animation) {
		
		this.animation = animation;
	}
	
	@Override
	public void show(Point point, SpriteBatch batch, Direction direction) {
		// TODO Wyświetlanie animacji

	}

	public void show(Point point, ShapeRenderer renderer) {
		
	}
}
