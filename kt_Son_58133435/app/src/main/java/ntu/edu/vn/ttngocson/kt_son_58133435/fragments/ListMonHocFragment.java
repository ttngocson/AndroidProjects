package ntu.edu.vn.ttngocson.kt_son_58133435.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ntu.edu.vn.ttngocson.kt_son_58133435.DetailActivity;
import ntu.edu.vn.ttngocson.kt_son_58133435.R;
import ntu.edu.vn.ttngocson.kt_son_58133435.models.MonHoc;
import ntu.edu.vn.ttngocson.kt_son_58133435.models.MonHocManager;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListMonHocFragment extends Fragment {


    ArrayList<MonHoc> listMonHocs = new ArrayList<>();
    RecyclerView rvMonHocs;
    MonHocAdapter adapter;
    int task;

    public ListMonHocFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list_mon_hoc, container, false);
        super.onCreate(savedInstanceState);
        task = getArguments().getInt("Task", 1);
        View view = inflater.inflate(R.layout.fragment_list_mon_hoc, container, false);
        rvMonHocs = view.findViewById(R.id.rvMonHoc);
        rvMonHocs.setLayoutManager(new LinearLayoutManager(getContext()));
        UpdateRV();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if( requestCode==1){
            if(resultCode==RESULT_OK)
                adapter.notifyDataSetChanged();
        }
    }

    private void UpdateRV() {
        listMonHocs = MonHocManager.getInstance().getMonHocs();
        adapter = new MonHocAdapter(listMonHocs);
        rvMonHocs.setAdapter(adapter);
    }

    private class MonHocHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtTenMT, txtNgayThi, txtStt;
        MonHoc m;


        public MonHocHolder(@NonNull View itemView) {
            super(itemView);
            txtTenMT = this.itemView.findViewById(R.id.txtTenMT);
            txtNgayThi = this.itemView.findViewById(R.id.txtNgayThi);
            txtStt = this.itemView.findViewById(R.id.txtStt);
            txtTenMT.setOnClickListener(this);
            txtNgayThi.setOnClickListener(this);
            txtStt.setOnClickListener(this);
        }

        public void onBind(MonHoc m, int i){
            this.m = m;
            txtTenMT.setText(this.m.getMonThi());
            txtNgayThi.setText(this.m.getNgayThi());
            txtStt.setText(String.valueOf(i+1));
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.txtTenMT || v.getId() == R.id.txtNgayThi || v.getId() == R.id.txtStt){
                Intent intent = new Intent(getContext(), DetailActivity.class);
                int position = MonHocManager.getInstance().getMonHocs().indexOf(this.m);
                Bundle bundle = new Bundle();
                bundle.putInt("Position", position);
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }
        }
    }

    private class MonHocAdapter extends RecyclerView.Adapter<MonHocHolder> {
        ArrayList<MonHoc> listMonHocs;

        public MonHocAdapter(ArrayList<MonHoc> listMonHocs) {
            this.listMonHocs = new ArrayList<>();
            if(task==1)
                for(MonHoc mh : listMonHocs)
                    this.listMonHocs.add(mh);
            else if(task==0)
                for(MonHoc mh : listMonHocs)
                    if(!mh.isThi())
                        this.listMonHocs.add(mh);
        }

        @NonNull
        @Override
        public MonHocHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View view = inflater.inflate(R.layout.item_rv, viewGroup, false);
            return new MonHocHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MonHocHolder monHocHolder, int i) {
            monHocHolder.onBind(listMonHocs.get(i), i);
        }

        @Override
        public int getItemCount() {
            return listMonHocs.size();
        }
    }
}
