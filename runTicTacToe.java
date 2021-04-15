import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class runTicTacToe {
	public static void main(String[] args) {
		new TicTacToe();
	}
}

class TicTacToe implements ActionListener {
	private JFrame frame = new JFrame();
	private JPanel settings = new JPanel();
	private JPanel titlePanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JLabel textfield = new JLabel();
	private JButton[] buttons = new JButton[9];
	private boolean xPlayerTurn;
	private JButton reset = new JButton("RESET GAME");
	public TicTacToe() {
		setIcon();
		frame.setTitle("Tic-Tac-Toe");
		frame.setSize(600, 500);
		frame.getContentPane().setBackground(new Color(123, 50, 250));
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);
		textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
		textfield.setBackground(new Color(0, 0,10));
		textfield.setForeground(new Color(123, 50, 250));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBounds(0, 0, 800, 100);
		titlePanel.add(textfield);
		buttonPanel.setLayout(new GridLayout(3, 3));
		buttonPanel.setBackground(new Color(123, 50, 250));
		
		settings.setLayout(new BorderLayout());
		settings.add(reset);
		settings.setBackground(new Color(123, 50, 250));
		
		for(int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			buttonPanel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
			buttons[i].setBackground(Color.WHITE);
		}
		reset.addActionListener(e-> reset(e));
		frame.add(titlePanel, BorderLayout.NORTH);
		frame.add(buttonPanel);
		frame.add(settings, BorderLayout.SOUTH);
		
		determineTurn();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0;i < 9; i++) {
			if(e.getSource()==buttons[i]) {
				if(xPlayerTurn) {
					if(buttons[i].getText().isBlank()) {
						buttons[i].setForeground(new Color(0,0,0));
						buttons[i].setText("X");
						xPlayerTurn=false;
						textfield.setText("O's turn");
						checkWin();
					}
				} else {
					if(buttons[i].getText().isBlank()) {
						buttons[i].setForeground(new Color(0,0,0));
						buttons[i].setText("O");
						xPlayerTurn=true;
						textfield.setText("X's turn");
						checkWin();
					}
				}
			}			
		}
	}
	private void determineTurn() {
		// Delays telling player whose turn it is to display title
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Determines X or O's turn through random comparisons
		if(Math.random()*11 > 5) {
			xPlayerTurn = true;
			textfield.setText("X's turn");
		} else {
			xPlayerTurn = false;
			textfield.setText("O's turn");
		}
	}
	private void setIcon() {
		// Allows for JPane to use an icon from the internet URL 
		try {
			URL url = new URL("https://miro.medium.com/max/512/1*Syzc_BbO0QHTx74NLHcpiQ.png");
			Image image = ImageIO.read(url);
			frame.setIconImage(image);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void checkWin() {
		// Method checks for any possible pattern win and invokes declareWinner if true, ending the game
		if (
				
			(buttons[0].getText().equals(buttons[1].getText())) &&
			(buttons[1].getText().equals(buttons[2].getText())) && !buttons[0].getText().isBlank()) {
			
					declareWinner(0 ,1 ,2 , buttons[0].getText().charAt(0));
		}else if(
				
			(buttons[3].getText().equals(buttons[4].getText())) &&
			(buttons[4].getText().equals(buttons[5].getText())) && !buttons[3].getText().isBlank()) {
			
					declareWinner(3 ,4 ,5 , buttons[3].getText().charAt(0));
		}else if(

			(buttons[6].getText().equals(buttons[7].getText())) &&
			(buttons[7].getText().equals(buttons[8].getText())) && !buttons[6].getText().isBlank()) {
			
					declareWinner(6 ,7 ,8 , buttons[6].getText().charAt(0));
		}else if(
				
				(buttons[0].getText().equals(buttons[3].getText())) &&
				(buttons[3].getText().equals(buttons[6].getText())) && !buttons[0].getText().isBlank()) {
				
					declareWinner(0 ,3 ,6 , buttons[0].getText().charAt(0));
		}else if(
				
				(buttons[1].getText().equals(buttons[4].getText())) &&
				(buttons[4].getText().equals(buttons[7].getText())) && !buttons[1].getText().isBlank()) {
				
					declareWinner(1 ,4 ,7 , buttons[1].getText().charAt(0));
		}else if(
				
				(buttons[2].getText().equals(buttons[5].getText())) &&
				(buttons[5].getText().equals(buttons[8].getText())) && !buttons[2].getText().isBlank()) {
				
					declareWinner(2 ,5,8 , buttons[2].getText().charAt(0));
		}else if(
				
				(buttons[0].getText().equals(buttons[4].getText())) &&
				(buttons[4].getText().equals(buttons[8].getText())) && !buttons[0].getText().isBlank()) {
				
					declareWinner(0 ,4 ,8 , buttons[0].getText().charAt(0));
		}else if(
				
				(buttons[2].getText().equals(buttons[4].getText())) &&
				(buttons[4].getText().equals(buttons[6].getText())) && !buttons[2].getText().isBlank()) {
				
					declareWinner(2 ,4 ,6 , buttons[2].getText().charAt(0));
		}
	}
	
	public void declareWinner(int a,int b,int c, char player) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText(String.format("%c wins!", player));
		
	}
	public void reset(ActionEvent e) {
		
		for(int i = 0; i < 9; i++) {
			buttons[i].setEnabled(true);
			buttons[i].setText("");
			buttons[i].resetKeyboardActions();
			buttons[i].setBackground(Color.WHITE);
		}
		determineTurn();
	}
}
