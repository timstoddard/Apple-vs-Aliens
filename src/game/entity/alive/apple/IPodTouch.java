package game.entity.alive.apple;

import java.awt.*;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class IPodTouch extends Apple {
	
	public IPodTouch(Point loc) {
		super(loc);
		setCost((short) 299);
		setPower((short) 10);
		setHealth((short) 150);
		setName("iPod Touch");
		setImageName("images"+System.getProperty("file.separator")+"IPodTouch.png");
		setLocation(loc);
	}

	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(new ImageIcon(getImageName()).getImage(), getX()+10, getY()+10, getX()+getWidth(), getY()+getHeight(), 0, 0, 600, 532, io);
	}
}