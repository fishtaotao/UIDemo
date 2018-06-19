package tao.tao.dagger2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

/**
 * Author：wjt on 2018/6/6 0006 09:02
 */

public class DaggerActivity extends AppCompatActivity {
    @Inject
    Test1 mTest1;

    @Inject
    Test2 mTest2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DaggerDaggerActivityComponent.builder().build().inject(this);
        DaggerDaggerActivityComponent.builder().mainMoudle(new MainMoudle(this)).build().inject(this);
        mTest1.say(this);
        mTest2.show2(this);
    }
}
