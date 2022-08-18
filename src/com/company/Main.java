package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static int a;
    public static int b;
    public static HashMap<String, Integer> romanToArabic = new HashMap<>();
    public static HashMap<Integer, String> arabicToRoman = new HashMap<>();

    public static void main(String[] args) {
        romanToArabic.put("I", 1);
        romanToArabic.put("II", 2);
        romanToArabic.put("III", 3);
        romanToArabic.put("IV", 4);
        romanToArabic.put("V", 5);
        romanToArabic.put("VI", 6);
        romanToArabic.put("VII", 7);
        romanToArabic.put("VIII", 8);
        romanToArabic.put("IX", 9);
        romanToArabic.put("X", 10);
        arabicToRoman.put(1, "I");
        arabicToRoman.put(2, "II");
        arabicToRoman.put(3, "III");
        arabicToRoman.put(4, "IV");
        arabicToRoman.put(5, "V");
        arabicToRoman.put(6, "VI");
        arabicToRoman.put(7, "VII");
        arabicToRoman.put(8, "VIII");
        arabicToRoman.put(9, "IX");
        arabicToRoman.put(10, "X");
        arabicToRoman.put(20, "XX");
        arabicToRoman.put(30, "XXX");
        arabicToRoman.put(40, "XL");
        arabicToRoman.put(50, "L");
        arabicToRoman.put(60, "LX");
        arabicToRoman.put(70, "LXX");
        arabicToRoman.put(80, "LXXX");
        arabicToRoman.put(90, "XC");
        arabicToRoman.put(100, "C");

        Scanner in = new Scanner(System.in);

        System.out.print("Введите выражение с пробелами: ");
        String inputString = in.nextLine();
	    String resultString = calc(inputString.trim());
        System.out.println("Результат: " + resultString);
    }

    public static String calc(String input) {

        boolean aIsRoman = true;
        boolean bIsRoman = true;
        int result = 1;
        String [] elements = input.split(" ");

        if (elements.length > 3) {
            System.out.println("Должно быть только два числа и одна операция!");
            System.exit(0);
        }

        if (isParsable(elements[0])) {
            a = Integer.parseInt(elements[0]);
            aIsRoman = false;
        }
        else {
            if (romanToArabic.containsKey(elements[0])) {
                a = romanToArabic.get(elements[0]);
                aIsRoman = true;
            }
            else {
                System.out.println("Неверный ввод!");
                System.exit(0);
            }
        }

        if (isParsable(elements[2])) {
            b = Integer.parseInt(elements[2]);
            bIsRoman = false;
        }
        else {
            if (romanToArabic.containsKey(elements[2])) {
                b = romanToArabic.get(elements[2]);
                bIsRoman = true;
            }
            else {
                System.out.println("Неверный ввод!");
                System.exit(0);
            }
        }

        if (aIsRoman != bIsRoman) {
            System.out.println("Разный формат чисел!");
            System.exit(0);
        }

        if ((a > 10) || (b > 10) || (a < 1) || (b < 1)) {
            try {
                throw new Exception();
            } catch (Exception e2) {
                System.out.println("Числа должны быть от 1 до 10!");
                System.exit(0);
            }
        }
        else {
            switch (elements[1]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "/":
                    result = a / b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    System.out.println("Неверная операция! Доступные операции: +, -, /, *");
                    System.exit(0);
            }
        }

        if (aIsRoman) {
            if (result < 1) {
                System.out.println("Результат меньше единицы!");
                System.exit(0);
            }
            else
                input = converter(result);
        }
        else
            input = String.valueOf(result);

        return input;
    }

    static String converter(int input) {
        int hundreds = 0;
        int tens = 0;
        int units  = 0;
        String result = "";

        hundreds = input / 100;
        tens = (input % 100) / 10;
        units = input % 10;

        if (arabicToRoman.containsKey(hundreds * 100))
            result = arabicToRoman.get(hundreds * 100);
        if (arabicToRoman.containsKey(tens * 10))
            result = result + arabicToRoman.get(tens * 10);
        if (arabicToRoman.containsKey(units))
            result = result + arabicToRoman.get(units);

        return result;
    }

    static boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
