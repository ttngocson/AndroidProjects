package ntu.edu.vn.ttngocson.thi_58133435.fragments;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ntu.edu.vn.ttngocson.thi_58133435.DetailActivity;
import ntu.edu.vn.ttngocson.thi_58133435.R;
import ntu.edu.vn.ttngocson.thi_58133435.models.KhachHang;
import ntu.edu.vn.ttngocson.thi_58133435.models.KhachHangManager;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListKhachHangFragment extends Fragment {

    ArrayList<KhachHang> listKhachHangs = new ArrayList<>();
    RecyclerView rvKhachHang;
    KhachHangAdapter adapter;
    int task;

    public ListKhachHangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_list_khach_hang, container, false);
        task = getArguments().getInt("Task", 1);
        rvKhachHang = view.findViewById(R.id.rvKhachHang);
        rvKhachHang.setLayoutManager(new LinearLayoutManager(getContext()));
        UpdateRV();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1)
            if(resultCode == RESULT_OK)
                adapter.notifyDataSetChanged();
    }

    private void UpdateRV() {
        listKhachHangs = KhachHangManager.getInstance().getKhachHangs();
        adapter = new KhachHangAdapter(listKhachHangs);
        rvKhachHang.setAdapter(adapter);
    }

    private class KhachHangHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtTenKH, txtSDT;
        ImageView imgTym, imgEdit;
        KhachHang kh;

        public KhachHangHolder(@NonNull View itemView) {
            super(itemView);
            txtTenKH = itemView.findViewById(R.id.txtTenKH);
            txtSDT = itemView.findViewById(R.id.txtSDT);
            imgTym = itemView.findViewById(R.id.imgTym);
            imgEdit = itemView.findViewById(R.id.imgEdit);

            txtSDT.setOnClickListener(this);
            imgEdit.setOnClickListener(this);
            imgTym.setOnClickListener(this);

        }

        public void onBind(KhachHang kh){
            this.kh = kh;
            txtTenKH.setText(kh.getTenKH());
            txtSDT.setText(kh.getSoDT());
            boolean tym = kh.isKHTT();
            if(tym == true)
                imgTym.setImageResource(R.drawable.ic_red);
            if(tym == false)
                imgTym.setImageResource(R.drawable.ic_blue);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.txtSDT){
                Uri uri = Uri.parse("tel:" + this.kh.getSoDT());
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                getActivity().startActivity(intent);
            }
            if(v.getId() == R.id.imgEdit){
                Intent intent = new Intent(getContext(), DetailActivity.class);
                Bundle bundle = new Bundle();
                int position = KhachHangManager.getInstance().getKhachHangs().indexOf(this.kh);
                bundle.putInt("Position", position);
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }
            if(v.getId() == R.id.imgTym){
                int position = KhachHangManager.getInstance().getKhachHangs().indexOf(this.kh);
                if(KhachHangManager.getInstance().getKhachHangs().get(position).isKHTT() == true){
                    KhachHangManager.getInstance().getKhachHangs().get(position).setKHTT(false);
                    imgTym.setImageResource(R.drawable.ic_blue);
                }
                else if(KhachHangManager.getInstance().getKhachHangs().get(position).isKHTT() == false){
                    KhachHangManager.getInstance().getKhachHangs().get(position).setKHTT(true);
                    imgTym.setImageResource(R.drawable.ic_red);
                }
            }
        }
    }

    private class KhachHangAdapter extends RecyclerView.Adapter<KhachHangHolder>{
        ArrayList<KhachHang> listKhachHangs;

        public KhachHangAdapter(ArrayList<KhachHang> listKhachHangs) {
            this.listKhachHangs = new ArrayList<>();
            if(task==1)
                for(KhachHang kh : listKhachHangs)
                    this.listKhachHangs.add(kh);
            else if(task==0)
                for(KhachHang kh : listKhachHangs)
                    if(kh.isKHTT())
                        this.listKhachHangs.add(kh);
        }


        @NonNull
        @Override
        public KhachHangHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View view = inflater.inflate(R.layout.item_rv, viewGroup, false);
            return new KhachHangHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull KhachHangHolder khachHangHolder, int i) {
            khachHangHolder.onBind(listKhachHangs.get(i));
        }

        @Override
        public int getItemCount() {
            return listKhachHangs.size();
        }
    }

}
