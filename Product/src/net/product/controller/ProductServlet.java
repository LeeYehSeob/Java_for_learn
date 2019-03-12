package net.product.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.product.model.GroupcodeVO;
import net.product.model.ProductDAOImpl;
import net.product.model.ProductVO;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.pro")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	ProductDAOImpl dao = new ProductDAOImpl();
    	RequestDispatcher dispatcher = null;
    	
    	
    	String uri = request.getRequestURI();
    	int lastIndex = uri.lastIndexOf("/");
    	String action = uri.substring(lastIndex + 1);
    	request.setCharacterEncoding("utf-8");
    	
    	
    	System.out.println(action);
    	
    	
    	if(action.equals("create.pro")) {
    		
    		ProductVO vo = new ProductVO();
    		vo.setP_code(request.getParameter("code"));
    		vo.setP_name(request.getParameter("pname"));
    		vo.setCost(Integer.parseInt(request.getParameter("cost")));
    		vo.setPnum(Integer.parseInt(request.getParameter("pnum")));
    		vo.setInum(Integer.parseInt(request.getParameter("inum")));
    		vo.setSale(Integer.parseInt(request.getParameter("sale")));
    		vo.setG_code(request.getParameter("gcode"));
    		
    		try {
				dao.create(vo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
    		response.sendRedirect("main.jsp");
    		
    	}else if(action.equals("readAndUpdate.pro")) {
    		String flag = request.getParameter("flag");
    		System.out.println(flag);
    		
    		if(flag.equals("조    회")) {
    			ProductVO vo = new ProductVO();
        		vo.setP_code(request.getParameter("code"));
        		try {
    				vo = dao.readOne(vo);
    				ArrayList<GroupcodeVO> list = (ArrayList<GroupcodeVO>)dao.readGroupcodeList();
    				request.setAttribute("list", list);
    				request.setAttribute("vo", vo);
    				dispatcher = request.getRequestDispatcher("searchAndModify.jsp");
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
        		
    		}else if(flag.equals("수    정")) {
    			ProductVO vo = new ProductVO();
        		vo.setP_code(request.getParameter("code"));
        		vo.setP_name(request.getParameter("pname"));
        		vo.setCost(Integer.parseInt(request.getParameter("cost")));
        		vo.setPnum(Integer.parseInt(request.getParameter("pnum")));
        		vo.setInum(Integer.parseInt(request.getParameter("inum")));
        		vo.setSale(Integer.parseInt(request.getParameter("sale")));
        		vo.setG_code(request.getParameter("gcode"));
        		try {
    				dao.update(vo);
    				ArrayList<GroupcodeVO> list = (ArrayList<GroupcodeVO>)dao.readGroupcodeList();
    				request.setAttribute("list", list);
    				request.setAttribute("vo", vo);
    				System.out.println("수정 성공");
    				dispatcher = request.getRequestDispatcher("searchAndModify.jsp");
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
        		
    		}
    		
    		
    	}
    	/*else if(action.equals("readone.pro")) { //조회
    		ProductVO vo = new ProductVO();
    		vo.setP_code(request.getParameter("code"));
    		try {
				vo = dao.readOne(vo);
				ArrayList<GroupcodeVO> list = (ArrayList<GroupcodeVO>)dao.readGroupcodeList();
				request.setAttribute("list", list);
				request.setAttribute("vo", vo);
				dispatcher = request.getRequestDispatcher("searchAndModify.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			}
    		
    	}else if(action.equals("update.pro")) { // 수정
    		ProductVO vo = new ProductVO();
    		vo.setP_code(request.getParameter("code"));
    		vo.setP_name(request.getParameter("pname"));
    		vo.setCost(Integer.parseInt(request.getParameter("cost")));
    		vo.setPnum(Integer.parseInt(request.getParameter("pnum")));
    		vo.setInum(Integer.parseInt(request.getParameter("inum")));
    		vo.setSale(Integer.parseInt(request.getParameter("sale")));
    		vo.setG_code(request.getParameter("gcode"));
    		try {
				dao.update(vo);
				ArrayList<GroupcodeVO> list = (ArrayList<GroupcodeVO>)dao.readGroupcodeList();
				request.setAttribute("list", list);
				request.setAttribute("vo", vo);
				System.out.println("수정 성공");
				dispatcher = request.getRequestDispatcher("searchAndModify.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			}
    		
    	}*/else if(action.equals("delete.pro")) { // 삭제
    		ProductVO vo = new ProductVO();
    		vo.setP_code(request.getParameter("code"));
    		
    		try {
				if(dao.delete(vo)>0) {
					vo.setP_code("");
					vo.setP_name("");
					vo.setG_code("");
				}
				ArrayList<GroupcodeVO> list = (ArrayList<GroupcodeVO>)dao.readGroupcodeList();
				request.setAttribute("vo", vo);
				request.setAttribute("list", list);
				System.out.println("삭제 성공");
				dispatcher = request.getRequestDispatcher("searchAndModify.jsp");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
    		
    	}else if(action.equals("toAddForm.pro")) { // 추가 페이지로
    	
    		
    		try {
				ArrayList<GroupcodeVO> list = (ArrayList<GroupcodeVO>)dao.readGroupcodeList();
				request.setAttribute("list", list);
				dispatcher = request.getRequestDispatcher("addProduct.jsp");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
    	}else if(action.equals("toModifyForm.pro")) {
    		try {
				ArrayList<GroupcodeVO> list = (ArrayList<GroupcodeVO>)dao.readGroupcodeList();
				request.setAttribute("list", list);
				dispatcher = request.getRequestDispatcher("searchAndModify.jsp");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
    	}
    	
    	if(dispatcher!=null) {
    		dispatcher.forward(request, response);
    	}
    	
	}

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
