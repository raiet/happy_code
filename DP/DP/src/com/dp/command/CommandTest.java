package com.dp.command;

import org.junit.Test;

//����ģʽ��Ŀ�ľ��Ǵﵽ����ķ����ߺ�ִ����֮�����
public class CommandTest {

	@Test
	public void test() {
		Reciver reciver = new Reciver();
		Command commd = new MyCommand(reciver);
		Invoke invoke = new Invoke(commd);
		invoke.action();
	}

}
