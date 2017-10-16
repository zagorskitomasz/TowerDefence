package objects.api;

import java.util.List;

public interface Damager {

	public void hitIt(GameObject hitter, List<GameObject> listOfEverything);
}
