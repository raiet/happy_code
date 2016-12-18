package com.dp.command;

import org.junit.Test;

//命令模式的目的就是达到命令的发出者和执行者之间解耦
public class CommandTest {

	@Test
	public void test() {
		Reciver reciver = new Reciver();
		Command commd = new MyCommand(reciver);
		Invoke invoke = new Invoke(commd);
		invoke.action();
	}

}
