package org.boboj.core.batch.temp;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/30 15:55
 * @detail
 */
public class AllClass extends AbstractAB{

    protected void transfer() {

    }


    public void write() {
        AbstractAB a = new AClass();
        a.write();

    }


    public void say() {
        AbstractAB b = new BClass();
        b.say();
    }
}
