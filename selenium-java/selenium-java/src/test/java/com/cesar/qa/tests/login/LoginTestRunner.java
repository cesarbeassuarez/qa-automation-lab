package com.cesar.qa.tests.login;

public class LoginTestRunner {
    public static void main(String[] args) {

        // === TESTS ===

        LoginPositiveTests positive = new LoginPositiveTests();
        positive.loginValido_deberiaIngresar();
        positive.clickLoginSinTipear();

        LoginNegativeTests negative = new LoginNegativeTests();
        negative.loginInvalido_passwordIncorrecta_muestraError();
        negative.loginInvalido_usuarioIncorrecto_muestraError();
        negative.loginInvalido_tipearEspacios();

    }
}
