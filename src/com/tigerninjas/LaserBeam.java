package com.tigerninjas;

import java.awt.Color;
import java.awt.Point;

import uwcse.graphics.GWindow;
import uwcse.graphics.Rectangle;
import uwcse.graphics.Shape;

public class LaserBeam extends MovingObject {

	public LaserBeam(GWindow window, Point center) {
		super(window, center);
		this.direction = MovingObject.UP;
		this.draw();
	}

	
	public void move() {
		// TODO Auto-generated method stub
		this.erase();
		this.center.y -= 5;
		this.draw();
		
	}

	
	protected void draw() {
		// TODO Auto-generated method stub
		this.shapes = new Shape[1];
		Rectangle beam = new Rectangle(this.center.x - 2, this.center.y - 5, 4, 10, Color.red, true);
		this.shapes[0] = beam;
		this.window.add(this.shapes[0]);
		
	}

}
