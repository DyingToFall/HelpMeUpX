package com.dyingtofall.helpmeupx;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static android.content.Context.AUDIO_SERVICE;

public class AlarmSoundPool
{
        Context context;
        private SoundPool soundPool;
        private int soundID;
        boolean plays = false, loaded = false;
        float actVolume, maxVolume, volume;
        AudioManager audioManager;
        int counter;

    public AlarmSoundPool(Context c)
    {
        this.context = c;

        audioManager = (AudioManager) c.getSystemService(AUDIO_SERVICE);

       // audioManager.setStreamVolume(AudioManager.STREAM_ALARM, audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM), AudioManager.FLAG_PLAY_SOUND);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        volume = actVolume / maxVolume;

        counter = 0;

        // Load the sounds
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status)
            {
                loaded = true;
            }
        });

        soundID = soundPool.load(c, R.raw.alarm, 1);
    }





        public void playSound() {
            // Is the sound loaded does it already play?
            if (loaded && !plays) {
                soundPool.play(soundID, 0.99f, 0.99f, 0, -1, 1f);
                counter = counter++;
                Toast.makeText(context, "Played sound", Toast.LENGTH_SHORT).show();
                plays = true;
            }
        }

        public void playLoop() {
            // Is the sound loaded does it already play?
            if (loaded && !plays) {

                // the sound will play for ever if we put the loop parameter -1
                soundPool.play(soundID, maxVolume, maxVolume, 0, -1, 1f);
                counter = counter++;
                Toast.makeText(context, "Plays loop", Toast.LENGTH_SHORT).show();
                plays = true;
            }
        }

        public void pauseSound() {
            if (plays) {
                soundPool.pause(soundID);
                soundID = soundPool.load(context, R.raw.alarm, counter);
                Toast.makeText(context, "Pause sound", Toast.LENGTH_SHORT).show();
                plays = false;
            }
        }

        public void stopSound() {
            if (plays) {
                soundPool.stop(soundID);
                soundID = soundPool.load(context, R.raw.alarm, counter);
                Toast.makeText(context, "Stop sound", Toast.LENGTH_SHORT).show();
                plays = false;
            }
        }
}
