package com.example.anhtuan.week_2.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.anhtuan.week_2.R;
import com.example.anhtuan.week_2.adapter.RecyclerViewAdapter;
import com.example.anhtuan.week_2.api.ArticleAPI;
import com.example.anhtuan.week_2.contract.IArticle;
import com.example.anhtuan.week_2.presenter.PresenterArticleImpl;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity implements IArticle.IView {

    RecyclerViewAdapter recyclerViewAdapter;
    PresenterArticleImpl presenterArticle;
    GridLayoutManager gridLayoutManager;
    int totalItemCount, lastVisibleItem, firstVisibleItem;
    int page = 1;
    String q, begin_date, sort;
    SharedPreferences sharedPreferences;

    Retrofit retrofit;
    ArticleAPI articleAPI;

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rcv_article)
    RecyclerView rcvArticle;

    private void onReadSharePreferences() {
        sharedPreferences = getSharedPreferences("dataShare", Context.MODE_PRIVATE);
        begin_date = sharedPreferences.getString("BEGIN_DATE", null);
        sort = sharedPreferences.getString("SORT", null);
        q = sharedPreferences.getString("TOPIC", null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        retrofit = new Retrofit.Builder()
                .baseUrl(ArticleAPI.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        onReadSharePreferences();

        articleAPI = retrofit.create(ArticleAPI.class);
        presenterArticle = new PresenterArticleImpl(this);
        recyclerViewAdapter = new RecyclerViewAdapter(this, presenterArticle.getDocList());
        gridLayoutManager = new GridLayoutManager(this, 3);
        rcvArticle.setLayoutManager(gridLayoutManager);
        rcvArticle.setAdapter(recyclerViewAdapter);
        presenterArticle.getDataArticle(articleAPI, page, q, begin_date, sort);

        rcvArticle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();
                totalItemCount = gridLayoutManager.getItemCount();
                lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
                if (firstVisibleItem + lastVisibleItem >= totalItemCount) {
                    page++;
                    presenterArticle.getDataArticle(articleAPI, page, q, begin_date, sort);
                }
            }
        });

        recyclerViewAdapter.setOnItemClickListener(new IArticle.OnItemClickListener() {
            @Override
            public void OnItemClick(int position, boolean selected) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("URL", presenterArticle.getDocList().get(position).getWebUrl());
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenterArticle.getDataArticle(articleAPI, page, q, begin_date, sort);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_icon, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.icon_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Search(query.trim());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.icon_filter:
                Intent intent = new Intent(MainActivity.this, FilterActivity.class);
                finish();
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showDataSuccess() {
        swipeRefreshLayout.setRefreshing(false);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDataFail() {
        swipeRefreshLayout.setRefreshing(false);
        makeText(this, "Show Fail", Toast.LENGTH_SHORT).show();
    }

    public void Search(String strSearch) {
        strSearch = strSearch.toLowerCase(Locale.getDefault());
        presenterArticle.getDocList().clear();
        presenterArticle.getDataArticle(articleAPI, page, strSearch, begin_date, sort);
        recyclerViewAdapter.notifyDataSetChanged();
    }

}
