package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.content.res.AssetFileDescriptor;
import java.io.IOException;
import android.media.MediaPlayer;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import static android.speech.RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS;
import static android.speech.SpeechRecognizer.ERROR_NO_MATCH;
import static android.speech.SpeechRecognizer.ERROR_SPEECH_TIMEOUT;
//import com.chaquo.python.*;
//import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {/*implements View.OnTouchListener{*/

    ImageButton AirButton, OilButton, S1Button, S2Button, P1Button, P2Button, P3Button, P4Button, AutoCruiseButton, NormalCruiseButton, PursuitButton;
    private SpeechRecognizer speechRecognizer;
    public static final Integer RecordAudioRequestCode = 1;
    private boolean isListening;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Checking for permission to record audio
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }

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

        /**
         * Is this needed?
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
        PursuitButton.setOnTouchListener(this);*/

        AutoCruiseButton.setAlpha(0.5f);
        NormalCruiseButton.setAlpha(1f);
        PursuitButton.setAlpha(0.5f);

        final MediaPlayer beep1 = MediaPlayer.create(this, R.raw.beep1);
        final MediaPlayer beep2 = MediaPlayer.create(this, R.raw.beep2);
        final MediaPlayer beep3 = MediaPlayer.create(this, R.raw.beep3);
        final MediaPlayer beep4 = MediaPlayer.create(this, R.raw.beep4);
        final MediaPlayer mp = new MediaPlayer();

        //Voice recognition code taken from https://medium.com/voice-tech-podcast/android-speech-to-text-tutorial-8f6fa71606ac
        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speechRecognizerIntent.putExtra(EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 1500);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);


        AirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopListening(beep1, R.raw.kitt_intro, mp);
            }
        });
        OilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopListening(beep2, R.raw.a_little_consideration_would_be_a_beginning, mp);
            }
        });
        S1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopListening(beep1, R.raw.anything_you_can_think_of_perform, mp);
            }
        });
        S2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopListening(beep2, R.raw.goodnight, mp);
            }
        });
        P1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopListening(beep4, R.raw.i_deal_solely_empirical_data, mp);
            }
        });
        P2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopListening(beep3, R.raw.i_dont_know_what_id_do_without_you, mp);
            }
        });
        P3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopListening(beep4, R.raw.if_it_werent_for_me_youd_be_walking, mp);
            }
        });
        P4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopListening(beep3, R.raw.were_only_human, mp);
            }
        });
        NormalCruiseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCruiseButton.setAlpha(0.5f);
                NormalCruiseButton.setAlpha(1f);
                PursuitButton.setAlpha(0.5f);
                speechRecognizer.stopListening();
                stopAndPlay(R.raw.beep2, mp);
            }
        });
        PursuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCruiseButton.setAlpha(0.5f);
                NormalCruiseButton.setAlpha(0.5f);
                PursuitButton.setAlpha(1f);
                speechRecognizer.stopListening();
                stopAndPlay(R.raw.beep3, mp);
            }
        });

        AutoCruiseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(!isListening) {
                    isListening=true;
                    stopAndPlay(R.raw.beep1, mp);
                    AutoCruiseButton.setAlpha(1f);
                    NormalCruiseButton.setAlpha(0.5f);
                    PursuitButton.setAlpha(0.5f);
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
            }
         });


//*******************Voice Recognition code taken from https://medium.com/voice-tech-podcast/android-speech-to-text-tutorial-8f6fa71606ac
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {
            }

            @Override
            public void onBeginningOfSpeech() {
            }

            @Override
            public void onRmsChanged(float v) {
            }

            @Override
            public void onBufferReceived(byte[] bytes) {
            }

            @Override
            public void onEndOfSpeech() {
            }

            @Override
            public void onError(int i) {
                /*Context context = getApplicationContext();
                CharSequence text = ("error: " +i);
                int duration = Toast.LENGTH_LONG;
                Toast.makeText(context, text, duration).show();*/
                if(i == ERROR_NO_MATCH){
                    stopAndPlay(R.raw.unfortunately_response_delayed,mp);
                    PursuitButton.setAlpha(0.5f);
                    AutoCruiseButton.setAlpha(0.5f);
                    NormalCruiseButton.setAlpha(1f);
                }
                isListening=false;
            }

            @Override
            public void onResults(Bundle bundle) {
                boolean commandHeard = true;
                while(commandHeard)
                    commandHeard=getCommandFromResult(bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION), speechRecognizerIntent);
            }

            @Override
            public void onPartialResults(Bundle bundle) {
            }

            @Override
            public void onEvent(int i, Bundle bundle) {
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }


    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }

//**************************************************
    // method to loop through results trying to find response
    private boolean getCommandFromResult(ArrayList<String> results, Intent speechRecognizerIntent) {
        boolean commandHeard = false;
        for (String str : results) {
            commandHeard=getCommandFromText(str, speechRecognizerIntent);
        }
        return commandHeard;
    }
    //Playing corresponding mp3
    private boolean getCommandFromText(String strCommand, Intent speechRecognizerIntent){

        final MediaPlayer mp = new MediaPlayer();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                speechRecognizer.startListening(speechRecognizerIntent);
            }
        });
        if(strCommand.contains("introduce yourself")) {
            stopAndPlay(R.raw.kitt_intro, mp);
        }
        else if(strCommand.contains("stress")){
            stopAndPlay(R.raw.good_vital_signs, mp);
        }
        else if(strCommand.contains("what should")){
            stopAndPlay(R.raw.how_would_i_know, mp);
        }
        else if(strCommand.contains("good night")){
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    speechRecognizer.stopListening();
                }
            });
            stopAndPlay(R.raw.goodnight,mp);
            PursuitButton.setAlpha(0.5f);
            AutoCruiseButton.setAlpha(0.5f);
            NormalCruiseButton.setAlpha(1f);
            isListening=false;
        }
        else{
            stopAndPlay(R.raw.what_can_i_do,mp);
        }
        return false;
    }

    private void stopListening(MediaPlayer beep, int mp3, MediaPlayer mp){
        speechRecognizer.stopListening();
        AutoCruiseButton.setAlpha(0.5f);
        NormalCruiseButton.setAlpha(1f);
        PursuitButton.setAlpha(0.5f);
        beep.start();
        stopAndPlay(mp3,mp);
    }

    //Code below (stopAndPlay method) written by UdeshUK on StackOverflow:
    //https://stackoverflow.com/questions/48054515/how-to-stop-playing-sounds-on-button-click-to-start-another-sound-file/48054638#48054638
    private boolean stopAndPlay(int rawId, MediaPlayer mediaPlayer) {
        mediaPlayer.reset();
        AssetFileDescriptor afd = this.getResources().openRawResourceFd(rawId);
        try {
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        return true;
    }
}

