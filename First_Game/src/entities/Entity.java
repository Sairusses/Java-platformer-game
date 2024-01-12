package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
	public float x,y;
	public int width, height;
	
	protected int index, tick, speed = 14;
	
	protected Rectangle hitbox;
	protected Rectangle floor = new Rectangle(0,592,1200,1);
	protected Rectangle border1 = new Rectangle(0, 0, 1, 630);
	protected Rectangle border2 = new Rectangle(1168, 0, 1, 630);
	
	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		Hitbox();
	}
	private void Hitbox() {
		hitbox = new Rectangle((int) x, (int) y, width, height);
	}
	
	protected void drawHitbox(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
	}
	protected void updateHitbox() {
		hitbox.x = (int)x;
		hitbox.y = (int)y;
	}
	public Rectangle getHitbox() {
		return hitbox;
	}
	public boolean isWall() {
		if(hitbox.intersects(floor)&& hitbox.intersects(border1)&& hitbox.intersects(border2)) return true;
		else return false;
	}
}
