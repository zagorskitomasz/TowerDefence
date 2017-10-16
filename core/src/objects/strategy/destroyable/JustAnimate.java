package objects.strategy.destroyable;

import com.badlogic.gdx.graphics.g2d.Animation;

import objects.api.Destroyable;
import objects.api.GameObject;

// Wyświetla animację i znika.

public class JustAnimate implements Destroyable {

	private Animation<?> animation;
	
	public JustAnimate(Animation<?> animation) {
		this.animation = animation;
	}
	
	@Override
	public boolean killYourself(GameObject toDestroy) {
		
		// TODO Pokaż animację

		return true;
	}

}
