package org.usfirst.frc.team5951.subsystems.intake;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class IntakeAndShooter {
	private CANTalon intakeFront;
	private CANTalon intakeRear;

	public IntakeAndShooter() {
		this.intakeFront = IntakeComponents.intakeFront;
		this.intakeRear = IntakeComponents.intakeRear;

		intakeFront.changeControlMode(TalonControlMode.PercentVbus);
		intakeRear.changeControlMode(TalonControlMode.PercentVbus);
	}

	// This function intakes the balls into the container.
	public void intake() {
		intakeFront.set(-1);
		intakeRear.set(-1);
	}

	/**
	 *  This function outtakes the balls from the container
	 */
	public void outtake() {
		intakeFront.set(1);
		intakeRear.set(1);
	}

	/**
	 *  This function shoots the balls to the low boiler
	 */
	public void shooter() {
		intakeFront.set(-1);
		intakeRear.set(1);
	}

	/**
	 *  This function stops all motors of the subsystem
	 */
	public void stop() {
		intakeRear.set(0);
		intakeFront.set(0);
	}
}
