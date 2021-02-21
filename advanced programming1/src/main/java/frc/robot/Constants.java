// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.analog.adis16470.frc.ADIS16470_IMU.ADIS16470CalibrationTime;
import com.analog.adis16470.frc.ADIS16470_IMU.IMUAxis;
import edu.wpi.first.wpilibj.I2C;

public final class Constants {
    //Hardware config
        //xbox controller
            //port
            public static final int xbox1_port = 0;
            //buttons
            public static final int xbox1_xbutton = 2;
            public static final int xbox1_ybutton = 3;
            public static final int xbox1_abutton = 0;
            public static final int xbox1_bbutton = 1;
            public static final int xbox1_menubutton = 4;
            public static final int xbox1_homebutton = 5;
            //stick axis(es?)
            public static final int x1_left_Y = 1;
            public static final int x1_right_Y = 5;
            public static final int x1_left_X = 0;
            public static final int x1_right_X = 4;
            //triggers
            public static final int x1_left_trig = 2;
            public static final int x1_right_trig = 3;

        //Logitech controller - the triggers are seen as buttons unfortunately so anything using anolog input from the triggers will not work with this controller
            public static final int log1_port = 0;
            //buttons
            public static final int log1_xbutton = 0;
            public static final int log1_ybutton = 3;
            public static final int log1_abutton = 1;
            public static final int log1_bbutton = 2;
            public static final int log1_menubutton = 9;
            public static final int log1_homebutton = 10;
            //sticks
            public static final int log1_LY = 1;
            public static final int log1_RY = 3;
            public static final int log1_LX = 0;
            public static final int log1_RX = 2;
        
        //drivebase motors (channels)
        public static int front_left_chan = 3;
        public static int front_right_chan = 1;
        public static int back_left_chan = 2;
        public static int back_right_chan = 0;

        //Talon SRX's (can id's)
        public static int falcon1_canid = 0;
        public static int falcon2_canid = 1;

        //colorsensor - will need to figure out ports, expecially for the second, as they are both assigned the same one
        public static final I2C.Port colorsensor_port = I2C.Port.kOnboard;
        public static final I2C.Port colorsensor2_port = I2C.Port.kOnboard;
                
        //IMU/gyro
        public static final int gyro_port = 0;
        public static final IMUAxis imu_yaw = null;
        public static final ADIS16470CalibrationTime imu_caltime = null;

        //camera array (ports)
        public static final int cam1_port = 0;
        public static final int cam2_port = 1;
        public static final int cam3_port = 2;

        //ultrasonic(s?)
        public static final int ultrasonic1_port = 0;
    
    //Software config/calibration
        //drivebase motor/controlling options
            //drivebase drivecontrol mode -> Options include the following: tank, race, trigger, arcade
            public static String db_drivemode = "race";
            //sets automatic squaring inside the tankdrive and arcadedrive functions (turned off if it is aready done in controller input function)
            public static boolean general_squareinp = false;
            //invert boolean for each side
            public static boolean db_left_invt = false;
            public static boolean db_right_invt = true;
            //multipliers for each stick axis
            public static double c1_left_Y_mult = -0.5;
            public static double c1_right_Y_mult = -0.5;
            public static double c1_left_X_mult = -0.5;
            public static double c1_right_X_mult = -0.5;
            //deadzone on controller sticks -> only set for drivebase control atm
            public static double deadzone = 0.4;
            //takes the controller stick output value to the power of this number (along with multiplying it by the above values)
            public static int power = 2;

        //colorsensor calibration arrays
        public static double[] yellow = {0.582, 0.382, 0.957, 0.757, 0.082, 0.282};     //calibration arrays are in the form of the 
        public static double[] green = {0.49, 0.49, 0.86, 0.79, 0.38, 0.17};            //lowest followed by the highest 
        public static double[] cyan = {0.48, 0.18, 0.8, 0.67, 0.75, 0.4};               //values of the individual RGB('s) that make 
        public static double[] red = {0.82, 0.48, 0.8, 0.54, 0.75, 0.18};               //up the color that they are calibrating

        //autonomous line following
        public static double linecolor[] = {0.48, 0.18, 0.8, 0.67, 0.75, 0.4};
        public static double floorcolor[] = {0.0, 0.1, 0.0, 0.1, 0.0, 0.1};
        public static double linespeed = 0.25;
	
}

//Project notes>>>
/*
- use any created command and a ".schedule()" inside the wanted robot "mode" to run the command
- Some "CommandScheduler" methods that can be run inside Robot.java include: run(), cancelAll(), etc..
*/

//Subsystems (also just look at all files inside the subsystem folder):
    //DriveControl - The drive base

//Commands (also known by all the files inside the commands folder):
    //DB_TankDrive - Tankdrive command that takes in controller input from two axis
