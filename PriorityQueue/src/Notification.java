import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Notification extends JFrame
{
	public Notification(String message)
	{
		getContentPane().setLayout(null);
		
		JLabel errorMsg = new JLabel(message);
		errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		errorMsg.setBounds(42, 11, 200, 35);
		getContentPane().add(errorMsg);
		setTitle("Notice");
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(105, 70, 70, 23);
		getContentPane().add(btnNewButton);
		setSize(300, 140);
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
