package pe.edu.utp.corpgenprojectburningencounters.login;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import pe.edu.utp.corpgenprojectburningencounters.domain.FirebaseHelper;
import pe.edu.utp.corpgenprojectburningencounters.login.eventbus.EventBus;
import pe.edu.utp.corpgenprojectburningencounters.login.eventbus.GreenRobotEventBus;
import pe.edu.utp.corpgenprojectburningencounters.login.eventbus.LoginEvent;
import pe.edu.utp.corpgenprojectburningencounters.login.interfaces.LoginRepository;
import pe.edu.utp.corpgenprojectburningencounters.login.interfaces.LoginView;

/**
 * Created by root on 10/31/17.
 */

public class LoginRepositoryImpl implements LoginRepository {

    private FirebaseHelper firebaseHelper;
    private Firebase dataReference;
    private Firebase myUserReference;
    private FirebaseAuth mAuth;
    private String email;
    private FirebaseAuth.AuthStateListener mAuthListener;
    LoginView loginView;


    public LoginRepositoryImpl(){
        this.firebaseHelper = FirebaseHelper.getInstance();
        this.dataReference = firebaseHelper.getDataReference();
        this.mAuth = firebaseHelper.getInstanceAuth();
    }


    @Override
    public void signUp(String email, String password) {
        Log.e("LoginRepositoryImpl","signup");

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    postEvent(LoginEvent.onSignInSuccess);
                }else{
                    String msj = task.getException().getLocalizedMessage();
                    postEvent(LoginEvent.onSignInError, msj);
                }
            }
        });

    }

    @Override
    public void signIn(String email, String password) {
        Log.e("LoginRepositoryImpl","signIn");

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Task<AuthResult> task1 = task;
                if(task1.isSuccessful()){
                    postEvent(LoginEvent.onSignInSuccess);
                }
                else{
                    String msg = task.getException().getLocalizedMessage();
                    postEvent(LoginEvent.onSignInError , msg);
                }
            }
        });
    }

    @Override
    public void checkSession() {

        Log.d("LoginRepositoryImpl","checkSession");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser() ;
                if(user != null){
                    postEvent(LoginEvent.onSignInSuccess);
                    Log.d("LoginRepositoryImpl",user.getEmail());
                }
                else {
                    postEvent(LoginEvent.onFailedToRecoverSession);
                }
            }
        };



    }

    @Override
    public void startListener() {

    }

    @Override
    public void stopListener() {
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void SignInGoogle(String token, FragmentActivity activity) {

        Log.d("xxx", "firebaseAuthWithGoogle:" + token);
        Fragment d ;
        AuthCredential credential = GoogleAuthProvider.getCredential(token,null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("xxx", "signInWithCredential:onComplete:" + task.isSuccessful());
                        if (task.isSuccessful()) {
                            Log.w("xx", "signInWithCredential", task.getException());
                            postEvent(LoginEvent.onSignInSuccess);
                        }
                        else{
                            postEvent(LoginEvent.onFailedToRecoverSession);
                        }
                        // ..   .
                    }
                });

    }

    @Override
    public void SignInFaceebook(String token, FragmentActivity activity) {

        Log.d("xxx", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        Log.d("xxx", "signInWithCredential:onComplete:" + task.isSuccessful());
                        if (task.isSuccessful())
                            postEvent(LoginEvent.onSignInSuccess);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("xxx", "signInWithCredential", task.getException());
                            // deslogearse y mostrar mensaje personalizado !!
                        }
                    }
                });

    }

    @Override
    public void cerrarSession() {

        Log.d("xxx", "getProviderId" + mAuth.getCurrentUser().getProviders().get(0));
        String provider = mAuth.getCurrentUser().getProviders().get(0);
        if(provider.equals("google.com")){
            postEvent(LoginEvent.onSignOffGoogle);

        }
        else if(provider.equals("facebook.com")){
            postEvent(LoginEvent.onSignOffFb);

        }
        else{
            postEvent(LoginEvent.onSignOff);

        }
        FirebaseAuth.getInstance().signOut();
    }

    private void postEvent(int type , String errorMessage){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if(errorMessage !=null){
            loginEvent.setErrorMessage(errorMessage);
        }
        EventBus eventeBus = GreenRobotEventBus.getInstance();
        eventeBus.post(loginEvent);

    }

    private void postEvent(int type){
        postEvent(type, null);
    }

}
