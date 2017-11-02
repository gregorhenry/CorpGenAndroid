package pe.edu.utp.corpgenprojectburningencounters.login.interfaces;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.api.GoogleApiClient;

import pe.edu.utp.corpgenprojectburningencounters.login.eventbus.LoginEvent;

/**
 * Created by root on 10/31/17.
 */

public interface LoginPresenter {

    void onCreate();
    void onDestroy();



    void checkForAuthenticatedUser();
    void validateLogin(String email , String password);
    void registerNewUser(String email , String password);
    void onEventMainThread(LoginEvent event);

    void onInGoogle(FragmentActivity fragmento , GoogleApiClient.OnConnectionFailedListener listener);
    void onClickGoogle(FragmentActivity fragmento);


    void onInFacebook(FragmentActivity fragmentActivity);
    void onClickFacebook(String token , FragmentActivity activity);


    void onStart();
    void onStop();
    void onResult(int requestCode, int resultCode, Intent data, FragmentActivity activity);


    void cerrarSession();
}
