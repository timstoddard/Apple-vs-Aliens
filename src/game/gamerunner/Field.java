package game.gamerunner;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import game.entity.alive.alien.Alien;
import game.entity.alive.apple.Apple;

public class Field {
	
	private Lane[] lanes;
	
	public Field() {
		lanes = new Lane[5];
		for (int i = 0; i < 5; i++)
			lanes[i] = new Lane();
	}
	
	public void act() {
		for (Lane l : lanes)
			l.act();
	}
	
	public void add(Alien a, short lane) {
		lanes[lane].add(a);
	}
	
	public void add(Apple a, short lane) {
		lanes[lane].add(a);
	}
	
	public void removeApple(short lane, short space) {
		lanes[lane].removeApple(space);
	}
	
	public void draw(Graphics g, ImageObserver io) {
		for (Lane l : lanes)
			l.draw(g, io);
	}
	
	/**
	 * checks to make sure the space in the lane is not already occupied
	 * @param lane
	 * @param space
	 * @return true if the space in the lane is already occupied, false otherwise
	 */
	public boolean spaceNotOccupied(short lane, short space) {
		return lanes[lane].spaceNotOccupied(space);
	}
	
	public boolean allAliensDead() {
		for (Lane l : lanes) {
			if (!l.allAliensDead())
				return false;
		}
		return true;
	}
	
	public short getMoneyToAdd() {
		short money = 0;
		for (Lane l : lanes) {
			money += l.getMoneyToAdd();
		}
		return money;
	}
	
	public short getAliensKilled() {
		short killed = 0;
		for (Lane l : lanes) {
			killed += l.getAliensKilled();
		}
		return killed;
	}
	
	public short numAliensKilled() {
		short killed = 0;
		for (Lane l : lanes) {
			killed += l.numAliensKilled();
		}
		return killed;
	}
	
	public short numApplesUsed() {
		short used = 0;
		for (Lane l : lanes) {
			used += l.numApplesUsed();
		}
		return used;
	}
	
	public boolean aliensWon() {
		for (Lane l : lanes) {
			if (l.aliensWon())
				return true;
		}
		return false;
	}
}