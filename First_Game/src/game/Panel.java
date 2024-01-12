package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import Inputs.*;

public class Panel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private Mouse mouseInputs;
    private Game game;
    public Panel(Game game){
    	this.game = game;
        mouseInputs = new Mouse(this);

        setPanelSize();
        addKeyListener(new Keyboard(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    
    private void setPanelSize() {
        Dimension size = new Dimension(1170, 630);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        setBackground(Color.gray);
    }
    
    public void updateGame() {
    	
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }
    public Game getGame() {
    	return game;
    }

}
