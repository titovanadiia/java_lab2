package java_lab2;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US); // щоб працювали і крапки, і коми при бажанні нижче

        System.out.println("=== ЗАВДАННЯ 1 (п. 14): Triangle ===");
        Triangle t1 = new Triangle();
        System.out.println("Базовий об'єкт: " + t1);
        System.out.print("Введи 3 сторони трикутника (a b c): ");
        double a1 = readDouble(sc);
        double b1 = readDouble(sc);
        double c1 = readDouble(sc);
        Triangle tUser = new Triangle(a1, b1, c1);
        System.out.println(tUser);
        System.out.printf("Периметр: %.2f, Площа: %.2f%n", tUser.getPerimeter(), tUser.getArea());
        System.out.println("Висоти: " + Arrays.toString(tUser.getHeights()));
        System.out.println("tUser.equals(t1): " + tUser.equals(t1));
        System.out.println("Всього створено Triangle: " + Triangle.getCreatedCount());

        System.out.println("\n=== ЗАВДАННЯ 2 (п. 4): SimpleTriangle ===");
        System.out.print("Введи 3 сторони другого трикутника (a b c): ");
        double sa = readDouble(sc);
        double sb = readDouble(sc);
        double scSide = readDouble(sc);
        SimpleTriangle stUser = new SimpleTriangle(sa, sb, scSide);
        SimpleTriangle stDefault = new SimpleTriangle();
        System.out.println(stUser);
        System.out.println(stDefault);
        System.out.println("stUser.equals(stDefault): " + stUser.equals(stDefault));
        System.out.println("Порівняння (stUser з stDefault): " + stUser.compareToTriangle(stDefault));
        System.out.println("Всього створено SimpleTriangle: " + SimpleTriangle.getInstancesCount());

        System.out.println("\n=== ЗАВДАННЯ 3 (п. 9): Complex ===");
        Complex z1;
        Complex z2;
        if (args.length >= 4) {
            z1 = new Complex(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
            z2 = new Complex(Double.parseDouble(args[2]), Double.parseDouble(args[3]));
            System.out.println("(Взято 4 числа з аргументів командного рядка)");
        } else {
            System.out.print("Введи дійсну та уявну частини першого числа (re1 im1): ");
            double r1 = readDouble(sc);
            double i1 = readDouble(sc);
            System.out.print("Введи дійсну та уявну частини другого числа (re2 im2): ");
            double r2 = readDouble(sc);
            double i2 = readDouble(sc);
            z1 = new Complex(r1, i1);
            z2 = new Complex(r2, i2);
        }

        System.out.println("z1 = " + z1 + ", |z1| = " + z1.modulus() + ", arg(z1) = " + z1.argument());
        System.out.println("z2 = " + z2 + ", |z2| = " + z2.modulus() + ", arg(z2) = " + z2.argument());
        System.out.println("z1 + z2 = " + z1.add(z2));
        System.out.println("z1 - z2 = " + z1.subtract(z2));
        System.out.println("z1 * z2 = " + z1.multiply(z2));
        System.out.println("z1 / z2 = " + z1.divide(z2));
        System.out.println("z1.equals(z2): " + z1.equals(z2));
        System.out.println("Всього створено Complex: " + Complex.getCreatedNumbers());

        sc.close();
    }

    private static double readDouble(Scanner sc) {
        String token = sc.next().replace(',', '.');
        return Double.parseDouble(token);
    }
}