package game.main;

import game.entity.alive.alien.*;
import java.io.*;
import java.util.*;

public class Reader {

	public short[] getStats() {
		FileReader reader = null;
		Scanner in = null;
		short[] stats = null;
		try {
			reader = new FileReader("txt"+System.getProperty("file.separator")+"achievements.txt");
			in = new Scanner(reader);
			String[] data = in.nextLine().split(",");
			stats = new short[data.length];
			for (int i = 0; i < stats.length; i++) {
				stats[i] = Short.parseShort(data[i]);
			}
		} catch (IOException ex) {
			System.out.println("File cannot be read.");
			return null;
		} catch (NumberFormatException ex) {
			System.out.println("File is in the wrong format.");
			return null;
		} finally {
			try {
				reader.close();
				in.close();
			} catch (IOException ex) {
				System.out.println("File cannot be closed.");
				return null;
			}
		}
		return stats;
	}
	
	public void setStats(long a1, long a2) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		Scanner kb = null;
		short count = 0;
		String info = "";
		try {
			reader = new BufferedReader(new FileReader("txt"+System.getProperty("file.separator")+"achievements.txt"));
			kb = new Scanner(reader);
			while (kb.hasNextLine()) {
				if (count == 0) {
					info += a1+","+a2+System.getProperty("line.separator");
					kb.nextLine();
				} else {
					info += kb.nextLine()+System.getProperty("line.separator");
				}
				count++;
			}
		} catch (IOException ex) {
			System.out.println("File could not be read");
		} finally {
			try {
				reader.close();
				kb.close();
			} catch (IOException ex) {
				System.out.println("File could not be read");
			}
		}
		try {
			writer = new BufferedWriter(new FileWriter("txt"+System.getProperty("file.separator")+"achievements.txt"));
			writer.write(info);
			writer.flush();
		} catch (IOException ex) {
			System.out.println("File could not be read");
		} finally {
			try {
				writer.close();
			} catch (IOException ex) {
				System.out.println("File could not be read");
			}
		}
	}
	
	public String[] getAchievements() {
		ArrayList<String> ac = new ArrayList<String>();
		FileReader reader = null;
		Scanner in = null;
		try {
			reader = new FileReader("txt"+System.getProperty("file.separator")+"achievements.txt");
			in = new Scanner(reader);
			while (in.hasNextLine()) {
				ac.add(in.nextLine());
			}
		} catch (IOException ex) {
			System.out.println("File cannot be read.");
			return null;
		} catch (NumberFormatException ex) {
			System.out.println("File is in the wrong format.");
			return null;
		} finally {
			try {
				reader.close();
				in.close();
			} catch (IOException ex) {
				System.out.println("File cannot be closed.");
				return null;
			}
		}
		String[] acs = new String[ac.size()-1];
		for (int i = 0; i < acs.length; i++) {
			acs[i] = ac.get(i+1);
		}
		return acs;
	}
	
	public void earnAchievement(String achievementName) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		Scanner kb = null;
		String info = "";
		short[] stats = null;
		try {
			reader = new BufferedReader(new FileReader("txt"+System.getProperty("file.separator")+"achievements.txt"));
			kb = new Scanner(reader);
			while (kb.hasNextLine()) {
				String[] line = kb.nextLine().split(",");
				if (line.length > 2) {
					if (line[2].equals(achievementName)) {
						line[0] = "true";
					}
					String newLine = line[0]+","+line[1]+","+line[2]+","+line[3];
					info += newLine + System.getProperty("line.separator");
				} else {
					stats = new short[line.length];
					for (int i = 0; i < stats.length; i++)
						stats[i] = Short.parseShort(line[i]);
				}
			}
		} catch (IOException ex) {
			System.out.println("File could not be read");
		} finally {
			try {
				reader.close();
				kb.close();
			} catch (IOException ex) {
				System.out.println("File could not be read");
			}
		}
		try {
				writer = new BufferedWriter(new FileWriter("txt"+System.getProperty("file.separator")+"achievements.txt"));
				writer.write(stats[0]+","+stats[1]+System.getProperty("line.separator")+info);
				writer.flush();
		} catch (IOException ex) {
			System.out.println("File could not be read");
		} finally {
			try {
				writer.close();
			} catch (IOException ex) {
				System.out.println("File could not be read");
			}
		}
	}

	public void resetAchievementsAndStats() {
		setStats(0, 0);
		BufferedReader reader = null;
		BufferedWriter writer = null;
		Scanner kb = null;
		String info = "";
		short[] stats = null;
		try {
			reader = new BufferedReader(new FileReader("txt"+System.getProperty("file.separator")+"achievements.txt"));
			kb = new Scanner(reader);
			while (kb.hasNextLine()) {
				String[] line = kb.nextLine().split(",");
				if (line.length > 2) {
					if (line[2].equals("Hello World!")) {
						String newLine = "true,"+line[1]+","+line[2]+","+line[3];
						info += newLine + System.getProperty("line.separator");
					} else {
						String newLine = "false,"+line[1]+","+line[2]+","+line[3];
						info += newLine + System.getProperty("line.separator");
					}
				} else {
					stats = new short[line.length];
					for (int i = 0; i < stats.length; i++)
						stats[i] = Short.parseShort(line[i]);
				}
			}
		} catch (IOException ex) {
			System.out.println("File could not be read");
		} finally {
			try {
				reader.close();
				kb.close();
			} catch (IOException ex) {
				System.out.println("File could not be read");
			}
		}
		try {
				writer = new BufferedWriter(new FileWriter("txt"+System.getProperty("file.separator")+"achievements.txt"));
				writer.write(stats[0]+","+stats[1]+System.getProperty("line.separator")+info);
				writer.flush();
		} catch (IOException ex) {
			System.out.println("File could not be read");
		} finally {
			try {
				writer.close();
			} catch (IOException ex) {
				System.out.println("File could not be read");
			}
		}
	}
	
	public short[] getOptions() {
		ArrayList<Short> op = new ArrayList<Short>();
		FileReader reader = null;
		Scanner in = null;
		try {
			reader = new FileReader("txt"+System.getProperty("file.separator")+"options.txt");
			in = new Scanner(reader);
			while (in.hasNext()) {
				op.add(in.nextShort());
			}
		} catch (IOException ex) {
			System.out.println("File cannot be read.");
			return null;
		} catch (NumberFormatException ex) {
			System.out.println("File is in the wrong format.");
			return null;
		} finally {
			try {
				reader.close();
				in.close();
			} catch (IOException ex) {
				System.out.println("File cannot be closed.");
				return null;
			}
		}
		short[] ops = new short[op.size()];
		for (int i = 0; i < ops.length; i++) {
			ops[i] = op.get(i);
		}
		return ops;
	}

	public void setOptions(int[] ops) {
		FileWriter writer = null;
		try {
			writer = new FileWriter("txt"+System.getProperty("file.separator")+"options.txt");
			for (int op : ops) {
				writer.write(op + "\n");
			}
		} catch (IOException ex) {
			System.out.println("File cannot be read.");
			return;
		} catch (NumberFormatException ex) {
			System.out.println("File is in the wrong format.");
			return;
		} finally {
			try {
				writer.close();
			} catch (IOException ex) {
				System.out.println("File cannot be closed.");
				return;
			}
		}
	}
	
