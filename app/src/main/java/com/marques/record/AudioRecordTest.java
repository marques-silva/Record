package com.marques.record;
/*
 * The application needs to have the permission to write to external storage
 * if the output file is written to the external storage, and also the
 * permission to record audio. These permissions must be set in the
 * application's AndroidManifest.xml file, with something like:
 *
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * <uses-permission android:name="android.permission.RECORD_AUDIO" />
 *
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.net.Uri;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;

public class AudioRecordTest extends Activity {
    RecordAudio rec = new RecordAudio();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button gravar, parar;
        gravar = (Button) findViewById(R.id.gravar);
        parar = (Button) findViewById(R.id.parar);


        gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent("GRAVAR");
                startService(it);
                // Log.i("script","botao gravar");
                Log.i("script", "reboot");
                //Runtime.getRuntime().exec(new String[]{"su","-c","reboot"});
                // Runtime.getRuntime().exec(new String[]{"/system/bin/","-c","reboot now"});
                // Runtime.getRuntime().exec("/system/bin/reboot.sh");
                // Runtime.getRuntime().exec(new String[]{"su","-c","reboot"});
                //sendEmail();
                Log.i("script", "email enviado");

            }

            ;
        });


        parar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //rec.onRecord(false);
                Intent it = new Intent("GRAVAR");
                stopService(it);
                Log.i("script", "botao parar");
            }
        });


    }

    protected void sendEmail() {
        Log.i("script", "Send email");

        String[] TO = {"marques369@gmail.com"};
        String[] CC = {"mcmohd@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("script", "Finished sending email...");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(AudioRecordTest.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
