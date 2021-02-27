// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.AutonomousRoutine;
import frc.robot.commands.TeleopDrive;
import frc.robot.commands.SensorDebug;
import frc.robot.commands.CancelAll;
import frc.robot.subsystems.ColorSense;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.UserInput;

public class RobotContainer {
  //subsystem instance creation
  public static DriveTrain db_main = new DriveTrain();
  public static ColorSense colorsrc = new ColorSense();
  public static UserInput input = new UserInput();

  //commmand declaration
  public static AutonomousRoutine auto_routine;
  public static TeleopDrive teleop_drive;
  public static SensorDebug sense_periodic;
  public static CancelAll stop;

  public RobotContainer() {
    teleop_drive = new TeleopDrive();
    sense_periodic = new SensorDebug();
    auto_routine = new AutonomousRoutine();
    stop = new CancelAll();
    // Configure the button bindings
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    RobotContainer.input.menubutton.whenHeld(stop);
  }
}
