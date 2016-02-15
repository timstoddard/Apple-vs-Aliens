package game.entity.alive.apple;

import java.awt.*;
import java.awt.image.ImageObserver;

import game.entity.Entity;

public abstract class Apple extends Entity {
	
	private short cost, power, wait;
	
	public Apple(Point loc) {
		super(loc);
		setWidth((short) 140);
		setHeight((short) 140);
		wait = (short) (Math.random()*20+40);
	}
	
	public abstract void draw(Graphics g, ImageObserver io);
	
	public short getCost() {
		return cost;
	}

	public void setCost(short cost) {
		this.cost = cost;
	}

	public short getPower() {
		return power;
	}

	public void setPower(short power) {
		this.power = power;
	}

	public short getHealth() {
		return super.getHealth();
	}

	public void setHealth(short health) {
		super.setHealth(health);
	}

	public String getImageName() {
		return super.getImageName();
	}

	public void setImageName(String name) {
		super.setImageName(name);
	}
	
	public short getX() {
		return super.getX();
	}
	
	public short getY() {
		return super.getY();
	}
	
	public void setLocation(Point loc) {
		super.setLocation(loc);
	}
	
	/**
	 * 
	 * @return the number which controls how often this Apple can shoot
	 */
	public short shootWaitTime() {
		return wait;
	}
}