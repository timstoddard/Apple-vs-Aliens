package game.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

	private short health, width, height;
	private Point loc;
	private String name, imageName;
	private BufferedImage i;

	/**
	 * creates a new Entity with the given location
	 * @param loc
	 */
	public Entity(Point loc) {
		this.loc = loc;
	}

	public short getHealth() {
		return health;
	}

	public void setHealth(short health) {
		this.health = health;
	}
	
	public short getX() {
		return (short) loc.x;
	}
	
	public short getY() {
		return (short) loc.y;
	}
	
	public short getWidth() {
		return width;
	}
	
	public void setWidth(short width) {
		this.width = width;
	}
	
	public short getHeight() {
		return height;
	}
	
	public void setHeight(short height) {
		this.height = height;
	}
	
	public void setLocation(Point loc) {
		this.loc = loc;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public void setImageName(String name) {
		this.imageName = name;
	}

	public String toString() {
		return name + " with the image " + imageName + " at (" + loc.x + ", " + loc.y + ").";
	}
	
	public Image getImage() {
		return i;
	}
	
	public void setImage(BufferedImage i) {
		this.i = i;
	}
}