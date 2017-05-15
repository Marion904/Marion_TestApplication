package com.example.wilder.marion_testapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

public class MainActivity extends AppCompatActivity {
    String name, surname, imageUrl;
    TextView nameAndSurname;
    ImageView imageProfile;
    DownLoadImage avatar;
    Button logOut;

    private ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logOut = (Button) findViewById(R.id.logout_button);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        shareDialog = new ShareDialog(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ShareLinkContent content = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse("https://developers.facebook.com"))
                        .build();
                shareDialog.show(content);
            }
        });

        nameAndSurname = (TextView) findViewById(R.id.textView_nameAndSurname);
        imageProfile =(ImageView) findViewById(R.id.profile_image);

        Bundle bundle = getIntent().getExtras();
        name = bundle.get("name").toString();
        surname = bundle.get("surname").toString();
        imageUrl = bundle.get("picture").toString();

        StringBuilder sb = new StringBuilder();

        sb.append(name);
        sb.append(" ");
        sb.append(surname);
        nameAndSurname.setText(sb.toString());

        avatar = new DownLoadImage(imageProfile);
        avatar.execute(imageUrl);
    }

}
