package model;

import enums.Color;
import interfaces.SuperFastInterface;

public class SuperFastFigure extends PlayableFigure implements SuperFastInterface {
	public SuperFastFigure(Color color) {
		super(color);
		this.movementQuotient = 2;
	}

}
