package com.unsw.Controller;

import com.unsw.Entity.Users;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "HeadPhotoUpLoad")
public class HeadPhotoUpLoad extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("111");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset = utf-8");
//        System.out.println(request.getParameter("fileupload").toString());
        System.out.println("222");
        PrintWriter out = response.getWriter();
        String uploadpath = "";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        int maxsize = 5*1024*1024;
        upload.setHeaderEncoding("utf-8");

        List<FileItem> items = null;
        try{
            items = upload.parseRequest(request);
        }catch (FileUploadException e){
            System.out.println("Head photo fail");
            e.printStackTrace();
        }

        Iterator<FileItem> itemIterator = items.iterator();
            File uploadFile = new File(request.getSession().getServletContext().getRealPath("/")+"upload/");
            uploadpath = uploadFile.getAbsolutePath()+File.separator+uploadpath;
        if(uploadFile.exists()==false){
            uploadFile.mkdir();
        }
        Timestamp time = new Timestamp(new Date().getTime());
        String filePath = "";
        Users user = (Users)request.getSession().getAttribute("login-user");
        while(itemIterator.hasNext()){
            FileItem item = (FileItem)itemIterator.next();
            if(!item.isFormField()){
                //在这里修改储存时的文件名

                filePath = user.getUid()+time.toString().replace(":"," ").replace("-"," ")
                        .replace("."," ")
                        .replace(" ","")+item.getName();
//                String filePath = time.toString();
                if(filePath!=null){
                    File filename = new File(item.getName());
                }
                if (item.getSize()>maxsize){
                    out.print("<p align='center>上传失败，文件大小不得超过5M</p>");
                    break;
                }
                File saveFile = new File(uploadpath,filePath);
                try{
                    item.write(saveFile);
                    out.print("<p align='center>上传成功</p>");
                }catch (Exception e){
                    out.print("<p align='center>上传失败</p>");
                }
            }
        }
        request.setAttribute("filePath","/upload/"+filePath);


        request.getRequestDispatcher("/control?action=profileupdate.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
