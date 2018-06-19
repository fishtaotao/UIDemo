package tao.tao.dagger2;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * Author：wjt on 2018/6/6 0006 09:47
 */

public class Test2 {

    private Test1 mTest1;

    @Inject
    public Test2(Test1 mTest1) {
        this.mTest1 = mTest1;
    }

    public void show2(Context context) {
        Toast.makeText(context, "Test2", Toast.LENGTH_SHORT).show();
    }
}
