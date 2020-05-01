package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/all")
public class AllUsersServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String name = (String) httpSession.getAttribute("name");
        String password = (String) httpSession.getAttribute("password");
        if (userService.checkingUser(name, password) && httpSession.getAttribute("access").equals(true)) {
            req.setAttribute("users", userService.getAllUsers());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}