package org.usfirst.frc.team5951.util;

public class ChassisMath {
	
	public static double[] calculatePower(double moveValue, double rotateValue){
		double[] arr = new double[2];
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
