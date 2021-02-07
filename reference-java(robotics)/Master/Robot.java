//MADE BY SAM AND GUS 2020 (under instruction of Old Man Josh); FOR COMPETITION 2020

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/* MAP OF CONTROLLER BOARD FOR REFERENCE;
  /------------------------------------/
  /                                    /
  /     A           B           C      /                  <- A, B, and C controllers for reference below
  /                                    /
  /------------------------------------/
  -A x-channel: forward/backwards DB
  -A y-channel: left/right DB
  -A button 0 (trigger): shoot motors (when held down)
  -B button 0 (trigger): winch (when held down)
  
*/

/* NOTES: 
-THE PORTS, BUTTONS, AND CONTROLLERS ASSIGNED TO DIFFERENT THINGS ARE MOST LIKELY INCORRECT!    ->    make sure to change.
-
-
-
-
-
-
-
-
-
*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DigitalInput;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.cameraserver.CameraServer;
//import com.analog.adis16470.frc.ADIS16470_IMU;
//import edu.wpi.first.wpilibj.AnalogGyro;
//import edu.wpi.first.wpilibj.DigitalSource;


public class Robot extends TimedRobot {                     // * = motor for quick counting and referencing
  
  //controllers
  private static final int a_joystick_channel = 0;          //joystick channels 
  private static final int b_joystick_channel = 1;
  private static final int c_joystick_channel = 2;
  private Joystick a_joystick;                              //joystick 1
  private Joystick b_joystick;                              //joystick 2
  private Joystick c_joystick;                              //joystick 3

  
  //limit switches
  private DigitalInput conveyorlimit1;
  private DigitalInput conveyorlimit2;
  private DigitalInput shooter_angle_limit;                 //shooter angle limit switch
  
   
  //shooter
 // private TalonSRX m_left_shoot;                            //left shooter motor (falcon 500)       *
 // private TalonSRX m_right_shoot;                           //right shooter motor (falcom 500)      *
  private double shoot_speed = 1;                           //shooter speed
  private SpeedController shoot_angle;                      //motor for raising/lowering shooter    *
  
  
  //drive base
  private DifferentialDrive m_myRobot;                      //main differential drive for drive base
  private SpeedControllerGroup m_left_drive;                //drive base left motors group
  private SpeedControllerGroup m_right_drive;               //drive base right motors group
  private SpeedController m_front_left;                     //front right db motor                  *
  private SpeedController m_back_left;                      //back left db motor                    * 
  private SpeedController m_front_right;                    //front right db motor                  *
  private SpeedController m_back_right;                     //back right db motor                   *
  private int front_left_channel = 0;                       //channels for drive base  v
  private int back_left_channel = 1;
  private int front_right_channel = 2;
  private int back_right_channel = 3;
  private int speed_channel = 1;
  private int rotation_channel = 4;

  
  //Conveyorbelt
  private SpeedControllerGroup belt1;                        //groups of motors to determine belts vvv
  private SpeedControllerGroup belt2;
  private SpeedControllerGroup belt3;               
  private SpeedController b1a_motor;                                                                             
  private SpeedController b1b_motor;
  private SpeedController b2a_motor;
  private SpeedController b2b_motor;
  private SpeedController b3a_motor;
  private SpeedController b3b_motor;                         //conveyor motors as specified by name ^^^^^^ *x6
  private SpeedController loader_m;                          //loader motor
  private double loader_speed = 0.4;
  private double belt_speed = 0.2;                           //default belt speed if specific speeds are not needed

  
  //arm
  //private SpeedController arm_t;
  private SpeedController winch;                              //motor for hanging arm      *
  private double winch_speed = 0.1;                            //speed ^

  
  //Gyro
  //private AnalogGyro m_gyro = new AnalogGyro(0);
  //private static final double kAngleSetpoint = 0.0;
  //private static final double kP = 0.005; 
  //private static final double kVoltsPerDegreePerSecond = 0.0128;

  
  //filler variable for no red stuffs
  //private int filler = 0;                                    //filler placeholder if waiting on exact ports/joystick buttons AND DONT WANT RED LINES OF DOOM

  //timing variable
  private double timing;

  
  @Override
  public void robotInit() {
    //camera
    CameraServer.getInstance().startAutomaticCapture(0);

    //Setup the parameters for the drive motors.
    m_front_left = new PWMVictorSPX(front_left_channel);
    m_back_left = new PWMVictorSPX(back_left_channel);
    m_front_right = new PWMVictorSPX(front_right_channel);
    m_back_right = new PWMVictorSPX(back_right_channel);
    m_left_drive = new SpeedControllerGroup(m_front_left, m_back_left);
    m_right_drive = new SpeedControllerGroup(m_front_right, m_back_right);
    m_myRobot = new DifferentialDrive(m_left_drive, m_right_drive);    

    //Setup the parameters for the shooter motors.
    //m_left_shoot = new TalonSRX(11);
    //m_right_shoot = new TalonSRX(12);
    //m_left_shoot.setInverted(true);
    //m_right_shoot.setInverted(false);
    
    //Setup the josticks for controlling the robot
    a_joystick = new Joystick(a_joystick_channel);                  //sets which joystick channel the program is to use. This allows for 5 joysticks to potentially be used on the computer.
    a_joystick.setXChannel(speed_channel);                          //sets which axis on the controller is designated as the 'X' channel, specifically used for speed.
    a_joystick.setYChannel(rotation_channel);                       //sets which axis on the controller is designated as the 'Y' channel, specifically used for rotation.
    b_joystick = new Joystick(b_joystick_channel);
    //b_joystick.setXChannel(filler);
    //b_joystick.setYChannel(filler);
    c_joystick = new Joystick(c_joystick_channel);
    //c_joystick.setXChannel(filler);
    //c_joystick.setYChannel(filler);
    
    //sets parameters for conveyorbelt
    belt1 = new SpeedControllerGroup(b1a_motor, b1b_motor);   
    belt2 = new SpeedControllerGroup(b2a_motor, b2b_motor); 
    belt3 = new SpeedControllerGroup(b3a_motor, b3b_motor); 
    b1a_motor = new PWMVictorSPX(4);
    b1b_motor = new PWMVictorSPX(5);
    b2a_motor = new PWMVictorSPX(6);
    b2b_motor = new PWMVictorSPX(7);
    //b3a_motor = new PWMVictorSPX(10);
    //b3b_motor = new PWMVictorSPX(11);
    loader_m = new PWMVictorSPX(8);

    //sets parameters for arm
    winch = new PWMVictorSPX(9);

    //limit switches
    shooter_angle_limit = new DigitalInput(0);
    conveyorlimit1 = new DigitalInput(1);
    conveyorlimit2 = new DigitalInput(2);
    }

  @Override                                                         //The override changes the implementation of teleopPeriodic to the code we have written below. If we don't override this, the robot does what the original function does.
  public void teleopPeriodic() {
    //Drive function
    double x = a_joystick.getX();
    double y = a_joystick.getY();
    m_myRobot.arcadeDrive(x, y);                                    //drive the wheels using the joystick's X and Y channel values.

    //use the A button to shoot the ball.
    if(a_joystick.getRawButton(0)) Shoot(shoot_speed);              //if the button is pressed, set the shoot speed to the constant above.
    else Shoot(0);                                                  //if the button isn't pressed, set the shoot speed to zero.
    
    //shooter angle toggle 
    //if(a_joystick.getRawButtonPressed(5)) toggle_shooter(angle_speed);                      //press button to change shooter angle (press X to doubt)
    //else toggle_shooter(0);                                                                 //  <--  EXPRERIMENTAL
    
    //shooter angle limit switch                                                            //limiting function of^
    //if(shooter_angle_limit.get()) shoot_angle.set(0);

    //arm control                                                                           //press button to trigger arm
    //if(a_joystick.getRawButtonPressed(2)) arm_up(winchSpeed);
    //else arm_up(0); 
    
    if(b_joystick.getRawButton(0)) winch_pull(winch_speed);
    else winch_pull(0);

    //conveyor and loader control + logic                                       //IMPORTANT: all this relies on limit switches being palced inbetween loader and beginning of belts, and another at the end of the belts, which theoretically lets us know if the blets are full or not, and if a ball is ready to be pulled up the belts
    if(conveyorlimit1.get()){                                                   //PLZ CHANGE OR COMMENT OUT IF THIS IS NOT TRUE!^^^^^
      set_belt1(belt_speed);                                                    //  <- go over and make sure this one wont just stop or go indefinately
      set_belt2(belt_speed);
    }
    else if(c_joystick.getRawButtonPressed(1)){                                 
      set_belt1(belt_speed);
      set_belt2(belt_speed);
    }
    else if(conveyorlimit2.get() && c_joystick.getRawButtonPressed(2)){
      set_belt1(.1);
      set_belt2(.1);
      set_belt3(belt_speed);
    }
    else if(c_joystick.getRawButton(0)){
       loader_m.set(loader_speed);                  
    }
    else if(conveyorlimit2.get()){
      set_belt1(0);
      set_belt2(0);
      loader_m.set(0);
    }
    else{
      set_belt1(0);
      set_belt2(0);
      set_belt3(0);
      loader_m.set(0);
    }

    
    /* ^LOGIC DOCUMENTATION^
      if first limit triggered, initialize two main belts                           <- I cant tell if the belts will only trigger while the limit is triggered, if they will go indefinately, or if they will start and go until further if/then prohibits movement. PLZ CHECK
      if not^, then if button 2 on joystick c is pressed, manually start two main belts
      if not^^, then if end limit switch is triggered and button 3 on joystick c is pressed, slow main belts but initialize transition belt (theoretically loading into the shooter. may need to add initialize shooter also get get the motors up to speed)
      if not^^, then if button 1 on joystick c is pressed manually start loader
      if not^^, then if the end limit switch is pressed, stop all motors to prevent balls from spilling out the back (this is below the other if/then with ending limit switch because this would overide that one and set all motors to 0)
      if none of the above, set all motors in conveyor system to 0 to prevent from CATASTROPHIC FAILIURE :)
    */
    
  }

  public void Shoot(double speed){                                          //various functions as used above vvvvvv
    //m_left_shoot.set(ControlMode.PercentOutput, speed);
    //m_right_shoot.set(ControlMode.PercentOutput, speed);
  }

  public void set_belt1(double speed){
    belt1.set(speed);
  }

  public void set_belt2(double speed){
    belt2.set(speed);
  }

  public void set_belt3(double speed){
    belt3.set(speed);
  }

  public void toggle_shooter(double speed){
    shoot_angle.set(speed);
  }

  public void winch_pull(double speed){
    winch.set(speed);
  }

  @Override
  public void autonomousInit() {
  timing = 0;
  }
  @Override
  public void autonomousPeriodic(){
    
    //if(false) auto1();
    //else if(false) auto2();
    //else if(true) auto3();
    //else if(true) auto4();

    timing ++;
  }

  private void auto1(){
    if(timing < 200 & timing >0) m_myRobot.tankDrive(0.5,0.5);
    else if (timing > 200 & timing < 300); //set_conveyor(1);
    else m_myRobot.tankDrive(0,0);//set_conveyor(0);
  }
  
  private void auto2(){
    if(timing < 200)m_myRobot.tankDrive(0.5,0.5);
    else if(timing > 200 & timing < 250)m_myRobot.tankDrive(-0.5,0.5);
    else if(timing > 250 & timing < 550)m_myRobot.tankDrive(0.5,0.5);
    else if(timing > 550); //set_conveyor(1);
    else m_myRobot.tankDrive(0,0); //set_conveyor(0);

  }
  private void auto3(){
    if(timing < 300)m_myRobot.tankDrive(0.5,0.5);
    else if(timing > 300 & timing < 350)m_myRobot.tankDrive(-0.5,0.5);
    else if(timing > 350 & timing < 650)m_myRobot.tankDrive(0.5,0.5);
    else if(timing > 550); //set_conveyor(1);
    else {
      m_myRobot.tankDrive(0,0);
      //set_conveyor(0);

    }
  }
  //private void auto4(){

  }
//}



