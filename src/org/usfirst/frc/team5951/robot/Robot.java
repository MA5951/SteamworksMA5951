package org.usfirst.frc.team5951.robot;

import org.usfirst.frc.team5951.subsystems.chassis.ChassisArcade;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static ChassisArcade chassisArcade;
	
	public Robot(){
		chassisArcade = new ChassisArcade();
	}
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chassisArcade.switchToLowGear();
	}

	/**
	 * This function is called once before the autonomous periodic starts.
	 */
	@Override
	public void autonomousInit() {
		chassisArcade.switchToLowGear();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
	}

	/**
	 * This function is called once before the teleop periodic starts.
	 */
	@Override
	public void teleopInit() {
		chassisArcade.stopChassis();
		chassisArcade.switchToHighGear();
	}
	
	/**
	 * Called each time the robot goes into disabled mode.
	 */
	@Override
	public void disabledInit() {
		chassisArcade.stopChassis();
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

