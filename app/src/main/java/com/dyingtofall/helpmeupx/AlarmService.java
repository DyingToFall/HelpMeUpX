package com.dyingtofall.helpmeupx;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Home on 9/12/2017.
 */

public class AlarmService extends Service
{
    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
     //   mediaPlayer.setOnCompletionListener(this);
    }


    public int onStartCommand(Intent intent, int flags, int startId)
    {
        try
        {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.prepare();
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        }catch (IOException e)
        {
            Toast.makeText(this, "Your alarm sound was unavailable.", Toast.LENGTH_LONG).show();
        }

        audioManager.setStreamVolume(AudioManager.STREAM_ALARM, audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM), AudioManager.FLAG_PLAY_SOUND);
        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }

}
