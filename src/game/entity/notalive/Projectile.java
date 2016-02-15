package game.entity.notalive;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import game.entity.Entity;

public class Projectile extends Entity {
	
	private short SPEED = 8;
	private boolean isUsed;

	public Projectile(Point loc) {
		super(loc);
		setImageName("images"+System.getProperty("file.separator")+"spinner.gif");
		setWidth((short) (140/3));
		setHeight((short) (140/3));
	}
	
	/**
	 * moves this Projectile "forward" 10 pixels
	 */
	public void move() {
		setLocation(new Point(getX() + SPEED, getY()));
	}
	
	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(new ImageIcon(getImageName()).getImage(), getX(), getY(), getX()+getWidth(), getY()+getHeight(), 0, 0, 60, 66, io);
	}
	
	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
}