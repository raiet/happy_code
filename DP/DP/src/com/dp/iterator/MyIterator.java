package com.dp.iterator;

public class MyIterator implements Iterator {
	private Collection collection;
	private int pos = 0;
	public MyIterator(Collection col){
		this.collection = col;
	}
	@Override
	public boolean hasNext() {
		return pos<collection.size();
	}
	@Override
	public int next() {
		int data = collection.get(pos++);
		return data;
	}

}
