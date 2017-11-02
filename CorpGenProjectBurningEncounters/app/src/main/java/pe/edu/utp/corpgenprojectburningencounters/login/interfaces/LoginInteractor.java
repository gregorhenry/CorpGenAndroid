package pe.edu.utp.corpgenprojectburningencounters.login.interfaces;

import android.support.v4.app.FragmentActivity;

/**
 * Created by root on 10/31/17.
 */

public interface LoginInteractor {

    void checkSession();
    void doSignUp(String email , String password);
    void doSignIn(String email , String password);
    void start();
    void stop();
    void doSignUpGoogle(String token ,FragmentActivity activity);
    void doSignUpFacebook(String token , FragmentActivity activity);
    void cerrarSession();
}
