package model;

import interfaces.FlyingInterface;
import interfaces.ImmuneToHolesInterface;
import enums.Color;

public class FlyingFigure extends PlayableFigure implements ImmuneToHolesInterface, FlyingInterface {
	boolean isAboveHole;
	
	public FlyingFigure(Color color) {
		super(color);
		this.movementQuotient = 1;
		this.isAboveHole = false;
	}
}
