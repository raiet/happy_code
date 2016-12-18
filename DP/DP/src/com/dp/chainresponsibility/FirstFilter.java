package com.test.chain.responsible;

public class FirstFilter implements Filter {

	@Override
	public void doFilter(Request req, Response res,FilterChain ch) {
		System.out.println("~~~do first filter,process request~~~~~~~~~~~~");
		req.setStr(req.getStr().replace("):", "^~^"));
		System.out.println("request process over:" + req.getStr());
		ch.doFilter(req, res, ch);
		System.out.println("~~~do first filter,process response~~~~~~~~~~~~");
		res.setStr(res.getStr().replace("):", "^~^"));
		System.out.println("response process over:                                         " + res.getStr());
	}

}
