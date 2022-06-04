package model;

import enums.Color;

public class BasicFigure extends PlayableFigure {
	public BasicFigure(Color color) {
		super(color);
		this.movementQuotient = 1;
	}
	

}
