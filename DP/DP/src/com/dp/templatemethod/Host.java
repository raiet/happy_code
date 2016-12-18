package com.dp.templatemethod;

import java.util.ArrayList;
import java.util.List;

public class Host extends Container {
	private List<Context> contexts = new ArrayList<Context>();
	public Host(String name){
		this.name = name;
	}
	public void startInternal(){
		System.out.println("begin start Host~~~" + name);
		for(Context context:contexts){
			context.start();
		}
		//System.out.println("after start Host~~~" + name);
	}
	public void add(Container context){
		contexts.add((Context)context);
	}
}
