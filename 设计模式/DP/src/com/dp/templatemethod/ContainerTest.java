package com.dp.templatemethod;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContainerTest {

	@Test
	public void test() {
		Container container = new Engine("first engine");
		
		Host host1 = new Host("host1");
		Host host2 = new Host("host2");
		
		
		Context context11 = new Context("context11");
		Context context12 = new Context("context12");
		Context context21 = new Context("context21");
		Context context22 = new Context("context22");
		
		host1.add(context11);
		host1.add(context12);
		
		host2.add(context21);
		host2.add(context22);
				
		
		container.add(host1);
		container.add(host2);
		
		
		container.start();
		
	}

}
