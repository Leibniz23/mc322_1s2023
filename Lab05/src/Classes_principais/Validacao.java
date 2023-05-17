package Classes_principais;

public class Validacao {
    public static boolean validarCPF(String cpf) {
        char[] v_cpf = cpf.toCharArray();
        int n = cpf.length();
        int n_semdigito = n-2;
        if (n != 11) {
            return false;
        }
        int iguais = 0;
        for (int i=1; i<n_semdigito; i++) {
            if (v_cpf[i]==v_cpf[i-1]) { // se um digito é igual ao seu antecessor
                iguais++;
            }
        }
        if (iguais == n_semdigito) { // verifica se todos os digitos são iguais
            return false;
        }

        /* Primeiro digito verificador */

        int primeiro = 0;
        for (int i=0, j=10; i<n_semdigito && j>=(n-n_semdigito); i++, j--) { // n - n_semdigito é 2
            primeiro += (Character.digit(v_cpf[i],10))*j;
        }
        primeiro = primeiro % n;
        if (primeiro < 2) {
            primeiro = 0;
        } else {
            primeiro = n - primeiro;
        }
        if (primeiro != Character.digit(v_cpf[n-2], 10)) {
            return false;
        }

        /* Segundo digito verificador */
        int segundo = 0;
        for (int i=0, j=11; i<n-1 && j>=(n-n_semdigito); i++, j--) { // i<n-1 porque agora contamos o primeiro digito verificador
            segundo += (Character.digit(v_cpf[i],10))*j;
        }
        segundo = segundo % n;
        segundo = n - segundo;
        if (segundo>=10) {
            segundo = 0;
        }
        if (segundo != Character.digit(v_cpf[n-1], 10)) {
            return false;
        }

        return true; // Se não falhou até aqui, então é válido
    }

    public static boolean validarCNPJ(String cnpj) {
        if (cnpj.length() != 14) {
            return false;
        }
    
        /* Calcula o primeiro dígito verificador */ 
        int soma = 0;
        int peso = 5;
        for (int i = 0; i < 12; i++) {
            soma += Integer.parseInt(String.valueOf(cnpj.charAt(i))) * peso;
            peso--;
            if (peso < 2) {
                peso = 9;
            }
        }
        int resto = soma % 11;
        int digito1;
        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        /* Calcula o segundo dígito verificador */
        soma = 0;
        peso = 6;
        for (int i = 0; i < 13; i++) {
            soma += Integer.parseInt(String.valueOf(cnpj.charAt(i))) * peso;
            peso--;
            if (peso < 2) {
                peso = 9;
            }
        }
        resto = soma % 11;
        int digito2;
        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }
        if (Integer.parseInt(String.valueOf(cnpj.charAt(12))) == digito1 && 
            Integer.parseInt(String.valueOf(cnpj.charAt(13))) == digito2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validarNome(String nome) {
        /*
         * Verifica se o nome passado tem apenas letras e espaços
         */

        for (int i=0; i< nome.length(); i++) {
            if (!Character.isLetter(nome.charAt(i)) && nome.charAt(i)!=' ') {
                return false;
            }
        }
        return true;   
    }
}
