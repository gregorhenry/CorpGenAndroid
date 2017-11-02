package pe.edu.utp.corpgenprojectburningencounters.login.eventbus;

/**
 * Created by root on 10/31/17.
 */

public interface EventBus {

    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
