package ntu.edu.vn.ttngocson.playmusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.MediaController;

import java.io.IOException;
import java.util.ArrayList;

import ntu.edu.vn.ttngocson.playmusic.Models.MyAudio;

public class MyAudioService extends Service implements MediaController.MediaPlayerControl {

    MediaPlayer mediaPlayer;
    ArrayList<MyAudio> listAudio;
    LocalBinder binder = new LocalBinder();

    public MyAudioService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    public class LocalBinder extends Binder{
        public MyAudioService getService(){
            return MyAudioService.this;
        }
    }

    public void playAudio(int indexPlaying){
        stopMedia();
        try{
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(listAudio.get(indexPlaying).getData());
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void stopMedia(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void start() {
        if(mediaPlayer != null)
            mediaPlayer.start();
    }

    @Override
    public void pause() {
        if( mediaPlayer != null)
            if(mediaPlayer.isPlaying())
                mediaPlayer.pause();
    }

    @Override
    public int getDuration() {
        if(mediaPlayer != null)
            mediaPlayer.getDuration();
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        if(mediaPlayer != null)
            return mediaPlayer.getCurrentPosition();
        return 0;
    }

    @Override
    public void seekTo(int pos) {
        if(mediaPlayer != null)
            mediaPlayer.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        if(mediaPlayer != null)
            return mediaPlayer.isPlaying();
        return false;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        if(mediaPlayer != null)
            return mediaPlayer.getAudioSessionId();
        return 0;
    }

    public void setListAudio(ArrayList<MyAudio> listAudio) {
        this.listAudio = listAudio;
    }
}
