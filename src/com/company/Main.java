package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String str;
        Scanner scanner = new Scanner(System.in);
        str = scanner.nextLine();
        scanner.close();
        System.out.println(calc(str));

    }

    public static String calc(String input) throws Exception {
        String result = "";
        String operation = "+-*/";
        String oper;
        String[] strings = input.split(" ");

        if (strings.length != 3) {
            throw new Exception("Неправильное выражение!");
        }

        oper = strings[1];

        if (!operation.contains(oper)) {
            throw new Exception("Нет такой операции!");
        }

        if (isNumeric(strings[0]) && isNumeric(strings[2])) {
            int num1 = Integer.parseInt(strings[0]);
            int num2 = Integer.parseInt(strings[2]);

            if ((num1 <= 0 || num1 > 10) || (num2 <= 0 || num2 > 10)) {
                throw new Exception("Числа только от 1 до 10!");
            }

            switch (oper) {
                case "+" -> result = String.valueOf(num1 + num2);
                case "-" -> result = String.valueOf(num1 - num2);
                case "*" -> result = String.valueOf(num1 * num2);
                case "/" -> result = String.valueOf(num1 / num2);
            }
        } else if (!isNumeric(strings[0]) && !isNumeric(strings[2])) {
            int num1 = romToArab(strings[0]);
            int num2 = romToArab(strings[2]);

            switch (oper) {
                case "+":
                    result = arabToRom(num1 + num2);
                    break;
                case "-":
                    if (num1 - num2 <= 0) {
                        throw new Exception("В римской системе нет отрицательных чисел!");
                    } else {
                        result = arabToRom(num1 - num2);
                    }
                    break;
                case "*":
                    result = arabToRom(num1 * num2);
                    break;
                case "/":
                    result = arabToRom(num1 / num2);
                    break;
            }

        }
        
        return result;
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }   catch (Exception e) {
            return false;
        }
    }

    public static int romToArab(String num) throws Exception {
        int result = 0;
        int i = 0;
        List<Roman> romanList = Roman.getReverseSortedValues();
        while (num.length() > 0 && i < romanList.size()) {
            Roman symbol = romanList.get(i);
            if (num.startsWith(symbol.name())) {
                result += symbol.getValue();
                num = num.substring(symbol.name().length());
            } else {
                i++;
            }

        }

        if (num.length() > 0) {
            throw new Exception("Не возможно преобразовать в арабское число: " + num);
        }

        return result;
    }

    public  static  String arabToRom(int num) {
        List<Roman> romanList = Roman.getReverseSortedValues();
        int i = 0;
        String sb = "";

        while (num > 0) {
            Roman currentSymbol = romanList.get(i);
            if (currentSymbol.getValue() <= num) {
                sb = sb.concat(currentSymbol.name());
                num -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb;
    }

}

