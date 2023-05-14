package com.komarov.projectnlo.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


public class SessionServletTest {
    HttpServletRequest request;
    HttpServletResponse response;
    RequestDispatcher dispatcher;
    HttpSession session;
    @BeforeEach
    void init() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void doGet_shouldRedirectToSessionViewJSPAndPassesRequestAndResponse() throws Exception {
        when(request.getRequestDispatcher("/sessionView.jsp")).thenReturn(dispatcher);
        new SessionServlet().doGet(request, response);
        verify(request, times(1)).getRequestDispatcher("/sessionView.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void doPost_shouldSaveNameParameterInSessionAttribute() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("name")).thenReturn("John");
        new SessionServlet().doPost(request, response);
        verify(session).setAttribute("name", "John");
    }

    @Test
    public void doPost_shouldSetNameUnknownIfNameParameterIsEmpty() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("name")).thenReturn("");
        new SessionServlet().doPost(request, response);
        verify(session).setAttribute("name", ">>Unknown<<");
    }
}
