package model;

import enums.Color;

public class SuperFastFigure extends PlayableFigure {
	public SuperFastFigure(String owner, Color color, int id) {
		super(owner, color, id);
		this.movementQuotient = 2;
	}

	@Override
	public String toString() {
		return "SFF";
	}
	
	public String getType() {
		return "SuperFastFigure";
	}
}
