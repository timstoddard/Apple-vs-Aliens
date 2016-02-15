package game.entity.alive.apple;

import java.awt.*;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class MacPro extends Apple {
	
	public MacPro(Point loc) {
		super(loc);
		setCost((short) 2499);
		setPower((short) 10);
		setHealth((short) 1250);
		setName("Mac Pro");
		setImageName("images"+System.getProperty("file.separator")+"MacPro.png");
		setLocation(loc);
	}

	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(new ImageIcon(getImageName()).getImage(), getX()+10, getY()+10, getX()+getWidth(), getY()+getHeight(), 0, 0, 688, 688, io);
	}
}