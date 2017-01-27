package org.usfirst.frc.team5951.subsystems.chassis;

import org.usfirst.frc.team5951.robot.RobotMap;
import org.usfirst.frc.team5951.util.ChassisMath;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * Subsystem for the chassis subsystem.
 * @author Yair Ziv
 */
public class ChassisArcade {
	
	//Talons
	private CANTalon chassisLeftFront;
	private CANTalon chassisLeftRear;
	private CANTalon chassisRightFront;
	private CANTalon chassisRightRear;
	
	//Solenoids (Shifters)
	private DoubleSolenoid shiftersPiston;
	
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
		
		//Pneumatics Init
		shiftersPiston = ChassisComponents.chassisShifters;
		
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
	
	/**
	 * Shifts the chassis into the high gear. 
	 */
	public void switchToHighGear(){
		//TODO Check what position is forward and what position is reverse for the piston (extended = high or low gear).
		this.shiftersPiston.set(Value.kForward);
	}
	
	/**
	 * Shifts the chassis into the low gear.
	 */
	public void switchToLowGear(){
		//TODO Check what position is forward and what position is reverse for the piston (extended = high or low gear).
		this.shiftersPiston.set(Value.kReverse);
	}
	
	/**
	 * Toggles the shifters between high and low gear.
	 */
	public void toggleShifters(){
		this.shiftersPiston.set(this.shiftersPiston.get().equals(Value.kForward) ? Value.kReverse : Value.kForward);
	}
}
