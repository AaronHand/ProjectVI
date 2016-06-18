package com.tigerninjas;
// Write your compliance statement here:
// What are your 4 extra features?
// How is your new alien different from the one described by the Alien class?
//
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import uwcse.graphics.GWindow;
import uwcse.graphics.GWindowEvent;
import uwcse.graphics.GWindowEventAdapter;
import uwcse.graphics.Oval;
import uwcse.graphics.Rectangle;

/**
 * A SpaceInvader displays a fleet of alien ships and a space ship. The player
 * directs the moves of the spaceship and can shoot at the aliens.
 */

public class SpaceInvader extends GWindowEventAdapter {
	// Possible actions from the keyboard
	/** No action */
	public static final int DO_NOTHING = 0;

	/** Steer the space ship */
	public static final int SET_SPACESHIP_DIRECTION = 1;

	/** To shoot at the aliens */
	public static final int SHOOT = 2;

	// Period of the animation (in ms)
	// (the smaller the value, the faster the animation)
	private int animationPeriod = 100;

	// Current action from the keyboard
	private int action;

	// Game window
	private GWindow window;

	// The space ship
	private SpaceShip spaceShip;

	// Direction of motion given by the player
	private int dirFromKeyboard = MovingObject.LEFT;

	// The aliens
	private ArrayList<Alien> aliens;
	
	// current laser beams
	private ArrayList<LaserBeam> laserBeams;

	// Is the current game over?
	private String messageGameOver = "";

	/**
	 * Construct a space invader game
	 */
	public SpaceInvader() {
		this.window = new GWindow("Space invaders", 500, 500);
		this.window.setExitOnClose();
		// this SpaceInvader handles all of
		// the events fired by the graphics window
		this.window.addEventHandler(this); 

		// Display the game rules
		String rulesOfTheGame = "Save the Earth! Destroy all of the aliens ships.\n"
				+ "To move left, press '<'.\n"
				+ "To move right, press '>'.\n"
				+ "To shoot, press the space bar.\n" + "To quit, press 'Q'.";
		JOptionPane.showMessageDialog(null, rulesOfTheGame, "Space invaders",
				JOptionPane.INFORMATION_MESSAGE);
		this.initializeGame();
	}

	/**
	 * Initialize the game (draw the background, aliens, and space ship)
	 */
	private void initializeGame() {
		// Clear the window
		this.window.erase();

		// Background (starry universe)
		Rectangle background = new Rectangle(0, 0,
				this.window.getWindowWidth(), this.window.getWindowHeight(),
				Color.black, true);
		this.window.add(background);
		// Add 50 stars here and there (as small circles)
		Random rnd = new Random();
		for (int i = 0; i < 50; i++) {
			// Random radius between 1 and 3
			int radius = rnd.nextInt(3) + 1;
			// Random location (within the window)
			// Make sure that the full circle is visible in the window
			int x = rnd.nextInt(this.window.getWindowWidth() - 2 * radius);
			int y = rnd.nextInt(this.window.getWindowHeight() - 2 * radius);
			this.window.add(new Oval(x, y, 2 * radius, 2 * radius, Color.white,
					true));
		}

		// ArrayList of aliens
		this.aliens = new ArrayList<Alien>();
		
		// ArrayList of laser beams
		this.laserBeams = new ArrayList<LaserBeam>();

		// Create 12 aliens
		// Initial location of the aliens
		// (Make sure that the space ship can fire at them)
		int x = 5 * SpaceShip.WIDTH;
		int y = 10 * Alien.RADIUS;
		for (int i = 0; i < 12; i++) {
			int ranY = rnd.nextInt(this.window.getWindowHeight()/2);
			this.aliens.add(new Alien(this.window, new Point(x, y + ranY)));
			x += 5 * Alien.RADIUS;
		}

		// Create the space ship at the bottom of the window
		x = this.window.getWindowWidth() / 2;
		y = this.window.getWindowHeight() - SpaceShip.HEIGHT / 2;
		this.spaceShip = new SpaceShip(this.window, new Point(x, y));

		// start timer events
		this.window.startTimerEvents(this.animationPeriod);
	}

