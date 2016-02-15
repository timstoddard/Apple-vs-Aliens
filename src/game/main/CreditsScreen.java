package game.main;

import java.awt.*;

import javax.swing.JPanel;

public class CreditsScreen extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int x = 60, y = 808, spacer = 60;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		int w = getWidth(), h = getHeight();
		setBackground(Color.black);
		y--;
//		g.setColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
//		g.setColor(new Color(0, (int)(Math.random()*128)+128, 0));
		g.setColor(Color.GREEN);
		g.setFont(new Font("Helvetica", Font.BOLD, 100));
		g.drawString("Apple vs. Aliens", x, y);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Helvetica", Font.BOLD, 60));
		g.drawString("A Game by Stoddard Studios", x, y+spacer*2);
		g.drawString("Designed by:", x, y+spacer*4);
		g.drawString("Tim Stoddard", x, y+spacer*5);
		g.drawString("Developed by:", x, y+spacer*7);
		g.drawString("Tim Stoddard", x, y+spacer*8);
		g.drawString("Coded by:", x, y+spacer*10);
		g.drawString("Tim Stoddard", x, y+spacer*11);
		g.drawString("Consultants:", x, y+spacer*13);
		g.drawString("Ryan Driggett", x, y+spacer*14);
		g.drawString("Ilay Shamia", x, y+spacer*15);
		g.drawString("Special Thanks:", x, y+spacer*17);
		g.drawString("Mr. Shelby", x, y+spacer*18);
		g.drawString("Based on Plants vs. Zombies", x, y+spacer*21);
		g.drawString("by PopCap Games, a trademark", x, y+spacer*22);
		g.drawString("of Electronic Arts, Inc.", x, y+spacer*23);
		g.drawString("Stoddard Studios (c) 2013", x, y+spacer*25);
		if (y < -(spacer*25+150)) reset();
		try {Thread.sleep(8);} catch (InterruptedException e) {}
		repaint();
	}

	public void reset() {
		y = 800;
	}
}