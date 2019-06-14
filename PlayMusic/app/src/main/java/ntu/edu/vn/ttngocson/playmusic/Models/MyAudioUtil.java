package ntu.edu.vn.ttngocson.playmusic.Models;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

public class MyAudioUtil {
    public static ArrayList<MyAudio> LoadAudio(Context context){
        ArrayList<MyAudio> listAudio = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=?";
        String[] selectionArgs = {"0"};
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(uri, null, selection, selectionArgs, null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            int idIndex = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int titleIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int dataIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int artistIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int albumIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            while (true){
                MyAudio audio = new MyAudio();
                audio.setAudioId(cursor.getInt(idIndex));
                audio.setTitle(cursor.getString(titleIndex));
                audio.setData(cursor.getString(dataIndex));
                audio.setArtist(cursor.getString(artistIndex));
                audio.setAlbum(cursor.getString(albumIndex));
                listAudio.add(audio);
                if(cursor.moveToNext() == false)
                    break;
            }
            return listAudio;
        }
        return null;
    }
}
