package servlet;

import com.atguigu.pojo.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = new ArrayList<Student>();
        for (int i = 0; i <10 ; i++) {
            int t = i+1;
            studentList.add(new Student(t,"name"+t,18+t,"phone"+t));
        }
            req.setAttribute("stuList",studentList);
            req.getRequestDispatcher("/test/showStudent.jsp").forward(req,resp);

    }
}
