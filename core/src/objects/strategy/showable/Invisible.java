package objects.strategy.showable;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import map.Point;
import objects.api.Showable;
import objects.util.Direction;

// Skoro invisible to metoda show nic nie robi

public class Invisible implements Showable {

	@Override
	public void show(Point point, SpriteBatch batch, Direction direction) {}

	public void show(Point point, ShapeRenderer renderer) {
		
	}
}
