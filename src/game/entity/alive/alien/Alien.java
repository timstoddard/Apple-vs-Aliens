package game.entity.alive.alien;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.ImageObserver;

import game.entity.Entity;
import game.entity.alive.apple.Apple;
import game.entity.notalive.Projectile;

public abstract class Alien extends Entity {
	
	public Alien(Point loc) {
		super(loc);
		setWidth((short) 140);
		setHeight((short) 140);
	}
	
	/**
	 * moves this Alien "forward" 2 pixels
	 */
	public void move() {
		setLocation(new Point(getX() - 2, getY()));
	}
	
	public abstract void draw(Graphics g, ImageObserver io);
	
	public short getX() {
		return super.getX();
	}
	
	public short getY() {
		return super.getY();
	}
	
	public void setLocation(Point loc) {
		super.setLocation(loc);
	}
	
	public short getHealth() {
		return super.getHealth();
	}

	public void setHealth(short health) {
		super.setHealth(health);
	}
	
	/**
	 * Detects if this Alien and the Apple are overlapping
	 * @param a
	 * @return true if this Alien and the Apple intersect, false otherwise
	 */
	public boolean intersects(Apple a) {
		return a.getX() + a.getWidth() > getX()+60 && getX()+60 + getWidth() > a.getX();
	}
	
	/**
	 * Detects if this Alien and the Projectile are overlapping
	 * @param p
	 * @return true if this Alien and the Projectile intersect, false otherwise
	 */
	public boolean intersects(Projectile p) {
		return p.getX() + p.getWidth() > getX()+50 && getX()+50 + getWidth() > p.getX();
	}
}