package pe.edu.utp.corpgenprojectburningencounters.domain;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by root on 10/31/17.
 */

public class FirebaseHelper {

    private Firebase dataReference;
    private final static String SEPARATOR = "___";
    private final static String USER_PATH = "users";
    private final static String CONTACTS_PATH = "contacts";
    private final static String FIREBASE_URL = "https://corpgen-3ae93.firebaseio.com";
    private final static String CHAT_PATH = "chats";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private FirebaseHelper() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    public Firebase getDataReference() {
        return dataReference;
    }

    public FirebaseAuth getInstanceAuth() {
        return mAuth;
    }


    public static FirebaseHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }
}
