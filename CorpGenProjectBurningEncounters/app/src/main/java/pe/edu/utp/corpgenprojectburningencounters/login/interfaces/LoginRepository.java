package pe.edu.utp.corpgenprojectburningencounters.login.interfaces;

import android.support.v4.app.FragmentActivity;

/**
 * Created by root on 10/31/17.
 */

public interface LoginRepository {

    void signUp(String email , String password);
    void signIn(String email , String password);
    void checkSession();

    void startListener();
    void stopListener();

    void SignInGoogle(String token ,  FragmentActivity activity);
    void SignInFaceebook(String token , FragmentActivity activity);

    void cerrarSession();
}
