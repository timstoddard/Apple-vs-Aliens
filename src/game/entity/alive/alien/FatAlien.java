package game.entity.alive.alien;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class FatAlien extends Alien {

	public FatAlien() {
		super(new Point(0, 0));
		setHealth((short) 15);
		setName("Fat Alien");
		setImageName("images"+System.getProperty("file.separator")+"FatAlien.png");
	}
	
	public FatAlien(Point loc) {
		super(loc);
		setHealth((byte) 10);
		setName("Fat Alien");
	}

	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(new ImageIcon(getImageName()).getImage(), getX()+10, getY()+10, getX()+140, getY()+140, 0, 0, 644, 626, io);
	}
}
