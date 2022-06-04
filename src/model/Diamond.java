package model;

public class Diamond {
	public static int count = 0;
	public static int MAX_NUM_OF_DIAMONDS = 7;
	public Diamond() {
		count++;
	}
	@Override
	public String toString() {
		return "<>";
	}
}
