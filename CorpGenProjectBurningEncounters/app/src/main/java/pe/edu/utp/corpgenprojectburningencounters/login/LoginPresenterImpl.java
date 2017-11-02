package pe.edu.utp.corpgenprojectburningencounters.login;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import pe.edu.utp.corpgenprojectburningencounters.login.eventbus.EventBus;
import pe.edu.utp.corpgenprojectburningencounters.login.eventbus.GreenRobotEventBus;
import pe.edu.utp.corpgenprojectburningencounters.login.eventbus.LoginEvent;
import pe.edu.utp.corpgenprojectburningencounters.login.interfaces.LoginInteractor;
import pe.edu.utp.corpgenprojectburningencounters.login.interfaces.LoginPresenter;
import pe.edu.utp.corpgenprojectburningencounters.login.interfaces.LoginView;
import pe.edu.utp.corpgenprojectburningencounters.login.interfaces.MenuView;

/**
 * Created by root on 10/31/17.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;
    private LoginInteractor loginInteractor;
    private EventBus eventBus;
    private GoogleApiClient mGoogleApiClient;
    private CallbackManager callbackManager;
    private MenuView menuView;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }


    public LoginPresenterImpl(MenuView menuView) {
        this.menuView = menuView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }


    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.loginView = null;
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticatedUser() {
        if (loginView != null) {
            loginView.disableInput();
            loginView.showpProgress();

        }
        loginInteractor.checkSession();
    }

    @Override
    public void validateLogin(String email, String password) {
        if (validateEmpty(email, password)) {
            if (loginView != null) {
                loginView.disableInput();
                loginView.showpProgress();
            }

            loginInteractor.doSignIn(email, password);
        }
    }

    @Override
    public void registerNewUser(String email, String password) {
        if (validateEmpty(email, password)) {
            if (loginView != null) {
                loginView.disableInput();
                loginView.showpProgress();
            }
            loginInteractor.doSignUp(email, password);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void onEventMainThread(LoginEvent event) {

        switch (event.getEventType()) {
            case LoginEvent.onSignInSuccess:
                OnSignInSuccess();
                break;
            case LoginEvent.onSignUpError:
                OnSignUpError(event.getErrorMessage());
                break;
            case LoginEvent.onSignInError:
                OnSignInError(event.getErrorMessage());
                break;
            case LoginEvent.onSignUpSuccess:
                OnSignUpSuccess();
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
            case LoginEvent.onSignOff:
                menuView.returnLogin();
                break;
            case LoginEvent.onSignOffFb:
                cerrarSessionFB();
                break;
            case LoginEvent.onSignOffGoogle:
                cerrarSessionGmail();
                break;
        }
    }

    @Override
    public void onInGoogle(FragmentActivity fragmento, GoogleApiClient.OnConnectionFailedListener listener) {
        //TODO REVISAR TOKEN
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("888531302951-i7ejkk0ddun87tn5ae7vffqe5lb48g3j.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(fragmento)
                .enableAutoManage(fragmento, listener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public void onClickGoogle(FragmentActivity fragmento) {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        fragmento.startActivityForResult(signInIntent, LoginEvent.RC_SIGN_IN);


    }

    @Override
    public void onInFacebook(FragmentActivity fragmentActivity) {
        loginView.setReadPermissionsFB();
        FacebookSdk.sdkInitialize(fragmentActivity.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        loginView.buttonFacebook(callbackManager, fragmentActivity);
    }

    @Override
    public void onClickFacebook(String token, FragmentActivity activity) {

        loginInteractor.doSignUpFacebook(token, activity);
        loginView.navigateToMainScreen();


    }

    @Override
    public void onStart() {
        loginInteractor.start();
    }

    @Override
    public void onStop() {
        loginInteractor.stop();
    }

    @Override
    public void onResult(int requestCode, int resultCode, Intent data, FragmentActivity activity) {
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == LoginEvent.RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result,activity);
        }
        else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
        loginView.navigateToMainScreen();
    }

    @Override
    public void cerrarSession() {

        loginInteractor.cerrarSession();

    }

    public void onFailedToRecoverSession() {
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
        }}





    private boolean validateEmpty(String email , String password){
        if(email.isEmpty() || password.isEmpty()) {
            loginView.loginError("error");
            return false;
        }
        else
            return true ;
    }

    private void OnSignInSuccess(){
        if(loginView != null){

            loginView.navigateToMainScreen();
        }
    }

    private void OnSignUpSuccess(){
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs() ;
            loginView.newUserSuccess();
        }
    }

    private void OnSignInError(String error){
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }

    private void OnSignUpError(String error){
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.newUserError(error);
        }
    }

    private void handleSignInResult(GoogleSignInResult result , FragmentActivity activity) {
        Log.d("xxx", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            firebaseAuthWithGoogle(acct,activity);

        } else {
            // Signed out, show unauthenticated UI.
            FirebaseAuth.getInstance().signOut();
            Auth.GoogleSignInApi.signOut(mGoogleApiClient);
        }
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct , FragmentActivity activity) {
        Log.d("xxx", "firebaseAuthWithGoogle:" + acct.getId());

        loginInteractor.doSignUpGoogle(acct.getIdToken(),activity);


    }

    public void cerrarSessionGmail(){
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        menuView.returnLogin();
                    }
                });
    }

    public void cerrarSessionFB(){
        LoginManager.getInstance().logOut();
        menuView.returnLogin();
    }

}
