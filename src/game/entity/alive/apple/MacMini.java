package game.entity.alive.apple;

import java.awt.*;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class MacMini extends Apple {
	
	public MacMini(Point loc) {
		super(loc);
		setCost((short) 599);
		setPower((short) 10);
		setHealth((short) 300);
		setName("Mac Mini");
		setImageName("images"+System.getProperty("file.separator")+"MacMini.png");
		setLocation(loc);
	}

	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(new ImageIcon(getImageName()).getImage(), getX()+10, getY()+10, getX()+getWidth(), getY()+getHeight(), 0, 0, 630, 634, io);
	}
}