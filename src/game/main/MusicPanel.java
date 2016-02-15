package game.main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class MusicPanel extends JPanel implements MouseMotionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int soundVolume, /*x, oldX,*/ y, oldY, width, height;
	private Reader r;
	
	public MusicPanel(int volume) {
		soundVolume = volume;
		//x = 0;
		//oldX = 0;
		y = 0;
		oldY = 0;
		r = new Reader();
		this.addMouseMotionListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = getWidth(), h = getHeight();
		width = w; height = h;
		setBackground(Color.BLACK);
		g.setColor(Color.WHITE);
		g.fillRect(0, 80, w, h);
		g.setColor(Color.GREEN);
		g.setFont(new Font("Helvetica", Font.BOLD, 52));
		g.drawString("    Music Volume", 10, 58);
		
		// sound bar
		g.setColor(Color.LIGHT_GRAY);
		g.fillPolygon(
				new int[]{w/10, 2*w/5, 3*w/5, 9*w/10},
				new int[]{h/4, 5*h/8, 5*h/8, h/4},
				4);
		g.setColor(Color.GREEN);
		g.fillPolygon(
				new int[]{w/10+(2*w/5-w/10)*(100-soundVolume)/100, 2*w/5, 3*w/5, 9*w/10-(9*w/10-3*w/5)*(100-soundVolume)/100},
				new int[]{h/4+(5*h/8-h/4)*(100-soundVolume)/100, 5*h/8, 5*h/8, h/4+(5*h/8-h/4)*(100-soundVolume)/100},
				4);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Helvetica", Font.BOLD, 40));
		g.fillRect(w/2-w/14, h/4+(5*h/8-h/4)*(100-soundVolume)/100-h/40, w/7, h/20);
		String whitespace = "";
		if (soundVolume >= 100) whitespace = "";
		else if (soundVolume >=10) whitespace = " ";
		else whitespace = "  ";
		g.setColor(Color.GREEN);
		g.drawString(whitespace+soundVolume, w/2-w/15, h/4+(5*h/8-h/4)*(100-soundVolume)/100+h/45);
		
		// speaker icon
		int width = 10;
		if (soundVolume >= 76) {
			drawFourth(g, w, h, width);
			drawThird(g, w, h, width);
			drawSecond(g, w, h, width);
			drawFirst(g, w, h, width);
			drawSpeaker(g, w, h);
		} else if (soundVolume >= 51) {
			drawThird(g, w, h, width);
			drawSecond(g, w, h, width);
			drawFirst(g, w, h, width);
			drawSpeaker(g, w, h);
		} else if (soundVolume >= 26) {
			drawSecond(g, w, h, width);
			drawFirst(g, w, h, width);
			drawSpeaker(g, w, h);
		} else if (soundVolume >= 1) {
			drawFirst(g, w, h, width);
			drawSpeaker(g, w, h);
		} else { // if soundVolume == 0
			drawSpeaker(g, w, h);
			drawX(g, w, h, width);
		}
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 2, h);
	}
	
	private void drawSpeaker(Graphics g, int w, int h) {
		g.setColor(Color.BLACK);
		g.fillRoundRect(w/4, 5*h/6-w/10, w/8, w/8, 10, 10);
		g.fillPolygon(
			new int[]{3*w/8-w/100, w/2-w/20,        w/2-w/20,            3*w/8-w/100},
			new int[]{5*h/6-w/10,  5*h/6-h/16-w/10, 5*h/6+w/8+h/16-w/10, 5*h/6+w/8-w/10},
			4);
	}
	
	private void drawFirst(Graphics g, int w, int h, int width) {
		g.setColor(Color.BLACK);
		g.fillArc(w/2-w/10, 5*h/6-w/10+w/16-144/6, 144/3, 144/3, -45, 90);
		g.setColor(Color.WHITE);
		g.fillArc(w/2-w/10, 5*h/6-w/10+w/16-(144/3-width)/2, 144/3-width, 144/3-width, -45, 90);
	}
	
	private void drawSecond(Graphics g, int w, int h, int width) {
		g.setColor(Color.BLACK);
		g.fillArc(w/2-w/10, 5*h/6-w/10+w/16-144/3, 2*144/3, 2*144/3, -45, 90);
		g.setColor(Color.WHITE);
		g.fillArc(w/2-w/10, 5*h/6-w/10+w/16-(2*144/3-width)/2, 2*144/3-width, 2*144/3-width, -45, 90);
	}
	
	private void drawThird(Graphics g, int w, int h, int width) {
		g.setColor(Color.BLACK);
		g.fillArc(w/2-w/10, 5*h/6-w/10+w/16-144/2, 144, 144, -45, 90);
		g.setColor(Color.WHITE);
		g.fillArc(w/2-w/10, 5*h/6-w/10+w/16-(144-width)/2, 144-width, 144-width, -45, 90);
	}
	
	private void drawFourth(Graphics g, int w, int h, int width) {
		g.setColor(Color.BLACK);
		g.fillArc(w/2-w/10, 5*h/6-w/10+w/16-2*144/3, 4*144/3, 4*144/3, -45, 90);
		g.setColor(Color.WHITE);
		g.fillArc(w/2-w/10, 5*h/6-w/10+w/16-(4*144/3-width)/2, 4*144/3-width, 4*144/3-width, -45, 90);
	}
	
	private void drawX(Graphics g, int w, int h, int width) {
		g.setColor(Color.RED);
		g.fillPolygon(
				new int[]{w/4-w/40, w/2-w/40, w/2-w/40, w/4-w/40},
				new int[]{4*h/6+h/15, 5*h/6+h/15, 5*h/6+h/15-width, 4*h/6+h/15-width},
				4);
		g.fillPolygon(
				new int[]{w/2-w/40, w/4-w/40, w/4-w/40, w/2-w/40},
				new int[]{4*h/6+h/15, 5*h/6+h/15, 5*h/6+h/15-width, 4*h/6+h/15-width},
				4);
	}
	
	public void mouseDragged(MouseEvent e) {
		if (width/2-width/14 < e.getX() && e.getX() < width/2+width/14
			&& height/4+(5*height/8-height/4)*(100-soundVolume)/100-height/40 < e.getY()
			&& e.getY() < height/4+(5*height/8-height/4)*(100-soundVolume)/100+height/40) {
			// oldX = x;
			oldY = y;
			// x = e.getX();
			y = e.getY();
			if (oldY > y) { if (soundVolume+1 <= 100) { soundVolume++; r.setOptions(new int[]{r.getOptions()[0], soundVolume}); } }
			if (oldY < y) { if (soundVolume-1 >= 0) { soundVolume--; r.setOptions(new int[]{r.getOptions()[0], soundVolume}); } }
			repaint();
		}
	}

	public void mouseMoved(MouseEvent e) {}
}