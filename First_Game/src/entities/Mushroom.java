package entities;
import static utils.Constants.EnemyConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static utils.Constants.Directions.*;
import utils.Load;

public class Mushroom extends Enemy{
	
	private BufferedImage[][] mushroom = new BufferedImage[5][9];
	private Player player;
	
	private Rectangle attackRange;
    private int range = 50;
    public int mushroomDamage = 25;
	public Mushroom(float x, float y, Player player) {
		super(x, y, 40, 70, MUSHROOM);
		this.player = player;
		attackRange();
		loadImage();
	}
	
	protected void attackRange() {
		attackRange = new Rectangle(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
	}
	protected void drawAttackRange(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(attackRange.x, attackRange.y, attackRange.width, attackRange.height);
	}
	protected void updateAttackRange() {
		if(direction == LEFT) {
			attackRange.x = hitbox.x - hitbox.width;
		}
		if(direction == RIGHT) {
			attackRange.x = hitbox.x + hitbox.width;
		}
		attackRange.y = hitbox.y;
	}
	
	private void loadImage() {
		idle = new BufferedImage[4];
		BufferedImage img = Load.GetSprite(Load.Mushroom_Idle);
		for(int x = 0; x < 4; x++) idle[x] = img.getSubimage(x * 150, 0, 150, 150);
		mushroom[0] = idle;
		
		run = new BufferedImage[GetSpriteAmount(MUSHROOM, RUN)];
		img = Load.GetSprite(Load.Mushroom_Run);
		for(int x = 0; x < GetSpriteAmount(MUSHROOM, RUN); x++) run[x] = img.getSubimage(x * 150, 0, 150, 150);
		mushroom[1] = run;
		
		attack = new BufferedImage[GetSpriteAmount(MUSHROOM, ATTACK)];
		img = Load.GetSprite(Load.Mushroom_Attack);
		for(int x = 0; x < GetSpriteAmount(MUSHROOM, ATTACK); x++) attack[x] = img.getSubimage(x * 150, 0, 150, 150);
		mushroom[2] = attack;
		
		damage = new BufferedImage[GetSpriteAmount(MUSHROOM, DAMAGE)];
		img = Load.GetSprite(Load.Mushroom_Damage);
		for(int x = 0; x < GetSpriteAmount(MUSHROOM, DAMAGE); x++) damage[x] = img.getSubimage(x * 150, 0, 150, 150);
		mushroom[3] = damage;
		
		dead = new BufferedImage[GetSpriteAmount(MUSHROOM, DEAD)];
		img = Load.GetSprite(Load.Mushroom_Death);
		for(int x = 0; x < GetSpriteAmount(MUSHROOM, DEAD); x++) dead[x] = img.getSubimage(x * 150, 0, 150, 150);
		mushroom[4] = dead;
	}
	public void render(Graphics g) {
		g.drawImage(mushroom[action][index],(int)x -130 + flipX, (int)y-129, 300 * flipW, 300, null);
//		drawHitbox(g);
//		drawAttackRange(g);
		drawHealthbar(g);
	}
	
	public void update() {
		updateMove();
		updateAnimation();
		updateHitbox();
		updateAttackRange();
		updateHealthbar();
	}
	
	private void updateMove() {
		float X = x;
		float Y = y;
		if(!hitbox.intersects(floor)) {
			inAir = true;
		}
		if (inAir) {
            if (!isWall()) {
                y += fallSpeed;
                fallSpeed += gravity;
            }
        }
		if(hitbox.intersects(floor)) {
			y = Y;
			inAir = false;
    	}
		
		if(!inAir) {
			switch(action) {
			case IDLE:
				if(inRange()) newAction(RUN);
				break;
			case RUN:
				float xSpeed = 1f;
				if(inRange()) Turn();
				if(direction == LEFT) {
					flipX = width + 260;
					flipW = -1;
					x -= xSpeed;
				}
				else {
					flipX = 0;
					flipW = 1;
					x += xSpeed;
				}
				if(inAttackRange()) newAction(ATTACK);
				if(!inRange()) newAction(IDLE);
				break;
			case ATTACK:
				if(index == 0) attackChecked = false;
				if(index == 7 && !attackChecked) checkHit(player);
				break;
			case DAMAGE:
				break;
			case DEAD:
				alive = false;
				break;
			}
		}
		
    	if(hitbox.intersects(border1)) x = X + 0.5f;
    	if(hitbox.intersects(border2)) x = X - 0.5f;
	}
	private void checkHit(Player player) {
		if(attackRange.intersects(player.hitbox)) player.damage(25);
		attackChecked = true;
	}
	private boolean inRange() {
		int absValue = (int) Math.abs(player.hitbox.x - hitbox.x);
		return absValue <= range * 5;
	}
	private boolean inAttackRange() {
		int absValue = (int) Math.abs(player.hitbox.x - hitbox.x);
		return absValue <= range;
	}
	private void Turn() {
		if(player.hitbox.x > hitbox.x) direction = RIGHT;
		else direction = LEFT;
	}
}
