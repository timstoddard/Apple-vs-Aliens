package game.main;

import game.gamerunner.GameRunner;
import game.gamerunner.sound.SoundPlayer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Main f = new Main("Apple vs Aliens");
		f.setBounds(25, 25, 1000, 800);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false); // should this be changed to true?  No.  Just no.
		f.setVisible(true);
		f.revalidate();
	}

	private Reader r;
	private JPanel cards, mainMenu, mainScreen, instructionsMenu, optionsMenu,
			achievementsMenu, creditsMenu;
	private CardLayout cL;
	private static short[] optionsList; // 0 = music, 1 = sound
	private static short musicInt = 0, soundInt = 0;
	private JButton start, instructions, options, achievements, credits, quit,
			iBack, oBack, aBack, cBack, reset;
	private JFrame fullscreenFrame;
	private GameRunner gr;
	private MainScreen ms;
	private InstructionsScreen is;
	private MusicPanel mp;
	private SoundPanel sp;
	private AchievementsScreen as;
	private CreditsScreen cs;
	private SoundPlayer soundplay;

	public Main(String name) {
		super(name);

		cL = new CardLayout();
		cards = new JPanel(cL);
		r = new Reader();

		// set up the main menu
		mainMenu = new JPanel();
		mainMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		start = new JButton("Start Game");
		start.setToolTipText("Start and play the game");
		start.setSize(new Dimension(100, 100));
		start.addActionListener(this);
		mainMenu.add(start);

		instructions = new JButton("Instructions");
		instructions.setToolTipText("Learn how to play the game");
		instructions.setSize(new Dimension(100, 100));
		instructions.addActionListener(this);
		mainMenu.add(instructions);

		options = new JButton("Options");
		options.setToolTipText("Change the options");
		options.setSize(new Dimension(100, 100));
		options.addActionListener(this);
		mainMenu.add(options);

		achievements = new JButton("Achievements");
		achievements.setToolTipText("View your achievements");
		achievements.setSize(new Dimension(100, 100));
		achievements.addActionListener(this);
		mainMenu.add(achievements);

		credits = new JButton("Credits");
		credits.setToolTipText("Find out who made this game");
		credits.setSize(new Dimension(100, 100));
		credits.addActionListener(this);
		mainMenu.add(credits);

		quit = new JButton("Quit");
		quit.setToolTipText("Quit the game");
		quit.setSize(new Dimension(100, 100));
		quit.addActionListener(this);
		mainMenu.add(quit);

		// create the cards
		
		mainScreen = new JPanel();
		mainScreen.setLayout(new BorderLayout());
		
		ms = new MainScreen();
		mainScreen.add(ms, BorderLayout.CENTER);

		instructionsMenu = new JPanel();
		instructionsMenu.setLayout(new BorderLayout());
		
		is = new InstructionsScreen();
		instructionsMenu.add(is, BorderLayout.CENTER);
		
		iBack = new JButton("Back");
		iBack.setToolTipText("Return to the main menu");
		iBack.setSize(new Dimension(100, 100));
		iBack.addActionListener(this);
		instructionsMenu.add(iBack, BorderLayout.SOUTH);

		optionsMenu = new JPanel();
		optionsMenu.setLayout(new BorderLayout());

		optionsList = r.getOptions();
		musicInt = optionsList[0];
		soundInt = optionsList[1];

		JPanel music = new JPanel(new BorderLayout()), sound = new JPanel(new BorderLayout());
		JPanel content = new JPanel(new GridLayout(1, 2));
		mp = new MusicPanel(musicInt);
		music.add(mp);
		sp = new SoundPanel(soundInt);
		sound.add(sp);
		sound.add(new SoundPanel(soundInt));
		content.add(music);
		content.add(sound);
		optionsMenu.add(content);
		
		oBack = new JButton("Back");
		oBack.setToolTipText("Return to the main menu");
		oBack.setSize(new Dimension(100, 100));
		oBack.addActionListener(this);
		optionsMenu.add(oBack, BorderLayout.SOUTH);

		achievementsMenu = new JPanel();
		achievementsMenu.setLayout(new BorderLayout());
		
		JPanel buttons = new JPanel(new BorderLayout());
		
		as = new AchievementsScreen();
		achievementsMenu.add(as, BorderLayout.CENTER);

		reset = new JButton("Reset");
		reset.setToolTipText("Reset all your achievements and stats");
		reset.setSize(new Dimension(100, 100));
		reset.addActionListener(this);
		buttons.add(reset, BorderLayout.NORTH);
		
		aBack = new JButton("Back");
		aBack.setToolTipText("Return to the main menu");
		aBack.setSize(new Dimension(100, 100));
		aBack.addActionListener(this);
		buttons.add(aBack, BorderLayout.SOUTH);
		
		achievementsMenu.add(buttons, BorderLayout.SOUTH);

		creditsMenu = new JPanel();
		creditsMenu.setLayout(new BorderLayout());
		
		cs = new CreditsScreen();
		creditsMenu.add(cs, BorderLayout.CENTER);

		cBack = new JButton("Back");
		cBack.setToolTipText("Return to the main menu");
		cBack.setSize(new Dimension(100, 100));
		cBack.addActionListener(this);
		creditsMenu.add(cBack, BorderLayout.SOUTH);

		cards.add(mainScreen, "1");
		cards.add(instructionsMenu, "2");
		cards.add(optionsMenu, "3");
		cards.add(achievementsMenu, "4");
		cards.add(creditsMenu, "5");

		getContentPane().add(mainMenu, BorderLayout.NORTH);
		getContentPane().add(cards, BorderLayout.CENTER);
		
		soundplay = new SoundPlayer("sound"+System.getProperty("file.separator")+"bgmusic.wav");
		soundplay.loop();
		
		// cL.show(cards, "1");
	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button == start) {
			cL.show(cards, "1");
			
			/* DIALOG BEFORE START OF GAME */
//			this.setVisible(false);
//			StartDialog sd = new StartDialog("Log In Screen");
//			sd.setResizable(false);
//			sd.setBounds(100, 100, 700, 500);
//			sd.setVisible(true);
			
			/* FULLSCREEN JFRAME THAT DISPLAYS THE GAME */
	 		fullscreenFrame = new JFrame();
	 		//fullscreenFrame.setBounds(0, 0, java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
	 		fullscreenFrame.setVisible(true);
	 		// fullscreenFrame.setUndecorated(true);
	 		fullscreenFrame.setResizable(true);
	 		fullscreenFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	 		gr = new GameRunner();
	 		fullscreenFrame.add(gr);
	 		fullscreenFrame.revalidate();
	 		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(fullscreenFrame);
	 		gr.startGame();
	 		
		} else if ((button == iBack) || (button == oBack) || (button == aBack)
				|| (button == cBack)) {
			cL.show(cards, "1");
			//ms.reset();
		} else if (button == reset) {
			r.resetAchievementsAndStats();
			as.init();
			as.repaint();
		} else if (button == instructions) {
			cL.show(cards, "2");
		} else if (button == options) {
			cL.show(cards, "3");
		} else if (button == achievements) {
			cL.show(cards, "4");
		} else if (button == credits) {
			cL.show(cards, "5");
			cs.reset();
		} else if (button == quit) {
			System.exit(0);
		}
	}
}