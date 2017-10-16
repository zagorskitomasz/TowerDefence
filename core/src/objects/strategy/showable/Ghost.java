package objects.strategy.showable;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import map.Point;
import objects.api.Showable;
import objects.util.Direction;

// Sam kontur obiektu. 

public class Ghost implements Showable {

	private Sprite image;
	
	public Ghost(Sprite image) {
		
		this.image = image;
	}
	
	@Override
	public void show(Point point, SpriteBatch batch, Direction direction) {
		// TODO Wyświetlanie konturu obiektu

	}

	public void show(Point point, ShapeRenderer renderer) {
		
	}
}
