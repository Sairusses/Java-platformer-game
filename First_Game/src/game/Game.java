package game;

import java.awt.Graphics;

import entities.*;
import levels.LevelManager;

public class Game implements Runnable{
	private Thread thread;
    private Panel panel;
    private final int setFPS = 120;
    private final int setUPS = 120;
    private LevelManager levelManager;
    
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
		levelManager = new LevelManager(this);
	}

	private void startGameLoop(){
        thread = new Thread(this);
        thread.start();
    }
	
    public void update() {
    	player.update();
    	enemies.update();
    	levelManager.update();
    }
    public void render(Graphics g) {
    	levelManager.render(g);
    	player.render(g);
    	enemies.render(g);
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