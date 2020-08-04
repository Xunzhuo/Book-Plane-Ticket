package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import javabean.db_conn;//�����������ݿ���
import javabean.get_md5;//����MD5��ϣ�����ࣨ����Ϊ�Լ�д�ģ���������е��ࣩ
public class check_login_reg extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("�ó��򲻽���ֱ�ӷ��ʣ��벻Ҫ���ԷǷ�����");
		resp.setHeader("refresh", "2;url=index/login_reg.jsp");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String log_name = req.getParameter("log_name");
		String log_pwd = req.getParameter("log_pwd");
		String reg_name = req.getParameter("reg_name");
		String reg_pwd1 = req.getParameter("reg_pwd1");
		String reg_pwd2 = req.getParameter("reg_pwd2");
		
		if(log_name!=null&&log_pwd!=null&&reg_name==null&&reg_pwd1==null&&reg_pwd2==null) {
			//���õ�¼���������¼
			go_login(log_name, log_pwd, req, resp);
			
		}
		else if(log_name==null&&log_pwd==null&&reg_name!=null&&reg_pwd1!=null&&reg_pwd2!=null&&reg_pwd1.equals(reg_pwd2)) {
			//����ע�᷽������ע��
			go_reg(reg_name, reg_pwd1, req, resp);
			
		}//Ϊʲô����дһ���ж�ע��ʱreg_pwd1��reg_pwd2�Ƿ���ȵ�����أ���Ϊ��ǰ��ҳ�����Ѿ���jsȥ���ж��ˣ�
		//����û�������������벻һ�¸����������ύ���ݵ���servlet�������û�����js�����޸���js��Ҳ�������˷Ƿ�����
		//��ˣ�ֱ�������ʾ�Ƿ���������ʾ��Ϣ����
		else {
			
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("�벻Ҫ���ԷǷ�����");
			/*****��������
			out.println("��¼�˺ţ�"+log_name+"\n��¼���룺"+log_pwd+"\nע���˺ţ�"
			+reg_name+"\nע������1��"+reg_pwd1+"\nע������2��"+reg_pwd2);
			out.print("---log_name==null:"+(log_name==null));
			out.print("---log_pwd==null:"+(log_pwd==null));
			out.print("---reg_name==null:"+(reg_name==null));
			out.print("---reg_pwd1==null:"+(reg_pwd1==null));
			out.print("---reg_pwd2==null:"+(reg_pwd2==null));
			*/
					
			resp.setHeader("refresh", "2;url=index/login_reg.jsp");
			//���˵�¼��ע��������ύ�������ݾ�Ϊ�Ƿ�����������������������ʾ�Ƿ���Ϣ
		}
	}
	
	protected void go_login(String name, String pwd, HttpServletRequest req, HttpServletResponse resp) {
		//System.out.println("go_login��������");
		db_conn conn = new db_conn();//�������ݿ����Ӷ���
		get_md5 MD5 = new get_md5();
		pwd = MD5.getMD5(pwd);
		pwd = MD5.getMD5(pwd);
		//ȡ��cookie����֤�Ƿ���url�������url������ת��url�����û������ת��index.jsp
		//Ŀ������ת���û������ԭҳ�棬����û������
		HttpSession session = req.getSession(); 
		/*
		 * ����cookie��ת��ʹ��session�����¼��ҳ����ת�����⣬cookieò���޷�����������
		 * Cookie c = null; Cookie[] cookies = req.getCookies(); for(int
		 * i=0;i<cookies.length;i++) { System.out.println("�ж�cookie��������");
		 * if(cookies[i].getName().equals("url")) { c = cookies[i];
		 * System.out.println("url_cookie��ȡ��"); } }
		 */
		String sql = "select * from common_user where user_name = '"+name+"'";
		ResultSet res = conn.executeQuery(sql);
		try {
			
			if(res.next()) {
				String user_pwd = res.getString(2);
				
				if(pwd.equals(user_pwd)) {
					//System.out.println("��¼��֤�ɹ�");
					session.setAttribute("user_id", name);
					
					if(session.getAttribute("url")!=null) {
						String url = session.getAttribute("url").toString();
						try{
							resp.sendRedirect(url);
							}
						catch (IOException e) {
							System.out.println("������Ϣ���£�"+e);
						}
					}else {
						try {
							resp.sendRedirect("user_center");
						}
						catch (IOException e) {
							System.out.println("������Ϣ���£�"+e);
						}
						
					}
				}else {
					//�û������������
					try {
						//System.out.println("�û��������");
						resp.setContentType("text/html;charset=utf-8");
						PrintWriter out = resp.getWriter();
						out.println("�˺Ż�������������µ�¼");
						resp.setHeader("refresh", "2;url=index/login_reg.jsp");
					}catch (IOException e) {
						System.out.println("������Ϣ���£�"+e);
					}
					
				}				
			}else {
				//�û��˺Ŵ���
				try {
					//System.out.println("�û��˻�����");
					resp.setContentType("text/html;charset=utf-8");
					PrintWriter out = resp.getWriter();
					out.println("�˺Ż�������������µ�¼");
					resp.setHeader("refresh", "2;url=index/login_reg.jsp");
				}catch (IOException e) {
					System.out.println("������Ϣ���£�"+e);
				}
			}
			conn.closeDB();
		}
		catch (SQLException e) {
			System.out.println("������Ϣ���£�"+e);
		}
	}
	protected void go_reg(String name, String pwd1,HttpServletRequest req, HttpServletResponse resp) {
		//System.out.println("ע�᷽����������");
		resp.setContentType("text/html;charset=utf-8");
		
		
		db_conn conn = new db_conn();//�������ݿ����Ӷ���
		get_md5 MD5 = new get_md5();
		pwd1 = MD5.getMD5(pwd1);
		pwd1 = MD5.getMD5(pwd1);
		String sql = "select * from common_user where user_name = '"+name+"'";
		ResultSet res = conn.executeQuery(sql);
		try {
			PrintWriter out = resp.getWriter();
			try {
				if(res.next()) {			
					out.println("���û����ѱ�ռ�ã���ʹ�������û�������ע��");
					resp.setHeader("refresh", "2;url=index/login_reg.jsp");			
				}else {
					sql = "insert into common_user (user_name,user_pwd) values('"+name+"','"+pwd1+"')";
					int in_res = conn.executeInsert(sql);
					//System.out.println(in_res);
					if(in_res==1) {
						out.println("��ϲ��ע��ɹ���2��֮����ת����¼ҳ��");
						resp.setHeader("refresh", "2;url=index/login_reg.jsp");	
					}else {
						out.println("������������ϵ������Ա�����޲�bug");
						resp.setHeader("refresh", "2;url=index/login_reg.jsp");
					}
				}
			}catch (Exception e) {
				System.out.print("������Ϣ���£�"+e);
			}
		}catch (IOException e) {
			System.out.println("������"+e);
		}
		
		conn.closeDB();
	}
}
