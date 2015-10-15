import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.*;

public class WindowInterface{
	
	private static JButton startStop = new JButton("Start!"); 
	private static JButton resetPosition = new JButton("Reset Position");
	private static JTextArea engineOutput = new JTextArea();
	private static DefaultCaret caret = (DefaultCaret)engineOutput.getCaret();
	private static JLabel score = new JLabel("score: ");
	private static JLabel whiteTime = new JLabel("white time: ");
	private static JLabel blackTime = new JLabel("black time: ");
	
	public static void showWindow(){

		DefaultCaret caret = (DefaultCaret)engineOutput.getCaret();
		caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(startStop);
		panel.add(resetPosition);
		panel.add(score);
		panel.add(whiteTime);
		panel.add(blackTime);
		panel.add(engineOutput);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("ChessCheater v0.2 dev build");
		frame.setSize(300,600);
		frame.add(panel);
		frame.setSize(300,600);
		frame.setResizable(false);
		frame.setVisible(true);
		
		engineOutput.append("testLine 1\n");
		engineOutput.append("testLine 2\n");
		engineOutput.append("testLine 3\n");
		engineOutput.append("testLine 4\n");
		engineOutput.append("testLine 5\n");
		engineOutput.append("testLine 6\n");
		engineOutput.append("testLine 1\n");
		engineOutput.append("testLine 2\n");
		engineOutput.append("testLine 3\n");
		engineOutput.append("testLine 4\n");
		engineOutput.append("testLine 5\n");
		engineOutput.append("testLine 6\n");
		engineOutput.append("testLine 1\n");
		engineOutput.append("testLine 2\n");
		engineOutput.append("testLine 3\n");
		engineOutput.append("testLine 4\n");
		engineOutput.append("testLine 5\n");
		engineOutput.append("testLine 6\n");
		engineOutput.append("testLine 1\n");
		engineOutput.append("testLine 2\n");
		engineOutput.append("testLine 3\n");
		engineOutput.append("testLine 4\n");
		engineOutput.append("testLine 5\n");
		engineOutput.append("testLine 6\n");
		engineOutput.append("testLine 1\n");
		engineOutput.append("testLine 2\n");
		engineOutput.append("testLine 3\n");
		engineOutput.append("testLine 4\n");
		engineOutput.append("testLine 5\n");
		engineOutput.append("testLine 6\n");
		engineOutput.append("testLine 1\n");
		engineOutput.append("testLine 2\n");
		engineOutput.append("testLine 3\n");
		engineOutput.append("testLine 4\n");
		engineOutput.append("testLine 5\n");
		engineOutput.append("testLine 6\n");
	}
	
	public static void main(String[] args){
		showWindow();
	}
	}
	
