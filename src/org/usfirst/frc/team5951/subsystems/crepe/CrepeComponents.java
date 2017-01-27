package org.usfirst.frc.team5951.subsystems.crepe;

import org.usfirst.frc.team5951.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class CrepeComponents {

	public static DoubleSolenoid crepeSolenoid = new DoubleSolenoid(RobotMap.k_CREPE_PISTON_FORWARD, RobotMap.k_CREPE_PISTON_REVERSE);
	

}
