package org.prototype.check.servelet;

import com.baidu.ueditor.ActionEnter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by root on 2017/3/30.
 */

@WebServlet(name = "UEditorServlet", urlPatterns = "/ueditor/index")
public class UEditorServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding( "utf-8" );
        response.setHeader("Content-Type" , "text/html");
        PrintWriter out = response.getWriter();
        ServletContext application=this.getServletContext();
        String rootPath = application.getRealPath( "/" );

        String action = request.getParameter("action");
        String result = new ActionEnter( request, rootPath ).exec();
        if( action!=null &&
                (action.equals("listfile") || action.equals("listimage") ) ){
            rootPath = rootPath.replace("\\", "/");
            result = result.replaceAll(rootPath, "/");
        }
        out.write( result );
    }
}
