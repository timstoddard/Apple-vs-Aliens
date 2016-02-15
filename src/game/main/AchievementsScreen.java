package game.main;

import java.awt.*;
import javax.swing.*;

public class AchievementsScreen extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Reader r;
	private Achievement[] achievements;

	public AchievementsScreen() {
		init();
	}
	
	public void init() {
		r = new Reader();
		String[] rawData = r.getAchievements();
		achievements = new Achievement[rawData.length];
		for (int i = 0; i < rawData.length; i++) {
			String[] curr = rawData[i].split(",");
			achievements[i] = new Achievement(Boolean.parseBoolean(curr[0]),
					"images"+System.getProperty("file.separator")+curr[1], curr[2], curr[3]);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//int w = getWidth(), h = getHeight();
		setBackground(Color.BLACK);
		g.setColor(Color.GREEN);
		g.setFont(new Font("Helvetica", Font.BOLD, 120));
		g.drawString("Achievements", 80, 120);
		g.setColor(Color.white);
		g.setFont(new Font("Helvetica", Font.BOLD, 60));
		drawAchievements(g);

	}

	public void drawAchievements(Graphics g) {
		for (int i = 0; i < achievements.length / 2; i++) {
			achievements[i].draw(g, 40, 120 * (i + 1) + 100);
			achievements[achievements.length / 2 + i].draw(g, 470, 120 * (i + 1) + 100);
		}
	}

	public class Achievement extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Image i;
		private String name, text;
		private boolean earned;

		public Achievement(boolean earned, String image, String name,
				String text) {
			i = new ImageIcon(image).getImage();
			this.name = name;
			this.text = text;
			this.earned = earned;
		}

		public void draw(Graphics g, int x, int y) {
			if (earned) {
				g.setColor(Color.WHITE);
				g.drawImage(getI(), x, y - 40, 100, 100, this);
			} else {
				g.setColor(Color.GRAY);
				g.fillRect(x, y - 40, 100, 100);
			}
			g.setFont(new Font("Helvetica", Font.BOLD, 50));
			g.drawString(getName(), x + 120, y + 5);
			g.setFont(new Font("Helvetica", 0, 30));
			g.drawString(getText(), x + 120, y + 45);
		}

		public Image getI() {
			return i;
		}

		public String getName() {
			return name;
		}

		public String getText() {
			return text;
		}
	}
}