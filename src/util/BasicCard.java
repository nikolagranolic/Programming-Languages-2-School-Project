package util;

public class BasicCard extends Card {
	int number;
	
	public BasicCard(String src, int n) {
		super(src);
		this.number = n;
	}
	
	public int getNumber() {
		return number;
	}
}
