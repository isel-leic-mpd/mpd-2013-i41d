package app;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


class IconAdapter extends JComponent{
	private Icon _icon; 
	public IconAdapter(Icon i){ 
		_icon = i; 
	} 
	protected void paintComponent(Graphics g) { 
		_icon.paintIcon(this, g, 0, 0); 
	} 
	public int getWidth() { 
		return _icon.getIconWidth(); 
	} 
	public int getHeight() { 
		return _icon.getIconHeight(); 
	} 
	public Dimension getPreferredSize() { 
		return new Dimension(getWidth(), getHeight()); 
	} 
}

public class Program {
	public static void main(String[] args) {
		
		JFrame frm = new JFrame();
		frm.setLayout(new FlowLayout());
		// frm.setLayout(new GridLayout(2,1)); // STRATEGY design pattern
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lb = new JLabel("Ola:");
		lb.setBorder(BorderFactory.createBevelBorder(1)); // STRATEGY !!!! Mas nao segue o PD Abstract Factory 
		frm.add(lb);
		frm.add(new JButton("Click"));

		JScrollPane txt = new JScrollPane(new JTextArea()); // DECORATOR !!! 
		txt.setPreferredSize(new Dimension(300, 100));
		frm.add(txt);
		frm.add(new IconAdapter( new ImageIcon("wave.jpg"))); // ADAPTER ... patterm

		frm.pack();
		frm.setVisible(true);
	}
}
