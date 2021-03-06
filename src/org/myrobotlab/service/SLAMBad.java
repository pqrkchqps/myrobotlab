package org.myrobotlab.service;

import java.awt.Color;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import org.myrobotlab.framework.Service;
import org.myrobotlab.framework.Status;
import org.myrobotlab.logging.Level;
import org.myrobotlab.logging.LoggerFactory;
import org.myrobotlab.logging.LoggingFactory;
import org.myrobotlab.mapper.gui.Simbad;
import org.myrobotlab.mapper.sim.Agent;
import org.myrobotlab.mapper.sim.EnvironmentDescription;
import org.myrobotlab.mapper.sim.Wall;
import org.slf4j.Logger;

/**
 * @author GroG
 * 
 * 
 * 
 *         Dependencies : Java3D simbad-1.4.jar
 * 
 *         Reference : http://simbad.sourceforge.net/guide.php#robotapi
 *         http://www.ibm.com/developerworks/java/library/j-robots/ - simbad &
 *         subsumption JMonkey
 */
public class SLAMBad extends Service {
	private static final long serialVersionUID = 1L;
	public final static Logger log = LoggerFactory.getLogger(SLAMBad.class.getCanonicalName());

	transient Simbad simbad;
	transient MyEnv env;

	public static class MyEnv extends EnvironmentDescription {
		public MyEnv() {
			// you can initialize the environment here
			//add(new Arch(new Vector3d(3, 0, -3), this));
			add(new MyRobot(new Vector3d(0, 0, 0), "my robot"));
		}

		
	}

	public static class MyRobot extends Agent {
		public MyRobot(Vector3d position, String name) {
			super(position, name);
		}

		public void initBehavior() {
		}

		public void performBehavior() {
			if (collisionDetected()) {
				// stop the robot
				setTranslationalVelocity(0.0);
				setRotationalVelocity(0);
			} else {
				// progress at 0.5 m/s
				setTranslationalVelocity(0.5);
				// frequently change orientation
				if ((getCounter() % 100) == 0)
					setRotationalVelocity(Math.PI / 2 * (0.5 - Math.random()));
			}
		}
	}

	public SLAMBad(String n) {
		super(n);
	}

	public void startService() {
		super.startService();
		if (simbad == null) {
			startSimulator();
		}
	}

	public void stopService() {
		super.stopService();
		if (simbad != null) {
			simbad.dispose();
			simbad = null;
		}
	}

	public void addWall(Double x, Double y, Double z, Float x1, Float y1, Float z1) {
		Wall wall = new Wall(new Vector3d(x, y, z), x1, y1, z1, env);
		wall.setColor(new Color3f(new Color(0, 0, 0, 0)));
		simbad.attach(wall);
	}

	public void addRandomWall() {
		double x = (Math.random() * 20) - 10;
		double y = (Math.random() * 20) - 10;

		float xdim = (float) (Math.random() * 4);
		float ydim = (float) (Math.random() * 4);
		float zdim = (float) (Math.random() * 2);
		
		// Arches
		// add(new Arch(new Vector3d(3, 0, -3), this));
		Wall wall = new Wall(new Vector3d(x, 0, y), xdim, zdim, ydim, env);
		wall.setColor(new Color3f(new Color(Color.HSBtoRGB((float) Math.random(), 0.9f, 0.7f))));
		simbad.attach(wall);
	}

	public void startSimulator() {

		env = new MyEnv();
		simbad = new Simbad(env, false);
		//env.add(new Box(new Vector3d(3, 0, 0), new Vector3f(1, 1, 1), env));
		simbad.setVisible(true);
	}

	@Override
	public String getDescription() {
		return "used as a general template";
	}
	
	public Status test(){
		Status status = super.test();
		SLAMBad slambad = (SLAMBad)Runtime.start(getName(), "SLAMBad");
		//slambad.addWall(3.0, 0.0, 0.0, 1.0f, 1.0f, 1.0f);
		slambad.addWall(5.0, 0.0, 1.0, 1.0f, 0.0f, 1.0f);
		
		/*
		for (int i = 0; i < 100; ++i){
			slambad.addRandomWall();
		}
		*/
		return status;
	}

	public static void main(String[] args) {
		LoggingFactory.getInstance().configure();
		LoggingFactory.getInstance().setLevel(Level.WARN);
		
		SLAMBad slambad = (SLAMBad)Runtime.start("slambad", "SLAMBad");
		slambad.test();
		//slambad.addWall(3.0, 0.0, 0.0, 1.0f, 1.0f, 1.0f);

		/*
		MyEnv env = new MyEnv();

		Simbad simbad = new Simbad(env, false);

		env.add(new Box(new Vector3d(3, 0, 0), new Vector3f(1, 1, 1), env));

		*/
		GUIService gui = new GUIService("gui");
		gui.startService();

	}
	
	@Override
	public String[] getCategories() {
		return new String[] {"simulator", "display"};
	}


}
