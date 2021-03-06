// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivefunctions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;
import frc.robot.Dynamics;
import frc.robot.RobotContainer;

public class TeleopDrive extends CommandBase {
  // private double decleft, decright;
  public TeleopDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.db_main);
    addRequirements(RobotContainer.input);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double lstick_Y = RobotContainer.input.OPControllerFunc1(Dynamics.controllerStick_ly, Constants.deadzone, Constants.c1_left_Y_mult, Constants.power);
    double rstick_Y = RobotContainer.input.OPControllerFunc1(Dynamics.controllerStick_ry, Constants.deadzone, Constants.c1_right_Y_mult, Constants.power);
    double lstick_X = RobotContainer.input.OPControllerFunc1(Dynamics.controllerStick_lx, Constants.deadzone, Constants.c1_left_X_mult, Constants.power);
    double rstick_X = RobotContainer.input.OPControllerFunc1(Dynamics.controllerStick_rx, Constants.deadzone, Constants.c1_right_X_mult, Constants.power);
    double ltrigger = RobotContainer.input.OPControllerFunc1(Dynamics.controllerTrigger_l, 0, 1, 2);
    double rtrigger = RobotContainer.input.OPControllerFunc1(Dynamics.controllerTrigger_r, 0, 1, 2);
    if(Dynamics.drivemode == "tank"){
      RobotContainer.db_main.tank_drive(lstick_Y, rstick_Y);
      // lstick_Y = decleft;
      // rstick_Y = decright;
    }else if(Dynamics.drivemode == "arcade"){
      RobotContainer.db_main.arcade_drive(rstick_X, rstick_Y);
      // double out[] = arcadeConversion(rstick_X, rstick_Y);
      // decleft = out[0];
      // decright = out[1];
    }else if(Dynamics.drivemode == "race"){
      RobotContainer.db_main.race_drive(ltrigger, rtrigger, rstick_X);
      // double comb = ltrigger - rtrigger;
      // double out[] = arcadeConversion(comb, rstick_X);
      // decleft = out[1];
      // decright = out[0];
    }else if(Dynamics.drivemode == "trigger"){
      RobotContainer.db_main.trigger_drive(ltrigger, rtrigger);
      // ltrigger = decleft;
      // rtrigger = decright;
    }else{
      System.out.println("drivemode error");
    }
   }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // System.out.println(String.valueOf(decleft));
    // System.out.println(String.valueOf(decright));
    RobotContainer.db_main.tank_drive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  private double[] arcadeConversion(double xSpeed, double zRotation) {
    int right_invt = 1;
    double leftOutput;
    double rightOutput;
    double ret[] = {0, 0};
    double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);
    if(xSpeed >= 0.0){
      if(zRotation >= 0.0){
        leftOutput = maxInput;
        rightOutput = xSpeed - zRotation;
      }else{
        leftOutput = xSpeed + zRotation;
        rightOutput = maxInput;
      }
    }else{
      if(zRotation >= 0.0){
        leftOutput = xSpeed + zRotation;
        rightOutput = maxInput;
      }else{
        leftOutput = maxInput;
        rightOutput = xSpeed - zRotation;
      }}
    if(Dynamics.db_right_invt){right_invt = -1;}
    ret[0] = (MathUtil.clamp(leftOutput, -1.0, 1.0));
    ret[1] = (MathUtil.clamp(rightOutput, -1.0, 1.0) * right_invt);
    return ret;
  }
}
