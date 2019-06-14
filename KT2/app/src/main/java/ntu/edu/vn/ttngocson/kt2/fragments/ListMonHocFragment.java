package ntu.edu.vn.ttngocson.kt2.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ntu.edu.vn.ttngocson.kt2.R;
import ntu.edu.vn.ttngocson.kt2.models.MonHoc;
import ntu.edu.vn.ttngocson.kt2.models.MonHocManager;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListMonHocFragment extends Fragment {

    ArrayList<MonHoc> listMonHocs = new ArrayList<>();
    RecyclerView rvMonHocs;
    MonHocAdapter adapter;

    public ListMonHocFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list_mon_hoc, container, false);
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

    private class MonHocHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtTenMH, txtDiemTB, txtStt;
        MonHoc m;

        public MonHocHolder(@NonNull View itemView) {
            super(itemView);
            txtTenMH = this.itemView.findViewById(R.id.txtTenMH);
            txtDiemTB = this.itemView.findViewById(R.id.txtDTB);
            txtStt = this.itemView.findViewById(R.id.txtStt);
        }

        public void onBind(MonHoc m){
            this.m = m;
            txtTenMH.setText(this.m.getTenMH());
            float diemTB = (this.m.getDiemKT()+this.m.getDiemT())/2;
            txtDiemTB.setText(String.valueOf(diemTB));
            txtStt.setText(String.valueOf(MonHocManager.getInstance().getMonHocs().indexOf(this.m)+1));
        }

        @Override
        public void onClick(View v) {

        }
    }


    private class MonHocAdapter extends RecyclerView.Adapter<MonHocHolder>{

        ArrayList<MonHoc> listMonHocs;

        public MonHocAdapter(ArrayList<MonHoc> listMonHocs) {
            this.listMonHocs = listMonHocs;
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
            monHocHolder.onBind(listMonHocs.get(i));
        }

        @Override
        public int getItemCount() {
            return listMonHocs.size();
        }
    }
}
