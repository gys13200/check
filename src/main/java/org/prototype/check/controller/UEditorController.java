package org.prototype.check.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import com.baidu.ueditor.ActionEnter;

/**
 * Created by gys on 2017/3/29.
 */

//@Controller
//@RequestMapping("/ueditor")
public class UEditorController {

    @RequestMapping("/index")
    public void ueditor(HttpServletRequest request, HttpServletResponse response) throws IOException{

        try{
            request.setCharacterEncoding( "utf-8" );
            response.setHeader("Content-Type" , "text/html");
            String realPath = request.getServletContext().getRealPath("/");
            response.getWriter().write(new ActionEnter( request, realPath ).exec());

        }catch (UnsupportedEncodingException e){
            // do nothing
        }
    }

    @RequestMapping("/uploadImage")
    public void uploadImage(){

    }
}
