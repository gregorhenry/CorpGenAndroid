package pe.edu.utp.corpgenprojectburningencounters.login;

import android.support.v4.app.FragmentActivity;

import pe.edu.utp.corpgenprojectburningencounters.login.interfaces.LoginInteractor;
import pe.edu.utp.corpgenprojectburningencounters.login.interfaces.LoginRepository;

/**
 * Created by root on 10/31/17.
 */

public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository loginRepository;

    public LoginInteractorImpl() {
        loginRepository = new LoginRepositoryImpl();
    }




    @Override
    public void checkSession() {
            loginRepository.checkSession();
    }

    @Override
    public void doSignUp(String email, String password) {
            loginRepository.signUp(email,password);
    }

    @Override
    public void doSignIn(String email, String password) {
            loginRepository.signIn(email,password);
    }

    @Override
    public void start() {
            loginRepository.startListener();
    }

    @Override
    public void stop() {
            loginRepository.stopListener();
    }

    @Override
    public void doSignUpGoogle(String token, FragmentActivity activity) {
            loginRepository.SignInGoogle(token,activity);
    }

    @Override
    public void doSignUpFacebook(String token, FragmentActivity activity) {
            loginRepository.SignInFaceebook(token,activity);

    }

    @Override
    public void cerrarSession() {
        loginRepository.cerrarSession();
    }
}
