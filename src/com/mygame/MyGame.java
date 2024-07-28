package com.mygame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MyGame extends JFrame implements ActionListener {

	JLabel heading, clockLablel;
	Font font = new Font("", Font.BOLD, 40);

	JPanel mainPanel;
	JButton[] btns = new JButton[9];

	// game intance variable
	int gameChance[] = { 2, 2, 2, 2, 2, 2, 2, 2, 2 };
	int activePlayer = 0;

	int wps[][] = {

			{ 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 }, { 2, 4, 6 }

	};
	int winner = 2;
	boolean gameOver = false;

	public MyGame() {
		System.out.println("creatig instance of game ");

		setTitle("My tic tac toe game");
		setSize(850, 850);
		ImageIcon icon = new ImageIcon("src/img/logo tic tac.png");
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createGUI();
		setVisible(true);

	}

	public void createGUI() {

		this.getContentPane().setBackground(Color.decode("#2196f3"));
		this.setLayout(new BorderLayout());

		// north heading
		heading = new JLabel("Tic ta toe");
		// heading.setIcon(new ImageIcon("src/img/logo tic tac.png"));
		heading.setFont(font);
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setForeground(Color.white);
		heading.setHorizontalTextPosition(SwingConstants.CENTER);
		heading.setVerticalTextPosition(SwingConstants.BOTTOM);

		this.add(heading, BorderLayout.NORTH);

		// creating object of JLable for clock

		clockLablel = new JLabel("Clock");
		clockLablel.setFont(font);
		clockLablel.setHorizontalAlignment(SwingConstants.CENTER);
		clockLablel.setForeground(Color.white);
		this.add(clockLablel, BorderLayout.SOUTH);

		Thread t = new Thread() {

			public void run() {

				try {

					while (true) {

						String datetime = new Date().toLocaleString();

						clockLablel.setText(datetime);
						Thread.sleep(1000);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		};
		t.start();

		//// pallnne section
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3, 3));
		for (int i = 1; i <= 9; i++) {
			JButton btn = new JButton();
			// btn.setIcon(new ImageIcon("src/img/O image.png"));
			btn.setBackground(Color.decode("#90caf9"));
			btn.setFont(font);
			mainPanel.add(btn);
			btns[i - 1] = btn;
			btn.addActionListener(this);
			btn.setName(String.valueOf(i - 1));

		}
		this.add(mainPanel, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton currentButton = (JButton) e.getSource();
		String name = currentButton.getName();

		int name1 = Integer.parseInt(name.trim());
		if (gameOver == true) {

			JOptionPane.showMessageDialog(this, "game Already over...");
			return;
		}

		if (gameChance[name1] == 2) {

			if (activePlayer == 1) {

				currentButton.setIcon(new ImageIcon("src/img/X image.png"));
				gameChance[name1] = activePlayer;

				activePlayer = 0;
			} else {

				currentButton.setIcon(new ImageIcon("src/img/O image.png"));
				gameChance[name1] = activePlayer;
				activePlayer = 1;
			}

			for (int[] temp : wps) {

				if ((gameChance[temp[0]] == gameChance[temp[1]]) && (gameChance[temp[1]] == gameChance[temp[2]])
						&& gameChance[temp[2]] != 2) {

					winner = gameChance[temp[0]];
					gameOver = true;
					JOptionPane.showMessageDialog(null, "player" + winner + "has won the match..");
					int i = JOptionPane.showConfirmDialog(this, "do you want to play  more ..??");
					System.out.println(i);
					if (i == 0) {

						this.setVisible(false);
						new MyGame();
					} else if (i == 1) {
						System.exit(786);

					} else {

					}
					System.out.println(i);
					break;
				}

			}
			// ....
			int c = 0;

			for (int x : gameChance) {

				if (x == 2) {

					c++;
					break;
				}
			}
			if (c == 0 && gameOver == false) {

				JOptionPane.showMessageDialog(null, "Its draw....");
				int i = JOptionPane.showConfirmDialog(this, "play more..??");
				if (i == 0) {

					this.setVisible(false);
					new MyGame();
				} else if (i == 1) {
					System.exit(786);

				} else {

				}

				gameOver = true;

			}

		} else {

			JOptionPane.showMessageDialog(this, "position already occupied");
		}

	}

}
