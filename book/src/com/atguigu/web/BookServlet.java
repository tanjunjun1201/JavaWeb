package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),0);
       pageNo +=1;
        Book book = WebUtils.copyParamToBean(request.getParameterMap(),new Book());
        bookService.addBook(book);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"),0);
        bookService.deleteBookById(id);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));

    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取请求参数封装成Book对象
        Book book = WebUtils.copyParamToBean(request.getParameterMap(),new Book());
        //2、调用BookService.updateBook;修改图书
        bookService.updateBook(book);
        //3、重定向图书列表管理页面
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取请求参数的图书编号
        int id = WebUtils.parseInt(request.getParameter("id"),0);
        //2、调用bookService.queryBookById查询图书信息
        Book book = bookService.queryBookById(id);
        //3、保存图书到Request域中
        request.setAttribute("book",book);
        //4、请求转发到。pages/manager/book_edit.jsp页面中
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);

    }
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.queryBooks();
        request.setAttribute("books",books);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    /**
     * 处理分页功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2、调用BOOKSERVICE
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3、保存page对象到request域中
        request.setAttribute("page",page);
        //4、请求转发到图书管理jsp页面中
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);

    }
}
