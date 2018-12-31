package net.toastynetworks.MCLEndUser.Domain;

public interface IObservable {
    void registerObserver(IObserver observer);

    void unregisterObserver(IObserver observer);

    void notifyObserver();
}
