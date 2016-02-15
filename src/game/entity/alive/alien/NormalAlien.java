package game.entity.alive.alien;

import java.awt.*;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class NormalAlien extends Alien {
	
	public NormalAlien() {
		super(new Point(0, 0));
		setHealth((short) 10);
		setName("Normal Alien");
		setImageName("images"+System.getProperty("file.separator")+"NormalAlien.png");
	}
	
	public NormalAlien(Point loc) {
		super(loc);
		setHealth((short) 10);
		setName("Normal Alien");
	}

	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(new ImageIcon(getImageName()).getImage(), getX()+10, getY()+10, getX()+140, getY()+140, 0, 0, 597, 639, io);
	}
}
