package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Game;
import utils.Load;

public class LevelManager {
	private Game game;
	
	public LevelManager(Game game) {
		this.game = game;
		
	}
	public void render(Graphics g) {
		BufferedImage img = Load.GetSprite(Load.Background);
		g.drawImage(img, 0, 0, 1170, 630, null);
	}
	public void update() {
		
	}
	
}
