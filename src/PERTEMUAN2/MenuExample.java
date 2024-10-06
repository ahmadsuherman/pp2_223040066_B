package PERTEMUAN2;

import javax.swing.*;
import java.awt.event.*;

public class MenuExample extends JFrame {

    private JTextField textFieldInput;
    private JTextArea textAreaOutput;
    
    public MenuExample() {
        this.setTitle("Menu Example with Reset and Exit");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        
        JMenu menu = new JMenu("Menu");
        
        JMenuItem menuReset = new JMenuItem("Reset");
        JMenuItem menuExit = new JMenuItem("Exit");
        
        menuReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        menuExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });

        menu.add(menuReset);
        menu.add(menuExit);

        menuBar.add(menu);

        this.setJMenuBar(menuBar);

        JLabel labelInput = new JLabel("Input:");
        labelInput.setBounds(20, 20, 100, 30);
        this.add(labelInput);

        textFieldInput = new JTextField();
        textFieldInput.setBounds(100, 20, 200, 30);
        this.add(textFieldInput);

        JButton buttonSubmit = new JButton("Submit");
        buttonSubmit.setBounds(100, 70, 100, 30);
        this.add(buttonSubmit);

        buttonSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputText = textFieldInput.getText();
                if (!inputText.isEmpty()) {
                    textAreaOutput.setText("Output: " + inputText);
                } else {
                    textAreaOutput.setText("No input provided!");
                }
            }
        });

        textAreaOutput = new JTextArea();
        textAreaOutput.setBounds(20, 120, 350, 100);
        textAreaOutput.setEditable(false);
        this.add(textAreaOutput);
    }

    private void resetForm() {
        textFieldInput.setText("");    
        textAreaOutput.setText("");  
    }

    
    public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MenuExample h = new MenuExample();
				h.setVisible(true);
			}
		});
	}
}
