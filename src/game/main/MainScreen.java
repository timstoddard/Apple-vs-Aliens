package game.main;

import java.awt.*;

import javax.swing.JPanel;

public class MainScreen extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean right = true, down = true;
	private Point loc = new Point(0, 60);
	private int speed = 2;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// int w = getWidth(), h = getHeight();
		setBackground(Color.white);
		// setBackground(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
		g.setColor(new Color(0, 150, 0)); // green
//		g.setFont(new Font("Helvetica", Font.BOLD, 80));
//		g.drawString("Apple vs. Aliens", loc(loc).x, loc(loc).y);
//		try {Thread.sleep(20);} catch (InterruptedException e) {}
//		repaint();
		g.setFont(new Font("Helvetica", Font.BOLD, 180));
		FontMetrics fm = g.getFontMetrics();
		g.drawString("Apple",  (1000-fm.stringWidth("Apple"))/2,  180);
		g.drawString("vs.",    (1000-fm.stringWidth("vs."))/2,    420);
		g.drawString("Aliens", (1000-fm.stringWidth("Aliens"))/2, 650);
	}
	
//	private Point loc(Point loc) {
//		if (loc.x >= 385) right = false;
//		if (loc.y >= 705) down = false;
//		if (loc.x <= 5) right = true;
//		if (loc.y <= 60) down = true;
//		if (right) loc.x+=speed;
//		else loc.x-=speed;
//		if (down) loc.y+=speed;
//		else loc.y-=speed;
//		return loc;
//	}
//	
//	public void reset() {
//		loc = new Point(0, 60);
//	}
}