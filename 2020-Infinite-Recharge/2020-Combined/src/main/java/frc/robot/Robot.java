package frc.robot;
//imports>>>
  //general
  import edu.wpi.first.wpilibj.TimedRobot;
  import edu.wpi.first.wpilibj.drive.DifferentialDrive;
  import edu.wpi.first.wpilibj.SpeedControllerGroup;
  import edu.wpi.first.wpilibj.SpeedController;
  //basic
  import edu.wpi.first.wpilibj.Joystick;
  //sensors
  import edu.wpi.cscore.UsbCamera;
  import edu.wpi.first.cameraserver.CameraServer;
  import edu.wpi.first.wpilibj.AnalogGyro;
  import edu.wpi.first.wpilibj.MedianFilter;
  import edu.wpi.first.wpilibj.Ultrasonic;
  import edu.wpi.first.wpilibj.AnalogInput;
  import edu.wpi.first.wpilibj.I2C;
  import com.analog.adis16470.frc.ADIS16470_IMU;
  import com.revrobotics.ColorSensorV3;
  import edu.wpi.first.wpilibj.DigitalSource;
  import edu.wpi.first.wpilibj.DigitalInput;
  //devices
  import edu.wpi.first.wpilibj.PWMVictorSPX;
  import com.ctre.phoenix.motorcontrol.can.TalonSRX;
  import com.ctre.phoenix.motorcontrol.ControlMode;

//CODE>>>
public class Robot extends TimedRobot {
  //CONFIGURABLE VARIABLES>>>
    //drive channels
      public static int fl_chan = 0;
      public static int bl_chan = 1;
      public static int fr_chan = 2;
      public static int br_chan = 3;
    //joystick channels
      int joychan_1 = 0;
      int joychan_2 = 1;
      int joychan_3 = 2;
      int speed_channel = 1;
      int direction_channel = 2;
    //Falcon channels
      int fchan1 = 0;
      int fchan2 = 1;
    //limit switch channels
      int limitchan1 = 0;
      int limitchan2 = 1;
    //gyro channel
      int gyrochan = 0;

  //DEFS,FUCNTIONS, and INITIALIZATIONS>>>
    //controllers
      Joystick joystick1 = new Joystick(joychan_1);
      Joystick joystick2 = new Joystick(joychan_2);
      Joystick joystick3 = new Joystick(joychan_3);
      double conx = joystick1.getX();
      double cony = joystick1.getY();
      
    //drive motors
      SpeedController drivefl = new PWMVictorSPX(fl_chan);
      SpeedController drivebl = new PWMVictorSPX(bl_chan);
      SpeedController drivefr = new PWMVictorSPX(fr_chan);
      SpeedController drivebr = new PWMVictorSPX(br_chan);
      SpeedControllerGroup left = new SpeedControllerGroup(drivefl, drivebl);
      SpeedControllerGroup right = new SpeedControllerGroup(drivefr, drivebr);
      DifferentialDrive robotdrive = new DifferentialDrive(left, right);

    //additional motors
      TalonSRX falcon1 = new TalonSRX(fchan1);
      TalonSRX falcon2 = new TalonSRX(fchan2);
      
    //additional inputs
      DigitalInput limit1 = new DigitalInput(limitchan1);
      DigitalInput limit2 = new DigitalInput(limitchan2);

    //camera
      UsbCamera camera1 = new UsbCamera("USB camera 1", 0);
      UsbCamera camera2 = new UsbCamera("USB camera 2", 1);
      void camera_stream(UsbCamera device){
        CameraServer.getInstance().startAutomaticCapture(device);
      }

    //colorsensor
      ColorSensorV3 colorsrc = new ColorSensorV3(I2C.Port.kOnboard);

      double magnitude(double red, double green, double blue)
      {return Math.sqrt(red*red + blue*blue + green*green);}

      double Red = colorsrc.getRed();
      double Green = colorsrc.getGreen();
      double Blue = colorsrc.getBlue();

      double cmag = magnitude(Red, Green, Blue);

      double red1 = (Red/cmag);
      double green1 = (Green/cmag);
      double blue1 =(Blue/cmag);

      boolean yellowval;
      boolean greenval;
      boolean cyanval;
      boolean redval;

      void calibation(){
        if ( red1<0.582 & red1>0.382 & green1<0.957 & green1>0.757 & blue1>0.082 & blue1<0.282   ){
          yellowval = true;
        }else{
          yellowval = false;
        }
        if ( red1<0.49 & red1>0.49 & green1<0.86 & green1>0.79 & blue1<0.38 & blue1>0.17   ){
          greenval = true;
        }else{
          greenval = false;
        }
        if ( red1<0.48 & red1>0.18 & green1<0.8 & green1>0.67 & blue1<0.75 & blue1>0.4  ){
          cyanval = true;
        }else{
          cyanval = false;
        }
        if ( red1<0.82 & red1>0.48 & green1<0.8 & green1>0.54 & blue1<0.37 & blue1>0.18   ){
          redval = true;
    
        }else{
          redval = false;
        }
      }
      
    //IMU
      ADIS16470_IMU imu = new ADIS16470_IMU();
      double dt = getPeriod();
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

    //ultrasonic
      int kUltrasonicPort = 0;
      // median filter to discard outliers; filters over 10 samples
      MedianFilter m_filter = new MedianFilter(10);
      Ultrasonic ultra = new Ultrasonic(1,2);
      AnalogInput m_ultrasonic = new AnalogInput(kUltrasonicPort);
      double currentDistance = m_filter.calculate(m_ultrasonic.getValue());
      double currentDistanceCM = m_ultrasonic.getVoltage() / 0.0048828125;
      double currentDistanceIN = currentDistanceCM/2.54;

    //gyro?
      AnalogGyro gyro = new AnalogGyro(gyrochan);
      double kAngleSetpoint = 0.0;
      double kP = 0.005;
      double kVoltsPerDegPsec = 0.0128;

//code that runs when during defined times>>>
  //when robot is started...
  @Override
  public void robotInit() {
    camera_stream(camera1);
    camera_stream(camera2);
  }

  @Override
  public void robotPeriodic() {
    //IMU 
      accelX[i] += imu.getAccelInstantX() * 9.8;
      accelY[i] += imu.getAccelInstantY() * 9.8;
      accelZ[i] += imu.getAccelInstantZ() * 9.8;
      double sum = 0;
      for(int j=0; j<10; j++){sum += accelX[j];}
      sum /= 10;
      velocityX += sum * dt;

      sum = 0;
      for(int j=0; j<10; j++){sum += accelY[j];}
      sum /= 10;
      velocityY += sum * dt;

      sum = 0;
      for(int j=0; j<10; j++){sum += accelZ[j];}
      sum /= 10;
      velocityZ += sum * dt;

      distanceX = velocityX;
      distanceY = velocityY;
      distanceZ = velocityZ;

      i ++;
      if(i>9) i = 0;
  }

  //when robot is in normal control mode...
  @Override
  public void teleopPeriodic() {
    
  }

  //when robot is in autonomous mode...
  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {

  }

  // @Override
  // public void teleopInit() {}

  // @Override
  // public void disabledInit() {}

  // @Override
  // public void disabledPeriodic() {}

  // @Override
  // public void testInit() {}

  // @Override
  // public void testPeriodic() {}
}

//bs/other default notes>>>
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.     (robot class)
 */
/**
 * This function is run when the robot is first started up and should be used for any
 * initialization code. (...robot extends TimedRobot...)
 */