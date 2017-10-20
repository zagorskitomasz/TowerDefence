package objects.strategy.attacking;

import java.util.List;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.TimeUtils;

import map.Edge;
import map.MapGraph;
import map.Point;
import objects.api.Attacking;
import objects.api.GameObject;
import objects.util.Collisions;
import objects.util.Direction;
import objects.util.Factory;

// W odpowiednich odstępach czasu wysyła nowe pociski w wyznaczonym kierunku

public class Shooter implements Attacking {

	private int damage;
	private int range;
	private int speed;
	private String missileType;
	private long prevTime;
	private MapGraph map;
	private Point location;
	private int faction;
	
	public Shooter(int damage, int range, int speed, String missileType, MapGraph map, Point location, int faction) {
		this.damage = damage;
		this.range = range;
		this.speed = speed;
		this.prevTime = TimeUtils.millis()-speed;
		this.missileType = missileType;
		this.map = map;
		this.location = location;
		this.faction = faction;
		affectMap(true);
	}
	
	@Override
	public void attack(GameObject attacker, List<GameObject> listOfEverything, Input input) {
		
		if(prevTime+speed < TimeUtils.millis()) {
			GameObject target = chooseTarget(attacker, listOfEverything);
			
			if(target!=null) {
				prevTime = TimeUtils.millis();
				Direction missileDir = setDirection(attacker, target);
				
				Factory.createMissile(missileType, attacker.getFaction(), damage,
						attacker.getPosition(), missileDir);
			}
		}	
	}

	private GameObject chooseTarget(GameObject attacker, List<GameObject> listOfEverything) {
		for(GameObject object : listOfEverything) {
			if(object.isVulnerable() && 
					attacker.getFaction()!=object.getFaction() && 
					Collisions.check(attacker, object, range))
				return object;
		}
		return null;
	}
	
	private Direction setDirection(GameObject attacker, GameObject target) {
		
		int xInterval = target.getX() - attacker.getX();
		int yInterval = target.getY() - attacker.getY();
		
		Direction result = new Direction(xInterval, yInterval);
		
		return result;
	}
	
	public void releaseMap() {
		
		affectMap(false);
	}
	
	public void affectMap(boolean mode) {
		
		if(this.faction==1) {
			List<Edge> allRoads = this.map.getAllRoads();
			
			for(Edge road : allRoads) {
				
				double distToRoad = calculateDistToRoad(road);
				System.out.println(distToRoad);
				if(distToRoad<this.range) {
					if(mode)
						road.lengthIt(this.damage, 1-distToRoad/this.range);
					else
						road.shortIt(this.damage, 1-distToRoad/this.range);
				}
			}
		}
	}
	
	public double calculateDistToRoad(Edge road) {
		
		Point p1 = road.getFrom();
		Point p2 = road.getTo();
		Point p = this.location;
		Point p3;
		
        double A = p2.getLat()-p1.getLat();
        double B = p2.getLon()-p1.getLon();
        
        double u = (A*(p.getLat()-p1.getLat())+B*(p.getLon()-p1.getLon()))/(Math.pow(A,2)+Math.pow(B,2));
           
        if (u<=0) {
        	p3 = p1;
        }
        else if(u>=1) {
            p3 = p2;
        }
        else {
        	p3 = new Point(p1.getLat() + u * A, p1.getLon() + u * B);
        }
        
        return Math.sqrt(Math.pow(p.getLat()-p3.getLat(),2)+Math.pow(p.getLon()-p3.getLon(),2));
	}
}
