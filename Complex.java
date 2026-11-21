package java_lab2;

import java.util.Objects;

public class Complex {
    private static int createdNumbers = 0;
    private static final double EPS = 1e-6;

    private final double re;
    private final double im;

    {
        createdNumbers++;
    }

    public Complex() {
        this(0.0, 0.0);
    }

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public Complex(Complex other) {
        this(other.re, other.im);
    }

    public static int getCreatedNumbers() {
        return createdNumbers;
    }

    public double getRe() { return re; }
    public double getIm() { return im; }

    public double modulus() {
        return Math.hypot(re, im);
    }

    public double argument() {
        return Math.atan2(im, re);
    }

    public Complex add(Complex other) {
        return new Complex(this.re + other.re, this.im + other.im);
    }

    public Complex subtract(Complex other) {
        return new Complex(this.re - other.re, this.im - other.im);
    }

    public Complex multiply(Complex other) {
        double real = this.re * other.re - this.im * other.im;
        double imag = this.re * other.im + this.im * other.re;
        return new Complex(real, imag);
    }

    public Complex divide(Complex other) {
        double denom = other.re * other.re + other.im * other.im;
        if (Math.abs(denom) < EPS) {
            throw new ArithmeticException("Ділення на нульове комплексне число");
        }
        double real = (this.re * other.re + this.im * other.im) / denom;
        double imag = (this.im * other.re - this.re * other.im) / denom;
        return new Complex(real, imag);
    }

    @Override
    public String toString() {
        String sign = im >= 0 ? "+" : "-";
        return String.format("%.2f %s %.2fi", re, sign, Math.abs(im));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Complex other)) return false;
        return Math.abs(re - other.re) < EPS && Math.abs(im - other.im) < EPS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(re * 1000), Math.round(im * 1000));
    }
}