package tao.tao.dagger2;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Author：wjt on 2018/6/6 0006 09:22
 */
@Module
public class MainMoudle {
    private Context context;

    public MainMoudle(Context context) {
        this.context = context;
    }


    @Provides
    public Test1 getTest1() {
        return new Test1();
    }


}
