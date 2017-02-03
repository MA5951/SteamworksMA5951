package org.usfirst.frc.team5951.robot;

public class RobotMap {
	//TWEEK Find real ports.
	
	//Talons
	//Chassis
	public static final int k_CHASSIS_RIGHT_FRONT_TALON = 1;
	public static final int k_CHASSIS_RIGHT_REAR_TALON = 2;
	public static final int k_CHASSIS_LEFT_FRONT_TALON = 3;
	public static final int k_CHASSIS_LEFT_REAR_TALON = 4;
	
	
	//Pneumatics
	public static final int k_PCM = 1;
	
	public static final int k_CHASSIS_SHIFTERS_OPEN = 1;
	public static final int k_CHASSIS_SHIFTERS_CLOSE = 2;
	

	//Intake talons
	public static final int k_INTAKE_FRONT_TALON=5;
	public static final int k_INTAKE_REAR_TALON=6;
	
	//Crepe ports
	public static final int k_CREPE_PISTON_FORWARD = 420;
	public static final int k_CREPE_PISTON_REVERSE = 720; 
	
	//Ascender ports
	public static final int k_ASCENDER_TALON = 0;
	
	//Sensors
	//Chassis Encoders
	public static final int k_CHASSIS_ENCODER_LEFT_A = 1;
	public static final int k_CHASSIS_ENCODER_LEFT_B = 2;
	public static final int k_CHASSIS_ENCODER_RIGHT_A = 3;
	public static final int k_CHASSIS_ENCODER_RIGHT_B = 4;
}
