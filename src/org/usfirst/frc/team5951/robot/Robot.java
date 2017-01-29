package org.usfirst.frc.team5951.robot;

import org.opencv.core.Mat;
import org.usfirst.frc.team5951.subsystems.chassis.ChassisArcade;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static ChassisArcade chassisArcade;
	
	public static Joystick driver;
	public static XboxController systemsDriver;

	public Robot() {
		chassisArcade = new ChassisArcade();
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chassisArcade.switchToLowGear();
		
		driver = new Joystick(ButtonPorts.k_DRIVER_JOYSTICK);
		systemsDriver = new XboxController(ButtonPorts.k_SYSTEMS_DRIVER_JOYSTICKS);
		Thread t = new Thread(() -> {

			boolean allowCam1 = false;

			UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
			camera1.setResolution(320, 240);
			camera1.setFPS(30);
			UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
			camera2.setResolution(320, 240);
			camera2.setFPS(30);

			CvSink cvSink1 = CameraServer.getInstance().getVideo(camera1);
			CvSink cvSink2 = CameraServer.getInstance().getVideo(camera2);
			CvSource outputStream = CameraServer.getInstance().putVideo("Switcher", 320, 240);

			Mat image = new Mat();

			while (!Thread.interrupted()) {

				if (driver.getRawButton(ButtonPorts.k_SWITCH_CAMERAS_DRIVER) || systemsDriver.getRawButton(0)) {
					allowCam1 = !allowCam1;
				}

				if (allowCam1) {
					cvSink2.setEnabled(false);
					cvSink1.setEnabled(true);
					cvSink1.grabFrame(image);
				} else {
					cvSink1.setEnabled(false);
					cvSink2.setEnabled(true);
					cvSink2.grabFrame(image);
				}

				outputStream.putFrame(image);
			}

		});
		t.setDaemon(true);
		t.start();
	}

	/**
	 * This function is called once before the autonomous periodic starts.
	 */
	@Override
	public void autonomousInit() {
		chassisArcade.switchToLowGear();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called once before the teleop periodic starts.
	 */
	@Override
	public void teleopInit() {
		chassisArcade.stopChassis();
		chassisArcade.switchToHighGear();
	}

	/**
	 * Called each time the robot goes into disabled mode.
	 */
	@Override
	public void disabledInit() {
		chassisArcade.stopChassis();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}
