package game.entity.alive.apple;

import java.awt.*;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class MacBookPro extends Apple {
	
	public MacBookPro(Point loc) {
		super(loc);
		setCost((short) 1199);
		setPower((short) 10);
		setHealth((short) 600);
		setName("MacBook Pro");
		setImageName("images"+System.getProperty("file.separator")+"MacBookPro.png");
		setLocation(loc);
	}

	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(new ImageIcon(getImageName()).getImage(), getX()+10, getY()+10, getX()+getWidth(), getY()+getHeight(), 0, 0, 800, 800, io);
	}
}