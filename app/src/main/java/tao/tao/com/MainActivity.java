package tao.tao.com;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.willowtreeapps.spruce.Spruce;
import com.willowtreeapps.spruce.animation.DefaultAnimations;
import com.willowtreeapps.spruce.sort.DefaultSort;

import java.util.ArrayList;
import java.util.List;

import tao.tao.dagger2.DaggerActivity;
import tao.tao.model.ExampleData;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Animator spruceAnimator;
    private RecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                super.onLayoutChildren(recycler, state);
                initSpruce();
            }
        };
        // Mock data objects
        List<ExampleData> placeHolderList = new ArrayList<>();

        placeHolderList.add(new ExampleData(1, "卡片列表", CardstackaActivity.class));
        placeHolderList.add(new ExampleData(1, "广告", AdInfoActivity.class));
        placeHolderList.add(new ExampleData(1, "滑动菜单I", Easyswipemenulayout.class));
        placeHolderList.add(new ExampleData(1, "DaggerTest", DaggerActivity.class));
        placeHolderList.add(new ExampleData(1, "建设中",null));
        mRecyclerAdapter=new RecyclerAdapter(placeHolderList);
        recyclerView.setAdapter(mRecyclerAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        initRef();
    }

    private void initSpruce() {
        spruceAnimator = new Spruce.SpruceBuilder(recyclerView)
                .sortWith(new DefaultSort(100))
                .animateWith(DefaultAnimations.shrinkAnimator(recyclerView, 800),
                        ObjectAnimator.ofFloat(recyclerView, "translationX", -recyclerView.getWidth(), 0f).setDuration(800))
                .start();
    }


    private void  initRef(){
        final RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        mAdapter.refresh(initData());
                        refreshLayout.finishRefresh();
                        refreshLayout.setNoMoreData(false);
                    }
                }, 2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mRecyclerAdapter.getItemCount() > 30) {
                            Toast.makeText(getApplication(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                            refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
                        } else {
//                            mRecyclerAdapter.loadMore(initData());
                            refreshLayout.finishLoadMore();
                        }
                    }
                }, 2000);
            }
        });

        //触发自动刷新
        refreshLayout.autoRefresh();
    }



    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        List<ExampleData> placeholderList;
        TextView mTextView;

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            RelativeLayout placeholderView;


            ViewHolder(View itemView) {
                super(itemView);
                placeholderView = itemView.findViewById(R.id.placeholder_view);
                mTextView = itemView.findViewById(R.id.darker_gray_strip);
                placeholderView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                if(null!=placeholderList.get(getPosition()).getToClass()){
                    Intent mIntent = new Intent();
                    mIntent.setClass(MainActivity.this, placeholderList.get(getPosition()).getToClass());
                    startActivity(mIntent);
                }
            }
        }

        RecyclerAdapter(List<ExampleData> placeholderList) {
            this.placeholderList = placeholderList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RelativeLayout view = (RelativeLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_placeholder, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            mTextView.setText(placeholderList.get(position).getText());
        }

        @Override
        public int getItemCount() {
            return placeholderList.size();
        }

    }
}
