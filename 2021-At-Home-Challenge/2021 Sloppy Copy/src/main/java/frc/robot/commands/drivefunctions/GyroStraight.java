// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivefunctions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class GyroStraight extends CommandBase {
  private double initial, speed, proportion;
  /** Creates a new CorrectiveStraight. */
  public GyroStraight(double speed, double PasinPID) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.db_main);
    addRequirements(RobotContainer.imu);
    this.speed = speed;
    //this.speed = speed;
    this.proportion = PasinPID;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initial = RobotContainer.imu.currentAngle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double angle = RobotContainer.imu.currentAngle();
    //a positive error means the robot is turning left
    double error = angle - initial;
    //set this to 1 by default, but change this to tune the [P controller]
    double control = error * proportion;
    RobotContainer.db_main.tank_drive(speed - control, speed + control);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
