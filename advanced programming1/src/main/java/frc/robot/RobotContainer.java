// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.AutoLine;
import frc.robot.commands.TeleopDrive;
import frc.robot.commands.groups.Slolom;
import frc.robot.commands.SensorDebug;
import frc.robot.commands.SwapController;
import frc.robot.commands.CancelAll;
import frc.robot.subsystems.CameraArray;
import frc.robot.subsystems.ColorSense;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IMU_Gyro;
import frc.robot.subsystems.UltrasonicArray;
import frc.robot.subsystems.UserInput;
import frc.robot.commands.DriveMode;

public class RobotContainer {
  //automated update functions
  public static Dynamics dynamics = new Dynamics();

  //subsystem instance creation
  public static DriveTrain db_main = new DriveTrain();
  public static ColorSense colorsrc = new ColorSense();
  public static IMU_Gyro spi_imu = new IMU_Gyro();
  //public static CameraArray camarr = new CameraArray();
  //public static UltrasonicArray sonic = new UltrasonicArray();
  public static UserInput input = new UserInput();
  
  //commmand declaration
  public static Slolom slolom;
  public static AutoLine linefollow;
  public static TeleopDrive teleop_drive;
  public static SensorDebug sense_periodic;
  public static CancelAll stop;
  public static SwapController swap;
  public static DriveMode drivemode_left;
  public static DriveMode drivemode_right;

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
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
  private void configureButtonBindings() {
    input.menubutton.whenHeld(stop);
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
