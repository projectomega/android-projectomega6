package com.example.andr_projectomega6;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
 
public class LoginActivity extends Activity {
	public String emailText;
	public String passwordText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.activity_login);
 
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        
        EditText EmailText = (EditText) findViewById(R.id.email);
        EditText PasswordText = (EditText) findViewById(R.id.password);
        emailText = EmailText.toString();
        passwordText = PasswordText.toString();
        Button LoginButton = (Button) findViewById(R.id.login); 
        LoginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParseUser.logInInBackground(emailText, passwordText, new LogInCallback() {
					  public void done(ParseUser user, ParseException e) {
					    if (user != null) {
					      // Hooray! The user is logged in.
			                Intent i = new Intent(getApplicationContext(), SearchActivity.class);
			                startActivity(i);
					    } else {
					      // Signup failed. Look at the ParseException to see what happened.
					    }
					  }

					});
				
			}
		});
        // Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
            }
        });
    }
}
