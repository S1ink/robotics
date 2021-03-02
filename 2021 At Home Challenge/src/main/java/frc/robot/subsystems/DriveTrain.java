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
//import frc.robot.RobotContainer;

public class DriveTrain extends SubsystemBase {
  //DriveBase motors and groups setup
  private SpeedController db_fl = new PWMVictorSPX(Constants.front_left_chan);
  private SpeedController db_fr = new PWMVictorSPX(Constants.front_right_chan);
  private SpeedController db_bl = new PWMVictorSPX(Constants.back_left_chan);
  private SpeedController db_br = new PWMVictorSPX(Constants.back_right_chan);
  private SpeedControllerGroup db_left = new SpeedControllerGroup(db_fl, db_bl);
  private SpeedControllerGroup db_right = new SpeedControllerGroup(db_fr, db_br);
  private DifferentialDrive drive_main = new DifferentialDrive(db_left, db_right);

//Constructor method
  /** Creates a new DriveTrain. */
  public DriveTrain(){
    db_right.setInverted(Constants.db_right_invt);
    db_left.setInverted(Constants.db_left_invt);
  }


//start of custom methods
  public void tank_drive(double left_speed, double right_speed){
    drive_main.tankDrive(left_speed, right_speed, Constants.default_squareinp);
  }

  public void arcade_drive(double x_axis, double y_axis){
    drive_main.arcadeDrive(x_axis, y_axis, Constants.default_squareinp);
  }

  /**The point of this method is for the controller triggers to be the forward and backwards parameters, and the rotation to be the x-axis of one of the sticks (left and right) */
  public void race_drive(double forward, double backward, double rotation){
    double cumulative = forward - backward;
    drive_main.arcadeDrive(cumulative, rotation, Constants.default_squareinp);
  }

  public void trigger_drive(double ltrig, double rtrig){
    drive_main.tankDrive(ltrig, rtrig, Constants.default_squareinp);
  }

  //basic side control
  public void leftmotors(double speed){
    db_left.set(speed);
  }

  public void rightmotors(double speed){
    db_right.set(speed);
  }

  // /**HAS NOT BEEN TESTED.
  //  * This function WILL have bugs, and it is probably not written the correct way, so be warned. 
  //  * 
  //  * @param degrees - the degrees to turn -> positive is a right turn, negative is a left turn
  //  * @param speed - the speed in which the motors will go throughout the turn
  //  */
  // public void correcturn(double degrees, double speed){
  //   double correct = RobotContainer.spi_imu.currentAngle();
  //   double comparr[] = {correct};
  //   double diff = Math.abs(Math.abs(comparr[0]) - Math.abs(correct));
  //   double vectortarget = Math.copySign(diff, degrees);
  //   if(degrees > 0){
  //     while(degrees <= vectortarget){
  //       drive_main.tankDrive(speed, -speed);
  //     }
  //   }else if(degrees < 0){
  //     while(degrees >= vectortarget){
  //       drive_main.tankDrive(-speed, speed);
  //     }
  //   }
  // }
  






//default periodic functions
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}