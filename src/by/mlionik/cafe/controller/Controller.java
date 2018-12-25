package by.mlionik.cafe.controller;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.command.ActionFactory;
import by.mlionik.cafe.pool.ConnectionPool;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().closePool();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        ActionCommand command = ActionFactory.defineCommand(request);

        SessionRequestContent requestContent = new SessionRequestContent(request);
        page = command.execute(requestContent);
        requestContent.insertValues(request);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

}