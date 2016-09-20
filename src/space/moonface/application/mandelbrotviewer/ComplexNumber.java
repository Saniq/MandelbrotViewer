package space.moonface.application.mandelbrotviewer;

public class ComplexNumber {
    private double real;
    private double imag;

    public ComplexNumber() {
    }

    public ComplexNumber(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImag() {
        return imag;
    }

    public void setImag(double imag) {
        this.imag = imag;
    }

    public static ComplexNumber pow(ComplexNumber num) {
        ComplexNumber result = new ComplexNumber();

        result.real = num.real * num.real - num.imag * num.imag;
        result.imag = num.real * num.imag + num.imag * num.real;

        return result;
    }

    public static ComplexNumber add(ComplexNumber num1, ComplexNumber num2) {
        ComplexNumber result = new ComplexNumber();

        result.real = num1.real + num2.real;
        result.imag = num1.imag + num2.imag;

        return result;
    }

    public static ComplexNumber sub(ComplexNumber num1, ComplexNumber num2) {
        ComplexNumber result = new ComplexNumber();

        result.real = num1.real - num2.real;
        result.imag = num1.imag - num2.imag;

        return result;
    }

    public static ComplexNumber mul(ComplexNumber num1, ComplexNumber num2) {
        ComplexNumber result = new ComplexNumber();

        result.real = num1.real * num2.real - num1.imag * num2.imag;
        result.imag = num1.real * num2.imag + num1.imag * num2.real;

        return result;
    }

    public static ComplexNumber div(ComplexNumber num1, ComplexNumber num2) {
        ComplexNumber result = new ComplexNumber();

        result.real = (num1.real * num2.real + num1.imag * num2.imag) / (num2.real * num2.real + num2.imag * num2.imag);
        result.imag = (num1.imag * num2.real - num1.real * num2.imag) / (num2.real * num2.real + num2.imag * num2.imag);

        return result;
    }

    public static double length(ComplexNumber num) {
        double length;

        length = Math.sqrt(num.real * num.real + num.imag * num.imag);

        return length;
    }
}