package com.stfn2.ggas.tools;

public class ToolCPFAndCNPJ {
    public static boolean validarCPF(String cpf) {
        // Remover caracteres não numéricos do CPF
        cpf = cpf.replaceAll("[^\\d]", "");

        // Verificar se o CPF tem 11 dígitos
        if (cpf.length() != 11)
            return false;

        // Verificar se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}"))
            return false;

        // Calcular o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++)
            sum += (cpf.charAt(i) - '0') * (10 - i);
        int remainder = sum % 11;
        int digit1 = (remainder < 2) ? 0 : (11 - remainder);

        // Verificar o primeiro dígito verificador
        if ((cpf.charAt(9) - '0') != digit1)
            return false;

        // Calcular o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++)
            sum += (cpf.charAt(i) - '0') * (11 - i);
        remainder = sum % 11;
        int digit2 = (remainder < 2) ? 0 : (11 - remainder);

        // Verificar o segundo dígito verificador
        return (cpf.charAt(10) - '0') == digit2;
    }

    public static boolean validarCNPJ(String cnpj) {
        // Remover caracteres não numéricos do CNPJ
        cnpj = cnpj.replaceAll("[^\\d]", "");

        // Verificar se o CNPJ tem 14 dígitos
        if (cnpj.length() != 14)
            return false;

        // Calcular o primeiro dígito verificador
        int sum = 0;
        int weight = 5;
        for (int i = 0; i < 12; i++) {
            sum += (cnpj.charAt(i) - '0') * weight;
            weight = (weight == 2) ? 9 : weight - 1;
        }
        int remainder = sum % 11;
        int digit1 = (remainder < 2) ? 0 : (11 - remainder);

        // Verificar o primeiro dígito verificador
        if ((cnpj.charAt(12) - '0') != digit1)
            return false;

        // Calcular o segundo dígito verificador
        sum = 0;
        weight = 6;
        for (int i = 0; i < 13; i++) {
            sum += (cnpj.charAt(i) - '0') * weight;
            weight = (weight == 2) ? 9 : weight - 1;
        }
        remainder = sum % 11;
        int digit2 = (remainder < 2) ? 0 : (11 - remainder);

        // Verificar o segundo dígito verificador
        return (cnpj.charAt(13) - '0') == digit2;
    }

    public static String limpar(String documento) {
        // Utilizar uma expressão regular para substituir caracteres especiais
        // por vazio
        return documento.replaceAll("[^0-9]", "");
    }
}
