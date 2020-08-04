package javabean;
import java.sql.*;

public class db_conn {
	public Connection conn = null;
	public ResultSet res = null;
	public Statement st = null;
	public  db_conn() {
		String URL="jdbc:mysql://localhost:3306/jsp_plane_ticket_book?serverTimezone=UTC";
		String USER="root";
		String PWD="lxz991203";
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}try{
			conn = DriverManager.getConnection(URL,USER,PWD);
			st=conn.createStatement();
		}catch(SQLException e){
			System.out.println(e);
		}
	}
	

	public int executeInsert(String sql){
		int num=0;
		try{
			num=st.executeUpdate(sql);
		}
		catch(SQLException e){
			System.out.println("������ݳ�����Ϣ:"+e.getMessage());
		}
		return num;
	}
	

	public ResultSet executeQuery(String sql){
		res=null;
		try{
			res=st.executeQuery(sql);
		}
		catch(SQLException e){
			System.out.print("��ѯ������Ϣ:"+e.getMessage());
		}
		return res;
	}
	

	public int Update(String sql){
		int num=0;
		try{
			num=st.executeUpdate(sql);
		}catch(SQLException ex){
			System.out.print("ִ���޸��д���"+ex.getMessage());
		}
		return num;
	}
	

	public int executeDelete(String sql){
		int num=0;
		try{
			num=st.executeUpdate(sql);
		}
		catch(SQLException e){
			System.out.print("ִ��ɾ���д���:"+e.getMessage());
		}
		return num;
	}
	

	public void closeDB(){
		try{
			st.close();
			conn.close();
		}
		catch(Exception e){
			System.out.print("ִ�йر�Connection�����д���:"+e.getMessage());
		}
	}
}
