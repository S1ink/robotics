// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.DB_TankDrive;
import frc.robot.subsystems.DriveControl;

public class Robot extends TimedRobot {
  //Define containers and commands
  public static RobotContainer robotContainer;
  public static DriveControl db_main = new DriveControl();
  public static DB_TankDrive drive_periodic;



  //basic robot functions
  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
    drive_periodic = new DB_TankDrive();
  }
  //remember to add "CommandScheduler.getInstance().run();" inside as this is necessary for anything depending on the scheduler to run
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
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
  public void autonomousPeriodic() {}





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
