package entities;

import static utils.Constants.PlayerConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import utils.Load;

public class Player extends Entity{
	private BufferedImage[][] animation;
    private int playerAction = JUMP;
    private boolean moving = false, attacking = false, attacking4 = false, jumping = false, hurt = false;
    private boolean left, jump, right, down;
    private float playerSpeed = 2f;
    private float fallSpeed = 0f;
    private float jumpSpeed = -5f;
    private float gravity = 0.09f;
    
    private Rectangle attackRange;
    private Rectangle healthbar;
    private int healthBarX = (int)hitbox.x - 30;
    private int healthBarY = (int)hitbox.y - 15;
    private int healthBarWidth = 80;
    private int healthBarHeight = 7;
    private int maxHealth = 100;
    public int health = maxHealth;
    private int healthWidth = healthBarWidth;
    
    private int flipX;
    private int flipW = 1;
    
    Enemies enemies;
    private boolean attacked;
    public boolean isAlive = true;
    
	public Player(float x, float y, int width, int height, Enemies enemies) {
		super(x, y, width-110, height - 25);
		this.enemies = enemies;
		healthbar();
		loadAnimations();
		attackRange();
	}
	public void setEnemies(Enemies enemies) {
		this.enemies = enemies;
	}
	
	
	
	private void attackRange() {
		attackRange = new Rectangle(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
	}
	private void drawAttackRange(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(attackRange.x, attackRange.y, attackRange.width, attackRange.height);
	}
	private void updateAttackRange() {
		if(right && !left) {
			attackRange.x = hitbox.x + hitbox.width + 10;
		}
		if(left && !right) {
			attackRange.x = hitbox.x - hitbox.width - 10;
		}
		attackRange.y = hitbox.y;
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
		healthbar.x = hitbox.x - 20;
		healthbar.y = hitbox.y - 15;
	}
	
	public void damage(int damage) {
		health -= damage;
		if(health <= 0) {
			health = 0;
			isAlive = false;
		}
		hurt = true;
	}
	

	public void update() {
		updateHitbox();
		updateAttackRange();
		updatePos();
		if(attacking)attacked();
		updateAnimation();
		updateHealthbar();
        setAnimation();
	}
	private void attacked() {
		if(attacked || index != 0) return;
		for (Mushroom mushroom : enemies.mushrooms) {
            if (attackRange.intersects(mushroom.getHitbox())) {
                mushroom.hurt(13);
            }
		}
		for (Eye eye : enemies.eyes) {
            if (attackRange.intersects(eye.getHitbox())) {
                eye.hurt(13);
            }
		}
		attacked = true;
	}
	

	public void render(Graphics g) {
		g.drawImage(animation[playerAction][index],(int)x - 65 + flipX, (int)y - 22, 150 * flipW, 121, null);
//		drawHitbox(g);
		drawHealthbar(g);
//		drawAttackRange(g);
	}
    public void updateAnimation() {
    	tick++;
    	if(tick >= speed) {
    		tick = 0;
    		index++;
    	}if(index == GetSpriteAmount(playerAction)) {
    		index = 0;
    		attacked = false;
    		hurt = false;
    	}
    }
    
    private void setAnimation() {
    	int start = playerAction;
    	
    	if(moving)playerAction = RUN;
    	else playerAction = IDLE;
    	if(jumping) {
    		if(fallSpeed < 0)playerAction = JUMP;
    		else playerAction = FALL;
    	}
    	if(attacking)playerAction = ATK1;
    	if(attacking4)playerAction = ATK4;
    	if(hurt) {
    		playerAction = HURT;
    	}
    	if(!isAlive)playerAction = DEAD;
    	
    	if(start != playerAction) {
    		tick = 0;
    		index = 0;
    	}
	}
    private void updatePos() {
    	moving = false;
    	if(jump)Jump();
    	if(!left && !right && !jumping) return;
    	
    	float X = x;
    	float Y = y;
    	
    	if(left && !right ) {
    		x -= playerSpeed;
    		flipX = width + 130;
    		flipW = -1;
    		moving = true;
    	}if(right && !left) {
    		x += playerSpeed;
    		flipX = 0;
    		flipW = 1;
    		moving = true;
    	}
    	
    	if (jumping) {
            if (!isWall()) {
                y += fallSpeed;
                fallSpeed += gravity;
            }
        }
    	
    	if(hitbox.intersects(floor)) {
    		y = Y - 1;
    		jumping = false;
    	}
    	if(hitbox.intersects(border1)) x = X + 1;
    	if(hitbox.intersects(border2)) x = X - 1;
    }
    
    private void Jump() {
		if(jumping) return;
		fallSpeed = jumpSpeed;
		jumping = true;
	}
	private void loadAnimations() {
            BufferedImage img = Load.GetSprite(Load.PlayerSprite);
            animation = new BufferedImage[8][9];
        	for(int j = 0; j < 8; j++)
        		for(int i = 0; i < 9; i++)
        			animation[j][i] = img.getSubimage(i*50, j*37, 50, 37);
    	
	}
	public void resetDirections() {
		left=false;
		jump=false;
		right=false;
		down=false;
	}
	public void setAttacking4(boolean attacking4) {
		this.attacking4 = attacking4;
	}
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isJumping() {
		return jump;
	}
	public void setJumping(boolean jump) {
		this.jump = jump;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	

}
