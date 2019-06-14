package ntu.edu.vn.ttngocson.rssnews.Fragments;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ntu.edu.vn.ttngocson.models.RSSItem;
import ntu.edu.vn.ttngocson.models.VNE_RSSChannelHandler;
import ntu.edu.vn.ttngocson.rssnews.PageActivity;
import ntu.edu.vn.ttngocson.rssnews.R;
import ntu.edu.vn.ttngocson.ui_helper.UiHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListRSSItemFragment extends Fragment
{

    RecyclerView rvListRSSItem;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<RSSItem> listRSSItem = new ArrayList<>();
    RSSAdapter adapter;
    String url = "https://vnexpress.net/rss/thoi-su.rss";
    boolean isUpdate = false;
    public ListRSSItemFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_rssitem, container, false);
        rvListRSSItem = view.findViewById(R.id.rvListItem);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout = view.findViewById(R.id.srlpullToRefresh);
        GridLayoutManager layoutManager = UiHelper.setupLayoutManager(getContext());
        rvListRSSItem.setLayoutManager(layoutManager);
        addEvents();
        return view;
    }

    private void addEvents(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isUpdate == true){
                    swipeRefreshLayout.setRefreshing(true);
                    isUpdate = true;
                    updateItem();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();
        if(isUpdate == false)
            updateItem();
            isUpdate = true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        GridLayoutManager layoutManager = UiHelper.setupLayoutManager(getContext());
        rvListRSSItem.setLayoutManager(layoutManager);
    }

    private void updateItem()
    {
        RSSFetch rssFetch = new RSSFetch();
        rssFetch.execute(new String[]{url});
    }

    private void setupAdapter()
    {
        if(isAdded())
        {
            adapter = new RSSAdapter(listRSSItem);
            rvListRSSItem.setAdapter(adapter);
        }

    }

    private class RSSViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView txtTitle, txtDescription;
        ImageView imgView;
        View itemContainer;
        RSSItem item;
        public RSSViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txtTitle = this.itemView.findViewById(R.id.txtTitle);
            txtDescription = this.itemView.findViewById(R.id.txtDescription);
            imgView = this.itemView.findViewById(R.id.imgView);
            itemContainer = this.itemView.findViewById(R.id.itemContainer);
            itemContainer.setOnClickListener(this);
        }

        private void onBind(RSSItem item)
        {
            this.item = item;
            txtTitle.setText(item.getTitle());
            txtDescription.setText(item.getDescription());
            Picasso.get().load(item.getImageLink()).into(imgView);
        }

        @Override
        public void onClick(View v) {
            Intent intent = PageActivity.getIntent(getContext(), item.getLink());
            startActivity(intent);
        }
    }

    private class RSSAdapter extends RecyclerView.Adapter<RSSViewHolder>
    {
        ArrayList<RSSItem> listRSSItem;

        public RSSAdapter(ArrayList<RSSItem> listRSSItem)
        {
            this.listRSSItem = listRSSItem;
        }

        @NonNull
        @Override
        public RSSViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            View view = layoutInflater.inflate(R.layout.item_rss, viewGroup, false);
            return new RSSViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RSSViewHolder rssViewHolder, int i)
        {
            RSSItem item = listRSSItem.get(i);
            rssViewHolder.onBind(item);

        }

        @Override
        public int getItemCount()
        {
            return listRSSItem.size();
        }
    }

    private class RSSFetch extends AsyncTask<String, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            listRSSItem.clear();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(String... strings)
        {
            VNE_RSSChannelHandler handler = new VNE_RSSChannelHandler();
            listRSSItem = handler.getListItem(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            setupAdapter();
            progressBar.setVisibility(View.GONE);
        }
    }





}
