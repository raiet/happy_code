package com.test.chain.responsible;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter{
	List<Filter> filterList = new ArrayList<Filter>();
	public List<Filter> getFilterList() {
		return filterList;
	}
	public void setFilterList(List<Filter> filterList) {
		this.filterList = filterList;
	}
	public int index = 0;
	public void addFilter(Filter f){
		filterList.add(f);
	}
	@Override
	public void doFilter(Request req, Response res, FilterChain ch) {
		if(index == ch.getFilterList().size()){
			return ;
		}
		Filter filter = ch.getFilterList().get(index);
		index++;
		filter.doFilter(req, res, ch);
	}
}
