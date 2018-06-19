package tao.tao.dagger2;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * Author：wjt on 2018/6/6 0006 09:02
 */

public class Test1 {
    private Context context;

    @Inject
    public Test1() {

    }

    public void say(Context context) {
        Toast.makeText(context, "Test1", Toast.LENGTH_SHORT).show();
    }
}
