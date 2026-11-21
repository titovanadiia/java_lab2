package java_lab2;

import java.util.Objects;

public class SimpleTriangle {
    private static int instancesCount = 0;
    private static final double EPS = 1e-6;

    private final double a;
    private final double b;
    private final double c;

    {
        instancesCount++;
    }

    public SimpleTriangle() {
        this(3.0, 4.0, 5.0);
    }

    public SimpleTriangle(double a, double b, double c) {
        if (!canFormTriangle(a, b, c)) {
            throw new IllegalArgumentException(
                String.format("Трикутник зі сторонами %.2f, %.2f, %.2f не існує", a, b, c)
            );
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public SimpleTriangle(SimpleTriangle other) {
        this(other.a, other.b, other.c);
    }

    public static int getInstancesCount() {
        return instancesCount;
    }

    public static boolean canFormTriangle(double a, double b, double c) {
        return a > 0 && b > 0 && c > 0 && (a + b > c) && (a + c > b) && (b + c > a);
    }

    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }

    public double perimeter() {
        return a + b + c;
    }

    public double area() {
        double s = perimeter() / 2.0;
        return Math.sqrt(Math.max(0.0, s * (s - a) * (s - b) * (s - c)));
    }

    public int compareToTriangle(SimpleTriangle other) {
        double diffArea = this.area() - other.area();
        if (Math.abs(diffArea) > EPS) {
            return Double.compare(this.area(), other.area());
        }
        return Double.compare(this.perimeter(), other.perimeter());
    }

    @Override
    public String toString() {
        return String.format("SimpleTriangle(a=%.2f, b=%.2f, c=%.2f, P=%.2f, S=%.2f)", a, b, c, perimeter(), area());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleTriangle other)) return false;
        return Math.abs(a - other.a) < EPS
                && Math.abs(b - other.b) < EPS
                && Math.abs(c - other.c) < EPS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(a * 1000), Math.round(b * 1000), Math.round(c * 1000));
    }
}