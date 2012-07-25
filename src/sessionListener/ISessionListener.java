package sessionListener;

import session.Session;

/**
 * SessionListener allows you to react to important session events as they occur.
 * Interface to be implemented by components that wish to be notified of events 
 * that occur during a Session's life cycle.
 * @author terrianne
 *
 */
public interface ISessionListener {

	/**
	 * Notification callback that occurs when the corresponding Session has started.
	 * @param session
	 */
	public void onStart(Session session);

	/**
	 * Notification callback that occurs when the corresponding Session has stopped, 
	 * either programmatically via Session.stop() or automatically upon a subject logging out.
	 * @param session
	 */
	public void onStop(Session session);

	/**
	 * Notification callback that occurs when the corresponding Session has expired.
	 * Note: this method is almost never called at the exact instant that the Session expires. 
	 * Almost all session management systems, lazily validate sessions - either when they are 
	 * accessed or during a regular validation interval. It would be too resource intensive to 
	 * monitor every single session instance to know the exact instant it expires.
	 * @param session
	 */
	public void onExpiration(Session session);

}
