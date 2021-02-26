// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SensorDebug extends CommandBase {
  private double redarr[] = {0.0, 0.0, 0.0};
  private double greenarr[] = {0.0, 0.0, 0.0};
  private double bluearr[] = {0.0, 0.0, 0.0};
  private double rgb[] = RobotContainer.colorsrc.rawcolors(1);


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
  public void initialize() {
    coloravg_setup();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //colorvalues();
    coloravg_periodic();    
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
    System.out.println(String.valueOf(rgb[0]));
    System.out.println(String.valueOf(rgb[1]));
    System.out.println(String.valueOf(rgb[2]));
  }

  private void coloravg_setup(){
    redarr[0] = redarr[1] = redarr[2] = rgb[0];
    greenarr[0] = greenarr[1] = greenarr[2] = rgb[1];
    bluearr[0] = bluearr[1] = bluearr[2] = rgb[2];
  }

  private double[] periodic_avg(double value, double[] avgarr){
    double running_avg = avgarr[0];
    double high = avgarr[1];
    double low = avgarr[2];
    double avg = (value + running_avg)/2;
    double ret[] = {avg, high, low};
    if(value > high){ret[1] = value;}
    if(value < low){ret[2] = value;}
    return ret;
  }

  private void coloravg_periodic(){
    double[] red = periodic_avg(rgb[0], redarr);
    double[] green = periodic_avg(rgb[1], greenarr);
    double[] blue = periodic_avg(rgb[2], bluearr);
    redarr[0] = red[0];
    redarr[1] = red[1];
    redarr[2] = red[2];
    greenarr[0] = green[0];
    greenarr[1] = green[1];
    greenarr[2] = green[2];
    bluearr[0] = blue[0];
    bluearr[1] = blue[1];
    bluearr[2] = blue[2];
    //output high, low, and avg for red, green, and blue
    SmartDashboard.putNumber("Average Red: ", red[0]);
    SmartDashboard.putNumber("Average Green: ", green[0]);
    SmartDashboard.putNumber("Average Blue: ", blue[0]);
    SmartDashboard.putNumber("Highest Red: ", red[1]);
    SmartDashboard.putNumber("Highest Green: ", green[1]);
    SmartDashboard.putNumber("Highest Blue: ", blue[1]);
    SmartDashboard.putNumber("Lowest Red: ", red[2]);
    SmartDashboard.putNumber("Lowest Green: ", green[2]);
    SmartDashboard.putNumber("Lowest Blue: ", blue[2]);
  }
}
