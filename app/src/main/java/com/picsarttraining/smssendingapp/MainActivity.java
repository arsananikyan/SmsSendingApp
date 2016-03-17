package com.picsarttraining.smssendingapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView messageText;
    private TextView phoneText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        messageText = (TextView) findViewById(R.id.message_text_id);
        phoneText = (TextView) findViewById(R.id.phone_text_id);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_button:
                sendSMS();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void sendSMS()
    {
        String smsBody = messageText.getText().toString();
        String phoneNumber = phoneText.getText().toString();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("smsto:" + phoneNumber));
        intent.setType("vnd.android-dir/mms-sms");
        intent.putExtra("sms_body", smsBody);
        intent.putExtra("address", phoneNumber);
        intent.putExtra(Intent.EXTRA_TEXT, smsBody);
        intent.putExtra("exit_on_sent", true);
        startActivity(intent);
    }
}
