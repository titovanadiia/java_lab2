package java_lab2;

import java.util.Objects;

public class Triangle {
    private static int createdCount = 0;
    private static final double EPS = 1e-6;

    private double sideA;
    private double sideB;
    private double sideC;
    private double angleA;
    private double angleB;
    private double angleC;

    {
        createdCount++;
    }

    public Triangle() {
        this(3.0, 4.0, 5.0);
    }

    public Triangle(double a, double b, double c) {
        if (!isValidSides(a, b, c)) {
            throw new IllegalArgumentException("Некоректні сторони трикутника");
        }
        this.sideA = a;
        this.sideB = b;
        this.sideC = c;
        recalculateAngles();
    }

    public static Triangle fromSideAngle(double sideA, double sideB, double angleCdeg) {
        if (sideA <= 0 || sideB <= 0 || angleCdeg <= 0 || angleCdeg >= 180) {
            throw new IllegalArgumentException("Некоректні параметри трикутника");
        }
        double radC = Math.toRadians(angleCdeg);
        double sideC = Math.sqrt(sideA * sideA + sideB * sideB - 2 * sideA * sideB * Math.cos(radC));
        return new Triangle(sideA, sideB, sideC);
    }

    public static int getCreatedCount() {
        return createdCount;
    }

    public static boolean isValidSides(double a, double b, double c) {
        return a > 0 && b > 0 && c > 0 && (a + b > c) && (a + c > b) && (b + c > a);
    }

    private void recalculateAngles() {
        double cosA = clampCos((sideB * sideB + sideC * sideC - sideA * sideA) / (2 * sideB * sideC));
        double cosB = clampCos((sideA * sideA + sideC * sideC - sideB * sideB) / (2 * sideA * sideC));
        this.angleA = Math.toDegrees(Math.acos(cosA));
        this.angleB = Math.toDegrees(Math.acos(cosB));
        this.angleC = 180.0 - this.angleA - this.angleB;
    }

    private static double clampCos(double val) {
        return Math.max(-1.0, Math.min(1.0, val));
    }

    public void setSides(double a, double b, double c) {
        if (!isValidSides(a, b, c)) {
            throw new IllegalArgumentException("Некоректні сторони трикутника");
        }
        this.sideA = a;
        this.sideB = b;
        this.sideC = c;
        recalculateAngles();
    }

    public double getSideA() { return sideA; }
    public double getSideB() { return sideB; }
    public double getSideC() { return sideC; }
    public double getAngleA() { return angleA; }
    public double getAngleB() { return angleB; }
    public double getAngleC() { return angleC; }

    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    public double getArea() {
        double p = getPerimeter() / 2.0;
        return Math.sqrt(Math.max(0.0, p * (p - sideA) * (p - sideB) * (p - sideC)));
    }

    public double[] getHeights() {
        double area = getArea();
        return new double[] {
            (2.0 * area) / sideA,
            (2.0 * area) / sideB,
            (2.0 * area) / sideC
        };
    }

    public String getKind() {
        boolean eqSides = Math.abs(sideA - sideB) < EPS && Math.abs(sideB - sideC) < EPS;
        if (eqSides) {
            return "рівносторонній";
        }
        boolean isoSides = Math.abs(sideA - sideB) < EPS
                || Math.abs(sideB - sideC) < EPS
                || Math.abs(sideA - sideC) < EPS;
        boolean rightAngle = Math.abs(angleA - 90.0) < EPS
                || Math.abs(angleB - 90.0) < EPS
                || Math.abs(angleC - 90.0) < EPS;

        if (isoSides && rightAngle) {
            return "рівнобедрений прямокутний";
        }
        if (isoSides) {
            return "рівнобедрений";
        }
        if (rightAngle) {
            return "прямокутний";
        }
        return "довільний різносторонній";
    }

    @Override
    public String toString() {
        return String.format(
            "Triangle{sides=[%.2f, %.2f, %.2f], angles=[%.2f°, %.2f°, %.2f°], type=%s}",
            sideA, sideB, sideC, angleA, angleB, angleC, getKind()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle other)) return false;
        return Math.abs(sideA - other.sideA) < EPS
                && Math.abs(sideB - other.sideB) < EPS
                && Math.abs(sideC - other.sideC) < EPS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(sideA * 1000), Math.round(sideB * 1000), Math.round(sideC * 1000));
    }
}