package vn.edu.ntu.tuananh.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.ntu.tuananh.models.Friend;
import vn.edu.ntu.tuananh.models.FriendManager;
import vn.edu.ntu.tuananh.mycustomlayout.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LisfriendFragment extends Fragment {

    ArrayList<Friend> listFriends = new ArrayList<>();
    RecyclerView rvFriends;
    FriendAdapter adapter;

    public LisfriendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lisfriend, container, false);
        rvFriends = view.findViewById(R.id.rvListFriend);
        rvFriends.setLayoutManager(new LinearLayoutManager(getContext()));
        UpdateRV();
        return view;
    }

    private void UpdateRV()
    {
        listFriends = FriendManager.getInstance().getFriend();
        adapter = new FriendAdapter(listFriends);
        rvFriends.setAdapter(adapter);
    }

    /**
     * View holder dùng để hiển thị thông tin của 1 người
     */
    private class  FriendHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView txtName, txtBirthday,txtPhone;
        ImageView imgEdit,imgCall,imgSms;
        Friend f;

        public FriendHolder(@NonNull View itemView) {

            super(itemView);
            txtName = this.itemView.findViewById(R.id.txtName);
            txtBirthday = this.itemView.findViewById(R.id.txtBirthday);
            txtPhone = this.itemView.findViewById(R.id.txtPhone);
            imgEdit = this.itemView.findViewById(R.id.imgEdit);
            imgCall = this.itemView.findViewById(R.id.imgCall);
            imgSms = this.itemView.findViewById(R.id.imgSms);
            imgCall.setOnClickListener(this);

        }

        /**
         * onBind hiển thị thông tin của đối tượng lên một item view
         * @param f
         */
        public void onBind(Friend f)
        {
            this.f = f;
            txtName.setText(f.getName());
            txtBirthday.setText(f.getBirthday());
            txtPhone.setText(f.getPhone());
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.imgCall)
            {
                Uri uri = Uri.parse("tel:" + this.f.getPhone());
                Intent intent = new Intent(Intent.ACTION_DIAL);
                getActivity().startActivity(intent);
            }
        }
    }

    private class FriendAdapter extends RecyclerView.Adapter<FriendHolder>
    {
        ArrayList<Friend> listFriend;

        public FriendAdapter(ArrayList<Friend> listFriend) {
            this.listFriend = listFriend;
        }

        @NonNull
        @Override
        public FriendHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View view = inflater.inflate(R.layout.item_rv,viewGroup,false);
            return new FriendHolder(view);
        }

        /**
         * Hiển thị thông tin 1 đối tượng trong listfriend lên 1 holder
         * @param friendHolder đối tượng
         * @param i chỉ mục của đối tượng trong list
         */
        @Override
        public void onBindViewHolder(@NonNull FriendHolder friendHolder, int i)
        {
            friendHolder.onBind(listFriend.get(i));
        }

        @Override
        public int getItemCount() {
            return listFriend.size();
        }
    }

}
