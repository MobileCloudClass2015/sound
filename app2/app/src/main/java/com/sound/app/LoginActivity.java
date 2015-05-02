package com.sound.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.sound.app.auth.Auth;
import com.sound.app.bonacell.BonacellAsyncTask;


public class LoginActivity extends ActionBarActivity {

    private static final String TAG = "LoginActivity";

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();
        Auth auth = intent.getParcelableExtra("auth");

        textView = (TextView) findViewById(R.id.editText);
        textView.setText(auth.toString());
        Log.d(TAG, auth.toString());

        new BonacellAsyncTask().execute();
    }

}
