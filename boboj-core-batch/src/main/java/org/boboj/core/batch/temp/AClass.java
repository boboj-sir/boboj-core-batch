package org.boboj.core.batch.temp;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/30 15:42
 * @detail
 */
public class AClass extends AbstractAB{
    @Override
    public void write() {
        System.out.println("我是A写");
    }

    @Override
    public void say() {

    }
    protected  void transfer(){
        write();
    }
}
