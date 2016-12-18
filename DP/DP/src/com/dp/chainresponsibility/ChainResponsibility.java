package com.test.chain.responsible;


/**
 * ������ģʽ
 * 
 * ����һ����Ϣ������ͬ�Ĺ������������ҷ���ʱҲ�����������Ĵ������Ҵ���˳�����෴��
 * 
 * */
public class ChainResponsibility {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String msg = "��Һ�):,�ֱ���ҵ��,������Ĳٵ�";
		//�������Ϣ
		Request req = new Request();
		//��Ӧ����Ϣ
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
