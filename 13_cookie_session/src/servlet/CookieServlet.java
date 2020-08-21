package servlet;

import util.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.CookieUtil.findCookie;

public class CookieServlet extends BaseServlet {
    protected void life3600(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Cookie cookie = new Cookie("life3600","life3600");
            cookie.setMaxAge(3600);
            resp.addCookie(cookie);
            resp.getWriter().write("设置存活时间一个小时");
    }


        protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Cookie cookie = CookieUtil.findCookie("key1",req.getCookies());
            if(cookie != null){
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
                resp.getWriter().write("key1的cookie已经被删除");
            }
    }

    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("defaultLife","defaultLife");
        cookie.setMaxAge(-1);
        resp.addCookie(cookie);
    }


    protected void updateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = CookieUtil.findCookie("key1",req.getCookies());
        if(cookie != null){
            cookie.setValue("newValue");
            resp.addCookie(cookie);
        }
        resp.getWriter().write("key1的值已更新");

    }
    protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            resp.getWriter().write("cookie["+cookie.getName()+"="+cookie.getValue()+"]<br/>");
        }

      Cookie iwantcookie   = findCookie("key1", cookies);
        if(iwantcookie != null){
            resp.getWriter().write("找到了");
        }


    }
    protected void createCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建cookie对象
        Cookie cookie = new Cookie("key1","value1");
        //通知客户端保存cookie对象
        resp.addCookie(cookie);
        resp.getWriter().write("Cookie对象创建成功");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
