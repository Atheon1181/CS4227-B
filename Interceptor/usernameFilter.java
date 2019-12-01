package Interceptor;

import java.io.IOException;

public class usernameFilter implements loginCheck {
    public boolean checkInput(String username, String password) throws IOException {
        boolean allowLogin;
        loginReader reader = new loginReader();
        pseudoEncrypt encrypter = new pseudoEncrypt();
        password = encrypter.encrypt(password);
        allowLogin = reader.checkCredentials(username, password);

        return allowLogin;
        }
    }
