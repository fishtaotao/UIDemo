package tao.tao.com;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.loopeer.cardstack.AllMoveDownAnimatorAdapter;
import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.UpDownAnimatorAdapter;
import com.loopeer.cardstack.UpDownStackAnimatorAdapter;

import java.util.ArrayList;

import tao.tao.adpater.TestStackAdapter;
import tao.tao.model.ExampleData;

/**
 * Author：wjt on 2018/4/23 0023 14:18
 */

public class CardstackaActivity extends AppCompatActivity implements CardStackView.ItemExpendListener {
    public static Integer[] TEST_DATAS = new Integer[]{
            R.color.color_1,
            R.color.color_2,
            R.color.color_3,
            R.color.color_4,
            R.color.color_5,
            R.color.color_6,
            R.color.color_7,
            R.color.color_8,
            R.color.color_9,
            R.color.color_10,
            R.color.color_11,
            R.color.color_12,
            R.color.color_13,
            R.color.color_14,
            R.color.color_15,
            R.color.color_16,
            R.color.color_17,
            R.color.color_18,
            R.color.color_19,
            R.color.color_20,
            R.color.color_21,
            R.color.color_22,
            R.color.color_23,
            R.color.color_24,
            R.color.color_25,
            R.color.color_26
    };
    private CardStackView mStackView;
    private LinearLayout mActionButtonContainer;
    private TestStackAdapter mTestStackAdapter;

    private ArrayList<ExampleData> mExtraDatas=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardstacka_activity);
        initData();
        mStackView = (CardStackView) findViewById(R.id.stackview_main);
        mActionButtonContainer = (LinearLayout) findViewById(R.id.button_container);
        mStackView.setItemExpendListener(this);
        mTestStackAdapter = new TestStackAdapter(this);
        mStackView.setAdapter(mTestStackAdapter);


        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        mTestStackAdapter.updateData(mExtraDatas);
                    }
                }, 200);
    }

    private void initData(){
        mExtraDatas.add(new ExampleData( R.color.color_1,"第一"));
        mExtraDatas.add(new ExampleData( R.color.color_2,"第一"));
        mExtraDatas.add(new ExampleData( R.color.color_3,"第一"));
        mExtraDatas.add(new ExampleData( R.color.color_4,"第一"));
        mExtraDatas.add(new ExampleData( R.color.color_5,"第一"));
        mExtraDatas.add(new ExampleData( R.color.color_6,"第一"));
        mExtraDatas.add(new ExampleData( R.color.color_7,"第一"));
        mExtraDatas.add(new ExampleData( R.color.color_8,"第一"));
        mExtraDatas.add(new ExampleData( R.color.color_9,"第一"));
        mExtraDatas.add(new ExampleData( R.color.color_10,"第一"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_all_down:
                mStackView.setAnimatorAdapter(new AllMoveDownAnimatorAdapter(mStackView));
                break;
            case R.id.menu_up_down:
                mStackView.setAnimatorAdapter(new UpDownAnimatorAdapter(mStackView));
                break;
            case R.id.menu_up_down_stack:
                mStackView.setAnimatorAdapter(new UpDownStackAnimatorAdapter(mStackView));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onPreClick(View view) {
        mStackView.pre();
    }

    public void onNextClick(View view) {
        mStackView.next();
    }

    @Override
    public void onItemExpend(boolean expend) {
        mActionButtonContainer.setVisibility(expend ? View.VISIBLE : View.GONE);
    }
}