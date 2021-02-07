/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.I2C;
import com.analog.adis16470.frc.ADIS16470_IMU;
//http://maven.highcurrent.io/vendordeps/ADIS16470.json
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */

public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_joystick;
  PWMVictorSPX frontLeft = new PWMVictorSPX(1);
  PWMVictorSPX frontRight = new PWMVictorSPX(2);
  PWMVictorSPX rearLeft = new PWMVictorSPX(0);
  PWMVictorSPX rearRight = new PWMVictorSPX(3);
  private ColorSensorV3 comm = new ColorSensorV3(I2C.Port.kOnboard);
  SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, rearLeft);
  SpeedControllerGroup right = new SpeedControllerGroup(frontRight, rearRight);
  DifferentialDrive drive = new DifferentialDrive(left, right);
  private int timing;
  public static final ADIS16470_IMU imu = new ADIS16470_IMU();
  private static final double kAngleSetPoint = 0.0;
  private static final double kP = 0.005;
  private static final double kVoltsPerDegreePerSecond = 0.0128;
  private AnalogGyro m_gyro = new AnalogGyro(0);
  double dt = 1;
  private static double magnitude = 0;
  private static double Red = 0;
  private static double Green = 0;
  private static double Blue = 0;


  double accelX[] = {0,0,0,0,0,0,0,0,0,0};
  double accelY[] = {0,0,0,0,0,0,0,0,0,0};
  double accelZ[] = {0,0,0,0,0,0,0,0,0,0};
  int i = 0;
  double velocityX = 0;
  double velocityY = 0;
  double velocityZ = 0;
  double distanceX = 0;
  double distanceY = 0;
  double distanceZ = 0;
  double red1 = 0;
  double blue1 = 0;
  double green1 = 0;
  boolean yellowd = false;
  boolean greend = false;
  boolean cyand = false;
  boolean redd = false;

  private double magnitude(double red, double green, double blue){
    return Math.sqrt(red*red + blue*blue + green*green);
  }
  @Override
  public void robotInit() {
   /* m_myRobot = new DifferentialDrive(left, right);
    m_joystick = new Joystick(1);
    m_joystick.setXChannel(1);
    m_joystick.setYChannel(5);
    distanceX = 0;
    distanceY = 0;
    distanceZ = 0;
    imu.reset();
    //imu.calibrate();*/
  }

  @Override
  public void teleopPeriodic() {
    
    
    //double turningValue = (kAngleSetPoint - imu.getAngle()) * kP;
    //turningValue = Math.copySign(turningValue, m_joystick.getY());
    //m_myRobot.arcadeDrive( m_joystick.getY()*0.5, turningValue);
    
    
    
  }
  
  @Override
  public void autonomousInit() {
    timing=0;
  }
  
  public void autonomousPeriodic() { 
    if(timing < 200 & timing >0) m_myRobot.tankDrive(0.5,0.5);
    else if(timing >= 200 & timing < 250 ) m_myRobot.tankDrive(0.5, -0.5);
    else m_myRobot.tankDrive(0,0);

    timing ++;
  }

  //Start of color sensor programs
  @Override
  public void robotPeriodic(){
    Red = comm.getRed();
    Green = comm.getGreen();
    Blue = comm.getBlue();
    magnitude = magnitude(Red, Green, Blue);
    SmartDashboard.putNumber("Angle", imu.getAngle());
    red1 = (Red/magnitude);
    green1 = (Green/magnitude);
    blue1 =(Blue/magnitude);
    SmartDashboard.putNumber("Magnitude", magnitude);
    if ( red1<0.582 & red1>0.382 & green1<0.957 & green1>0.757 & blue1>0.082 & blue1<0.282   ){
      yellowd = true;
      SmartDashboard.putBoolean("Yellowdetected", yellowd);
    }else{
      yellowd = false;
    }
    if ( red1<0.363 & red1>0.163 & green1<0.984 & green1>0.784 & blue1<0.483 & blue1>0.283   ){
      greend = true;
      SmartDashboard.putBoolean("Greendetected", greend);
    }else{
      greend = false;
    }
    if ( red1<0.322 & red1>0.129 & green1<0.811 & green1>0.611 & blue1<0.768 & blue1>0.568   ){
      cyand = true;
      SmartDashboard.putBoolean("Cyandetected", cyand);
    }else{
      cyand = false;
    }
    if ( red1<0.889 & red1>0.689 & green1<0.762 & green1>0.562 & blue1<0.318 & blue1>0.118   ){
      redd = true;
      SmartDashboard.putBoolean("Reddetected", redd);
    }else{
      redd = false;
    }

    // start of IMU programs
    /*
    dt = getPeriod();
    
    accelX[i] += imu.getAccelInstantX() * 9.8;
    accelY[i] += imu.getAccelInstantY() * 9.8;
    accelZ[i] += imu.getAccelInstantZ() * 9.8;
    double sum = 0;
    for(int j=0; j<10; j++){
      sum += accelX[j];
    }
    sum /= 10;
    velocityX += sum * dt;

    sum = 0;
    for(int j=0; j<10; j++){
      sum += accelY[j];
    }
    sum /= 10;

    velocityY += sum * dt;

    sum = 0;
    for(int j=0; j<10; j++){
      sum += accelZ[j];
    }
    sum /= 10;

    velocityZ += sum * dt;

    distanceX = velocityX;
    distanceY = velocityY;
    distanceZ = velocityZ;
    SmartDashboard.putNumber("Distance X", distanceX);
    SmartDashboard.putNumber("Distance Y", distanceY);
    SmartDashboard.putNumber("Distance Z", distanceZ);
    i ++;
    if(i>9) i = 0;
    //SmartDashboard.putNumber("Distance", ());
*/
    }
}


