package com.dp.iterator;

import java.util.ArrayList;
import java.util.List;


public class MyCollection implements Collection {
	private List<Integer> list = new ArrayList<Integer>();
	@Override
	public Iterator iterator() {
		
		return new MyIterator(this);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void add(int data) {
		// TODO Auto-generated method stub
		list.add(data);
	}

	@Override
	public int get(int i) {
		// TODO Auto-generated method stub
		return (Integer)list.get(i);
	}

}
