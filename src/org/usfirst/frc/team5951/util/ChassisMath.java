package org.usfirst.frc.team5951.util;

/**
 * Contains functions to help with the steering of the chassis.
 * 
 * @author Yair Ziv
 */
public class ChassisMath {

	/**
	 * Function used to calculate power to each side of the chassis.
	 * 
	 * @param moveValue
	 *            - Joystick's Y value
	 * @param rotateValue
	 *            - Joystick X value
	 * @return - Power to each side of the chassis, position 0 is left side
	 *         position 1 is right side.
	 */
	public static double[] calculatePower(double moveValue, double rotateValue) {
		double[] arr = new double[2];
		if (moveValue > 1)
			moveValue = 1;
		if (moveValue < -1)
			moveValue = -1;
		if (rotateValue > 1)
			rotateValue = 1;
		if (rotateValue < -1)
			rotateValue = -1;
		double max = Math.max(Math.abs(moveValue), Math.abs(rotateValue));
		double sum = moveValue + rotateValue;
		double dif = moveValue - rotateValue;
		if (moveValue >= 0) {
			if (rotateValue >= 0) {
				arr[0] = max;
				arr[1] = dif;
			} else {
				arr[0] = sum;
				arr[1] = max;
			}
		} else {
			if (rotateValue >= 0) {
				arr[0] = sum;
				arr[1] = -max;
			} else {
				arr[0] = -max;
				arr[1] = dif;
			}
		}
		return arr;
	}

}
