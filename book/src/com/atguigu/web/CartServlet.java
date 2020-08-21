package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
   private BookService bookService =new BookServiceImpl();

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("方法被调用");
        //获取修改的id和数量
        int id =WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"),1);
        //获取cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //调取方法修改
        if(cart != null){
            cart.updateCount(id,count);
            //重定向回原始页面
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }
    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //判断是否为null然后删除
        if(cart != null){
            cart.clear();
            //重定向回原页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       int id = WebUtils.parseInt(req.getParameter("id"),0);
       Cart cart = (Cart) req.getSession().getAttribute("cart");
       if(cart != null){

           cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
       }


    }
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("添加商品到购物车");
        //获取请求参数商品的编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //调用bookservice查询得到商品信息
        Book book = bookService.queryBookById(id);
        //把图书信息转换为Cartitem
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //调用cart。add方法加入到map中
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }

        cart.addItem(cartItem);
        req.getSession().setAttribute("lastName",cartItem.getName());
        resp.sendRedirect(req.getHeader("Referer"));
    }
  protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("添加商品到购物车");
        //获取请求参数商品的编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //调用bookservice查询得到商品信息
        Book book = bookService.queryBookById(id);
        //把图书信息转换为Cartitem
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //调用cart。add方法加入到map中
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }

        cart.addItem(cartItem);
        req.getSession().setAttribute("lastName",cartItem.getName());
      Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());
      Gson gson = new Gson();
      String resultMapJsonString = gson.toJson(resultMap);
      resp.getWriter().write(resultMapJsonString);

//        resp.sendRedirect(req.getHeader("Referer"));
    }

}
