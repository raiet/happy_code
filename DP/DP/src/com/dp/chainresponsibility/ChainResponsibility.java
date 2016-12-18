package com.test.chain.responsible;


/**
 * 责任链模式
 * 
 * 对于一个消息，经不同的过滤器处理，并且返回时也经过过滤器的处理，并且处理顺序是相反的
 * 
 * */
public class ChainResponsibility {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String msg = "大家好):,又被就业了,真他妈的操蛋";
		//请求的消息
		Request req = new Request();
		//响应的消息
		Response res = new Response();
		req.setStr(msg);
		res.setStr(msg);
		//
		FilterChain ch = new FilterChain();
		FirstFilter filter1 = new FirstFilter();
		SecondFilter filter2 = new SecondFilter();
		ThirdFilter filter3 = new ThirdFilter();
		
		FilterChain sech = new FilterChain();
		//ForthFileter filter4 = new ForthFileter();
		sech.addFilter(filter3);
		
		
		
		ch.addFilter(filter1);
		ch.addFilter(filter2);
		//ch.addFilter(filter3);
		ch.addFilter(sech);
		
		
		ch.doFilter(req, res, ch);
		//System.out.println("request:" + req.getStr());
		//System.out.println("response:" + res.getStr());
	
	}

}
