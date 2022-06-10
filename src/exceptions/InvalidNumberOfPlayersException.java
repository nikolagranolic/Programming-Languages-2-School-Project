package exceptions;

public class InvalidNumberOfPlayersException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidNumberOfPlayersException() {
		super("Zadani broj igraca nije u postavljenim granicama!");
	}
	
	public InvalidNumberOfPlayersException(String msg) {
		super(msg);
	}
}