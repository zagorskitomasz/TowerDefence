package objects.strategy.destroyable;

import objects.api.Destroyable;
import objects.api.GameObject;

// Znika z mapy po cichu (np mały pocisk uderzający w cel)

public class JustDisappear implements Destroyable {

	@Override
	public boolean killYourself(GameObject toDestroy) {
		
		return true;
	}
}
