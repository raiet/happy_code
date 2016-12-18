package com.dp.iterator;

import org.junit.Test;

public class IteratorTest {
	@Test
	public  void tes(){
		Collection col = new MyCollection();
		for(int i = 0;i<5;i++){
			col.add(i);
		}
		Iterator iter = col.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
}
