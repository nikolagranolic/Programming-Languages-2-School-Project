package model;

import enums.Color;

public class BasicFigure extends PlayableFigure {
	public BasicFigure(String owner, Color color, int id) {
		super(owner, color, id);
		this.movementQuotient = 1;
	}
	
	@Override
	public String toString() {
		return "BF";
	}
}
