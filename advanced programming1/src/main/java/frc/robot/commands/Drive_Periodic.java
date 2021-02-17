// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Drive_Periodic extends CommandBase {
  /** Creates a new DB_TankDrive. */
  public Drive_Periodic() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.db_main);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double lstick_Y = Robot.robotContainer.ControllerAxis_raw(Constants.c1_left_Y);
    double rstick_Y = Robot.robotContainer.ControllerAxis_raw(Constants.c1_right_Y);
    double lstick_X = Robot.robotContainer.ControllerAxis_raw(Constants.c1_left_X);
    double rstick_X = Robot.robotContainer.ControllerAxis_raw(Constants.c1_right_X);
    if(Constants.db_drivemode == "tank"){
      Robot.db_main.tank_drive(lstick_Y, rstick_Y, 0);
    }else if(Constants.db_drivemode == "arcade"){
      Robot.db_main.arcade_drive(lstick_X, lstick_Y);
    }
   }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.db_main.tank_drive(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}