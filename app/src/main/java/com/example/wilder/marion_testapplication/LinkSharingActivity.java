package com.example.wilder.marion_testapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

public class LinkSharingActivity extends AppCompatActivity {

    private ShareDialog shareDialog;
    private EditText linkSharing;
    private Button sharingButton;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_sharing);
        linkSharing = (EditText) findViewById(R.id.shareLinkEditText);
        sharingButton = (Button) findViewById(R.id.link_sharing_button);
        shareDialog = new ShareDialog(this);

        sharingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = linkSharing.getText().toString();
                if(url == null){
                    Toast.makeText(LinkSharingActivity.this, getString(R.string.noLink),Toast.LENGTH_SHORT).show();
                }else{
                    ShareLinkContent content = new ShareLinkContent.Builder()
                            .setContentUrl(Uri.parse("https://developers.facebook.com"))
                            .build();
                    shareDialog.show(content);
                    startActivity(new Intent(LinkSharingActivity.this, MainActivity.class));
                }
            }
        });


    }
}
