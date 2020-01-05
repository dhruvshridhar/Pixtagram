package in.blogspot.tecnopandit.pixtagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpAct extends AppCompatActivity {
EditText username,password,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);

    }
    public void signup(View view)
    {
        if (TextUtils.isEmpty(username.getText().toString())||TextUtils.isEmpty(password.getText().toString())||TextUtils.isEmpty(email.getText().toString()))
        {
            Toast.makeText(getApplicationContext(),"Username/Password/Email cannot be empty",Toast.LENGTH_SHORT).show();
        }
        else
        {
            ParseUser parseUser=new ParseUser();
            parseUser.setUsername(username.getText().toString());
            parseUser.setPassword(password.getText().toString());
            parseUser.setEmail(email.getText().toString());
            parseUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e==null)
                    {
                        Toast.makeText(getApplicationContext(),"Sign up successful",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
