package frc.robot;

import edu.wpi.first.wpilibj.I2C;

public final class Constants{
    //Hardware config
        //Controller layout - layouts are defined here -> can be used as-is, or, can access basic buttons through the input instance of UserInput, which will give the layout based on what controlmode is set
        //In order for buttons, sticks, etc. to be set, the setcontrolmode method, which is under UserInput, must be run at startup, or could be run continuously to make it so keybinds could be changed dynamically
        public static String controlmode = "xbox";
        public static final int controller_port = 0;
            //xbox controller keybinds
            public static final int x_controllerButton_A = 1;
            public static final int x_controllerButton_B = 2;
            public static final int x_controllerButton_X = 3;
            public static final int x_controllerButton_Y = 4;
            public static final int x_controllerButton_menu = 8;
            public static final int x_controllerButton_home = 7;
            public static final int x_controllerButton_lb = 5;
            public static final int x_controllerButton_rb = 6;
            public static final int x_controllerButton_ls = 9;
            public static final int x_controllerButton_rs = 10;
            public static final int x_controllerStick_ly = 1;
            public static final int x_controllerStick_lx = 0;
            public static final int x_controllerStick_ry = 5;
            public static final int x_controllerStick_rx = 4;
            public static final int x_controllerTrigger_l = 2;
            public static final int x_controllerTrigger_r = 3;
            //logitech controller keybinds
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
            //similar controller keybinds

        //drivebase motors (channels)
        public static final int front_left_chan = 3;
        public static final int front_right_chan = 1;
        public static final int back_left_chan = 2;
        public static final int back_right_chan = 0;

        //Talon SRX's (can id's)
        public static final int falcon1_canid = 0;
        public static final int falcon2_canid = 1;

        //colorsensor - will need to figure out ports, expecially for the second, as they are both assigned the same one
        public static final I2C.Port colorsensor_port = I2C.Port.kOnboard;
        public static final I2C.Port colorsensor2_port = I2C.Port.kOnboard;
                
        //IMU/gyro
        public static final int gyro_port = 0;

        //camera array (ports)
        public static final int cam1_port = 0;
        public static final int cam2_port = 1;
        public static final int cam3_port = 2;

        //ultrasonic(s?)
        public static final int ultrasonic1_port = 0;
    
    //Software config/calibration -> dynamic in the future?
        //drivebase motor/controlling options
            //drivebase drivecontrol mode -> Options are in the following array:
            public static String drivemodes[] = {"tank", "arcade", "race", "trigger"};
            public static String db_drivemode = "race";
            //sets automatic squaring inside the tankdrive and arcadedrive functions (turned off if it is aready done in controller input function)
            public static boolean default_squareinp = false;
            //invert boolean for each side
            public static boolean db_left_invt = false;
            public static boolean db_right_invt = false;
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
        public static final double[] yellow = {0.582, 0.382, 0.957, 0.757, 0.082, 0.282};     //calibration arrays are in the form of the 
        public static final double[] green = {0.49, 0.49, 0.86, 0.79, 0.38, 0.17};            //lowest followed by the highest 
        public static final double[] cyan = {0.48, 0.18, 0.8, 0.67, 0.75, 0.4};               //values of the individual RGB('s) that make 
        public static final double[] red = {0.82, 0.48, 0.8, 0.54, 0.75, 0.18};               //up the color that they are calibrating

        //autonomous line following
        public static final double linecolor[] = {0.48, 0.18, 0.8, 0.67, 0.75, 0.4};
        public static final double floorcolor[] = {0.0, 0.1, 0.0, 0.1, 0.0, 0.1};
        public static final double linespeed = 0.25;
	
}