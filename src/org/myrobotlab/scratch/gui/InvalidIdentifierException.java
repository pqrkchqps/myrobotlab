package org.myrobotlab.scratch.gui;

/**
 * Die InvalidIdentifierException wird geworfen, wenn eine neue
 * Methode erstellt werden soll, deren Name ung[?]ltig in irgendeiner
 * Form ist. Beispielsweise entspricht der Name nicht den Java
 * Konventionen oder ist bereits vergeben.
 * 
 * based on the Scratch-Interface in the "hamster-simulator"
 * -> http://www.java-hamster-modell.de/scratch.html
 * by J[ue]rgen Boger
 * this file ???
 * @author LunDev (github), Ma. Vo. (MyRobotLab)
 *
 */
public class InvalidIdentifierException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8751976428424902863L;
	
	private String message;

	/**
	 * Erstelle eine neue Exception mit der [?]bergebenen Message
	 * @param message
	 */
	public InvalidIdentifierException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
