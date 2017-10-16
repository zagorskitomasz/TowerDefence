package objects.util;

import map.Point;
import objects.api.GameObject;

public class Collisions {

	public static boolean check(GameObject one, GameObject two) {
		
		return (Math.sqrt(Math.pow(Math.abs(one.getX()-two.getX()), 2) 
				+ Math.pow(Math.abs(one.getY()-two.getY()), 2))
				< one.getSize()+two.getSize());
	}
	
	public static boolean check(GameObject one, GameObject two, int range) {
		
		return (Math.sqrt(Math.pow(Math.abs(one.getX()-two.getX()), 2) 
				+ Math.pow(Math.abs(one.getY()-two.getY()), 2))
				< range+two.getSize());
	}
	
	public static boolean check(GameObject moved, Point destination) {
		
		return (Math.sqrt(Math.pow(Math.abs(moved.getX()-destination.getLat()), 2) 
				+ Math.pow(Math.abs(moved.getY()-destination.getLon()), 2))
				< moved.getSize());
	}
}
