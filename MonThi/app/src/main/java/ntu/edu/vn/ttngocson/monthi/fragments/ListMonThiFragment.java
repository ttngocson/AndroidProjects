package ntu.edu.vn.ttngocson.monthi.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ntu.edu.vn.ttngocson.monthi.DetailActivity;
import ntu.edu.vn.ttngocson.monthi.R;
import ntu.edu.vn.ttngocson.monthi.models.MonThi;
import ntu.edu.vn.ttngocson.monthi.models.MonThiManager;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListMonThiFragment extends Fragment {

    ArrayList<MonThi> listMonThis = new ArrayList<>();
    RecyclerView rvMonThi;
    MonThiAdapter adapter;

    public ListMonThiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list_mon_thi, container, false);
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_list_mon_thi, container, false);
        rvMonThi = view.findViewById(R.id.rvMonThi);
        rvMonThi.setLayoutManager(new LinearLayoutManager(getContext()));
        UpdateRV();
        return view;
    }

    private void UpdateRV() {
        listMonThis = MonThiManager.getInstance().getList();
        adapter = new MonThiAdapter(listMonThis);
        rvMonThi.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1)
            if(resultCode == RESULT_OK)
                adapter.notifyDataSetChanged();
    }

    private class MonThiAdapter extends RecyclerView.Adapter<MonThiHolder>{

        ArrayList<MonThi> listMonThi;

        public MonThiAdapter(ArrayList<MonThi> listMonThi) {
            this.listMonThi = listMonThi;
        }

        @NonNull
        @Override
        public MonThiHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View view = inflater.inflate(R.layout.rv_item, viewGroup, false);
            return new MonThiHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MonThiHolder monThiHolder, int i) {
            monThiHolder.onBind(listMonThi.get(i), i);
        }

        @Override
        public int getItemCount() {
            return listMonThi.size();
        }
    }

    private class MonThiHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtStt, txtMonThi, txtNgayThi;
        MonThi m;

        public MonThiHolder(@NonNull View itemView) {
            super(itemView);
            txtStt = itemView.findViewById(R.id.txtStt);
            txtMonThi = itemView.findViewById(R.id.txtMonThi);
            txtNgayThi = itemView.findViewById(R.id.txtNgayThi);

            txtStt.setOnClickListener(this);
            txtMonThi.setOnClickListener(this);
            txtNgayThi.setOnClickListener(this);
        }

        public void onBind( MonThi m, int i){
            this.m = m;
            txtStt.setText(String.valueOf(i+1));
            txtMonThi.setText(m.getMonThi());
            txtNgayThi.setText(m.getNgayThi());
        }

        @Override
        public void onClick(View v) {

            if(v.getId() == R.id.txtStt || v.getId() == R.id.txtMonThi || v.getId() == R.id.txtNgayThi){
                Intent intent = new Intent(getContext(), DetailActivity.class);
                Bundle bundle = new Bundle();
                int position = MonThiManager.getInstance().getList().indexOf(this.m);
                bundle.putInt("Position", position);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }

        }
    }
}
