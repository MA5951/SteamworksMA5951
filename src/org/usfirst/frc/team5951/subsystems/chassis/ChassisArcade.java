package org.usfirst.frc.team5951.subsystems.chassis;

import javax.security.auth.login.CredentialNotFoundException;

import org.usfirst.frc.team5951.util.ChassisMath;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

/**
 * Subsystem for the chassis subsystem.
 * @author Yair Ziv
 */
public class ChassisArcade {
	
	private CANTalon chassisLeftFront;
	private CANTalon chassisLeftRear;
	private CANTalon chassisRightFront;
	private CANTalon chassisRightRear;
	
	private ADXRS450_Gyro gyro;
	
	/**
	 * Constructor for the ChassisArcade class, initializes components.
	 */
	public ChassisArcade(){
		//Talons init
		chassisLeftFront = ChassisComponents.chassisLeftFront;
		chassisLeftRear = ChassisComponents.chassisLeftRear;
		chassisRightFront = ChassisComponents.chassisRightFront;
		chassisRightRear = ChassisComponents.chassisRightRear;
		
		chassisLeftFront.changeControlMode(TalonControlMode.PercentVbus);
		chassisLeftRear.changeControlMode(TalonControlMode.Follower);
		chassisRightFront.changeControlMode(TalonControlMode.PercentVbus);
		chassisRightRear.changeControlMode(TalonControlMode.Follower);
		
		//Gyro init
		gyro = ChassisComponents.gyro;
	}
	
	/**
	 * Drives the robot with values from the joystick using the {@link ChassisMath}'s math function.
	 * @param moveValue - Joystick's Y value
	 * @param rotateValue - Joystick's X value
	 */
	public void arcadeDrive(double moveValue, double rotateValue){
		double[] chassisValues = ChassisMath.calculatePower(moveValue, rotateValue);
		
		setLeftPower(chassisValues[0]);
		setRightPower(chassisValues[1]);
	}
	
	/**
	 * Sets the power to the left side of the chassis
	 * @param power - Power to give
	 */
	public void setLeftPower(double power){
		this.chassisLeftFront.set(power);
		this.chassisLeftRear.set(this.chassisLeftFront.getDeviceID());
	}
	
	/**
	 * Sets the power to the right side of the chassis
	 * @param power - Power to give
	 */
	public void setRightPower(double power){
		this.chassisRightFront.set(power);
		this.chassisRightRear.set(chassisRightFront.getDeviceID());
	}
	
	/**
	 * Stops the chassis. Disables all motors.
	 */
	public void stopChassis(){
		this.setRightPower(0);
		this.setLeftPower(0);
	}
	
	/**
	 * Turns the chassis to the wanted angle with an offset of 2.
	 * @param angle - Angle to turn to.
	 */
	public void turnToAngle(double angle){
		this.gyro.reset();
		
		if(angle < 0){
			angle = 360 - angle;
			while(this.gyro.getAngle() > angle + 2 && this.gyro.getAngle() < angle - 2){
				this.setLeftPower(0.3);
			}
		} else {
			while(this.gyro.getAngle() > angle + 2 && this.gyro.getAngle() < angle - 2){
				this.setRightPower(0.3);
			}
		}
	}
	
}
