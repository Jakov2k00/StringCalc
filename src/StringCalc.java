
/*
    StringCalc for Kata.Academy
    Author: Jakov2k00
 */

import java.io.IOException;
import java.util.Scanner;
import static java.lang.Integer.*;

public class StringCalc {
    static String input;
    static char oper;
    static String[] str;

    public static void main(String[] args) throws IOException, NumberFormatException {

        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();

        System.out.println("Это - строковый калькулятор. Допустимый формат выражений: \"a\" + \"b\", \"a\" - \"b\", \"a\" * b, \"a\" / b.");
        System.out.println("Введи выражение в поле ниже:");

        input = scanner.nextLine();
        input = input.replace(" ", "");

        System.out.println("Input: ");
        System.out.println(input);
        System.out.println("\nOutput: ");

        if (input.contains("+")) {
            str = input.split("\\+");
            oper = '+';
        } else if (input.contains("-")) {
            str = input.split("-");
            oper = '-';
        } else if (input.contains("*")) {
            str = input.split("\\*");
            oper = '*';
        } else if (input.contains("/")) {
            str = input.split("/");
            oper = '/';
        } else throw new IOException("Неверный знак операции!");


        if (str[0].matches("[0-9]")) {
            throw new NumberFormatException("Неверный формат вводимых данных!");
        }


        if (oper == '*' || oper == '/') {
            if (str[1].contains("\"")) throw new IOException("Неверный формат выражения!");
        }

        for (int i = 0; i < str.length; i++) {
            str[i] = str[i].replace("\"", "");
        }

        if ((str[0].length() > 10) || (str[1].length() > 10)) {
            throw new IOException("Длина введенной строки не может превышать 10 символов!");
        }

        switch(oper) {
            case '+': {
                printer.printFmt(str[0] + str[1]);
            }
            case '*': {
                int num = parseInt(str[1]);
                if (num < 1 || num > 10) {
                    throw new IOException("Допустимо использовать числа от 1 до 10!");
                }
                int multi = parseInt(str[1]);
                String result = "";
                for (int i = 0; i < multi; i++) {
                    result = result + str[0];
                }
                printer.printFmt(result);
            }
            case '-': {
                int idx = str[0].indexOf(str[1]);
                if (idx == -1) {
                    printer.printFmt(str[0] + "-" + str[1]);
                } else {
                    String minus = str[0].substring(0, idx);
                    minus = minus + str[0].substring(idx + str[1].length());
                    printer.printFmt(minus);
                }
            }
            default: {
                int num = parseInt(str[1]);
                if (num < 1 || num > 10) {
                    throw new IOException("Допустимо использовать числа от 1 до 10!");
                }
                int div = str[0].length() / parseInt(str[1]);
                String divStr = str[0].substring(0, div);
                printer.printFmt(divStr);
            }
        }
    }
}

class Printer {
    void printFmt(String outputStr) {
        if (outputStr.length() > 40) {
            System.out.println("\"" + outputStr.substring(0,40) + "...\"");
        } else {
            System.out.println("\"" + outputStr + "\"");
        }
    }
}
