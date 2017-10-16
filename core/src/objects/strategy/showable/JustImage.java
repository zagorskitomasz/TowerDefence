package objects.strategy.showable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import map.Edge;
import map.Point;
import map.Vertex;
import objects.api.Showable;
import objects.util.Direction;

// Wyświetla obiekt, który jest reprezentowany przez zwyczajny obrazek.

public class JustImage implements Showable {

	private Sprite image;
	private Color color;
	private int size;
	
	public JustImage(Sprite image) {
		
		this.image = image;
	}
	
	public JustImage(Color color, int size) {
		
		this.color = color;
		this.size = size;
	}
	
	@Override
	public void show(Point point, SpriteBatch batch, Direction direction) {
		// TODO Wyświetlanie konturu obiektu

	}

	public void show(Point point, ShapeRenderer renderer) {
		
		renderer.setColor(color);
		renderer.begin(ShapeType.Filled);
		renderer.circle(point.getLat(), point.getLon(), size);
		
		renderer.end();
	}
}
