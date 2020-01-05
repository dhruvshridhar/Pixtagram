package in.blogspot.tecnopandit.pixtagram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class UserFeed extends AppCompatActivity {
LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);
        Intent intent=getIntent();
        String msg=intent.getStringExtra("username");
        linearLayout=findViewById(R.id.l2layout);

        setTitle(msg+"'s Feed");
        ParseQuery<ParseObject> file=new ParseQuery<ParseObject>("Images");
        file.whereEqualTo("username",msg);
        file.orderByDescending("createdAt");
        file.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e==null&&objects.size()>0)
                {
                    for (ParseObject object:objects)
                    {
                        ParseFile file1=(ParseFile)object.get("image");
                        file1.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if (e==null&&data!=null)
                                {
                                    Bitmap img=BitmapFactory.decodeByteArray(data,0,data.length);
                                    ImageView imageView=new ImageView(getApplicationContext());
                                    imageView.setLayoutParams(new ViewGroup.LayoutParams(
                                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                                    ));
                                    imageView.setImageBitmap(img);
                                    linearLayout.addView(imageView);
                                }
                            }
                        });
                    }
                }




            }
        });

    }
}
