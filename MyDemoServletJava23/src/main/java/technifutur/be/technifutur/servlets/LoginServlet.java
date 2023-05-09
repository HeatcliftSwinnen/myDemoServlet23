package technifutur.be.technifutur.servlets;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import technifutur.be.technifutur.domain.entities.User;
import technifutur.be.technifutur.services.UserServiceImpl;

@WebServlet(name = "login",urlPatterns ="/login",loadOnStartup = 1)
public class LoginServlet extends HttpServlet {

    public void init() {
        String connectionString= this.getServletContext().getInitParameter("CONNECTION_STRING");
        String dbUser= this.getServletContext().getInitParameter("CONNECTION_STRING");
        String dbPasseword= this.getServletContext().getInitParameter("CONNECTION_STRING");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("login","");
        request.getRequestDispatcher("pages/login.jsp").forward(request,response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        String login =request.getParameter("login");
        String password = request.getParameter("password");
        request.setAttribute("login",login);

        UserServiceImpl userService = new UserServiceImpl();
        try{

            User user = userService.login(login,password);
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);

        }catch (RuntimeException e){

        }


//        if(login.equals("kevin") && password.equals("dundalk")){
//            response.sendRedirect(request.getContextPath()+"/");
//        }else{
//
//           request.getRequestDispatcher("pages/login.jsp").forward(request,response);
//        }
    }

    public void destroy() {
    }
}