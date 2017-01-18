package org.usfirst.frc.team5951.subsystems.ascender;

import org.usfirst.frc.team5951.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

/**
 * Class for the Ascender subsystem. The ascender lifts the robot on the rope at the end of the game.
 * The Ascender can also lift the robot off the rope, and stop at any point.
 * @author Tomer Asher
 */
public class Ascender {
	
	private CANTalon ascenderTalon;
	
	public Ascender(){
		ascenderTalon = AscenderComponents.ascenderTalon;
		ascenderTalon.changeControlMode(TalonControlMode.PercentVbus);
	}
	/**
	 * Lifts up the robot at the end of the game.
	 */
	public void liftUp(){
		ascenderTalon.set(1);
	}
	
	/**
	 * Lifts the robot down if needed, FailSafe.
	 */
	public void liftDown(){
		ascenderTalon.set(-1);
	}
	
	/**
	 * Stop the ascender motor at any point, FailSafe.
	 */
	public void stopAscender(){
		ascenderTalon.set(0);
	}
}
