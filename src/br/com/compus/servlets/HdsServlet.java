package br.com.compus.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.compus.dao.HdDAO;
import br.com.compus.models.Hd;

@WebServlet("/hds")
public class HdsServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public HdsServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("employee_cpf") == null || session.getAttribute("client_cpf") == null) {
      response.sendRedirect(request.getContextPath() + "/identificacao");
    } else {
      List<Hd> hds = null;
      try {
        hds = HdDAO.getInstance().getAll();
        request.setAttribute("hds", hds);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/hds/index.jsp");
        requestDispatcher.forward(request, response);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}