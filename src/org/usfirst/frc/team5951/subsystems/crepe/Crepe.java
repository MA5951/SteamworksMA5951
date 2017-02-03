package org.usfirst.frc.team5951.subsystems.crepe;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * the crepe system opens and closes so it can take gears to the target.
 * 
 * @author Omer Libai
 */
public class Crepe {

	// Declaring variables
	private DoubleSolenoid crepeSolenoid; //TODO find real values and ports for solenoid.
	private boolean isCrepeOpen; // boolean of the current status of the crepe
								 // (is it open or closed?)

	/**
	 * constructor for crepe.
	 */
	public Crepe() {
		crepeSolenoid = CrepeComponents.crepeSolenoid;
		isCrepeOpen = false;
	}

	/**
	 * open's the crepe.
	 */
	public void openCrepe() {
		crepeSolenoid.set(Value.kForward);
		isCrepeOpen = true;
	}

	/**
	 * close's the crepe.
	 */
	public void closeCrepe() {
		crepeSolenoid.set(Value.kReverse);
		isCrepeOpen = false;
	}

	/**
	 * if the crepe is open, it closes. if it's closed, it opens it.
	 */
	public void toggleCrepe() {
		if (isCrepeOpen) {
			this.closeCrepe();
			return;
		}

		this.openCrepe();
	}
}
