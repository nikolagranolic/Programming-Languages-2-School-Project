package exceptions;

public class InvalidArgumentsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidArgumentsException() {
		super("Argumenti komandne linije nisu ispravno uneseni! Pokusajte ponovo u formatu '[broj_igraca] [dimenzija_matrice].");
	}
	
	public InvalidArgumentsException(String msg) {
		super(msg);
	}
}
