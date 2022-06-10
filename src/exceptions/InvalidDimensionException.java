package exceptions;

public class InvalidDimensionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidDimensionException() {
		super("Zadane dimenzije nisu u postavljenim granicama!");
	}
	public InvalidDimensionException(String msg) {
		super(msg);
	}
}