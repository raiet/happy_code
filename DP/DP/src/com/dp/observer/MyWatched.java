package com.dp.observer;

import java.util.ArrayList;
import java.util.List;

public class MyWatched implements Watched {
	private  List<Watcher> watchers = new ArrayList();
	@Override
	public void addWatcher(Watcher watcher) {
		// TODO Auto-generated method stub
		watchers.add(watcher);
	}

	@Override
	public void removeWatcher(Watcher watcher) {
		// TODO Auto-generated method stub
		watchers.remove(watcher);
	}

	@Override
	public void notifyAllWatcher() {
		// TODO Auto-generated method stub
		for(Watcher w : watchers){
			w.update();
		}
	}

}
