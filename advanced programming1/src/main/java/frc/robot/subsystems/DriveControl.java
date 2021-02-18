// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import frc.robot.Constants;

public class DriveControl extends SubsystemBase {
  //DriveBase motors and groups setup
  private SpeedController db_fl = new PWMVictorSPX(Constants.front_left_chan);
  private SpeedController db_fr = new PWMVictorSPX(Constants.front_right_chan);
  private SpeedController db_bl = new PWMVictorSPX(Constants.back_left_chan);
  private SpeedController db_br = new PWMVictorSPX(Constants.back_right_chan);
  private SpeedControllerGroup db_left = new SpeedControllerGroup(db_fl, db_bl);
  private SpeedControllerGroup db_right = new SpeedControllerGroup(db_fr, db_br);
  private DifferentialDrive drive_main = new DifferentialDrive(db_left, db_right);

  /** Creates a new ExampleSubsystem. */
  public DriveControl(

  ){
    db_right.setInverted(Constants.db_right_invt);
    db_left.setInverted(Constants.db_left_invt);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  //>>>Add scaling mode functions later, and use them based on the "scale_mode" parameter<<<
  public void tank_drive(double left_speed, double right_speed, int scale_mode){
    drive_main.tankDrive(left_speed, right_speed, Constants.general_squareinp);
  }

  public void arcade_drive(double x_axis, double y_axis){
    drive_main.arcadeDrive(x_axis, y_axis, Constants.general_squareinp);
  }
  //Define any functions that involve the drive-motors>>>
  
}