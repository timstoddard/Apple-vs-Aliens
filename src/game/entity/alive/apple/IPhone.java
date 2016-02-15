package game.entity.alive.apple;

import java.awt.*;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class IPhone extends Apple {
	
	public IPhone(Point loc) {
		super(loc);
		setCost((short) 299);
		setPower((short) 10);
		setHealth((short) 150);
		setName("iPhone");
		setImageName("images"+System.getProperty("file.separator")+"IPhone.png");
		setLocation(loc);
	}

	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(new ImageIcon(getImageName()).getImage(), getX()+10, getY()+10, getX()+getWidth(), getY()+getHeight(), 0, 0, 500, 380, io);
	}
}