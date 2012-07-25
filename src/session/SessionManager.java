package session;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

import sessionListener.ISessionListener;
import sessionStorage.ISessionDAO;
import sessionStorage.SessionDAOMemory;

/**
 * Holds the session's components: TimeOut, DAO, Listeners
 * @author terrianne
 *
 */
public class SessionManager {

	private static SessionManager instance;
	private List<ISessionListener> sessionListeners = new LinkedList<ISessionListener>();
	private ISessionDAO sessionDAO;
	private SessionTimeout sessionTimeout = new SessionTimeout();

	private SessionManager(ISessionDAO sessionDAO) {
		super();
		this.sessionDAO = sessionDAO;

		Timer timer = new Timer();

		long delay = 50;
		long period = sessionTimeout.TIME_INTERVAL;
		timer.scheduleAtFixedRate(sessionTimeout, delay, period);
	}

	/**
	 * 
	 * @return
	 */
	public synchronized static SessionManager getInstance() {
		if(instance == null)
			instance = new SessionManager(new SessionDAOMemory());
		return instance;
	}

	/**
	 * 
	 * @param sessionListener
	 * @return
	 */
	public boolean addSessionListener(ISessionListener sessionListener) {
		return sessionListeners.add(sessionListener);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<ISessionListener> getSessionListeners() {
		return sessionListeners;
	}

	/**
	 * 
	 * @return
	 */
	public ISessionDAO getSessionDAO() {
		return sessionDAO;
	}

	/**
	 * 
	 * @return
	 */
	public SessionTimeout getSessionTimeout() {
		return sessionTimeout;
	}

}
