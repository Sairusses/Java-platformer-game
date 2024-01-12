package Inputs;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import game.*;
public class Keyboard implements KeyListener{
	private Panel panel;
    public Keyboard(Panel panel){
    	
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	switch (e.getKeyCode()){
    	case KeyEvent.VK_W:
    		panel.getGame().getPlayer().setJumping(true);
        	break;
        case KeyEvent.VK_A:
        	panel.getGame().getPlayer().setLeft(true);
        	break;
        case KeyEvent.VK_D:
        	panel.getGame().getPlayer().setRight(true);
        	break;
        case KeyEvent.VK_S:
        	panel.getGame().getPlayer().setDown(true);
        	break;
        case KeyEvent.VK_SPACE:
        	panel.getGame().getPlayer().setAttacking(true);
        	break;
    	}
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	switch (e.getKeyCode()){
    	case KeyEvent.VK_W:
    		panel.getGame().getPlayer().setJumping(false);
        	break;
        case KeyEvent.VK_A:
        	panel.getGame().getPlayer().setLeft(false);
        	break;
        case KeyEvent.VK_D:
        	panel.getGame().getPlayer().setRight(false);
        	break;
        case KeyEvent.VK_S:
        	panel.getGame().getPlayer().setDown(false);
        	break;
        case KeyEvent.VK_SPACE:
        	panel.getGame().getPlayer().setAttacking(false);
        	break;
    	}
    }
    
}

