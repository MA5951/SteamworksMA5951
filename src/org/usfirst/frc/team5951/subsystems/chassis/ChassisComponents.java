package org.usfirst.frc.team5951.subsystems.chassis;

import org.usfirst.frc.team5951.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI.Port;

/**
 * Components for the chassis subsystem; Talons, Encoders etc.
 * @author Yair Ziv
 */
public class ChassisComponents {

	public static final CANTalon chassisLeftFront = new CANTalon(RobotMap.k_CHASSIS_LEFT_FRONT_TALON);
	public static final CANTalon chassisLeftRear = new CANTalon(RobotMap.k_CHASSIS_LEFT_REAR_TALON);
	public static final CANTalon chassisRightFront = new CANTalon(RobotMap.k_CHASSIS_RIGHT_FRONT_TALON);
	public static final CANTalon chassisRightRear = new CANTalon(RobotMap.k_CHASSIS_RIGHT_REAR_TALON);
	
	public static final Encoder chassisEncoderLeft = new Encoder(RobotMap.k_CHASSIS_ENCODER_LEFT_A, RobotMap.k_CHASSIS_ENCODER_LEFT_B);
	public static final Encoder chassisEncoderRight = new Encoder(RobotMap.k_CHASSIS_ENCODER_RIGHT_A, RobotMap.k_CHASSIS_ENCODER_RIGHT_B);
	
	public static final ADXRS450_Gyro gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
	
}
