package objects.util;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;

import map.Point;
import objects.api.GameObject;
import objects.strategy.attackable.Immortal;
import objects.strategy.attackable.Vulnerable;
import objects.strategy.attacking.Passive;
import objects.strategy.attacking.Shooter;
import objects.strategy.damager.Peacefull;
import objects.strategy.damager.SingleTarget;
import objects.strategy.destroyable.JustDisappear;
import objects.strategy.movable.FlyerDirected;
import objects.strategy.movable.Standing;
import objects.strategy.movable.WalkerAI;
import objects.strategy.penetrability.Blockade;
import objects.strategy.penetrability.WalkingSpot;
import objects.strategy.showable.JustImage;

public class Factory {

	private static List<GameObject> listOfCandidates = new LinkedList<>();
	
	public static void appendCandidates(List<GameObject> listOfEverything) {
		
		listOfEverything.addAll(listOfCandidates);
		listOfCandidates = new LinkedList<>();
	}
	
	public static void createExplosion(String explosionType, int damage, int startingX, int startingY) {
	
		//GameObject newExplosion;
	
		//TODO Tworzenie eksplozji konkretnego rodzaju
	
		//Factory.listOfCandidates.add(newExplosion);\
	}
	
	public static void createMissile(String missileType, int faction, int damage,
			Point spawnPoint, Direction direction) {
			
		GameObject newMissile = new GameObject(spawnPoint.getLat(), spawnPoint.getLon(), faction, 3, 
				new JustImage(Color.RED, 2), 
				new FlyerDirected(5, direction), 
				new Immortal(), 
				new SingleTarget(damage), 
				new Passive(), 
				new JustDisappear(), 
				new WalkingSpot());
			
		Factory.listOfCandidates.add(newMissile);
	}
	
	public static void createUnit(String unitType, int faction, Point spawnPoint, Point destPoint) {
		
		if(unitType.equals("sampleTank")) {
			
			GameObject newUnit = new GameObject(spawnPoint.getLat(), spawnPoint.getLon(), faction, 7, 
					new JustImage(Color.ORANGE, 5), 
					new WalkerAI(1, destPoint), 
					new Vulnerable(20), 
					new Peacefull(), 
					new Passive(), 
					new JustDisappear(), 
					new Blockade());
			
			Factory.listOfCandidates.add(newUnit);
		}

		else if(unitType.equals("sampleShooter")) {
			
			GameObject newUnit = new GameObject(spawnPoint.getLat(), spawnPoint.getLon(), faction, 6, 
					new JustImage(Color.PURPLE, 4), 
					new WalkerAI(2, destPoint), 
					new Vulnerable(12), 
					new Peacefull(), 
					new Shooter(5, 80, 600, "sampleMissile"), 
					new JustDisappear(), 
					new Blockade());
			
			Factory.listOfCandidates.add(newUnit);
		}
	}
	
	public static void createTower(String towerType, int faction, Point spawnPoint) {
	
		if(towerType.equals("sampleTower")) {
			
			GameObject newTower = new GameObject(spawnPoint.getLat(), spawnPoint.getLon(), faction, 15, 
					new JustImage(Color.GREEN, 12), 
					new Standing(), 
					new Vulnerable(60), 
					new Peacefull(), 
					new Shooter(8, 150, 600, "sampleMissile"), 
					new JustDisappear(), 
					new Blockade());
			
			Factory.listOfCandidates.add(newTower);
		}
	}
}
