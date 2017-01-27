package org.usfirst.frc.team5951.subsystems.ascender;
import org.usfirst.frc.team5951.robot.RobotMap;

import com.ctre.CANTalon;

/**
 * Constructors for the {@link Ascender} subsysten.
 * @author בזק
 *
 */
public class AscenderComponents {
	public static CANTalon ascenderTalon = new CANTalon(RobotMap.k_ASCENDER_TALON);
}
