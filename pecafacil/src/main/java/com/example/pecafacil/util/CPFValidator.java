package com.example.pecafacil.util;

public class CPFValidator {

    public static boolean isValidCPF(String cpf) {
        if (cpf == null) return false;

        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}"))
            return false;

        try {
            int sum1 = 0, sum2 = 0;

            for (int i = 0; i < 9; i++) {
                sum1 += (cpf.charAt(i) - '0') * (10 - i);
                sum2 += (cpf.charAt(i) - '0') * (11 - i);
            }

            int check1 = 11 - (sum1 % 11);
            int check2 = 11 - ((sum2 + check1 * 2) % 11);

            check1 = (check1 > 9) ? 0 : check1;
            check2 = (check2 > 9) ? 0 : check2;

            return check1 == (cpf.charAt(9) - '0') && check2 == (cpf.charAt(10) - '0');

        } catch (Exception e) {
            return false;
        }
    }
}
