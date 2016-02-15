package game.entity.alive.alien;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class StrongAlien extends Alien {

	public StrongAlien() {
		super(new Point(0, 0));
		setHealth((short) 16);
		setName("Strong Alien");
		setImageName("images"+System.getProperty("file.separator")+"StrongAlien.png");
	}
	
	public StrongAlien(Point loc) {
		super(loc);
		setHealth((byte) 10);
		setName("Strong Alien");
	}

	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(new ImageIcon(getImageName()).getImage(), getX()+10, getY()+10, getX()+140, getY()+140, 0, 0, 614, 682, io);
	}
}
