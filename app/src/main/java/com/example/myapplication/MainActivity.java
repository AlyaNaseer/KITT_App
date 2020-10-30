package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.content.res.AssetFileDescriptor;
//import com.chaquo.python.*;
//import com.chaquo.python.android.AndroidPlatform;
import java.io.IOException;
import android.media.MediaPlayer;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    ImageButton AirButton, OilButton, S1Button, S2Button, P1Button, P2Button, P3Button, P4Button, AutoCruiseButton, NormalCruiseButton, PursuitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }*/

        AirButton = (ImageButton) findViewById(R.id.air);
        OilButton = (ImageButton) findViewById(R.id.oil);
        S1Button = (ImageButton) findViewById(R.id.s1);
        S2Button = (ImageButton) findViewById(R.id.s2);
        P1Button = (ImageButton) findViewById(R.id.p1);
        P2Button = (ImageButton) findViewById(R.id.p2);
        P3Button = (ImageButton) findViewById(R.id.p3);
        P4Button = (ImageButton) findViewById(R.id.p4);
        AutoCruiseButton = (ImageButton) findViewById(R.id.auto);
        NormalCruiseButton = (ImageButton) findViewById(R.id.normal);
        PursuitButton = (ImageButton) findViewById(R.id.pursuit);

        AirButton.setOnTouchListener(this);
        OilButton.setOnTouchListener(this);
        S1Button.setOnTouchListener(this);
        S2Button.setOnTouchListener(this);
        P1Button.setOnTouchListener(this);
        P2Button.setOnTouchListener(this);
        P3Button.setOnTouchListener(this);
        P4Button.setOnTouchListener(this);
        AutoCruiseButton.setOnTouchListener(this);
        NormalCruiseButton.setOnTouchListener(this);
        PursuitButton.setOnTouchListener(this);

        AutoCruiseButton.setAlpha(0.5f);
        NormalCruiseButton.setAlpha(1f);
        PursuitButton.setAlpha(0.5f);

        final MediaPlayer beep1 = MediaPlayer.create(this, R.raw.beep1);
        final MediaPlayer beep2 = MediaPlayer.create(this, R.raw.beep2);
        final MediaPlayer beep3 = MediaPlayer.create(this, R.raw.beep3);
        final MediaPlayer beep4 = MediaPlayer.create(this, R.raw.beep4);
        final MediaPlayer mp = new MediaPlayer();



        AirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beep1.start();
                stopAndPlay(R.raw.kitt_intro, mp);
            }
        });
        OilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beep2.start();
                stopAndPlay(R.raw.a_little_consideration_would_be_a_beginning, mp);
            }
        });
        S1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beep1.start();
                stopAndPlay(R.raw.anything_you_can_think_of_perform, mp);
            }
        });
        S2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beep2.start();
                stopAndPlay(R.raw.goodnight, mp);
            }
        });
        P1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beep4.start();
                stopAndPlay(R.raw.i_deal_solely_empirical_data, mp);
            }
        });
        P2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beep3.start();
                stopAndPlay(R.raw.i_dont_know_what_id_do_without_you, mp);
            }
        });
        P3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beep4.start();
                stopAndPlay(R.raw.if_it_werent_for_me_youd_be_walking, mp);
            }
        });
        P4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beep3.start();
                stopAndPlay(R.raw.were_only_human, mp);
            }
        });
        AutoCruiseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                beep1.start();
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
                startActivityForResult(intent, 10);
            }
        });
        NormalCruiseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beep2.start();
            }
        });
        PursuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beep3.start();
            }
        });
    }

//VOICE RECOGNITION

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case 10:
                    boolean commandHeard =getCommandFromResult(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS));
                    if(commandHeard){
                        break;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Sorry, I didn't catch that! Please try again", Toast.LENGTH_LONG).show();
                    }
            }
        } else {
            Toast.makeText(getApplicationContext(), "Failed to recognize speech!", Toast.LENGTH_LONG).show();
        }

    }


    // method to loop through results trying to find an operator
    private boolean getCommandFromResult(ArrayList<String> results) {
        boolean commandHeard = false;
        for (String str : results) {
            commandHeard=getCommandFromText(str);
        }
        return commandHeard;
    }

    // method to convert string operator to char
    private boolean getCommandFromText(String strCommand) {
        final MediaPlayer mp = new MediaPlayer();
        if(strCommand.contains("introduce yourself")) {
            stopAndPlay(R.raw.kitt_intro, mp);
            return true;
        }
        return false;
    }



    //Code below (stopAndPlay method) written by UdeshUK on StackOverflow:
    //https://stackoverflow.com/questions/48054515/how-to-stop-playing-sounds-on-button-click-to-start-another-sound-file/48054638#48054638
    private void stopAndPlay(int rawId, MediaPlayer mediaPlayer) {
        mediaPlayer.reset();
        AssetFileDescriptor afd = this.getResources().openRawResourceFd(rawId);
        try {
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if((v.getId() != R.id.auto) && (v.getId() != R.id.normal) && (v.getId() != R.id.pursuit)) {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
                  v.setAlpha(0.5f);
            else
                v.setAlpha(1f);
        }
        else if (v.getId() == R.id.auto){
            v.setAlpha(1f);
            NormalCruiseButton.setAlpha(0.5f);
            PursuitButton.setAlpha(0.5f);
        }
        else if (v.getId() == R.id.normal){
            v.setAlpha(1f);
            AutoCruiseButton.setAlpha(0.5f);
            PursuitButton.setAlpha(0.5f);
        }
        else{
            v.setAlpha(1f);
            AutoCruiseButton.setAlpha(0.5f);
            NormalCruiseButton.setAlpha(0.5f);
        }
            return false;
    }

}

