package com.test.chain.responsible;

public class ThirdFilter implements Filter {

	@Override
	public void doFilter(Request req, Response res, FilterChain ch) {
		System.out.println("~~~do third filter,process request~~~~~~~~~~~~");
		req.setStr(req.getStr().replace("�ٵ�", "ˬ"));
		System.out.println("request process over:" + req.getStr());
		ch.doFilter(req, res, ch);
		System.out.println("~~~do third filter,process response~~~~~~~~~~~~");
		res.setStr(res.getStr().replace("�ٵ�", "ˬ"));
		System.out.println("response process over:                                         " + res.getStr());
	}

}
