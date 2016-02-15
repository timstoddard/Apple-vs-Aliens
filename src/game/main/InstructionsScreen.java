package game.main;

import java.awt.*;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

public class InstructionsScreen extends JPanel implements ImageObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);
		int /*w = getWidth(),*/ h = getHeight();
		g.setColor(new Color(0, 150, 0)); // green
		g.setFont(new Font("Helvetica", Font.BOLD, 80));
		g.drawString("Instructions", 100, h/8);
		g.setFont(new Font("Helvetica", Font.BOLD, 40));
		g.drawString("You are the manager of the greatest Apple", 100, 2*h/8);
		g.drawString("store ever, but some aliens from a galaxy", 100, 3*h/8);
		g.drawString("far, far away heard about it and want to", 100, h/2);
		g.drawString("steal all of your cool products!  Your job", 100, 5*h/8);
		g.drawString("is to use your inventory of Apple products", 100, 3*h/4);
		g.drawString("to fight them off.  Good Luck!", 100, 7*h/8);
		g.drawString("", 100, 8*h/8);
	}
}