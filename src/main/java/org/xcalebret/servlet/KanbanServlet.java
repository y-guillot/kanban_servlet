package org.xcalebret.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xcalebret.constants.Routing;
import org.xcalebret.constants.Session;
import org.xcalebret.constants.View;
import org.xcalebret.model.Kanban;
import org.xcalebret.model.User;
import org.xcalebret.tools.TimeTool;

public class KanbanServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Kanban kanban;
	private static Logger LOGGER;

	@Override
	public void init() throws ServletException {

		KanbanServlet.LOGGER = LoggerFactory.getLogger(KanbanServlet.class);
		KanbanServlet.kanban = new Kanban();
		kanban.setCreatedOn(TimeTool.getLocalCurrentTime());
	}

	/**
	 * Handle default entry view.
	 * Forward on {@link Routing.HOME} view with all needed attributes.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getParameter(Routing.LOGOUT) != null) {
			/* Logout user when asked */
			this.onLogout(req, resp);
			resp.sendRedirect(this.getServletContext().getContextPath() + Routing.INDEX);
			
		} else {
			User user = (User) req.getSession().getAttribute(Session.USER);
			if (user != null) {
				/* Session succeeded */
				this.onSessionSuccess(req, resp);
				
			} else {
				/* Session fail */
				this.onSessionFail(req, resp);
			}
			this.getServletContext().getRequestDispatcher(Routing.HOME).forward(req, resp);
		}
		
	}

	/**
	 * Set the user in session when the login succeeded.
	 * Forward on {@link Routing.HOME} view with all needed attributes.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String login = req.getParameter(Routing.LOGIN);
		
		if (login != null && !login.trim().isEmpty()) {
			/* User account granted */
			User user = new User();
			user.setName(login.trim());
			req.getSession().setAttribute(Session.USER, user);
			req.setAttribute(View.LOGIN, View.LOGIN_SUCCEEDED);
			this.onSessionSuccess(req, resp);
			/* Debug */
			KanbanServlet.LOGGER.debug("Login is OK");
			
		} else {
			/* Wrong user account */
			req.setAttribute(View.LOGIN, View.LOGIN_FAIL);
			this.onSessionFail(req, resp);
			/* Debug */
			KanbanServlet.LOGGER.debug("Login is KO");
		}
		this.getServletContext().getRequestDispatcher(Routing.HOME).forward(req, resp);	
	}
	
	/**
	 * Set view attributes on session succeeded.
	 * 
	 * @param req
	 * @param resp
	 */
	private void onSessionSuccess(HttpServletRequest req, HttpServletResponse resp) {
		
		User user = (User) req.getSession().getAttribute(Session.USER);
		req.setAttribute(View.USERNAME, user.getName());
		req.setAttribute(View.KANBAN, KanbanServlet.kanban);
		
		/* Debug */
		KanbanServlet.LOGGER.debug("User session has been initialised.");
	}
	
	/**
	 * Set view attributes on session fail.
	 * 
	 * @param req
	 * @param resp
	 */
	private void onSessionFail(HttpServletRequest req, HttpServletResponse resp) {
		
		req.setAttribute(View.USERNAME, "");
		/* Debug */
		KanbanServlet.LOGGER.debug("Session user does not exists : " + req.getAttribute(View.LOGIN));
	}
	
	/**
	 * Logout the user from session.
	 * 
	 * @param req
	 * @param resp
	 */
	private void onLogout(HttpServletRequest req, HttpServletResponse resp) {
		
		req.getSession().removeAttribute(Session.USER);
		/* Debug */
		KanbanServlet.LOGGER.debug("User has been logged out.");
	}
	
}
