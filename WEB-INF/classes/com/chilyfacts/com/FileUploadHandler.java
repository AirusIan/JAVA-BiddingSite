
package com.chilyfacts.com;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
public class FileUploadHandler extends HttpServlet {
 private static final long serialVersionUID = 1 ;
 public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
 doPost(request, response);
 }
 public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
 String file_name = null;
 String image = "";
 response.setContentType("text/html");
 PrintWriter out = response.getWriter();
 boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
 if (!isMultipartContent) {
 return;
 }
 FileItemFactory factory = new DiskFileItemFactory();
 ServletFileUpload upload = new ServletFileUpload(factory);
 try {
 List < FileItem > fields = upload.parseRequest(request);
 Iterator < FileItem > it = fields.iterator();
 if (!it.hasNext()) {
 return;
 }
 while (it.hasNext()) {
 FileItem fileItem = it.next();
 boolean isFormField = fileItem.isFormField();
 if (isFormField) {
 if (file_name == null) {
 if (fileItem.getFieldName().equals("file_name")) {
 file_name = fileItem.getString();
 }
 }
 } else {
 if (fileItem.getSize() > 0) {
 fileItem.write(new File("D:\\Desktop\\NCU_MIS_SA\\statics\\img\\product2\\" + fileItem.getName()));
 image = fileItem.getName();
 }
 }
 }
 } catch (Exception e) {
 e.printStackTrace();
 } finally {
 out.println("<script type='text/javascript'>");
 out.println("window.location.href='add_product.html?image="+image+"'");
 out.println("</script>");
 out.close();
 }
 }
}