// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivefunctions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class GyroTurn extends CommandBase {
  //in the future, change to a speed, ratio, and pivotwheel based turn
  private double lspeed, rspeed, target, initial, mult = 1;
  private boolean left = false, right = false, finished = false;
  /**
   * A positive degree value is a right turn, a negative one is a left turn
  */
  public GyroTurn(double leftspeed, double rightspeed, double degrees) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.db_main);
    addRequirements(RobotContainer.imu);
    this.target = degrees;
    this.lspeed = leftspeed;
    this.rspeed = rightspeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initial = RobotContainer.imu.currentAngle();
    if(lspeed > rspeed){
      left = true;
    }else if(lspeed < rspeed){
      right = true;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double angle = RobotContainer.imu.currentAngle();
    double correctAngle = angle * mult;
    double progress = correctAngle - initial;
    if(left){
      if(progress >= target){
        finished = true;
      }
    }else if(right){
      if(progress <= target){
        finished = true;
      }
    }else if (!left && !right){
      System.out.println("not turning left or right");
      RobotContainer.db_main.tank_drive(0, 0);
    }
    RobotContainer.db_main.tank_drive(lspeed, rspeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
