package org.myrobotlab.scratch.elements.controls;

import org.myrobotlab.scratch.Renderable;
import org.myrobotlab.scratch.ScratchUtils;
import org.myrobotlab.scratch.elements.ControlObject;
import org.myrobotlab.scratch.elements.booleans.FalseBooleanObject;
import org.myrobotlab.scratch.elements.booleans.TrueBooleanObject;
import org.myrobotlab.scratch.elements.voids.FunctionResultException;

/**
 * 
 * based on the Scratch-Interface in the "hamster-simulator"
 * -> http://www.java-hamster-modell.de/scratch.html
 * by J�rgen Boger
 * this file ???
 * @author LunDev (github), Ma. Vo. (MyRobotLab)
 *
 */

public class IfObject extends ControlObject {
	public IfObject() {
		super("falls", ScratchUtils.DEF_IF);
	}

	@Override
	public Renderable clone() {
		IfObject temp = new IfObject();
		return temp;
	}

	@Override
	public void writeSourceCode(StringBuffer buffer, int layer,
			boolean comment, boolean needsReturn)
			throws FunctionResultException {
		startLine(buffer, layer, comment);
		buffer.append("if (");
		boolean reachableEnd = true;
		boolean reachableBlock = true;

		if (childs.get(0) == null) {
			buffer.append("true");
			reachableEnd = false;
		} else {
			childs.get(0).writeSourceCode(buffer, 0, false, needsReturn);
			if (childs.get(0).getClass() == TrueBooleanObject.class)
				reachableEnd = false;
			if (childs.get(0).getClass() == FalseBooleanObject.class)
				reachableBlock = false;
		}

		buffer.append(") {" + NEWLINE);

		if (childs.get(1) == null) {
			startLine(buffer, layer + 1, comment);
			buffer.append(NEWLINE);
		} else {
			try {
				childs.get(1).writeSourceCode(buffer, layer + 1, comment,
						needsReturn);
			} catch (FunctionResultException e) {
			}
		}

		startLine(buffer, layer, comment);
		buffer.append("}" + NEWLINE);

		if (next != null)
			next.writeSourceCode(buffer, layer, comment, needsReturn);
	}

	// @Override
	// public void writeSourceCode(StringBuffer buffer, int layer,
	// boolean comment, boolean needsReturn)
	// throws FunctionResultException {
	// startLine(buffer, layer, comment);
	// buffer.append("if (");
	// boolean reachableEnd = true;
	// boolean reachableBlock = true;
	//
	// if (childs.get(0) == null) {
	// buffer.append("true");
	// reachableEnd = false;
	// } else {
	// childs.get(0).writeSourceCode(buffer, 0, false, needsReturn);
	// if (childs.get(0).getClass() == TrueBooleanObject.class)
	// reachableEnd = false;
	// if (childs.get(0).getClass() == FalseBooleanObject.class)
	// reachableBlock = false;
	// }
	//
	// buffer.append(") {" + NEWLINE);
	//
	// if (reachableBlock) {
	// if (childs.get(1) == null) {
	// startLine(buffer, layer + 1, comment);
	// buffer.append(NEWLINE);
	// } else {
	// try {
	// childs.get(1).writeSourceCode(buffer, layer + 1, comment,
	// needsReturn);
	// } catch (FunctionResultException e) {
	// }
	// }
	// } else {
	// startLine(buffer, layer + 1, comment);
	// buffer.append("// Unreachable Code." + NEWLINE);
	// }
	//
	// startLine(buffer, layer, comment);
	// buffer.append("}" + NEWLINE);
	//
	// if (!reachableEnd) {
	// // Unreachable Code
	// throw new FunctionResultException(true);
	// }
	//
	// if (next != null)
	// next.writeSourceCode(buffer, layer, comment, needsReturn);
	// else if (needsReturn && layer == 1) {
	// startLine(buffer, layer, comment);
	// buffer.append("return true;" + NEWLINE);
	// }
	// }
}
