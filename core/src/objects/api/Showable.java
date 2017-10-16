package objects.api;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import map.Point;
import objects.util.Direction;

public interface Showable {

	public void show(Point point, SpriteBatch batch, Direction direction);
	public void show(Point point, ShapeRenderer renderer);
}
