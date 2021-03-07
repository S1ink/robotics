// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.drivefunctions.AutoLine;
import frc.robot.commands.drivefunctions.TeleopDrive;
import frc.robot.commands.groups.Slolom;
import frc.robot.commands.sensors.SensorDebug;
import frc.robot.commands.controller.SwapController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.controller.CancelAll;
import frc.robot.subsystems.CameraArray;
import frc.robot.subsystems.ColorSense;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IMU_Gyro;
import frc.robot.subsystems.UltrasonicArray;
import frc.robot.subsystems.UserInput;
import frc.robot.commands.controller.DriveMode;
import frc.robot.commands.drivefunctions.Decelerate;
import frc.robot.commands.drivefunctions.GyroStraight;
import frc.robot.commands.drivefunctions.GyroTurn;
import frc.robot.commands.sensors.Distance;

public class RobotContainer {
  //automated update functions
  public static Dynamics dynamics = new Dynamics();

  //subsystem instance creation
  public static DriveTrain db_main = new DriveTrain();
  public static ColorSense colorsrc = new ColorSense();
  public static IMU_Gyro imu = new IMU_Gyro();
  //public static CameraArray camarr = new CameraArray();
  //public static UltrasonicArray sonic = new UltrasonicArray();
  public static UserInput input = new UserInput();
  
  //commmand declaration
  public static Distance distance;
  public static Slolom slolom;
  public static AutoLine linefollow;
  public static TeleopDrive teleop_drive;
  public static SensorDebug sense_periodic;
  public static CancelAll stop;
  public static SwapController swap;
  public static DriveMode drivemode_left;
  public static DriveMode drivemode_right;
  public static GyroTurn testurn;
  public static GyroStraight straight;

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
      straight = new GyroStraight(0.2, 0.2);
      testurn = new GyroTurn(0.2, -0.2, 10);
      distance = new Distance(false, true);
      drivemode_left = new DriveMode(true, false);
      drivemode_right = new DriveMode(false, true);
      slolom = new Slolom(null);
      teleop_drive = new TeleopDrive();
      sense_periodic = new SensorDebug();
      linefollow = new AutoLine();
      stop = new CancelAll();
      swap = new SwapController();
      // Configure the button bindings
      configureButtonBindings();
    }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  public void configureButtonBindings() {
    CommandScheduler.getInstance().clearButtons();
    input.menubutton.whenPressed(stop);
    input.homebutton.whenPressed(swap);
    input.leftbutton.whenPressed(drivemode_left);
    input.rightbutton.whenPressed(drivemode_right);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return m_autoCommand;
  // }
}
