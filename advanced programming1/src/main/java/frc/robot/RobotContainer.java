// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.AutonomousRoutine;
import frc.robot.commands.TeleopDrive;
import frc.robot.commands.SensorDebug;
import frc.robot.subsystems.CameraArray;
import frc.robot.subsystems.ColorSense;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IMU_Gyro;
import frc.robot.subsystems.UltrasonicArray;
import frc.robot.subsystems.UserInput;

public class RobotContainer {
  //subsystem instance creation
  public static DriveTrain db_main = new DriveTrain();
  public static ColorSense colorsrc = new ColorSense();
  public static IMU_Gyro spi_imu = new IMU_Gyro();
  public static CameraArray camarr = new CameraArray();
  public static UltrasonicArray sonic = new UltrasonicArray();
  public static UserInput input = new UserInput();

  //commmand declaration
  public static AutonomousRoutine auto_routine;
  public static TeleopDrive teleop_drive;
  public static SensorDebug sense_periodic;

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
      teleop_drive = new TeleopDrive();
      sense_periodic = new SensorDebug();
      auto_routine = new AutonomousRoutine();
      // Configure the button bindings
      configureButtonBindings();
    }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

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
