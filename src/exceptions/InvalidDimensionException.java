package exceptions;

public class InvalidDimensionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidDimensionException() {
		super("Zadane dimenzije nisu u postavljenim granicama!");
		System.out.println("Dimenzija kvadratne matrice mora biti izmedju 7 i 10!");
	}
	public InvalidDimensionException(String msg) {
		super(msg);
	}
}
