package com.study.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CCommand 
{
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
}
