package model;
/**
 * the subject interface of the Observer pattern
 */
public interface Subject {
	/**
	 * method that registers an observer
	 * @param o , the observer
	 */
	void registraObserver(Observer o);
	/**
	 * method that removes an observer
	 * @param o , the observer
	 */
	void rimuoviObserver(Observer o);
	/**
	 * method that notifies all the observers
	 */
	void notificaObservers();
}
