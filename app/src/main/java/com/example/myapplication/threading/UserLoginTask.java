package com.example.myapplication.threading;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.myapplication.R;
import com.example.myapplication.activity.LoginActivity;
import com.example.myapplication.activity.ProfileActivity;

/**
 * Represents an asynchronous login/registration task used to authenticate
 * the user.
 */
public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

    private final String mEmail;
    private final String mPassword;
    private final LoginActivity mActivity;

    public UserLoginTask(String email, String password, LoginActivity activity) {
        mEmail = email;
        mPassword = password;
        mActivity = activity;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        // TODO: attempt authentication against a network service.

        try {
            // Simulate network access.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return false;
        }

        for (String credential : LoginActivity.DUMMY_CREDENTIALS) {
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
        mActivity.mAuthTask = null;
        mActivity.showProgress(false);

        if (success) {
            Intent intent = new Intent(mActivity, ProfileActivity.class);
            mActivity.startActivity(intent);
        } else {
            mActivity.mPasswordView.setError(mActivity.getString(R.string.error_incorrect_password));
            mActivity.mPasswordView.requestFocus();
        }
    }

    @Override
    protected void onCancelled() {
        mActivity.mAuthTask = null;
        mActivity.showProgress(false);
    }
}
