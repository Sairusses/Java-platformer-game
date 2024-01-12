package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.*;
import utils.Load;

public class Game implements Runnable{
	private Thread thread;
    private Panel panel;
    private final int setFPS = 120;
    private final int setUPS = 120;
    
    private int replay = 0;
    private boolean game_over = false;
    private Enemies enemies;
    private Player player;
    
    public Game(){
    	initClasses();
        panel = new Panel(this);
        new Window(panel);
        panel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
    	player = new Player(0,496,150,121, enemies);
		enemies = new Enemies(player);
		player.setEnemies(enemies);
	}

	private void startGameLoop(){
        thread = new Thread(this);
        thread.start();
    }
	
    public void update() {
    	enemies.update();
    	if(player.isAlive)
        	player.update();
    	else {
    		if(replay <= 50) {
    			player.update();
    			replay++;
    			game_over = true;
    		}
    		return;
    	}
    }
    public void render(Graphics g) {
    	renderBackground(g);
    	player.render(g);
    	enemies.render(g);
    	if(game_over) GameOver(g);
    }
    private void GameOver(Graphics g) {
    	BufferedImage img = Load.GetSprite(Load.Game_Over);
		g.drawImage(img, 100, 0, 500, 300, null);
	}

	public void renderBackground(Graphics g) {
    	BufferedImage img = Load.GetSprite(Load.Background);
		g.drawImage(img, 0, 0, 1170, 630, null);
    }
    

    @Override
    public void run() {
        double timePerFrame = 1000000000/setFPS;
        double timePerUpdate = 1000000000/setUPS;
        
        long lastTime = System.nanoTime();
        
        int frames = 0;
        int updates = 0;
        
        long lastCheck = System.currentTimeMillis();
        
        double deltaU = 0;
        double deltaF = 0;
        while(true){
            long currentTime = System.nanoTime();
            deltaU += (currentTime - lastTime) / timePerUpdate;
            deltaF += (currentTime - lastTime) / timePerFrame;
            lastTime = currentTime;
            
            if(deltaU >= 1) {
            	update();
            	updates++;
            	deltaU--;
            }
            if(deltaF >= 1) {
            	panel.repaint();
                frames++; 
                deltaF--;
            }
            
            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS = " + frames + " | UPS = " + updates);
                frames = 0;
                updates = 0;
            }

        }
    }
    public void windowFocusLost() {
    	player.resetDirections();
    }
	public Player getPlayer() {
		return player;
	}
}