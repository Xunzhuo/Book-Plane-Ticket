package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javabean.db_conn;
public class upload_file extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//GET��ʽ�ύ�����ݻ������ǷǷ����������ô���ֱ�ӷ���edit_infoҳ��Ϳ���
			resp.sendRedirect("/jsp_plane_ticket_book/index/edit_info.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		if(session.getAttribute("user_id")==null) {
			response.sendRedirect("index/user_info.jsp");
		}
		else {
			//request.setCharacterEncoding("utf-8");
			boolean flag=ServletFileUpload.isMultipartContent(request);
			if(flag) {
				DiskFileItemFactory factory=new DiskFileItemFactory();
				ServletFileUpload upload=new ServletFileUpload(factory);
				try {
					List<FileItem>fileList = upload.parseRequest(request);
					Iterator<FileItem> myitor = fileList.iterator();
					while(myitor.hasNext()) {
						FileItem item=myitor.next();
						if(item!=null) {
							String filename=item.getName();
							if(filename!=null) {
								String path="index/upload";
								String absolutepath=this.getServletContext().getRealPath(path);
								//System.out.println("�ļ���"+filename);
								
								File file = new File(filename);
								//System.out.println("�ļ���Ϊ��"+file.getName());
								String file_name=file.getName();
								String ext=file_name.substring(file_name.lastIndexOf("."));
								//System.out.println("�ļ���չ��Ϊ��"+ext);
								String end_filename=UUID.randomUUID().toString()+ext;
								//System.out.println("ͷ��ͼƬ����Ϊ��"+end_filename);
								File uploadFile = new File(absolutepath, end_filename);
								try {
									if(uploadFile.exists()) {
										uploadFile.delete();
									}
									item.write(uploadFile);
									//System.out.println("�ϴ��ɹ�");
									//System.out.println("ͼƬ�洢�ļ���Ϊ��"+absolutepath);
									
									
									//�ϴ��ļ��ɹ����ļ���д�����ݿ�
									
									db_conn conn= new db_conn();
									String sql="update common_user set avatar_img='"+end_filename+"' "
											+ "where user_name='"+session.getAttribute("user_id")+"'";
									int res=conn.Update(sql);
									//System.out.println(res);
									PrintWriter out=response.getWriter();
									response.setContentType("text/html;charset=utf-8");
									if(res!=0) {
										response.sendRedirect("/jsp_plane_ticket_book/index/user_info.jsp");
									}else {
										
										out.println("�ļ��ϴ�ʧ�ܣ��������bug������ϵ������Ա�޸�bug");
										response.setHeader("refresh", "2;url=/jsp_plane_ticket_book/index/user_info.jsp");
									}
									
									
									
									
									
								}catch (Exception e) {
									System.out.println("������1��������Ϣ���£�"+e);
								}
								
							}				
						}
					}
				} catch (FileUploadException e) {
					System.out.println("������2��������Ϣ���£�"+e);
					e.printStackTrace();
				}
			}
		}
		
	}
}
