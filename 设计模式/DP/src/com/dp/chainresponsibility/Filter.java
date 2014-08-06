package com.test.chain.responsible;

public interface Filter {
	public void doFilter(Request req,Response res,FilterChain ch);
}
