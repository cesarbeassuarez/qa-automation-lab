package com.cesar.qa.tests.login;

public class LoginTestRunner {
    public static void main(String[] args) {

        LoginPositiveTests positive = new LoginPositiveTests();
        positive.loginValido_deberiaIngresar();

        LoginNegativeTests negative = new LoginNegativeTests();
        negative.loginInvalido_passwordIncorrecta_muestraError();
        negative.loginInvalido_camposVacios_muestraError();

    }
}
