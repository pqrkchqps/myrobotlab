package org.myrobotlab.scratch.elements.voids;

import java.util.ArrayList;

import org.myrobotlab.scratch.Renderable;
import org.myrobotlab.scratch.elements.VoidObject;

/**
 * 
 * based on the Scratch-Interface in the "hamster-simulator"
 * -> http://www.java-hamster-modell.de/scratch.html
 * by J�rgen Boger
 * this file ???
 * @author LunDev (github), Ma. Vo. (MyRobotLab)
 *
 */

public class NimmVoidObject extends VoidObject {
	public NimmVoidObject() {
		super("nimm", getParameter());
	}
	
	private static ArrayList<RType> getParameter() {
		ArrayList<RType> parameter = new ArrayList<RType>();
		return parameter;
	}
	
	@Override
	public Renderable clone() {
		return new NimmVoidObject();
	}
}
