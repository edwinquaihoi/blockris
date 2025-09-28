package au.com.blockris;

import javax.swing.JFrame;

public class App {
	
	public static void main(String[] args) {
		
		System.out.println("App::main");
		
		JFrame window = new JFrame("Blockris");
		
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		GamePanel gp = new GamePanel();
		
		window.add(gp);
		window.validate();
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		//gp.launch();
	}
	
}