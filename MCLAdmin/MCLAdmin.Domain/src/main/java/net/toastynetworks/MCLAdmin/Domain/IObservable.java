package net.toastynetworks.MCLAdmin.Domain;

public interface IObservable {

    void registerObserver(IObserver observer);

    void unregisterObserver(IObserver observer);

    void notifyObserver();

}
