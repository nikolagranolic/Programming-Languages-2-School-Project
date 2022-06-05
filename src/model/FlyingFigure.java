package model;

import interfaces.FlyingInterface;
import interfaces.ImmuneToHolesInterface;
import enums.Color;

public class FlyingFigure extends PlayableFigure implements ImmuneToHolesInterface, FlyingInterface {
	
	public FlyingFigure(String owner, Color color) {
		super(owner, color);
		this.movementQuotient = 1;
	}
	
	@Override
	public String toString() {
		return "FF";
	}
}
