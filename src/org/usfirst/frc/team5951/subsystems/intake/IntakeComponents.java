package org.usfirst.frc.team5951.subsystems.intake;

import org.usfirst.frc.team5951.robot.RobotMap;

import com.ctre.CANTalon;

/**
 * Components for the {@code}{@link IntakeAndShooter} subsystem
 * @author Matan
 */
public class IntakeComponents {
	public static CANTalon intakeFront = new CANTalon(RobotMap.k_INTAKE_FRONT_TALON);
	public static CANTalon intakeRear = new CANTalon(RobotMap.k_INTAKE_REAR_TALON);
}
