package model;

import interfaces.FlyingInterface;
import enums.Color;

public class FlyingFigure extends PlayableFigure implements FlyingInterface {
	public FlyingFigure(String owner, Color color, int id) {
		super(owner, color, id);
		this.movementQuotient = 1;
	}
	
	@Override
	public String toString() {
		return "FF";
	}
	
	public String getType() {
		return "FlyingFigure";
	}
}
