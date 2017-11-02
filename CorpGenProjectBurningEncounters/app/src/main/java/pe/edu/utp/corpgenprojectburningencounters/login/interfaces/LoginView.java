package pe.edu.utp.corpgenprojectburningencounters.login.interfaces;

import android.support.v4.app.FragmentActivity;

import com.facebook.CallbackManager;

/**
 * Created by root on 10/31/17.
 */

public interface LoginView {
    void enableInputs () ;
    void disableInput() ;
    void showpProgress();
    void hideProgress();
    void handleSignUp();
    void handleSignIn();
    void navigateToMainScreen();
    void loginError(String error);
    void newUserSuccess();
    void newUserError(String error);
    void setReadPermissionsFB();
    void buttonFacebook(CallbackManager callback , FragmentActivity activity);
}
