package com.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	//츄성매서드
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception;

}