	/**
	 * Move the objects within the graphics window every time the timer fires an
	 * event
	 */
	public void timerExpired(GWindowEvent we) {
		// Perform the action requested by the user?
		switch (this.action) {
		case SpaceInvader.SET_SPACESHIP_DIRECTION:
			this.spaceShip.setDirection(this.dirFromKeyboard);
			break;
		case SpaceInvader.SHOOT:
			Point beamCenter = new Point(this.spaceShip.center);
			this.laserBeams.add(new LaserBeam(this.window, beamCenter));
			break;
		}

		this.action = SpaceInvader.DO_NOTHING; // Don't do the same action
		// twice

		// Show the new locations of the objects
		this.updateGame();
	}

	/**
	 * Select the action requested by the pressed key
	 */
	public void keyPressed(GWindowEvent e) {
		// Don't perform the actions (such as shoot) directly in this method.
		// Do the actions in timerExpired, so that the alien ArrayList can't be
		// modified at the same time by two methods (keyPressed and timerExpired
		// run in different threads).

		switch (Character.toLowerCase(e.getKey())) // not case sensitive
		{
            // Put here the code to move the space ship with the < and > keys
            case '.':
                spaceShip.direction = MovingObject.RIGHT;
                break;
            case ',':
                spaceShip.direction = MovingObject.LEFT;
                break;
            case '>':
                spaceShip.direction = MovingObject.RIGHT;
                break;
            case '<':
                spaceShip.direction = MovingObject.LEFT;
                break;

            case ' ': // shoot at the aliens
                this.action = SpaceInvader.SHOOT;
                break;

            case 'q': // quit the game (BlueJ might not like that one)
                System.exit(0);
                break;


            default: // no new action
                this.action = SpaceInvader.DO_NOTHING;
                break;
		}
	}

	/**
	 * Update the game (Move aliens + space ship)
	 */
	private void updateGame() {

		this.window.suspendRepaints(); // to speed up the drawing

		// Move the aliens
		for (Alien a : aliens) {
			a.move();
		}
		
		// move laser beams, check collision with aliens
		if (this.laserBeams.size() > 0){
			for (int i = 0; i < this.laserBeams.size(); i++){
				LaserBeam b = this.laserBeams.get(i);
				b.move();
				if (b.center.y <= 0) {
					this.laserBeams.remove(i);
				}
				for (int j = 0; j < this.aliens.size(); j++){
					Alien a = this.aliens.get(j);
					if(b.center.x <= a.getBoundingBox().getX() + a.getBoundingBox().getWidth()
		                    && b.center.x >= a.getBoundingBox().getX()
		                    && b.center.y <= a.getBoundingBox().getY() + a.getBoundingBox().getHeight()
		                    && b.center.y >= a.getBoundingBox().getY()){
		                a.isShot();
		                if (a.isDead()){
		                	a.erase();
		                	this.aliens.remove(j);
		                }
		                b.erase();
		                this.laserBeams.remove(i);
		            }
				}
			}
		}
		
		if (this.aliens.size() == 0) {
			// wait a second, or spacebar auto confirms dialog
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (this.anotherGame("They're all dead!")){
				// reinitialize game
				this.initializeGame();
			} else {
				// quit the game
				System.exit(0);
			}
		}
		
		// Move the space ship
		this.spaceShip.move();

		// Display it all
		this.window.resumeRepaints();
	}

	/**
	 * Does the player want to play again?
	 */
	public boolean anotherGame(String s) {
		// this method is useful at the end of a game if you want to prompt the
		// user
		// for another game (s would be a String describing the outcome of the
		// game
		// that just ended, e.g. "Congratulations, you saved the Earth!")
		int choice = JOptionPane.showConfirmDialog(null, s
				+ "\nDo you want to play again?", "Game over",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		return (choice == JOptionPane.YES_OPTION);
	}

	/**
	 * Starts the application
	 */
	public static void main(String[] args) {
		new SpaceInvader();
	}
}
