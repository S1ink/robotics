// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class TeleopDrive extends CommandBase {
  public TeleopDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.db_main);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double lstick_Y = RobotContainer.input.OPControllerFunc1(Constants.l_controllerStick_ly, Constants.deadzone, Constants.c1_left_Y_mult, Constants.power);
    double rstick_Y = RobotContainer.input.OPControllerFunc1(Constants.l_controllerStick_ry, Constants.deadzone, Constants.c1_right_Y_mult, Constants.power);
    double lstick_X = RobotContainer.input.OPControllerFunc1(Constants.l_controllerStick_lx, Constants.deadzone, Constants.c1_left_X_mult, Constants.power);
    // double rstick_X = RobotContainer.input.OPControllerFunc1(Constants.l_controllerStick_rx, Constants.deadzone, Constants.c1_right_X_mult, Constants.power);
    // double ltrigger = RobotContainer.input.OPControllerFunc1(Constants.x_controllerTrigger_l, 0, 1, 2);
    // double rtrigger = RobotContainer.input.OPControllerFunc1(Constants.x_controllerTrigger_r, 0, 1, 2);
    if(Constants.db_drivemode == "tank"){
      RobotContainer.db_main.tank_drive(lstick_Y, rstick_Y);
    }else if(Constants.db_drivemode == "arcade"){
      RobotContainer.db_main.arcade_drive(lstick_X, lstick_Y);
    }
    // else if(Constants.db_drivemode == "race"){
    //   RobotContainer.db_main.race_drive(ltrigger, rtrigger, rstick_X);
    // }else if(Constants.db_drivemode == "trigger"){
    //   RobotContainer.db_main.trigger_drive(ltrigger, rtrigger);
    // }
   }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.db_main.tank_drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
