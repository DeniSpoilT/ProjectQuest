package com.komarov.projectnlo.controller;

import com.komarov.projectnlo.model.*;
import com.komarov.projectnlo.model.Step;
import com.komarov.projectnlo.utils.YAMLLoader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

import static com.komarov.projectnlo.model.Step.*;


@WebServlet(name = "game", value = "/game")
public class GameController extends HttpServlet {
    private Step state = GREETING;
    private FailResolver fail = new FailResolver();
    private QuestionSupplier questionSupplier = new QuestionSupplier(new YAMLLoader("questions.yaml"));
    private Question question;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        var dispatcher = request.getRequestDispatcher("/Game.jsp");
        boolean restartGame = Boolean.parseBoolean(request.getParameter("restart"));

        if (restartGame) {
            state = GREETING;
            request.getSession().invalidate();
            response.sendRedirect("index.html");
            return;
        }

        if (state == GREETING) {
            gameInitQuestion();
        }

        request.setAttribute("answer", true);
        request.setAttribute("state", getState());
        request.setAttribute("question", getCurrentQuestion().getQuestion());
        request.setAttribute("answer1", getCurrentQuestion().getAnswer1());
        request.setAttribute("answer2", getCurrentQuestion().getAnswer2());

        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean answer = Boolean.parseBoolean(request.getParameter("answer"));
        request.setAttribute("answer", answer);
        fail.setState(state);
        request.setAttribute("state", getState());
        gameSetState(state, answer);

        gameInitQuestion();

        request.setAttribute("question", getCurrentQuestion().getQuestion());
        request.setAttribute("answer1", getCurrentQuestion().getAnswer1());
        request.setAttribute("answer2", getCurrentQuestion().getAnswer2());

        var dispatcher = request.getRequestDispatcher("/Game.jsp");
        dispatcher.forward(request, response);

    }

    private void gameSetState(Step state, boolean answer) {
        if (answer) {
            switch (state) {
                case GREETING -> this.state = BRIDGE;
                case BRIDGE -> this.state = CAPTAIN;
                case CAPTAIN -> this.state = TELL_ME_ABOUT_YOURSELF;
            }
        } else {
            this.state = FAIL;
        }
    }

    private void gameInitQuestion() {
        if (state == FAIL) {
            question = new Question(fail.getMessage());
        } else {
            question = questionSupplier.getQuestion(state);
        }
    }


    public Step getState() {
        return state;
    }

    public Question getCurrentQuestion() {
        return question;
    }
}
