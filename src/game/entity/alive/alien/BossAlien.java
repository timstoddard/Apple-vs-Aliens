package game.entity.alive.alien;

import java.awt.*;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class BossAlien extends Alien {
	
	public BossAlien() {
		super(new Point(0, 0));
		setHealth((short) 25);
		setName("Boss Alien");
		setImageName("images"+System.getProperty("file.separator")+"BossAlien.png");
	}
	
	public BossAlien(Point loc) {
		super(loc);
		setHealth((byte) 10);
		setName("Boss Alien");
	}

	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(new ImageIcon(getImageName()).getImage(), getX()+10, getY()+10, getX()+140, getY()+140, 0, 0, 655, 723, io);
	}
}
