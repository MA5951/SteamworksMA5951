package org.usfirst.frc.team5951.robot;

import org.opencv.core.Mat;
import org.usfirst.frc.team5951.autonomous.AutonomousRunner;
import org.usfirst.frc.team5951.subsystems.ascender.Ascender;
import org.usfirst.frc.team5951.subsystems.chassis.ChassisArcade;
import org.usfirst.frc.team5951.subsystems.crepe.Crepe;
import org.usfirst.frc.team5951.subsystems.intake.IntakeAndShooter;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static ChassisArcade chassisArcade;
	public static Crepe crepe;
	public static Ascender ascender;
	public static IntakeAndShooter intakeAndShooter;
	
	public static Joystick driver;
	public static XboxController systemsDriver;
	
	//Threads
	Thread cameraThread;
	
	//Sendable Choosers for autonomous
	public SendableChooser<String> startingPosition;
	public SendableChooser<Boolean> dropGear;
	public SendableChooser<Boolean> passLine;
	public SendableChooser<Boolean> doNothing;
	public SendableChooser<Boolean> goBack;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chassisArcade = new ChassisArcade();
		crepe = new Crepe();
		ascender = new Ascender();
		intakeAndShooter = new IntakeAndShooter();
		
		driver = new Joystick(ButtonPorts.k_DRIVER_JOYSTICK);
		systemsDriver = new XboxController(ButtonPorts.k_SYSTEMS_DRIVER_JOYSTICKS);
		cameraThread = new Thread(() -> {

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
		cameraThread.setDaemon(true);
		
		//Sendable Choosers init
		startingPosition = new SendableChooser<>();
		startingPosition.addDefault("Left", "left");
		startingPosition.addObject("Middle", "middle");
		startingPosition.addObject("Reich", "Right");
		SmartDashboard.putData("Starting position", startingPosition);
		
		dropGear = new SendableChooser<>();
		dropGear.addDefault("Drop gear in dildo", true);
		dropGear.addObject("Don't drop the gear", false);
		SmartDashboard.putData("Drop gears?", dropGear);
		
		passLine = new SendableChooser<>();
		passLine.addDefault("Pass line after gear drop", true);
		passLine.addObject("Stay under airship", false);
		SmartDashboard.putData("Line pass", passLine);
		
		doNothing = new SendableChooser<>();
		doNothing.addDefault("Do something", true);
		doNothing.addObject("Be a feminist", false);
		SmartDashboard.putData("What to do", doNothing);
		
		goBack = new SendableChooser<>();
		goBack.addDefault("There's no way back", false);
		goBack.addObject("GET BACK TO THE HOPPER", true);
		SmartDashboard.putData("Go back, or don't go back. that is the question", goBack);
	}

	/**
	 * This function is called once before the autonomous periodic starts.
	 */
	@Override
	public void autonomousInit() {
		chassisArcade.switchToLowGear();
		AutonomousRunner.run(startingPosition.getSelected(), dropGear.getSelected(), passLine.getSelected(), doNothing.getSelected(), goBack.getSelected());
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
		cameraThread.start();
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
