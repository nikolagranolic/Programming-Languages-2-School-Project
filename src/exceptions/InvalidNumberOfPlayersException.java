package exceptions;

public class InvalidNumberOfPlayersException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidNumberOfPlayersException() {
		super("Zadani broj igraca nije u postavljenim granicama!");
		System.out.println("Broj igraca mora biti izmedju 2 i 4!");
	}
	
	public InvalidNumberOfPlayersException(String msg) {
		super(msg);
	}
}
