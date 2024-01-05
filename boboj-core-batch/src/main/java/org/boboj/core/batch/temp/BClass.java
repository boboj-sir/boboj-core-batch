package org.boboj.core.batch.temp;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/30 15:42
 * @detail
 */
public class BClass extends AbstractAB{
    @Override
    public void write() {

    }

    @Override
    public void say() {
        System.out.println("我是B说");
    }

    protected  void transfer(){
        say();
    }

}
