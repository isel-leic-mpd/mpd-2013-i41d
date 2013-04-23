package app.test.model;

public class Account {

	public final long balance;
	public final int nr;
	private String holder;
	public final String studentNr;
	
	
	public Account() {
		this.balance = 0;
		this.nr = 0;
		this.holder = null;
		this.studentNr = null;
	}
	
	public Account(long balance, int nr, String holder, String studentNr) {
		this.balance = balance;
		this.nr = nr;
		this.holder = holder;
		this.studentNr = studentNr;
	}

	public String getHolderName() {
		return holder;
	}

	public void setHolderName(String holder) {
		this.holder = holder;
	}
	
}
