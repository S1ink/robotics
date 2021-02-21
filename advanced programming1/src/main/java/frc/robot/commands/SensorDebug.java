// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class SensorDebug extends CommandBase {
  /** Creates a new PeriodiColor. */
  public SensorDebug() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.colorsrc);
    addRequirements(RobotContainer.camarr);
    addRequirements(RobotContainer.spi_imu);
    addRequirements(RobotContainer.input);
    addRequirements(RobotContainer.sonic);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    colorvalues();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Interrupted");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  //test colorsensor input
  private void colorvalues(){
    double[] rgb = RobotContainer.colorsrc.rawcolors(1);
    System.out.println(String.valueOf(rgb[0]));
    System.out.println(String.valueOf(rgb[1]));
    System.out.println(String.valueOf(rgb[2]));
  }
}
