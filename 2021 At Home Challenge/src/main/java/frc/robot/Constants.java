// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.I2C;

public final class Constants{
    //Hardware config
        public static final int controller_port = 0;
        public static final int l_controllerButton_A = 1;
        public static final int l_controllerButton_B = 2;
        public static final int l_controllerButton_X = 0;
        public static final int l_controllerButton_Y = 3;
        public static final int l_controllerButton_menu = 9;
        public static final int l_controllerButton_home = 10;
        public static final int l_controllerStick_ly = 1;
        public static final int l_controllerStick_lx = 0;
        public static final int l_controllerStick_ry = 3;
        public static final int l_controllerStick_rx = 2;
        public static final int l_controllerTrigger_l = 0;
        public static final int l_controllerTrigger_r = 0;
        
        //drivebase motors (channels)
        public static int front_left_chan = 3;
        public static int front_right_chan = 1;
        public static int back_left_chan = 2;
        public static int back_right_chan = 0;

        //colorsensor - will need to figure out ports, expecially for the second, as they are both assigned the same one
        public static final I2C.Port colorsensor_port = I2C.Port.kOnboard;
        public static final I2C.Port colorsensor2_port = I2C.Port.kOnboard;

    //Software config/calibration
        //drivebase motor/controlling options
            //drivebase drivecontrol mode -> Options include the following: tank, race, trigger, arcade
            public static String db_drivemode = "tank";
            //sets automatic squaring inside the tankdrive and arcadedrive functions (turned off if it is aready done in controller input function)
            public static boolean default_squareinp = false;
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

        //autonomous line following
        public static double linecolor[] = {0.48, 0.18, 0.8, 0.67, 0.75, 0.4};
        public static double floorcolor[] = {0.0, 0.1, 0.0, 0.1, 0.0, 0.1};
        public static double linespeed = 0.25;
	
}
