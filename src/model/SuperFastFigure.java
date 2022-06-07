package model;

import enums.Color;
import interfaces.SuperFastInterface;

public class SuperFastFigure extends PlayableFigure implements SuperFastInterface {
	public SuperFastFigure(String owner, Color color, int id) {
		super(owner, color, id);
		this.movementQuotient = 2;
	}

	@Override
	public String toString() {
		return "SFF";
	}
}
