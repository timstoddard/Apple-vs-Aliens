package game.entity.alive.apple;

import java.awt.*;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class IPodClassic extends Apple {
	
	public IPodClassic(Point loc) {
		super(loc);
		setCost((short) 429);
		setPower((short) 10);
		setHealth((short) 215);
		setName("iPad Mini");
		setImageName("images"+System.getProperty("file.separator")+"IPodClassic.png");
		setLocation(loc);
	}

	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(new ImageIcon(getImageName()).getImage(), getX()+10, getY()+10, getX()+getWidth(), getY()+getHeight(), 0, 0, 312, 300, io);
	}
}