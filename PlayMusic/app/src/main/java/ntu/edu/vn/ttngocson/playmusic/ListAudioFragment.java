package ntu.edu.vn.ttngocson.playmusic;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ntu.edu.vn.ttngocson.playmusic.Models.MyAudio;
import ntu.edu.vn.ttngocson.playmusic.Models.MyAudioUtil;
import ntu.edu.vn.ttngocson.playmusic.service.MyAudioService;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListAudioFragment extends Fragment {

    RecyclerView rvListAudio;
    ImageView imageMediaController;
    AudioAdapter adapter;
    ArrayList<MyAudio> listAudio;
    MyAudioService myAudioService;
    boolean isServiceBinded = false;
    MediaController mediaController;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyAudioService.LocalBinder localBinder = (MyAudioService.LocalBinder)service;
            myAudioService = localBinder.getService();
            myAudioService.setListAudio(listAudio);
            mediaController.setMediaPlayer(myAudioService);
            isServiceBinded = true;
            Log.d("Play music", "Ket noi service thanh cong");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceBinded = false;
        }
    };

    public ListAudioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_audio, container, false);
        rvListAudio = view.findViewById(R.id.rvListAudio);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        rvListAudio.setLayoutManager(gridLayoutManager);
        imageMediaController = view.findViewById(R.id.imvMediaController);
        listAudio = MyAudioUtil.LoadAudio(getContext());
        mediaController = new MediaController(getContext());
        Log.e("set view","da set view thanh cong");
        mediaController.setAnchorView(imageMediaController);
        if(listAudio != null){
            adapter = new AudioAdapter(listAudio);
            Log.d("Play music", listAudio.get(0).getTitle());
            rvListAudio.setAdapter(adapter);
        }
        Addevent();
        Log.d("Play music", "khong tai duoc");
        return view;
    }

    private void Addevent(){
        imageMediaController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaController.isShowing())
                    mediaController.hide();
                else
                    mediaController.show();
            }
        });
        mediaController.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if(isServiceBinded == false){
            Intent intent = new Intent(getContext(), MyAudioService.class);
            getContext().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isServiceBinded = false;
        myAudioService.stopSelf();
    }

    //Inner classes
    private class AudioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MyAudio audio;
        TextView txtTitle, txtArtist;
        View audioContainer;
        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = this.itemView.findViewById(R.id.txtTitle);
            txtArtist = this.itemView.findViewById(R.id.txtArtist);
            audioContainer = this.itemView.findViewById(R.id.item_audio_container);
            audioContainer.setOnClickListener(this);
        }
        public void onBind(MyAudio item){
            this.audio = item;
            txtTitle.setText(item.getTitle());
            txtArtist.setText(item.getArtist());
        }

        /*@Override
        public void onClick(View v) {
            if(mediaPlayer != null){
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
            }
            mediaPlayer = new MediaPlayer();
            try{
                mediaPlayer.setDataSource(this.item.getData());
                mediaPlayer.prepare();
                mediaPlayer.start();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }*/
        public void onClick(View v){
            int indexPlaying = listAudio.indexOf(audio);
            myAudioService.playAudio(indexPlaying);
            mediaController.show(0);
        }
    }
    private class AudioAdapter extends RecyclerView.Adapter<AudioViewHolder>{
        ArrayList<MyAudio> list;
        public AudioAdapter(ArrayList<MyAudio> list){
            this.list = list;
        }

        @NonNull
        @Override
        public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            View view = layoutInflater.inflate(R.layout.item_audio, viewGroup, false);
            return new AudioViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AudioViewHolder audioViewHolder, int i) {
            MyAudio item = listAudio.get(i);
            audioViewHolder.onBind(item);
        }

        @Override
        public int getItemCount() {
            return listAudio.size();
        }
    }

}
