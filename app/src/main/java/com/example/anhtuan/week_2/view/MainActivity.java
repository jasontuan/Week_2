package com.example.anhtuan.week_2.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.anhtuan.week_2.R;
import com.example.anhtuan.week_2.adapter.RecyclerViewAdapter;
import com.example.anhtuan.week_2.api.ArticleAPI;
import com.example.anhtuan.week_2.contract.IArticle;
import com.example.anhtuan.week_2.presenter.PresenterArticleImpl;

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

    @BindView(R.id.rcv_article)
    RecyclerView rcvArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ArticleAPI.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final ArticleAPI articleAPI = retrofit.create(ArticleAPI.class);
        presenterArticle = new PresenterArticleImpl(this);
        recyclerViewAdapter = new RecyclerViewAdapter(this, presenterArticle.getDocList());
        gridLayoutManager = new GridLayoutManager(this, 3);
        rcvArticle.setLayoutManager(gridLayoutManager);
        rcvArticle.setAdapter(recyclerViewAdapter);
        presenterArticle.getDataArticle(articleAPI, page);

        rcvArticle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();
                totalItemCount = gridLayoutManager.getItemCount();
                lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
                if (firstVisibleItem + lastVisibleItem >= totalItemCount) {
                    page++;
                    presenterArticle.getDataArticle(articleAPI, page);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.icon_search:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.icon_filter:
                Intent intent = new Intent(MainActivity.this, FilterActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showDataSuccess() {
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDataFail() {
        makeText(this, "Show Fail", Toast.LENGTH_SHORT).show();
    }
}
