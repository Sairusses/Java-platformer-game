package entities;

import static utils.Constants.Directions.*;
import static utils.Constants.EnemyConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Enemy extends Entity{
	protected BufferedImage[] idle;
	protected BufferedImage[] run;
	protected BufferedImage[] attack;
	protected BufferedImage[] damage;
	protected BufferedImage[] dead;
	
	protected int type, action;
	protected boolean inAir = false;
	protected float fallSpeed = 0f;
	protected float gravity = 0.09f;
	protected int direction = LEFT;
	protected int flipX, flipW = 1;
	
	
	private Rectangle healthbar;
	private int healthBarX = (int)hitbox.x - 30;
    private int healthBarY = (int)hitbox.y - 15;
    private int healthBarWidth = 50;
    private int healthBarHeight = 7;
    private int maxHealth = 50;
    private int health = maxHealth;
    private int healthWidth = healthBarWidth;
    
    protected boolean attackChecked;
    protected boolean alive = true;
    
	public Enemy(float x, float y, int width, int height, int type) {
		super(x, y, width, height);
		this.type = type;
		healthbar();
	}
	
	protected void updateAnimation() {
    	tick++;
    	if(tick >= speed) {
    		tick = 0;
    		index++;
    	}if(index == GetSpriteAmount(MUSHROOM, action)) {
    		index = 0;
    		if(action == ATTACK) action = IDLE;
    		else if(action == DAMAGE) action = IDLE;
    		else if(action == DEAD) action = DEAD;
    	}
    }

	
	private void healthbar() {
		healthbar = new Rectangle(healthBarX, healthBarY, healthBarWidth, healthBarHeight);
	}
	protected void drawHealthbar(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(healthbar.x, healthbar.y, healthWidth, healthbar.height);
	}
	public void updateHealthbar() {
		healthWidth = (int) ((health / (float)maxHealth) * healthbar.width);
		healthbar.x = hitbox.x - 5;
		healthbar.y = hitbox.y - 15;
	}
	
	public void hurt(int damage) {
		health -= damage;
		if(health <= 0) newAction(DEAD);
		else newAction(DAMAGE);
	}
	public boolean isAlive() {
		return alive;
	}
	
	protected void newAction(int action) {
		tick = 0;
		index = 0;
		this.action = action;
	}
	public int getIndex() {
		return index;
	}
	public int getAction() {
		return action;
	}
	
}
