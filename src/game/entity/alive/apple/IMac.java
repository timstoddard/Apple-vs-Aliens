package game.entity.alive.apple;

import java.awt.*;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class IMac extends Apple {
	
	public IMac(Point loc) {
		super(loc);
		setCost((short) 1299);
		setPower((short) 10);
		setHealth((short) 650);
		setName("iMac");
		setImageName("images"+System.getProperty("file.separator")+"IMac.png");
		setLocation(loc);
	}

	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(new ImageIcon(getImageName()).getImage(), getX()+10, getY()+10, getX()+getWidth(), getY()+getHeight(), 0, 0, 557, 887, io);
	}
}