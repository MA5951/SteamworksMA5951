package org.usfirst.frc.team5951.subsystems.crepe;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
/**
 * 
 * @author Omer Libai
 *the crepe system opens and closes so it can take gears on the target.
 *
 */
public class Crepe {
	
	//declaring the DoubleSolenoid
	private DoubleSolenoid crepeSolenoid;
	private boolean crepeOpen; //boolean of the current status of the crepe (is it open or closed?) 
	/**
	 * constructor for crepe.
	 */
	public Crepe(){
		crepeSolenoid = CrepeComponents.crepeSolenoid;
		crepeOpen = false;
	}
	/**
	 * open's the crepe.
	 */
	public void openCrepe(){
		crepeSolenoid.set(Value.kForward);
	}
	
	/**
	 * 
	 * close's the crepe.
	 */
	public void closeCrepe(){
		crepeSolenoid.set(Value.kReverse);
	}
	/**
	 * if the crepe is open, it closes. if it's closed, it opens it.
	 */
	public void toggleCrepe(){
		if(crepeOpen)			
			this.closeCrepe();
		if(!crepeOpen)
			this.openCrepe();
	}
}
