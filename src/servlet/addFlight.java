package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabean.db_conn;
public class addFlight extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ʼ��ȡ���ֲ���
		req.setCharacterEncoding("utf-8");
		String flight_id=req.getParameter("flight_id");
		String start_place=req.getParameter("start_place");
		String end_place=req.getParameter("end_place");
		String start_airport=req.getParameter("start_airport");
		String end_airport=req.getParameter("end_airport");
		String take_off_time=req.getParameter("take_off_time");
		String landing_time=req.getParameter("landing_time");
		String first_class_price_str=req.getParameter("first_class_price");
		Integer first_class_price=Integer.parseInt(first_class_price_str);
		String business_class_price_str=req.getParameter("business_class_price");
		Integer business_class_price=Integer.parseInt(business_class_price_str);
		String economy_class_price_str=req.getParameter("economy_class_price");
		Integer economy_class_price=Integer.parseInt(economy_class_price_str);
		//������ȡ����
		db_conn conn=new db_conn();
		String sql="select * from flight where f_n='"+flight_id+"'";
		ResultSet res=conn.executeQuery(sql);
		try {
			if(res.next()) {
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter out=resp.getWriter();
				out.println("������ĺ�����ظ��ˣ���ѡ�������������ӣ�5s�󷵻�");
				resp.setHeader("refresh", "5;url=admin/flight_add.jsp");
			}else {
				sql="insert into flight values('"+flight_id+"','"+start_place+"','"+end_place+"','"+start_airport+"','"+end_airport+"','"+take_off_time+"','"+landing_time+"','"+first_class_price+"','"+business_class_price+"','"+economy_class_price+"')";
				//Integer res=
				conn.executeInsert(sql);
				//System.out.println(res);
				//System.out.println(sql);
				resp.sendRedirect("admin/flight_list.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
