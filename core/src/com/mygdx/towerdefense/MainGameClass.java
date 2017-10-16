package com.mygdx.towerdefense;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

import map.MapGraph;
import map.Point;
import objects.api.GameObject;
import objects.util.Factory;

public class MainGameClass extends ApplicationAdapter {
	
	private ShapeRenderer renderer;
	private OrthographicCamera camera;
	
	private MapGraph map;
	private List<GameObject> listOfEverything;
	
	private long lastTankAdded;
	private long lastShooterAdded;
	private long lastMove;
	
	private int tankAddingInterval;
	private int shooterAddingInterval;
	private long movingInterval;
	
	private Point enemySpawnPoint;
	private Point enemyDestPoint;
	
	@Override
	public void create () {
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 640, 480);
		renderer = new ShapeRenderer();
		
		initializeTimeIntervals();
		setGameStartingState();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
	    
	    performInteractions();
	    createNewObjects();
	    Factory.appendCandidates(listOfEverything);
	    
	    map.draw(renderer);
	    listOfEverything.forEach(object -> object.showIt(renderer));
	}

	@Override
	public void dispose () {
		
		renderer.dispose();
	}
	
	private MapGraph createMap(MapGraph map, Point spawnPoint, Point destPoint) {
		
		Point[] points = {
				spawnPoint,
				new Point(120,100),
				new Point(200,100),
				new Point(150, 200),
				new Point(160, 350),
				new Point(300, 340),
				new Point(500, 260),
				destPoint
		};
		
		map.addVertex(points);
		
		map.addRoad(points[0], points[1]);
		map.addRoad(points[1], points[2]);
		map.addRoad(points[1], points[3]);
		map.addRoad(points[3], points[4]);
		map.addRoad(points[4], points[5]);
		map.addRoad(points[2], points[6]);
		map.addRoad(points[6], points[5]);
		map.addRoad(points[5], points[7]);
		
		return map;
	}
	
	Vector3 getMousePosInGameWorld() {
		 return camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
	}
	
	private void initializeTimeIntervals() {
		
		tankAddingInterval = 2000;
		shooterAddingInterval = 3500;
		movingInterval = 50;
		
		lastTankAdded = TimeUtils.millis();
		lastShooterAdded = TimeUtils.millis();
		lastMove = TimeUtils.millis();
	}
	
	private void setGameStartingState() {
		
		listOfEverything = new LinkedList<>();
		
		enemySpawnPoint = new Point(50, 50);
		enemyDestPoint = new Point(600, 430);
		
		map = new MapGraph();
		map = createMap(map, enemySpawnPoint, enemyDestPoint);
		
		Factory.createUnit("sampleTank", 0, enemySpawnPoint, enemyDestPoint);
		Factory.appendCandidates(listOfEverything);
	}
	
	private void createNewObjects() {
		
	    if(lastTankAdded < TimeUtils.millis()-tankAddingInterval) {
	    	
	    	lastTankAdded = TimeUtils.millis();
	    	Factory.createUnit("sampleTank", 0, enemySpawnPoint, enemyDestPoint);
	    }
	    
	    if(lastShooterAdded < TimeUtils.millis()-shooterAddingInterval) {
	    	
	    	lastShooterAdded = TimeUtils.millis();
	    	Factory.createUnit("sampleShooter", 0, enemySpawnPoint, enemyDestPoint);
	    }
	    
	    if(Gdx.input.justTouched()) {
	    	Vector3 pointerPosition = getMousePosInGameWorld();
	    	Point myPosition = new Point(pointerPosition.x, 480-(pointerPosition.y));
			Factory.createTower("sampleTower", 1, myPosition);
	    }
	}

	private void performInteractions() {
		
		if(lastMove < TimeUtils.millis()-movingInterval) {
	    	
	    	listOfEverything.forEach(object -> object.moveIt(null, map));
	    	lastMove = TimeUtils.millis();
	    }
	    
	    listOfEverything.forEach(object -> object.attackWithIt(listOfEverything, null));
	    listOfEverything.forEach(object -> object.damageWithIt(listOfEverything));
	    listOfEverything.removeIf(object -> object.isToKill() ||
	    		object.getX() < -100 ||
	    		object.getX() > 1000 ||
	    		object.getY() < -100 ||
	    		object.getY() > 1000);
	}
}
