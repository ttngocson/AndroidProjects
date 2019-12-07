package ttngocson.ntu.edu.vn.thi12;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ttngocson.ntu.edu.vn.thi12.Models.KhachHang;
import ttngocson.ntu.edu.vn.thi12.Models.KhachHangManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rvView;
    ArrayList<KhachHang> listKhachHang = new ArrayList<>();
    LiskKhachHangAdapter adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        AddViews();
    }

    private void AddViews() {
        rvView = findViewById(R.id.rvView);
        rvView.setLayoutManager(new LinearLayoutManager(this));
        listKhachHang = KhachHangManager.getInstance().getKhachHangs();
        adapter = new LiskKhachHangAdapter(listKhachHang);
        rvView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK)
            adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.KHTT) {
            return true;
        }

        if (id == R.id.notKHTT) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==fab.getId()){
            Intent intent = new Intent(this, Main2Activity.class);
            startActivityForResult(intent,1);
        }
    }

    private class LiskKhachHangAdapter extends RecyclerView.Adapter<ListKhachHangHolder> {
        ArrayList<KhachHang> khachHangs;

        public LiskKhachHangAdapter(ArrayList<KhachHang> khachHangs) {
            this.khachHangs = khachHangs;
        }

        @NonNull
        @Override
        public ListKhachHangHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View view = inflater.inflate(R.layout.item_rv, viewGroup, false);
            return new ListKhachHangHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ListKhachHangHolder listKhachHangHolder, int i) {
            listKhachHangHolder.onBind(listKhachHang.get(i));
        }

        @Override
        public int getItemCount() {
            return khachHangs.size();
        }
    }

    private class ListKhachHangHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtName, txtSDT;
        ImageView imgTym, imgEdit;
        KhachHang kh;

        public ListKhachHangHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtTenKH);
            txtSDT = itemView.findViewById(R.id.txtSDT);
            imgTym = itemView.findViewById(R.id.imgTym);
            imgEdit = itemView.findViewById(R.id.imgEdit);

            imgTym.setOnClickListener(this);
            imgEdit.setOnClickListener(this);
            txtSDT.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.txtSDT){
                Uri uri = Uri.parse("tel:" + this.kh.getSoDT());
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        }

        public void onBind(KhachHang kh){
            this.kh = kh;
            txtName.setText(kh.getTenKH());
            txtSDT.setText(kh.getSoDT());
            if(kh.isKHTT())
                imgTym.setImageResource(R.drawable.ic_heat_full);
            else
                imgTym.setImageResource(R.drawable.ic_heat_boder);
        }
    }
}
