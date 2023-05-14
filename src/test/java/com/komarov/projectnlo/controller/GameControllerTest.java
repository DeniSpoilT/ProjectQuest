package com.komarov.projectnlo.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.komarov.projectnlo.model.Step.BRIDGE;
import static org.mockito.Mockito.*;

public class GameControllerTest {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher dispatcher;

    GameController gameController;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        gameController = new GameController();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    void doGet_shouldSetAttributesAndRedirectToGameJSP() throws ServletException, IOException {
        when(request.getRequestDispatcher("/Game.jsp")).thenReturn(dispatcher);
        when(request.getParameter("restart")).thenReturn("false");
        when(request.getSession()).thenReturn(session);

        gameController.doGet(request, response);

        verify(request).setAttribute("answer", true);
        verify(request).setAttribute("state", gameController.getState());
        verify(request).setAttribute("question", gameController.getCurrentQuestion().getQuestion());
        verify(request).setAttribute("answer1", gameController.getCurrentQuestion().getAnswer1());
        verify(request).setAttribute("answer2", gameController.getCurrentQuestion().getAnswer2());
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void doPost_shouldSetAttributesAndRedirectToGameJSPIfAnswerTrue() throws ServletException, IOException {
        when(request.getParameter("answer")).thenReturn("true");
        when(request.getRequestDispatcher("/Game.jsp")).thenReturn(dispatcher);

        GameController controller = Mockito.spy(new GameController());
        controller.doPost(request, response);

        verify(request, times(1)).setAttribute("answer", true);
        verify(request, times(1)).setAttribute("question", controller.getCurrentQuestion().getQuestion());
        verify(request, times(1)).setAttribute("answer1", controller.getCurrentQuestion().getAnswer1());
        verify(request, times(1)).setAttribute("answer2", controller.getCurrentQuestion().getAnswer2());
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void doGet_shouldResetSessionAndRedirectToIndexHTMLIfAttributeRestartIsTrue() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/Game.jsp")).thenReturn(dispatcher);
        when(request.getParameter("restart")).thenReturn("true");

        GameController controller = new GameController();
        controller.doGet(request, response);

        verify(session).invalidate();
        verify(response).sendRedirect("index.html");
    }

    @Test
    public void doPost_shouldSetAttributesAndRedirectToGameJSPIfAnswerFalse() throws ServletException, IOException {
        when(request.getParameter("answer")).thenReturn("false");
        when(request.getRequestDispatcher("/Game.jsp")).thenReturn(dispatcher);

        GameController controller = Mockito.spy(new GameController());
        controller.doPost(request, response);

        verify(request, times(1)).setAttribute("answer", false);
        verify(request, times(1)).setAttribute("question", controller.getCurrentQuestion().getQuestion());
        verify(request, times(1)).setAttribute("answer1", controller.getCurrentQuestion().getAnswer1());
        verify(request, times(1)).setAttribute("answer2", controller.getCurrentQuestion().getAnswer2());
        verify(dispatcher).forward(request, response);
    }
}
