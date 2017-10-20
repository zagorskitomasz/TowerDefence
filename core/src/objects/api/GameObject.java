package objects.api;

import java.util.List;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import map.MapGraph;
import map.Point;
import objects.strategy.attackable.Vulnerable;
import objects.util.Direction;

public class GameObject {

	private int x;
	private int y;
	private int faction;
	private Direction direction;
	private int size;
	private boolean toKill = false;
	
	//Zmienne zawierające konkretne implementacje zachowań obiektu.
	
	// Do wyboru: statyczny obrazek, animacja, brak wyświetlania w ogóle (może się na coś przydać)
	private Showable shower;
	
	// Do wyboru: nieruchomy, chodzący po drogach, latający nisko/wysoko itp, podążający za kursorem.
	private Movable mover;
	
	// Do wyboru: może dostawać dmg albo nie. Jak dmg mu zjedzie hp do 0 to ginie.
	private Attackable dmgTaker;
	
	// Obiekt sam w sobie "przechowuje" jakiś dmg, np. pocisk niesie dmg który zada celowi przy kolizji
	// budynek też może mieć ukryty dmg, który zadaje w formie eksplozji gdy jest niszczony.
	// NIE JEST to dmg ataków generowanych przez obiekt. Do wyboru: dmg pojedynczy, obszarowy, rozłożony w czasie itp.
	private Damager dmgKeeper;
	
	// A tu jest właśnie interfejs atakowania, przykład: wieża strzelnicza strzela w kierunku celu używając 
	// interfejsu Attacking w taki sposób, że tworzy nowy obiekt klasy Missile, któremu przekazuje dmg
	// Obiekt missile "przenosi" dmg w swoim interfejsie Damager
	private Attacking attacker;
	
	// Ten obiekt decyduje w jaki sposób obiekt zostanie zniszczony
	private Destroyable destroyer;
	
	// Czy coś innego może przejść po polu zajmowanym przez obiekt
	private Penetrability penetrable;
	
	public GameObject(int x, int y, int faction, int size,
			Showable showable, Movable movable, Attackable attackable, 
			Damager damager, Attacking attacking, Destroyable destroyable, Penetrability penetrability) {
		
		this.x = x;
		this.y = y;
		this.faction = faction;
		this.size = size;
		this.direction = new Direction(0, 0);
		
		this.shower = showable;
		this.mover = movable;
		this.dmgTaker = attackable;
		this.dmgKeeper = damager;
		this.attacker = attacking;
		this.destroyer = destroyable;
		this.penetrable = penetrability;
	}
	
	public int getX() {
		
		return x;
	}
	
	public int getY() {
		
		return y;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setPosition(int x, int y) {
		
		this.x = x;
		this.y = y;
	}
	
	public int getFaction() {
		return faction;
	}
	
	public Point getPosition() {
		
		return new Point(this.x, this.y);
	}

	// Sprawdzanie czy może być trafiony - metoda użyteczna dla pocisków
	public boolean canItBeHit() {
		
		if(dmgTaker instanceof Vulnerable)
			return true;
		else
			return false;
	}
	
	// Tu są metody odpowiadające za wywoływanie zachowań, one będą odpalane z głównego interfejsu gry.
	public void showIt(SpriteBatch batch) {
		
		shower.show(new Point(x, y), batch, direction);
	}
	
	public void showIt(ShapeRenderer renderer) {
		
		shower.show(new Point(x, y), renderer);
	}
	
	public void moveIt(Input input, MapGraph map, List<GameObject> listOfEverything) {
		
		direction = mover.move(this, input, map, listOfEverything);
	}
	
	public void damageIt(List<GameObject> listOfEverything, int dmgValue) {
		
		dmgTaker.takeDmg(this, dmgValue);
	}
	
	//Tutaj będą sprawdzane wszystkie kolizje między obiektami i jeśli coś oberwie, 
	// to mu się wywoła metodę damageIt(obiekt który go trafił)
	public void damageWithIt(List<GameObject> listOfEverything) {
		
		dmgKeeper.hitIt(this, listOfEverything);
	}
	
	//AI wybiera cel z listy, gracz wybiera cel przy użyciu inputu (np jakieś specjalne ataki obszarowe odpalane z myszki)
	public void attackWithIt(List<GameObject> listOfEverything, Input input) {
		
		attacker.attack(this, listOfEverything, input);
	}
	
	// Samousuwanie się z listy wszystkiego + ewentualny efekt np. eksplozja
	// W ten sposób zrobimy wybuchające pociski - przy kolizji pocisk jest niszczony przy użyciu obiektu Explosive
	public void destroy() {
		
		attacker.releaseMap();
		penetrable.unBlockRoad();
		toKill = destroyer.killYourself(this);
	}
	
	public boolean canBeWalkedThrough() {
		return penetrable.canIWalkHere();
	}
	
	public boolean canBuildOn() {
		return penetrable.canIBuildHere();
	}
	
	public boolean isBarricade() {
		return penetrable.isBarricaded();
	}
	
	public void blockRoad(MapGraph map, Point one, Point two) {
		penetrable.blockRoad(map, one, two);
	}
	
	public boolean isToKill() {
		return toKill;
	}
	
	public boolean isVulnerable() {
		
		return this.dmgTaker instanceof Vulnerable;
	}
}
