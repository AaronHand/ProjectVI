package com.tigerninjas;
import java.awt.Color;
import java.awt.Point;

import uwcse.graphics.GWindow;
import uwcse.graphics.Line;
import uwcse.graphics.Oval;
import uwcse.graphics.Rectangle;
import uwcse.graphics.Shape;

/**
 * The representation and display of an Alien
 */

public class Alien extends MovingObject {
	// Size of an Alien
	public static final int RADIUS = 5;
	
	// Colors
	public static final Color RED = new Color(255,43,36);
	public static final Color YELLOW = new Color(255,222,36);
	public static final Color GREEN = new Color(113,232,36);

	// Number of lives in this Alien
	// When 0, this Alien is dead
	private int lives;
	
	// move alien down every x cycles
	private int moveTimer = 0;

	/**
	 * Create an alien in the graphics window
	 * 
	 * @param window
	 *            the GWindow this Alien belongs to
	 * @param center
	 *            the center Point of this Alien
	 */
	public Alien(GWindow window, Point center) {
		super(window, center);
		this.lives = (int) (Math.random() * 3 + 1);

		// Display this Alien
		this.draw();
	}

	/**
	 * The alien is being shot Decrement its number of lives and erase it from
	 * the graphics window if it is dead.
	 */
	public void isShot() {
		
		// still have lives left?
		if (this.lives > 0) {
			this.lives--;
		}
       
        
        if(isDead()) { 
        	this.erase();
        }
        
        if (this.shapes == null) {
        	return;
        }
        
        Color newColor;
        if (this.lives == 1) {
        	newColor = Alien.RED;
        } else if (this.lives == 2) {
        	newColor = Alien.YELLOW;
        } else {
        	newColor = Alien.GREEN;
        }
        
        for (Shape shape : this.shapes) {
        	shape.setColor(newColor);
        }
	}

	/**
	 * Is this Alien dead?
	 */
	public boolean isDead() {
		return this.lives == 0;
	}

	/**
	 * Return the location of this Alien
	 */
	public Point getLocation() {
		return this.center;
	}

	/**
	 * Move this Alien As a start make all of the aliens move downward. If an
	 * alien reaches the bottom of the screen, it reappears at the top.
	 */
	public void move() {
		// move the alien downward
		// alien still alive?
		if( !this.isDead() ){
			if (this.moveTimer >= 10){
				this.center.setLocation(this.center.x, this.center.y + 10);
				this.moveTimer = 0;
				if (this.center.y >= this.window.getWindowHeight()){
					// set back to top
					this.center.y = 0;
				}
				
				this.erase();
				this.draw();
			} else {
				moveTimer++;
			}	
		}
	}

	/**
	 * Display this Alien in the graphics window
	 */
	protected void draw() {
		// pick the color (according to the number of lives left)
		Color color;
        switch (lives){
            case 3:
                color = Alien.GREEN; //green
                break;
            case 2:
                color = Alien.YELLOW; //yellow
                break;
            default:
                color = Alien.RED; //red
                break;

        }

		// Graphics elements for the display of this Alien
		// A circle on top of an X
		this.shapes = new Shape[3];
		this.shapes[0] = new Line(this.center.x - 2 * RADIUS, this.center.y - 2
				* RADIUS, this.center.x + 2 * RADIUS, this.center.y + 2
				* RADIUS, color);
		this.shapes[1] = new Line(this.center.x + 2 * RADIUS, this.center.y - 2
				* RADIUS, this.center.x - 2 * RADIUS, this.center.y + 2
				* RADIUS, color);
		this.shapes[2] = new Oval(this.center.x - RADIUS, this.center.y
				- RADIUS, 2 * RADIUS, 2 * RADIUS, color, true);

		for (int i = 0; i < this.shapes.length; i++)
			this.window.add(this.shapes[i]);

		// Bounding box of this Alien
		this.boundingBox = new Rectangle(this.center.x - 2 * RADIUS,
				this.center.y - 2 * RADIUS, 4 * RADIUS, 4 * RADIUS);

		this.window.doRepaint();
	}
}
