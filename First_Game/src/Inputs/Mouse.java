package Inputs;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import game.Panel;
public class Mouse implements MouseInputListener{
	private Panel panel;
    public Mouse(Panel pan){
        this.panel = pan;
    }
     @Override
    public void mouseClicked(MouseEvent e) {
    	 
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)panel.getGame().getPlayer().setAttacking4(true);
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    	if(e.getButton() == MouseEvent.BUTTON1)panel.getGame().getPlayer().setAttacking4(false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
    
   
}

