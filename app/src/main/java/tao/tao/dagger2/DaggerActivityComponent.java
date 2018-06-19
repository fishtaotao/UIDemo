package tao.tao.dagger2;

import dagger.Component;

/**
 * Author：wjt on 2018/6/6 0006 09:13
 */
@Component(modules = MainMoudle.class)
public interface DaggerActivityComponent {
    void  inject(DaggerActivity DaggerActivity);
}
