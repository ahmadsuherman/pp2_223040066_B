package PERTEMUAN1;

import javax.swing.*;

public class HelloWorldSwing {
	public static void createAndShowGUI() {
		JFrame frame = new JFrame("My First Project With Java Swing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("Hello, Ahmad Suherman");
		frame.getContentPane().add(label);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] arg) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
