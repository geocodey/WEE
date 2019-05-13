package com.example.wmseasyexpert.mvp.presenters;

import android.os.AsyncTask;

import com.example.wmseasyexpert.models.screen.BaseScreenData;
import com.example.wmseasyexpert.models.screen.ScreenResponse;
import com.example.wmseasyexpert.mvp.contracts.LoginContract;
import com.example.wmseasyexpert.network.interactors.GetScreenNetworkInteractor;
import com.example.wmseasyexpert.parser.XMLParser;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void executeLogin(String email, String password) {
        if (mAuthTask != null) {
            return;
        }
        mAuthTask = new UserLoginTask(email, password);
        mAuthTask.execute((Void) null);
    }


    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            view.showProgress(false);

            if (success) {
                login(mEmail,mPassword);
            } else {
                view.showWrongPasswordError();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            view.showProgress(false);
        }
    }

    private void login(String user, String password) {
        GetScreenNetworkInteractor.execute(new GetScreenNetworkInteractor.GetScreenResponseCallback() {
            @Override
            public void onGetScreenResponseSuccess(ScreenResponse response) {
                BaseScreenData screenData = XMLParser.parseDoc(response.getResult());
                view.displayScreen(screenData);
            }

            @Override
            public void onGetScreenResponseGenericError() {
                view.showOnGetScreenError();
            }

        });
    }
}
