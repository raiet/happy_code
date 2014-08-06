package com.test.chain.responsible;

public class SecondFilter implements Filter {

	@Override
	public void doFilter(Request req, Response res, FilterChain ch) {
		System.out.println("~~~do second filter,process request~~~~~~~~~~~~");		
		req.setStr(req.getStr().replace("被就业", "就业"));
		System.out.println("request process over:" + req.getStr());
		ch.doFilter(req, res, ch);
		System.out.println("~~~do second filter,process response~~~~~~~~~~~~");
		res.setStr(res.getStr().replace("被就业", "就业"));
		System.out.println("response process over:                                         " + res.getStr());
	}

}
