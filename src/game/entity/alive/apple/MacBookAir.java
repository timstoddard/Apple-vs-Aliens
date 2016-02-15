package game.entity.alive.apple;

import java.awt.*;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class MacBookAir extends Apple {
	
	public MacBookAir(Point loc) {
		super(loc);
		setCost((short) 999);
		setPower((short) 10);
		setHealth((short) 500);
		setName("MacaBook Air");
		setImageName("images"+System.getProperty("file.separator")+"MacBookAir.png");
		setLocation(loc);
	}

	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(new ImageIcon(getImageName()).getImage(), getX()+10, getY()+10, getX()+getWidth(), getY()+getHeight(), 0, 0, 910, 548, io);
	}
}