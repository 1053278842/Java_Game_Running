package Game;

import javax.swing.*;

/*
 * ´°Ìו
 */
public class GameFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int 	fw=300,fh=340;
	public GameFrame() {
		setSize(fw+11,fh);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Run!");
//		setLayout(null);
	}
	public static void main(String[] args) {
		GameFrame f=new GameFrame();
		GamePanel p=new GamePanel(f);
//		p.setBounds(0, 0, fw, 800);
		p.startGame();
		p.startEmptyMove();
		f.add(p);
		f.setVisible(true);
	}
}