//	public String[] getNames() {
//		String nameList = "";
//		FileReader reader = null;
//		Scanner in = null;
//		try {
//			reader = new FileReader("txt"+System.getProperty("file.separator")+"names.txt");
//			in = new Scanner(reader);
//			while (in.hasNextLine()) {
//				String s = in.nextLine();
//				int i = s.indexOf(',');
//				nameList += s.substring(0, i) + ",";
//			}
//		} catch (IOException ex) {
//			System.out.println("File cannot be read.");
//			return null;
//		} catch (NumberFormatException ex) {
//			System.out.println("File is in the wrong format.");
//			return null;
//		} finally {
//			try {
//				reader.close();
//				in.close();
//			} catch (IOException ex) {
//				System.out.println("File cannot be closed.");
//				return null;
//			}
//		}
//		return nameList.split(",");
//	}
//	
//	public void addUser(String newName, String newPassword) {
//		FileWriter writer = null;
//		String names = "";
//		String[] nameList = getNames();
//		for (int i = 0; i < nameList.length; i++)
//			names += nameList[i] + "," + getPassword(nameList[i]) + "\n";
//		try {
//			writer = new FileWriter("txt"+System.getProperty("file.separator")+"names.txt");
//			writer.write(names + newName + "," + newPassword);
//		} catch (IOException ex) {
//			System.out.println("File cannot be read.");
//			return;
//		} catch (NumberFormatException ex) {
//			System.out.println("File is in the wrong format.");
//			return;
//		} finally {
//			try {
//				writer.close();
//			} catch (IOException ex) {
//				System.out.println("File cannot be closed.");
//				return;
//			}
//		}
//	}
//	
//	public String getPassword(String name) {
//		String password = "";
//		FileReader reader = null;
//		Scanner in = null;
//		try {
//			reader = new FileReader("txt"+System.getProperty("file.separator")+"names.txt");
//			in = new Scanner(reader);
//			while (in.hasNextLine()) {
//				String s = in.nextLine();
//				int i = s.indexOf(',');
//				String userName = s.substring(0, i), userPassword = s.substring(i+1, s.length());
//				if (name.equals(userName))
//					password = userPassword;
//			}
//		} catch (IOException ex) {
//			System.out.println("File cannot be read.");
//			return null;
//		} catch (NumberFormatException ex) {
//			System.out.println("File is in the wrong format.");
//			return null;
//		} finally {
//			try {
//				reader.close();
//				in.close();
//			} catch (IOException ex) {
//				System.out.println("File cannot be closed.");
//				return null;
//			}
//		}
//		return password;
//	}
	
	public Alien[] getLevelData(short level_number) {
		ArrayList<Alien> alienList = new ArrayList<Alien>();
		FileReader reader = null;
		Scanner in = null;
		try {
			reader = new FileReader("txt"+System.getProperty("file.separator")+"level"+System.getProperty("file.separator")+"level"+level_number+".txt");
			in = new Scanner(reader);
			while (in.hasNextLine()) {
				String nextAlien = in.nextLine();
				if (nextAlien.equals("Backwards_Alien")) {
					alienList.add(new BackwardsAlien());
				} else if (nextAlien.equals("Boss_Alien")) {
					alienList.add(new BossAlien());
				} else if (nextAlien.equals("Fat_Alien")) {
					alienList.add(new FatAlien());
				} else if (nextAlien.equals("Normal_Alien")) {
					alienList.add(new NormalAlien());
				} else if (nextAlien.equals("Strong_Alien")) {
					alienList.add(new StrongAlien());
				}
			}
		} catch (IOException ex) {
			System.out.println("File cannot be read.");
			return null;
		} catch (NumberFormatException ex) {
			System.out.println("File is in the wrong format.");
			return null;
		} finally {
			try {
				reader.close();
				in.close();
			} catch (IOException ex) {
				System.out.println("File cannot be closed.");
				return null;
			}
		}
		Alien[] aliens = new Alien[alienList.size()];
		for (int i = 0; i < aliens.length; i++) {
			aliens[i] = alienList.get(i);
		}
		return aliens;
	}
}