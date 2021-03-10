package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorSensorV3;

public class ColorSense {
        private I2C.Port port;
        private double yellowcal[];     //<<<arrays must consist of 6 values, two values of each color:
        private double greencal[];      //<< red, green and blue, that correspond to the values of the
        private double cyancal[];       //<< high and low ends of the range of the single (RGB) 
        private double redcal[];        //<< values for the color specified by the name of the array

        //array values found previously>>
        public double[] yellow = {0.582, 0.382, 0.957, 0.757, 0.082, 0.282};
        public double[] green = {0.49, 0.49, 0.86, 0.79, 0.38, 0.17};
        public double[] cyan = {0.48, 0.18, 0.8, 0.67, 0.75, 0.4};
        public double[] red = {0.82, 0.48, 0.8, 0.54, 0.75, 0.18};

    public ColorSense(
        I2C.Port port, 
        double yellowcal[], 
        double greencal[], 
        double cyancal[], 
        double redcal[]
    ){
        this.port = port;
        this.yellowcal = yellowcal;
        this.greencal = greencal;
        this.cyancal = cyancal;
        this.redcal = redcal;
    }
        
        double magnitude(double red, double green, double blue)
        {return Math.sqrt(red*red + blue*blue + green*green);}
        
        private ColorSensorV3 colorsrc = new ColorSensorV3(port);

        private double Red = colorsrc.getRed();
        private double Green = colorsrc.getGreen();
        private double Blue = colorsrc.getBlue(); 

        private double colormag = magnitude(Red, Green, Blue);

        private double red1 = (Red/colormag);
        private double green1 = (Green/colormag);
        private double blue1 =(Blue/colormag);

        public boolean yellowbool;
        public boolean greenbool;
        public boolean cyanbool;
        public boolean redbool;

        void calibate(){
            if (red1<0.582 & red1>0.382 & green1<0.957 & green1>0.757 & blue1>0.082 & blue1<0.282){
            yellowbool = true;
            }else{
            yellowbool = false;
            }if (red1<0.49 & red1>0.49 & green1<0.86 & green1>0.79 & blue1<0.38 & blue1>0.17){
            greenbool = true;
            }else{
            greenbool = false;
            }if (red1<0.48 & red1>0.18 & green1<0.8 & green1>0.67 & blue1<0.75 & blue1>0.4){
            cyanbool = true;
            }else{
            cyanbool = false;
            }if (red1<0.82 & red1>0.48 & green1<0.8 & green1>0.54 & blue1<0.37 & blue1>0.18){
            redbool = true;
            }else{
            redbool = false;
            }
        }
}
