// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AutonomousRoutine;
import frc.robot.commands.Drive_Periodic;
import frc.robot.commands.PeriodiColor;
import frc.robot.subsystems.CameraArray;
import frc.robot.subsystems.ColorSense;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IMU_Gyro;
import frc.robot.subsystems.UltrasonicArray;

public class Robot extends TimedRobot {
  //Define containers and commands
  public static RobotContainer robotContainer;

  //subsystem instance creation
  public static DriveTrain db_main = new DriveTrain();
  public static ColorSense colorsrc = new ColorSense();
  public static IMU_Gyro spi_imu = new IMU_Gyro();
  public static CameraArray camarr = new CameraArray();
  public static UltrasonicArray sonic = new UltrasonicArray();

  //variable declaration
  public static AutonomousRoutine auto_routine;
  public static Drive_Periodic drive_periodic;
  public static PeriodiColor color_periodic;
  public double dt;



  //basic robot functions
  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
    drive_periodic = new Drive_Periodic();
    color_periodic = new PeriodiColor();
    auto_routine = new AutonomousRoutine();
  }
  //remember to add "CommandScheduler.getInstance().run();" inside as this is necessary for anything depending on the scheduler to run
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    dt = getPeriod();
  }


  
  //normal operation mode
  @Override
  public void teleopInit() {
    drive_periodic.schedule();
  }
  @Override
  public void teleopPeriodic() {}


  //autonomous mode
  @Override
  public void autonomousInit() {}
  @Override
  public void autonomousPeriodic() {
    auto_routine.schedule();
  }





  //test mode
  @Override
  public void testInit() {}
  @Override
  public void testPeriodic() {}
  //disabled mode
  @Override
  public void disabledInit() {}
  @Override
  public void disabledPeriodic() {}

}
