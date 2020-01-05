package in.blogspot.tecnopandit.pixtagram;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {
EditText username,password;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)==PackageManager.PERMISSION_GRANTED)
            {
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        if (checkSelfPermission(Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.INTERNET},0);
            Toast.makeText(getApplicationContext(),"Thanks!",Toast.LENGTH_SHORT).show();
        }
        if(ParseUser.getCurrentUser()!=null)
        {
            showuserlist();
        }
    }
    public void loginuser(View view)
    {
        if (TextUtils.isEmpty(username.getText().toString())||TextUtils.isEmpty(password.getText().toString()))
        {
            Toast.makeText(getApplicationContext(),"Username/Password cannot be blank!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user!=null&&e==null)
                    {
                        Toast.makeText(getApplicationContext(),"Welcome: "+user.getUsername(),Toast.LENGTH_SHORT).show();
                        showuserlist();
                    }
                    else
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
    public void gotosignup(View view)
    {
        Intent intent=new Intent(getApplicationContext(),SignUpAct.class);
        startActivity(intent);
    }
    public  void showuserlist()
    {
        Intent intent=new Intent(getApplicationContext(),UsersListAct.class);
        startActivity(intent);
    }
}
