// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Drive_Periodic extends CommandBase {
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
    double lstick_Y = Robot.robotContainer.OPControllerFunc1(Constants.c1_left_Y, Constants.deadzone, Constants.c1_left_Y_mult, Constants.power);
    double rstick_Y = Robot.robotContainer.OPControllerFunc1(Constants.c1_right_Y, Constants.deadzone, Constants.c1_right_Y_mult, Constants.power);
    double lstick_X = Robot.robotContainer.OPControllerFunc1(Constants.c1_left_X, Constants.deadzone, Constants.c1_left_X_mult, Constants.power);
    double rstick_X = Robot.robotContainer.OPControllerFunc1(Constants.c1_right_X, Constants.deadzone, Constants.c1_right_X_mult, Constants.power);
    double ltrigger = Robot.robotContainer.OPControllerFunc1(Constants.c1_left_trig, 0, 1, 2);
    double rtrigger = Robot.robotContainer.OPControllerFunc1(Constants.c1_right_trig, 0, 1, 2);
    if(Constants.db_drivemode == "tank"){
      Robot.db_main.tank_drive(lstick_Y, rstick_Y);
    }else if(Constants.db_drivemode == "arcade"){
      Robot.db_main.arcade_drive(lstick_X, lstick_Y);
    }else if(Constants.db_drivemode == "race"){
      Robot.db_main.race_drive(ltrigger, rtrigger, rstick_X);
    }else if(Constants.db_drivemode == "trigger"){
      Robot.db_main.trigger_drive(ltrigger, rtrigger);
    }
   }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.db_main.tank_drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
