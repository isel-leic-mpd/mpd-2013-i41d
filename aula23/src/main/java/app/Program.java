package app;

import javax.swing.JOptionPane;

public class Program {
	public static void main(String[] args) {
		final Alarm a  = new Alarm();
		a.addListener(new AlarmListener() {
			@Override
			public void alert() {
				System.out.println("Ole Ole!!!!!");
			}
		});
		a.addListener(new AlarmListener() {
			@Override
			public void alert() {
				JOptionPane.showMessageDialog(null, "Viva Viva!!!");
				a.removeListener(this);
			}
		});
		a.addEvent(System.currentTimeMillis() + 2000);
		a.addEvent(System.currentTimeMillis() + 5000);
		a.addEvent(System.currentTimeMillis() + 6000);
		a.start();
	}
}
