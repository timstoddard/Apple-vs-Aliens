package game.gamerunner;

import game.entity.alive.alien.Alien;
import game.entity.alive.apple.Apple;
import game.entity.notalive.Projectile;
import game.gamerunner.sound.SoundPlayer;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.util.*;

public class Lane {

	private ArrayList<Apple> apples;
	private ArrayList<Alien> aliens;
	private ArrayList<Projectile> projs;
	private long num;
	private short moneyToAdd, aliensKilled, killed, applesUsed;
	private SoundPlayer sp;

	public Lane() {
		apples = new ArrayList<Apple>();
		aliens = new ArrayList<Alien>();
		projs = new ArrayList<Projectile>();
		num = 0;
		moneyToAdd = 0;
		aliensKilled = 0;
		killed = 0;
		applesUsed = 0;
		sp = new SoundPlayer("sound"+System.getProperty("file.separator")+"laser.wav");
	}

	/**
	 * 
	 * updates every object in this lane
	 * <br><br>
	 * for every Apple, it checks to see if the Apple can shoot, and if it can it does
	 * <br><br>
	 * for every Alien, it checks to see if it intersects with an Apple
	 * <br>  if it does, it takes away some health from the Apple
	 * <br>  otherwise, it moves
	 * <br><br>
	 * for every projectile, it moves the projectile and checks to see if it hit an Alien
	 * <br>  if it did, it takes some health away from the Alien and becomes used (this is discussed farther down)
	 * <br><br>
	 * lastly, it removes any Apples with no health left, any Aliens with no health left, and any Projectiles that are off the screen or have been used
	 */
	public void act() {
		for (Apple a : apples) {
			// possibly shoot a new projectile
			boolean canShoot = false;
			for (Alien al : aliens) {
				if (a.getX() < al.getX())
					canShoot = true;
			}
			if (num % a.shootWaitTime() == 0 && canShoot) {
				Projectile p = new Projectile(new Point(a.getX() + (160/3), a.getY() + (160/3)));
				projs.add(p);
				sp.play();
			}
		}
		for (Alien al : aliens) {
			boolean intersects = false;
			for (Apple a : apples) {
				if (al.intersects(a)) {
					a.setHealth((short) (a.getHealth()-1));
					intersects = true;
				}
			}
			if (!intersects)
				al.move();
		}
		for (Projectile p : projs) {
			p.move();
			for (Alien al : aliens) {
				if (al.intersects(p)) {
					al.setHealth((short) (al.getHealth()-1));
					p.setUsed(true);
				}
			}
		}
		try {
			ListIterator<Apple> appleIter = apples.listIterator();
			while (appleIter.hasNext()) {
				Apple a = appleIter.next();
				if (a.getHealth() <= 0 && apples.size() > 0)
					apples.remove(apples.indexOf(a));
			}
		} catch (ConcurrentModificationException ex) {
			//System.out.println("apples had a cme");
		}
		try {
			ListIterator<Alien> alienIter = aliens.listIterator();
			while (alienIter.hasNext()) {
				Alien a = alienIter.next();
				if (a.getHealth() <= 0 && aliens.size() > 0) {
					aliens.remove(aliens.indexOf(a));
					moneyToAdd += 100;
					aliensKilled++;
					killed++;
				}
			}
		} catch (ConcurrentModificationException ex) {
			//System.out.println("aliens had a cme");
		}
		try {
			ListIterator<Projectile> projIter = projs.listIterator();
			while (projIter.hasNext()) {
				Projectile p = projIter.next();
				if ((p.getX() >= 1500 || p.isUsed()) && projs.size() > 0)
					projs.remove(projs.indexOf(p));
			}
		} catch (ConcurrentModificationException ex) {
			//System.out.println("projs had a cme");
		}
		// */
		
		num++;
	}

	public void draw(Graphics g, ImageObserver io) {
		try {
			for (Apple a : apples)
				a.draw(g, io);
		} catch (ConcurrentModificationException ex) {}
		try {
			for (Alien a : aliens)
				a.draw(g, io);
		} catch (ConcurrentModificationException ex) {}
		try {
			for (Projectile p : projs)
				p.draw(g, io);
		} catch (ConcurrentModificationException ex) {}
	}

	public void add(Alien a) {
		aliens.add(a);
	}

	public void add(Apple a) {
		apples.add(a);
		applesUsed++;
	}
	
	public void removeApple(short space) {
		try {
			ListIterator<Apple> appleIter = apples.listIterator();
			while (appleIter.hasNext()) {
				Apple a = appleIter.next();
				if (a.getX() == space*160+100) {
					apples.remove(apples.indexOf(a));
				}
			}
		} catch (ConcurrentModificationException ex) {}
	}

	public void add(Projectile p) {
		projs.add(p);
	}
	
	/**
	 * checks to make sure the space is not already occupied
	 * @param space
	 * @return true if the space is already occupied, false otherwise
	 */
	public boolean spaceNotOccupied(short space) {
		for (Apple a : apples) {
			if ((a.getX()-100)/160 == space)
				return false;
		}
		return true;
	}
	
	public boolean allAliensDead() {
		return aliens.size() == 0;
	}
	
	public short getAliensKilled() {
		return aliensKilled;
	}
	
	public short numAliensKilled() {
		short aliens = killed;
		killed = 0;
		return aliens;
	}
	
	public short getMoneyToAdd() {
		short money = moneyToAdd;
		moneyToAdd = 0;
		return money;
	}

	public boolean aliensWon() {
		for (Alien a : aliens) {
			if (a.getX() < 100) {
				return true;
			}
		}
		return false;
	}
	
	public short numApplesUsed() {
		short used = applesUsed;
		applesUsed = 0;
		return used;
	}
}