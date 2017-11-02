package pe.edu.utp.corpgenprojectburningencounters.login.eventbus;

/**
 * Created by root on 10/31/17.
 */

public class LoginEvent {

    public final static int onSignInError = 0 ;
    public final static int onSignUpError = 1 ;
    public final static int onSignInSuccess = 2 ;
    public final static int onSignUpSuccess = 3 ;
    public final static int onFailedToRecoverSession = 4 ;
    public final static int RC_SIGN_IN = 9001;

    //menu
    public final static int onSignOff = 5 ;
    public final static int onSignOffFb = 6 ;
    public final static int onSignOffGoogle = 7 ;


    private int eventType ;
    private String errorMessage ;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
