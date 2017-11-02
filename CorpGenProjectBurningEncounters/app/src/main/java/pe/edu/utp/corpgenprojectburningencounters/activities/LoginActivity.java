package pe.edu.utp.corpgenprojectburningencounters.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.wangyuwei.particleview.ParticleView;
import pe.edu.utp.corpgenprojectburningencounters.R;
import pe.edu.utp.corpgenprojectburningencounters.login.LoginPresenterImpl;
import pe.edu.utp.corpgenprojectburningencounters.login.interfaces.LoginPresenter;
import pe.edu.utp.corpgenprojectburningencounters.login.interfaces.LoginView;


public class LoginActivity extends FragmentActivity implements LoginView, GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    @BindView(R.id.email) EditText email;
    @BindView(R.id.Password) EditText password;
    @BindView(R.id.wrapperPassword) TextInputLayout wrapperPassword;
    @BindView(R.id.btnIngresar) Button btnIngresar;
    @BindView(R.id.btnNuevo) Button btnNuevo;
    @BindView(R.id.layoutButtons) LinearLayout layoutButtons;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.idcontenedor) RelativeLayout contenedor;
    @BindView(R.id.view) TextInputLayout view;
    @BindView(R.id.btn_facebook) LoginButton btnFacebook;
    @BindView(R.id.btn_join) SignInButton btnJoin;
    private LoginPresenter loginPresenter;



    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.login_activity);


            ButterKnife.bind(this);

            loginPresenter = new LoginPresenterImpl(this);
            loginPresenter.onCreate();

            loginPresenter.checkForAuthenticatedUser();

            enableInputs();
            hideProgress();

            loginPresenter.onInFacebook(this);
            loginPresenter.onInGoogle(this, this);


    }


    @Override
    protected void onStart() {
        super.onStart();
        loginPresenter.onStart();
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginPresenter.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        loginPresenter.onResult(requestCode, resultCode, data, this);

    }

    @Override
    public void enableInputs() {
        setInput(true);
    }

    @Override
    public void disableInput() {
        setInput(false);
    }

    @Override
    public void showpProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void handleSignUp() {
        loginPresenter.registerNewUser(email.getText().toString(),
                password.getText().toString());

    }

    @Override
    public void handleSignIn() {
        loginPresenter.validateLogin(email.getText().toString(),
                password.getText().toString());

    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, BurningMainActivity.class));
    }

    @Override
    public void loginError(String error) {
        password.setText("");
        String message = getString(R.string.login_error_validation);
        password.setError(message);
        email.setError(message);
        Snackbar.make(contenedor, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(contenedor, getString(R.string.login_success), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void newUserError(String error) {
        password.setText("");
        password.setError(error);
    }

    @Override
    public void setReadPermissionsFB() {
        btnFacebook.setReadPermissions("email", "public_profile");
    }

    @Override
    public void buttonFacebook(CallbackManager callback, final FragmentActivity activity) {

        btnFacebook.registerCallback(callback,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        //    handleFacebookAccessToken(loginResult.getAccessToken());
                        loginPresenter.onClickFacebook(loginResult.getAccessToken().getToken(), activity);

                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

    }


    private void setInput(boolean enabled) {
        email.setEnabled(enabled);
        password.setEnabled(enabled);
        btnIngresar.setEnabled(enabled);
        btnNuevo.setEnabled(enabled);
    }


    @OnClick({R.id.btnIngresar, R.id.btnNuevo, R.id.btn_join})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnIngresar:
                handleSignIn();

                break;
            case R.id.btnNuevo:
                handleSignUp();

                break;
            case R.id.btn_join:
                onClickGoogle();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void onClickGoogle() {
        loginPresenter.onClickGoogle(this);

    }


}
