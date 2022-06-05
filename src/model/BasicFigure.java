package model;

import enums.Color;

public class BasicFigure extends PlayableFigure {
	public BasicFigure(String owner, Color color) {
		super(owner, color);
		this.movementQuotient = 1;
	}
	
	@Override
	public String toString() {
		return "BF";
	}
}
