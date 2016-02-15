package game.gamerunner;

import game.entity.alive.alien.Alien;
import game.entity.alive.apple.*;
import game.gamerunner.sound.SoundPlayer;
import game.main.Reader;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * 
 * @author TimStoddard
 * @version 1.0
 * 
 *
 */
public class GameRunner extends JPanel implements MouseListener, MouseMotionListener, Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private short mouseX = 0, mouseY = 0, CURRENT_LEVEL = 0, currentLane, currentSpace, clickedLane, clickedSpace, lastLane, currentAlien, currSelectedNum, clickedNum;
	private long money, counter;
	private Reader r;
	private boolean isPaused, onExitButton, onField, onTopBar, onPauseButton, shovel, onLeftHalf, drawCurrSelectedRect, wonGame, lostGame;
	private Alien[] rawAliens;
	private Field field;
	private Apple currSelected;
	
	
	public GameRunner() {
		super();
		initGame();
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		FontMetrics fm;
		super.paintComponent(g2);
		setBackground(Color.GRAY);
		int w = getWidth(), h = getHeight(); // 1500, 902-22 (=880)
		
		// makes everything scalable (and easy)
		g2.scale(w/1500., h/880.);
		
		// draws basic background
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 1500, 80);
		for (int j = 1; j < 5; j++)
			for (int l = 100; l <= 1500; l+=40) {
				int yVal = 800/5 * j + 80;
				g2.drawLine(l, yVal, l+20, yVal);
			}
		g2.fillRect(0, 80, 100, 880); // apple store
		g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"storetext.png").getImage(), 0, 80, 100, 435, 0, 0, 137, 608, this);
		g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"logo.png").getImage(), 5, 435, 95, 525, 0, 0, 2000, 2000, this);
		g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"storetext.png").getImage(), 0, 525, 100, 880, 0, 0, 137, 608, this);
		
		// updates field
		if (!isPaused) {
			if (onField) {
				g2.setColor(Color.WHITE);
				g2.fillRect(currentSpace*160+100, currentLane*160+80, 160, 160);
			}
			if (onTopBar) {
				g2.setColor(Color.BLACK);
				g2.fillRect(currSelectedNum*100+100, 0, 100, 80);
			}
		}
		
		// draws a red rectangle around the currently selected item, if there is one
		if (drawCurrSelectedRect) {
			g2.setColor(Color.RED);
			g2.drawRect(clickedNum*100+100, 0, 100, 79);
		}
		
		// draws the pause button
		g2.setFont(new Font("SansSerif", Font.BOLD, 16));
		fm = g2.getFontMetrics();
		if (!onPauseButton && !isPaused) {
			g2.setColor(Color.GRAY);
			g2.fillRoundRect(0, 0, 100, 80, 0, 0);
			g2.setColor(Color.BLACK);
			if (!isPaused)
				g2.drawString("Pause", (100-fm.stringWidth("Pause"))/2, (100-fm.getHeight())/2+8);
			else
				g2.drawString("Unpause", (100-fm.stringWidth("Unpause"))/2, (100-fm.getHeight())/2+8);
		} else {
			g2.setColor(Color.BLACK);
			g2.fillRoundRect(0, 0, 100, 80, 0, 0);
			g2.setColor(Color.GRAY);
			if (!isPaused)
				g2.drawString("Pause", (100-fm.stringWidth("Pause"))/2, (100-fm.getHeight())/2+8);
			else
				g2.drawString("Unpause", (100-fm.stringWidth("Unpause"))/2, (100-fm.getHeight())/2+8);
		}
		
		// draws the items on the top bar
		g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"IMac.png").getImage(),        100+20,  5, 100+80,  60, 0, 0, 557, 887, this);
		g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"IPad.png").getImage(),        200+20,  5, 200+80,  60, 0, 0, 204, 265, this);
		g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"IPhone.png").getImage(),      400+20,  5, 400+80,  60, 0, 0, 500, 380, this);
		g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"IPodClassic.png").getImage(), 300+20,  5, 300+80,  60, 0, 0, 312, 300, this);
		g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"IPodNano.png").getImage(),    500+20,  5, 500+80,  60, 0, 0, 375, 371, this);
		g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"IPodTouch.png").getImage(),   600+20,  5, 600+80,  60, 0, 0, 600, 532, this);
		g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"MacBookAir.png").getImage(),  700+20,  5, 700+80,  60, 0, 0, 910, 548, this);
		g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"MacBookPro.png").getImage(),  800+20,  5, 800+80,  60, 0, 0, 800, 800, this);
		g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"MacMini.png").getImage(),     900+20,  5, 900+80,  60, 0, 0, 630, 634, this);
		g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"MacPro.png").getImage(),      1000+20, 5, 1000+80, 60, 0, 0, 688, 688, this);
		g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"Shovel.png").getImage(),      1100+20, 5, 1100+80, 60, 0, 0, 917, 909, this);
		g2.setColor(Color.GRAY);
		g2.setFont(new Font("Arial", Font.BOLD, 12));
		fm = g2.getFontMetrics();
		// names
		g2.drawString("iMac",         100+ (100-fm.stringWidth("iMac"))/2,         75);
		g2.drawString("iPad",         200+ (100-fm.stringWidth("iPad"))/2,         75);
		g2.drawString("iPod Classic", 300+ (100-fm.stringWidth("iPod Classic"))/2, 75);
		g2.drawString("iPhone",       400+ (100-fm.stringWidth("iPhone"))/2,       75);
		g2.drawString("iPod Nano",    500+ (100-fm.stringWidth("iPod Nano"))/2,    75);
		g2.drawString("iPod Touch",   600+ (100-fm.stringWidth("iPod Touch"))/2,   75);
		g2.drawString("MacBook Air",  700+ (100-fm.stringWidth("MacBook Air"))/2,  75);
		g2.drawString("MacBook Pro",  800+ (100-fm.stringWidth("MacBook Pro"))/2,  75);
		g2.drawString("Mac Mini",     900+ (100-fm.stringWidth("Mac Mini"))/2,     75);
		g2.drawString("Mac Pro",      1000+(100-fm.stringWidth("Mac Pro"))/2,      75);
		g2.drawString("Shovel",       1100+(100-fm.stringWidth("Shovel"))/2,       75);
		// background for the prices
		g2.setColor(Color.BLACK);
		for (int i = 1 ; i <= 10; i++)
			g2.fillRect(i*100+30, 28, 40, 16);
		// prices
		g2.setColor(Color.WHITE);
		g2.drawString("$1299", 100+ (100-fm.stringWidth("$1299"))/2, 40);
		g2.drawString("$599",  200+ (100-fm.stringWidth("$599"))/2,  40);
		g2.drawString("$429",  300+ (100-fm.stringWidth("$429"))/2,  40);
		g2.drawString("$299",  400+ (100-fm.stringWidth("$299"))/2,  40);
		g2.drawString("$149",  500+ (100-fm.stringWidth("$149"))/2,  40);
		g2.drawString("$299",  600+ (100-fm.stringWidth("$299"))/2,  40);
		g2.drawString("$999",  700+ (100-fm.stringWidth("$999"))/2,  40);
		g2.drawString("$1199", 800+ (100-fm.stringWidth("$1199"))/2, 40);
		g2.drawString("$599",  900+ (100-fm.stringWidth("$599"))/2,  40);
		g2.drawString("$2499", 1000+(100-fm.stringWidth("$2499"))/2, 40);
		
		// draws cost on the top bar
		g2.setColor(Color.black);
		g2.setFont(new Font("Arial", Font.BOLD, 16));
		fm = g2.getFontMetrics();
		g2.drawString("Money: $"+money, 1200+(300-fm.stringWidth("Money: $"+money))/2, (40+fm.getHeight())/2);
		
		// draws progress on the top bar
		double percent = 100 * (double) (field.getAliensKilled()) / rawAliens.length;
		String strPercent = String.format("%10.2f", percent);
		if (percent < 100./3)
			g2.setColor(Color.RED);
		else if (percent < 200./3)
			g2.setColor(Color.YELLOW);
		else
			g2.setColor(Color.GREEN);
		g2.fillRect(1220, 45, (int) (260*percent/100), 30);
		g2.setColor(Color.BLACK);
		g2.drawRect(1220, 45, 260, 30);
		g2.drawString("Level " + CURRENT_LEVEL + " Progress: "+strPercent.trim()+"%", 1200+(300-fm.stringWidth("Level " + CURRENT_LEVEL + " Progress: "+strPercent.trim()+"%"))/2, 40+(40+fm.getHeight())/2);
		
		// draws all apples, aliens, and projectiles currently on the field
		field.draw(g2, this);
		
		// draws paused screen if game is paused
		if (isPaused) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g2.setColor(Color.DARK_GRAY);
			g2.fillRect(0, 80, 1500, 800);
			g2.fillRect(100, 0, 1400, 80);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Arial", Font.BOLD, 120));
			fm = g2.getFontMetrics();
			g2.drawString("The game is now paused", (1500-fm.stringWidth("The game is now paused"))/2, 200);
			g2.drawString("Press the Unpause button", (1500-fm.stringWidth("Press the Unpause button"))/2, 400);
			g2.drawString("to unpause", (1500-fm.stringWidth("to unpause"))/2, 600);
			if (!onExitButton) {
				g2.setColor(Color.RED);
				g2.fillRect(600, 700, 300, 120);
				g2.setColor(Color.WHITE);
				g2.drawString("Quit", (1500-fm.stringWidth("Quit"))/2, 800);
			} else {
				g2.setColor(Color.WHITE);
				g2.fillRect(1500/2-150, 700, 300, 120);
				g2.setColor(Color.RED);
				g2.drawString("Quit", (1500-fm.stringWidth("Quit"))/2, 800);
			}
		}
		
		g2.setFont(new Font("Arial", Font.BOLD, 120));
		fm = g2.getFontMetrics();
		
		// draws won screen if game was won
		if (wonGame) {
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, 1500, 880);
			g2.setColor(Color.BLACK);
			g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"smile.png").getImage(), 235, 10, 515, 370, 0, 0, 633, 842, this);
			g2.drawString("Good Job!", (750-fm.stringWidth("Good Job!"))/2, 640);
			g2.drawString("You Win!", (750-fm.stringWidth("You Win!"))/2, 760);
			g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"smile.png").getImage(), 985, 10, 1265, 370, 0, 0, 633, 842, this);
			g2.drawString("Good Job!", 750+(750-fm.stringWidth("Good Job!"))/2, 640);
			g2.drawString("You Win!", 750+(750-fm.stringWidth("You Win!"))/2, 760);
			drawRestartOrQuitScreen(g2, fm);
		}
		
		// draw lost screen if game was lost
		if (lostGame) {
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, 1500, 880);
			g2.setColor(Color.BLACK);
			g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"frown.png").getImage(), 211, 10, 540, 370, 0, 0, 1067, 1200, this);
			g2.drawString("Awww!", (750-fm.stringWidth("Awww!"))/2, 640);
			g2.drawString("You lost!", (750-fm.stringWidth("You Lost!"))/2, 760);
			g2.drawImage(new ImageIcon("images"+System.getProperty("file.separator")+"frown.png").getImage(), 961, 10, 1290, 370, 0, 0, 1067, 1200, this);
			g2.drawString("Awww!", 750+(750-fm.stringWidth("Awww!"))/2, 640);
			g2.drawString("You lost!", 750+(750-fm.stringWidth("You Lost!"))/2, 760);
			drawRestartOrQuitScreen(g2, fm);
		}
	}
	
	private void drawRestartOrQuitScreen(Graphics2D g2, FontMetrics fm) {
		g2.setFont(new Font("Arial", Font.BOLD, 120));
		fm = g2.getFontMetrics();
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		g2.setColor(Color.RED);
		g2.fillRect(0, 0, 750, 880);
		g2.setColor(Color.WHITE);
		g2.drawString("QUIT", (750-fm.stringWidth("QUIT"))/2, (800+fm.getHeight())/2);
		g2.setColor(Color.BLUE);
		g2.fillRect(750, 0, 750, 880);
		g2.setColor(Color.WHITE);
		g2.drawString("RESTART", 750+(750-fm.stringWidth("RESTART"))/2, (800+fm.getHeight())/2);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		if (onLeftHalf) {
			g2.setColor(Color.RED);
			g2.fillRect(0, 0, 750, 880);
			g2.setColor(Color.WHITE);
			g2.drawString("QUIT", (750-fm.stringWidth("QUIT"))/2, (800+fm.getHeight())/2);
		} else {
			g2.setColor(Color.BLUE);
			g2.fillRect(750, 0, 750, 880);
			g2.setColor(Color.WHITE);
			g2.drawString("RESTART", 750+(750-fm.stringWidth("RESTART"))/2, (800+fm.getHeight())/2);
		}
	}

	public void run() {
		while (true) {
			long start = 0, end = 0;
			if (!isPaused) {
				start = System.currentTimeMillis();
				counter++;
				if (counter%10==0) {
					addMoney((short) (Math.random()*10));
				}
				if (counter%300==0) {
					addAlien();
				}
				if (!(currentAlien < rawAliens.length) && field.allAliensDead()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					if (CURRENT_LEVEL < 10)
						initGame();
					else {
						wonGame = true;
						r.earnAchievement("Winner!");
					}
				}
				if (field.aliensWon())
					lostGame = true;
				field.act();
				addMoney(field.getMoneyToAdd());
				if (counter % 25 == 0) {
					short killed = field.numAliensKilled(), used = field.numApplesUsed();
					short[] stats = r.getStats();
					r.setStats(stats[0]+killed, stats[1]+used);
					if (stats[0] > 10) {
						r.earnAchievement("Killer");
						if (stats[0] > 100) {
							r.earnAchievement("Super-Killer");
							if (stats[0] > 1000)
								r.earnAchievement("Terminator");
						}
					}
					if (stats[1] > 10) {
						r.earnAchievement("Consumer");
						if (stats[1] > 100) {
							r.earnAchievement("Uber-Consumer");
							if (stats[1] > 1000)
								r.earnAchievement("Steve Jobs");
						}
					}
				}
				end = System.currentTimeMillis();
			}
			repaint();
			try {
				long time = 30 - (end-start);
				if (time > 0)
					Thread.sleep(time);
				else
					Thread.yield();
			}
			catch (InterruptedException e) {}
		}
	}
	
	/**
	 * adds a new alien in a random lane
	 *
	 */
	public boolean addAlien() {
		// if level has next alien, add it in a lane
		try {
			currentAlien++;
			Alien a = rawAliens[currentAlien];
			short l = getNextLane();
			a.setLocation(new Point(1500, l*160+80));
			field.add(a, l);
			return true;
		} catch (ArrayIndexOutOfBoundsException ex) {
			return false;
		}
	}
	
	/**
	 * adds a new Apple to the field at the clicked lane and space
	 * @param a - Apple to be added to the field
	 * @post a was added to the field if and only if there is not already another alien in the currently clicked lane and space
	 */
	public void addApple(Apple a) {
		// if mouse is clicked in a space in a lane, add an apple to that space in that lane
		if (a != null) {
			if (money > a.getCost()) {
				a.setLocation(new Point(currentSpace*160+100, currentLane*160+80));
				field.add(a, currentLane);
				removeMoney(a.getCost());
			}
		}
	}
	
	public void removeApple(short lane, short space) {
		field.removeApple(lane, space);
	}
	
	/**
	 * 
	 * @return a random lane number between 0 and 4, inclusive
	 * @post the number returned is different from the last one
	 */
	public short getNextLane() { // postcondition: 0 <= lane <= 4
		// calculate random number between 0 and 4 (inclusive)
		// but make sure it is not the same as the last one
		short lane = lastLane;
		do {
			lane = (short) (Math.random()*5);
		}while (lane == lastLane);
		// save the number that was just generated
		lastLane = lane;
		// return the lane number that the alien will be added in
		return lane;
	}
	
	public void addMoney(short amount) {
		money += amount;
	}
	
	public void removeMoney(short amount) {
		money -= amount;
	}

	public void initGame() {
		CURRENT_LEVEL++;
		if (wonGame || lostGame)
			CURRENT_LEVEL = 1;
		r = new Reader();
		currentLane = -1;
		currentSpace = -1;
		clickedLane = -1;
		clickedSpace = -1;
		lastLane = -1;
		counter = 0;
		currentAlien = -1;
		currSelectedNum = -1;
		clickedNum = -1;
		isPaused = false;
		onExitButton = false;
		onField = false;
		onTopBar = false;
		onPauseButton = false;
		shovel = false;
		drawCurrSelectedRect = false;
		wonGame = false;
		lostGame = false;
		if (CURRENT_LEVEL <= 10)
			rawAliens = r.getLevelData(CURRENT_LEVEL);
		if (CURRENT_LEVEL == 1)
			money = 2000000000;
		field = new Field();
		currSelected = null;
	}
	
	public void startGame() {
		new Thread(this).start();
	}

	public void mouseClicked(MouseEvent e) {
		short x = (short) e.getX(), y = (short) e.getY();
		
		if (onPauseButton)
			isPaused = !isPaused;
		
		if (isPaused) {
			if (onExitButton) {
				((JFrame) SwingUtilities.getRoot(this)).dispose();
			}
		} else {
			if (onTopBar) {
				clickedNum = currSelectedNum;
				drawCurrSelectedRect = true;
				if (currSelectedNum == 0) {
					currSelected = new IMac(new Point(0, 0));
				} else if (currSelectedNum == 1) {
					currSelected = new IPad(new Point(0, 0));
					shovel = false;
				} else if (currSelectedNum == 2) { 
					currSelected = new IPodClassic(new Point(0, 0));
					shovel = false;
				} else if (currSelectedNum == 3) {
					currSelected = new IPhone(new Point(0, 0));
					shovel = false;
				} else if (currSelectedNum == 4) {
					currSelected = new IPodNano(new Point(0, 0));
					shovel = false;
				} else if (currSelectedNum == 5) {
					currSelected = new IPodTouch(new Point(0, 0));
					shovel = false;
				} else if (currSelectedNum == 6) {
					currSelected = new MacBookAir(new Point(0, 0));
					shovel = false;
				} else if (currSelectedNum == 7) {
					currSelected = new MacBookPro(new Point(0, 0));
					shovel = false;
				} else if (currSelectedNum == 8) {
					currSelected = new MacMini(new Point(0, 0));
					shovel = false;
				} else if (currSelectedNum == 9) {
					currSelected = new MacPro(new Point(0, 0));
					shovel = false;
				} else if (currSelectedNum == 10) {
					currSelected = null;
					shovel = true;
				}
			}
		}
		
		if (onField) {
			double yCoord = ((y-80*(getHeight()/880.))/(160*(getHeight()/880.)));
			if (0 <= yCoord && yCoord < 5) {
				short lane = (short) yCoord;
				clickedLane = lane;
				double xCoord = ((x-100*(getWidth()/1500.))/(160*(getWidth()/1500.)));
				if (0 <= xCoord && xCoord < 8) {
					short space = (short) xCoord;
					clickedSpace = space;
				}
			}
			if (clickedLane == currentLane && clickedSpace == currentSpace) {
				if (field.spaceNotOccupied(clickedLane, clickedSpace)) {
					addApple(currSelected);
					currSelected = null;
					drawCurrSelectedRect = false;
				}
				if (shovel) {
					removeApple(clickedLane, clickedSpace);
					drawCurrSelectedRect = false;
					shovel = false;
				}
			}	
		}
		
		if (wonGame || lostGame) {
			if (mouseX < 750/(1500./getWidth()))
				((JFrame) SwingUtilities.getRoot(this)).dispose();
			else
				initGame();
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	
	public void mouseMoved(MouseEvent e) {
		// updates the mouse coordinates
		mouseX = (short) e.getX();
		mouseY = (short) e.getY();
		double xRatio = 1500./getWidth(), yRatio = 880./getHeight();
		
		// updates paused button
		if (mouseY*yRatio < 80 && mouseX*xRatio < 100) {
			onPauseButton = true;
		} else onPauseButton = false;
		
		// updates exit button
		// g2.fillRect(600, 700, 300, 120);
		if (mouseX*xRatio > 600 && mouseX*xRatio < 900
				&& mouseY*yRatio > 700 && mouseY*yRatio < 820) {
			onExitButton = true;
		} else onExitButton = false;
		
		// updates the top panel
		if (mouseY*yRatio < 80 && 100 <= mouseX*xRatio && mouseX*xRatio <= 1200) {
			currSelectedNum = (short) ((mouseX*xRatio-100)/100);
			onTopBar = true;
		} else onTopBar = false;
		
		// updates the field
		double yCoord = ((mouseY-80/yRatio)/(160/yRatio));
		if (0 <= yCoord && yCoord < 5) {
			short lane = (short) yCoord;
			currentLane = lane;
			double xCoord = ((mouseX-100/xRatio)/(160/xRatio));
			if (0 <= xCoord && xCoord < 8) {
				short space = (short) xCoord;
				currentSpace = space;
				onField = true;
			} else onField = false;
		} else onField = false;
		
		// updates screen ***IF*** game was won/lost
		if (wonGame || lostGame) {
			if (mouseX < 750/xRatio) {
				onLeftHalf = true;
			} else {
				onLeftHalf = false;
			}
		}
	}
}