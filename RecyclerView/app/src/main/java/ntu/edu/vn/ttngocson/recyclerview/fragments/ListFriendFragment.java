package ntu.edu.vn.ttngocson.recyclerview.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.style.UpdateLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ntu.edu.vn.ttngocson.recyclerview.Models.Friend;
import ntu.edu.vn.ttngocson.recyclerview.Models.FriendManager;
import ntu.edu.vn.ttngocson.recyclerview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFriendFragment extends Fragment {

    ArrayList<Friend> listFriend = new ArrayList<>();
    RecyclerView rvFriends;
    public ListFriendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_friend,container, false);
        rvFriends = view.findViewById(R.id.rvListFriend);
        rvFriends.setLayoutManager(new LinearLayoutManager(getContext()));
        UpdateRV();
        return view;
    }

    private void UpdateRV() {
        listFriend = FriendManager.getInstance().getFriend();
    }

}
