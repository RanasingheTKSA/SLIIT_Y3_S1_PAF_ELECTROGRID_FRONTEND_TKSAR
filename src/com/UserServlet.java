package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	User userObj = new User();
	
    public UserServlet() {
        super();
    }

    private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String output = userObj.insertUser(
				request.getParameter("user_name"), 
				request.getParameter("user_nic"), 
				request.getParameter("user_address"), 
				request.getParameter("user_zip_code"), 
				request.getParameter("user_contact_number"), 
				request.getParameter("user_email"));
		       		        
		response.getWriter().write(output);
		doGet(request, response);
	}
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = userObj.updateUser(
				paras.get("hidConnReqIdSave").toString(), 
				paras.get("name").toString(), 
				paras.get("nic").toString(), 
				paras.get("address").toString(),
				paras.get("zipcode").toString(), 
				paras.get("contactnumber").toString(), 
				paras.get("email").toString());
		        
		response.getWriter().write(output);
	}
	

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = userObj.deleteUser(paras.get("id").toString());
		response.getWriter().write(output);
	}

}
