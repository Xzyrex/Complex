public class Complex {
    private double real, imaginary,r,angle;

    public Complex(double real,double imaginary){
        this.real = real;
        this.imaginary = imaginary;
        String pl = imaginary>=0?" + ":" ";
        r = Math.sqrt(real*real + imaginary*imaginary);
        angle = Math.asin(imaginary/r);
    }
    public double getReal(){
        return real;
    }
    Complex add(Complex val){
        return new Complex(this.real+val.real,this.imaginary + val.imaginary);
    }
    Complex multiply(Complex val){
        return new Complex(this.real * val.real - this.imaginary * val.imaginary,this.real * val.imaginary + this.imaginary * val.real);
    }
    Complex divide(Complex val)throws DivisionByZeroException {
        double x1 = this.real,x2 = val.real,y1 = this.imaginary,y2 = val.imaginary;
        double divider = x2*x2 + y2*y2;
        if(divider==0)throw new DivisionByZeroException();
        return new Complex((x1*x2+y1*y2)/divider,(y1*x2-x1*y2)/divider);
    }
    Complex pow(double power){
        double r1 = Math.pow(this.r,power);
        double angle1 = this.angle * power;
        return new Complex(r1*Math.cos(angle1),r1*Math.sin(angle1));
    }
    Complex root(double val)throws DivisionByZeroException {
        if(val == 0)throw new DivisionByZeroException();
        return pow(1/val);
    }
    Complex conjugate(){
        return new Complex(this.real,-this.imaginary);
    }
    Complex toNegative(){
        return new Complex(-this.real,-this.imaginary);
    }
    String toExpotential(){
        return "e^" + angle + "i";
    }
    String toTrigonometrical(){
        return  r + "(cos(" + angle + ") + sin(" + angle + ")*i)";
    }
    String toAlgebraic(){
        String pl = imaginary>=0?" + ":" ";
        return   real + pl + imaginary + "i";
    }
}
