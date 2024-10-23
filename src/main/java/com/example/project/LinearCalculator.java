package com.example.project;
    //FOR EXTRA CREDIT 
    //you should copy and paste all of your code from the LinearCalculator class
    // but NOT printInfo(). Please update it below

public class LinearCalculator{
    //INSTANCE VARIABLES 
    //4 INTEGER variables (name them: x1,x2,y1,y2) 
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    //CONSTRUCTOR
    //1 constructor with 2 String parameters. Each parameter represents a coordinate. 
    //For example, "(1,2)" and "(3,4)" would be two parameter values 
    //You will have to parse the string into 4 integers, representing the 2 points.
    public LinearCalculator(String coord1, String coord2){ // <--add 2 string parameters to this constructor
    
        //Each of the coordinates gets parsed here to get the 2 points
     x1 = Integer.parseInt(coord1.substring(coord1.indexOf("(") + 1, coord1.indexOf(",")));
     x2 = Integer.parseInt(coord2.substring(coord2.indexOf("(") + 1, coord2.indexOf(",")));
     y1 = Integer.parseInt(coord1.substring(coord1.indexOf(",") + 1, coord1.indexOf(")")));
     y2 = Integer.parseInt(coord2.substring(coord2.indexOf(",") + 1, coord2.indexOf(")")));
    }

    //METHODS
    //getters and setters for the 4 instance variables (8 methods total) 
    
    //returns the coordinates separately
    public int getX1(){   
        return x1;
    }                   
    public int getY1(){
        return y1;
    }
    public int getX2(){
        return x2;
    }
    public int getY2(){
        return y2;
    }
    
    //sets new coordinates
    public void setX1(int x){
        this.x1 = x;
    }
    public void setY1(int y){
        this.y1 = y;
    }
    public void setX2(int x){
        this.x2 = x;
    }
    public void setY2(int y){
        this.y2 = y;
    }


    //distance() -> returns a double. 
    //calculates the distance between the two points to the nearest HUNDREDTH and returns the value.
    public double distance(){
        double varX = Math.pow((x2-x1), 2); //Same as change in X to the power of 2
        double varY = Math.pow((y2-y1), 2); //Same as change in Y to the power of 2
        double distance = Math.sqrt((varX+varY)); //Square root of delta X squared + delta Y squared
        double roundedDistance = roundedToHundredth(distance); //Rounds the distance to the nearest hundredth
        return roundedDistance; //Final distance after rounding
    }
      
    //yInt() -> returns a double.
    //calculates the y intercept of the equation and returns the value to the nearest HUNDREDTH
    //if y-int if undefined, should return -999.99
    public double yInt(){
        double y = y1; //x and y can be any coordinates that are being used
        double x = x1;
        double m = slope();    //calling the slope method
        double b = y - (m*x);  //seperating the y-int from y = mx+b leads to b = y - mx
        double yInt = roundedToHundredth(b); //rounds the y-int to the nearest hundredth
        if (m == -999.99){ 
            yInt = -999.99;
        } //yInt get set to -999.99 if slope is undefined
        return yInt; //returns yInt
    }

    //slope() -> returns a double. 
    //calculates the slope of the equations and returns the value to the nearest HUNDREDTH
    //if slope is undefined, should return -999.99
    public double slope(){
    double changeX = x2 - x1; //for delta X when finding the slope
    double changeY = y2 - y1; //for delta Y when finding the slope
    double slope = changeY / changeX; //slope is equal to delta Y over delta X
    if (changeX == 0){
        slope = -999.99;
    } //when delta X is equal to 0, the slope will become undefined as delta X is in the denominator of slope
    
    else{
        double roundedSlope = roundedToHundredth(slope);
    slope = roundedSlope;
    } //rounds the slope to the nearest hundredth and sets the slope to its rounded version
    return slope;
    } //returns the slope

    //equations() -> returns a String.
    //calculates the final equation in y=mx+b form and returns the string
    //if the equation has no slope, the equation should return -> "undefined"
    //HINT: You may need other custom methods to decrease the amount of code in the equations() method
    public String equation(){
        double m = slope();
        double b = yInt();
        String posB = "+" + b;
        String equation = "y=" + m + "x" + b; //Slope-intercept form is y = mx + b
        if (m == -999.99){
         equation = "undefined"; //no slope leads to equation being undefined
        }
        if (b > 0){
        equation = "y=" + m + "x" + posB; //adds a plus sign when b is greater than 0 to deal with sign change
        }
        if (b == 0){
            equation = "y=" + m + "x";  //removes b when yInt is 0
        }
        if (m == 0){
            equation = "y=" + b; //removes m when slope is 0
        }
        return equation; //returns the equation
    }


    //roundedToHundredth(double x)-> returns double
    //calculates the input to the nearest hundredth and returns that value
    public double roundedToHundredth(double x){
        return Math.round(x * 100) / 100.00; //rounds the inputted parameter to the nearest hundredth
    }

     //findSymmetry()-> returns a string 
    //the method should determine if there is symmetry between the two points
    // there should be  4 return statements 
    // return "Symmetric about the x-axis";
    // return "Symmetric about the y-axis";
    //return "Symmetric about the origin";
    //return "No symmetry";
    
    public String findSymmetry(){
        int negativeX2 = -1 * x2;
        int negativeY2 = -1 * y2;
    
         if (x1 == negativeX2 && y1 == negativeY2){
                return "Symmetric about the origin";
                }
            if (y1 == negativeY2){
            return "Symmetric about the x-axis";    
            }
            else if (x1 == negativeX2){
            return "Symmetric about the y-axis";
            }
            else{
            return "No symmetry";
        }
        }

     //Midpoint()->return a string 
    //the method should calculate the midpoint between the two points
    //it should return "The midpoint of this line is: (0,0)";
    public String Midpoint(){
        double midX = (x1 + x2) / 2;
        double midY = (y1 + y2) / 2;
        String midpoint = "(" + midX + "," + midY + ")";
        return "The midpoint of this line is: " + midpoint;
    }
    
    //You will need to concatenate to the string 
    //the results from findSymmetry() and Midpoint()
    public String printInfo(){
        String str = "The two points are: (" + x1 + "," + y1  + ")";
        str += " and " + "(" + x2 + "," + y2 + ")"; //returns the 2 coordinates
        str += "\nThe equation of the line between these points is: " + equation(); //returns the equation of the line from the 2 coordinates
        str += "\nThe slope of this line is: " + slope(); //returns the slope of the equation
        str += "\nThe y-intercept of the line is: " + yInt(); //returns the y-int of the equation
        str += "\nThe distance between the two points is: " + distance(); //returns the distance of the 2 points
        str += "\n" + findSymmetry(); //returns the symmetry of the 2 points
        str += "\nThe midpoint of this line is: " + Midpoint();
        return str;

    }
}



