package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Load {
	public static final String PlayerSprite = "adventurer-v1.5-Sheet.png";
	public static final String Background = "background.gif";
	
	public static final String Mushroom_Idle = "Mushroom_Idle.png";
	public static final String Mushroom_Run = "Mushroom_Run.png";
	public static final String Mushroom_Damage = "Mushroom_Take Hit.png";
	public static final String Mushroom_Attack = "Mushroom_Attack.png";
	public static final String Mushroom_Death = "Mushroom_Death.png";
	
	public static final String Eye_Idle = "Eye_Idle.png";
	public static final String Eye_Run = "Eye_Idle.png";
	public static final String Eye_Damage = "Eye_Take Hit.png";
	public static final String Eye_Attack = "Eye_Attack.png";
	public static final String Eye_Death = "Eye_Death.png";
	
	public static final String Game_Over = "Game_Over.png";
	public static BufferedImage GetSprite(String fileName) {
		BufferedImage img = null;
		InputStream is = Load.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	try {
        		is.close();
        	} catch(IOException e){
        		e.printStackTrace();
        	}
        }
        return img;
	}
	
	
}
