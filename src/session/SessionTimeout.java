package session;

import java.util.Map;
import java.util.TimerTask;
import java.util.UUID;

/**
 * Performs a time out if the session has been idle for a predetermined amount of time
 * by running a timer every <tt>TIME_INTERVAL</tt> milliseconds that periodically checks the elapsed time since the last activity
 * @author terrianne
 *
 */
public class SessionTimeout extends TimerTask {

	public final long TIME_INTERVAL = 500;

	public SessionTimeout() {
	}

	/**
	 * Checks the elapsed time. If it has passed the threshold amount, it expires the session
	 * and returns the result
	 * @param session
	 * @return
	 */
	public boolean expirationCheck(Session session) {
		System.out.println("[" + this.getClass() + "] expirationCheck (sessionId = " + session.getId() + ")");
		
		long lastAccessedTime = session.getLastAccessTime();
		long elapsedTime = session.getElapsedTime();
		
		System.out.println("lastAccessedTime: " + lastAccessedTime +
				", elapsedTime: " + elapsedTime + 
				", timeout: " + (elapsedTime > session.getTimeout()));
		
		if(elapsedTime > session.getTimeout()) {
			session.setExpired(true);
		}
		return session.isExpired();
	}

	@Override
	public void run() {
		Map<UUID, Session> activeSessions = SessionManager.getInstance().getSessionDAO().getActiveSessions();
		for(Session session: activeSessions.values()) {
			expirationCheck(session);
		}
	}
}
