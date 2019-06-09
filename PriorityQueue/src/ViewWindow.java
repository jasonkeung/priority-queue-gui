import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ViewWindow extends JFrame {
	private JPanel controls;
	private Canvas canvas;
	private PriorityQueue myPQ;
	private Graphics g;
	private int lastId;
	private int lastInv;

	private int width, height;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblVarIsEmpty;
	private JButton btnAddLast;

	public ViewWindow(PriorityQueue pq) {
		myPQ = pq;

		setBackground(Color.WHITE);
		setResizable(false);

		width = 1006;
		height = 828;
		initContentPane();

		setVisible(true);
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void initContentPane() {
		canvas = new Canvas();
		canvas.setSize(700, 800);

		canvas.setPreferredSize(new Dimension(700, 800));
		canvas.setMinimumSize(new Dimension(700, 800));
		canvas.setMaximumSize(new Dimension(700, 800));
		canvas.setLocation(0, 0);
		canvas.setFocusable(false);
		getContentPane().add(canvas);

		controls = new JPanel();
		controls.setBounds(700, 0, 300, 800);
		getContentPane().add(controls);
		controls.setLayout(null);

		JButton btnLoadFromFile = new JButton("Load from file20");
		btnLoadFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addFromFile();
				drawQueue();
			}
		});
		btnLoadFromFile.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnLoadFromFile.setBounds(50, 44, 200, 30);
		controls.add(btnLoadFromFile);

		JButton btnRemoveFromList = new JButton("Remove from queue");
		btnRemoveFromList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					myPQ.remove();
					drawQueue();
				} catch (Exception e) {
					Error err = new Error(e.getMessage());
				}
			}
		});
		btnRemoveFromList.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnRemoveFromList.setBounds(50, 85, 200, 30);
		controls.add(btnRemoveFromList);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblNewLabel.setBounds(75, 179, 19, 19);
		controls.add(lblNewLabel);
		
		btnAddLast = new JButton("Add Last Added Item: None");
		btnAddLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myPQ.add(new Item(lastId, lastInv));
				drawQueue();
			}
		});
		btnAddLast.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnAddLast.setBounds(50, 289, 200, 46);
		controls.add(btnAddLast);
		btnAddLast.setEnabled(false);

		JLabel lblInventory = new JLabel("Inventory:");
		lblInventory.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblInventory.setBounds(47, 218, 73, 19);
		controls.add(lblInventory);

		textField = new JTextField();
		textField.setBounds(130, 180, 120, 20);
		controls.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(130, 219, 120, 20);
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (!textField.getText().equals("") && !textField_1.getText().equals("")
							&& validNum(textField.getText()) && validNum(textField_1.getText())) {
						myPQ.add(new Item(Integer.parseInt(textField.getText()),
								Integer.parseInt(textField_1.getText())));
						drawQueue();
						lastId = Integer.parseInt(textField.getText());
						lastInv = Integer.parseInt(textField_1.getText());
						btnAddLast.setText(
								"<html>Add Last-Added Item: <br>" + "[" + lastId + ", " + lastInv + "] </html>");
						btnAddLast.setEnabled(true);
						textField.setText("");
						textField_1.setText("");
						textField.requestFocus();
					} else {
						Error err = new Error("Invalid numerical ID and Inventory.");
						textField.setText("");
						textField_1.setText("");
					}
				} catch (NumberFormatException e) {
					Error err = new Error("ID or Inv value entered is too large.");
				}
			}
		});
		controls.add(textField_1);
		textField_1.setColumns(10);

		JButton btnClearQueue = new JButton("CLEAR QUEUE");
		btnClearQueue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myPQ = new PriorityQueue();
				drawQueue();
			}
		});
		btnClearQueue.setBackground(Color.RED);
		btnClearQueue.setForeground(Color.WHITE);
		btnClearQueue.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnClearQueue.setBounds(75, 725, 150, 40);
		controls.add(btnClearQueue);

		

		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (!textField.getText().equals("") && !textField_1.getText().equals("")
							&& validNum(textField.getText()) && validNum(textField_1.getText())) {
						myPQ.add(new Item(Integer.parseInt(textField.getText()),
								Integer.parseInt(textField_1.getText())));
						drawQueue();
						lastId = Integer.parseInt(textField.getText());
						lastInv = Integer.parseInt(textField_1.getText());
						btnAddLast.setText(
								"<html>Add Last-Added Item: <br>" + "[" + lastId + ", " + lastInv + "] </html>");
						btnAddLast.setEnabled(true);
						textField.setText("");
						textField_1.setText("");
						textField.requestFocus();
					} else {
						Error err = new Error("Invalid numerical ID and Inventory.");
						textField.setText("");
						textField_1.setText("");
					}
				} catch (NumberFormatException e) {
					Error err = new Error("ID or Inv value entered is too large.");
				}
			}
		});
		btnAddItem.setBounds(50, 252, 200, 30);
		btnAddItem.setFont(new Font("Arial Black", Font.PLAIN, 13));
		controls.add(btnAddItem);

		JButton btnWriteToFile = new JButton("<html>Write to desktop - output.txt <br>      and clear queue </html>");
		btnWriteToFile.setForeground(Color.WHITE);
		btnWriteToFile.setBackground(new Color(34, 139, 34));
		btnWriteToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				writeAndClear();
			}
		});
		btnWriteToFile.setVerticalAlignment(SwingConstants.TOP);
		btnWriteToFile.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnWriteToFile.setBounds(70, 649, 160, 65);
		controls.add(btnWriteToFile);

		JLabel lblTextIsEmpty = new JLabel("isEmpty?: ");
		lblTextIsEmpty.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblTextIsEmpty.setBounds(50, 391, 83, 30);
		controls.add(lblTextIsEmpty);

		lblVarIsEmpty = new JLabel("FALSE");
		lblVarIsEmpty.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblVarIsEmpty.setText(myPQ.isEmpty() + "");
		lblVarIsEmpty.setBounds(143, 391, 70, 30);
		controls.add(lblVarIsEmpty);

		JButton btnPeek = new JButton("Peek the queue");
		btnPeek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Notification err = new Notification("Peek returns: " + myPQ.peek());
				} catch (Exception ex) {
					Error er = new Error("The PriorityQueue is empty.");
				}
			}
		});
		btnPeek.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnPeek.setBounds(50, 126, 200, 30);
		controls.add(btnPeek);

		getContentPane().setLayout(null);
	}

	private void addFromFile() {
		int id, inv;
		Scanner inFile;
		inFile = new Scanner(ViewWindow.class.getResourceAsStream("/data/file20.txt"));

		while (inFile.hasNext()) {
			id = inFile.nextInt();

			inv = inFile.nextInt();
			myPQ.add(new Item(id, inv));
		}
		inFile.close();

		lblVarIsEmpty.setText(myPQ.isEmpty() + "");
	}

	private void writeAndClear() {
		int size = myPQ.size();
		try {
			File output = new File(System.getProperty("user.home") + "/Desktop", "output.txt");
			FileWriter fw = new FileWriter(output);
			PrintWriter pw = new PrintWriter(fw);

			for (int i = 1; i < size; i++) {
				Item item = (Item) myPQ.remove();
				pw.println(item.getId() + ", " + item.getInv());
			}

			pw.close();
			Notification notify = new Notification("Output written to desktop.");
			drawQueue();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawQueue() {
		int[] xCoords = new int[myPQ.size() + 1];
		int[] yCoords = new int[myPQ.size() + 1];

		if (canvas.getBufferStrategy() == null)
			canvas.createBufferStrategy(2);

		g = canvas.getBufferStrategy().getDrawGraphics();
		int layerHeight = 700 / (logBase2(myPQ.size() - 1) + 1);
		g.clearRect(0, 0, width, height);

		// draw

		g.setColor(Color.WHITE);
		g.drawRect(0, 0, 700, 800);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", 1, 13));

		for (int i = 1; i < myPQ.size(); i++) {
			Item item = (Item) myPQ.getArrayList().get(i);
			xCoords[i] = (int) (700 / (1 + Math.pow(2, logBase2(i))) * (i + 1 - Math.pow(2, logBase2(i))));
			yCoords[i] = 100 + layerHeight * logBase2(i);

			int extraSpace = 4 - (int) Math.log10(item.getId());
			g.drawString("" + (int) (item.getId()),
					(int) (700 / (1 + Math.pow(2, logBase2(i))) * (i + 1 - Math.pow(2, logBase2(i)))),
					100 + layerHeight * logBase2(i));
		}
		for (int i = myPQ.size() - 1; i > 1; i--) {
			((Graphics2D) g).setStroke(new BasicStroke(5));
			g.drawLine(xCoords[i] + 20, yCoords[i] - 15, xCoords[i / 2] + 20, yCoords[i / 2] + 15);
		}
		// stop drawing

		lblVarIsEmpty.setText(myPQ.isEmpty() + "");
		lblVarIsEmpty.repaint();

		canvas.getBufferStrategy().show();
		g.dispose();
	}

	private int logBase2(int num) {
		return (int) (Math.log(num) / Math.log(2));
	}

	private boolean validNum(String potential) {

		for (int i = 0; i < potential.length(); i++) {
			boolean isNum = false;
			for (int k = 0; k < 10; k++) {
				if (potential.substring(i, i + 1).equalsIgnoreCase(Integer.toString(k))) {
					isNum = true;
				}
			}
			if (!isNum) {
				return false;
			}
		}
		return true;
	}
}
