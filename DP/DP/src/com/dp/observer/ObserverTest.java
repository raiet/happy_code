package com.dp.observer;



public class ObserverTest {
	public static void main(String[] args) {
		Watched wed = new MyWatched();
		Watcher w1 = new MyWatcher();
		Watcher w2 = new MyWatcher();
		Watcher w3 = new MyWatcher();
		
		wed.addWatcher(w1);
		wed.addWatcher(w2);
		wed.addWatcher(w3);
		
		
		
		wed.notifyAllWatcher();
		
	}
}
