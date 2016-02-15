package game.entity.alive.alien;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class BackwardsAlien extends Alien {

	public BackwardsAlien() {
		super(new Point(0, 0));
		setHealth((short) 10);
		setName("Backwards Alien");
		setImageName("images"+System.getProperty("file.separator")+"BackwardsAlien.png");
	}
	
	public BackwardsAlien(Point loc) {
		super(loc);
		setHealth((byte) 10);
		setName("Backwards Alien");
	}

	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(new ImageIcon(getImageName()).getImage(), getX()+10, getY()+10, getX()+140, getY()+140, 0, 0, 682, 715, io);
	}
}
