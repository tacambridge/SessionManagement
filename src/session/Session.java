package session;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * A session is a long lived object that saves a user's data and expires when left idle.
 * @author terrianne
 *
 */
public class Session {

	private Map<Object, Object> attributes = new HashMap<Object, Object>();
	private String host;
	private UUID id;
	private long lastAccessedTime = 0;
	private long startTimestamp = 0;
	private long elapsedTime = 0;
	private long timeout = 500;
	private boolean isExpired;

	public Session() {
		System.out.println("[" + this.getClass() + "] new Session");
		this.id = UUID.randomUUID();
		this.startTimestamp = Calendar.getInstance().getTimeInMillis();
		this.lastAccessedTime = Calendar.getInstance().getTimeInMillis();
		SessionManager.getInstance().getSessionDAO().onStart(this);
	}
	
	/**
	 * Checks if the session has expired
	 * @return
	 */
	public boolean isExpired() {
		return isExpired;
	}

	/**
	 * Expires the session
	 * @param isExpired
	 */
	public void setExpired(boolean isExpired) {
		System.out.println("[" + this.getClass() + "] setExpired: " + isExpired);
		this.isExpired = isExpired;
		if(isExpired) {
			SessionManager.getInstance().getSessionDAO().onExpiration(this);
			stop();
		}
	}

	/**
	 * Returns the object bound to this session identified by the specified key.
	 * @param key
	 * @return
	 */
	public Object getAttribute(Object key) {
		System.out.println("[" + this.getClass() + "] getAttribute");
		touch();
		return attributes.get(key);
	}

	/**
	 * Returns the keys of all the attributes stored under this session.
	 * @return
	 */
	public Set<Object> getAttributeKeys() {
		System.out.println("[" + this.getClass() + "] getAttributeKeys");
		touch();
		return attributes.keySet();
	}

	/**
	 * Returns the host name or IP string of the host that originated this session, or null if the host is unknown.
	 * @return
	 */
	public String getHost() {
		System.out.println("[" + this.getClass() + "] getHost");
		touch();
		return host;
	}

	/**
	 * Returns the unique identifier assigned by the system upon session creation.
	 * @return
	 */
	public UUID getId() {
		System.out.println("[" + this.getClass() + "] getId");
		touch();
		return id;
	}

	/**
	 * Returns the last time the application received a request or method invocation from the user associated with this session.
	 * @return
	 */
	public long getLastAccessTime() {
		System.out.println("[" + this.getClass() + "] getLastAccessTime");
		return lastAccessedTime;
	}

	/**
	 * Returns the time the session was started; that is, the time the system created the instance.
	 * @return
	 */
	public long getStartTimestamp() {
		System.out.println("[" + this.getClass() + "] getStartTimestamp");
		return startTimestamp;
	}

	public long getElapsedTime() {
		System.out.println("[" + this.getClass() + "] getElapsedTime");
		//touch();
		return elapsedTime;
	}

	/**
	 * Returns the time in milliseconds that the session session may remain idle before expiring.
	 * @return
	 */
	public long getTimeout() {
		System.out.println("[" + this.getClass() + "] getTimeout");
		return timeout;
	}

	/**
	 * Removes (unbinds) the object bound to this session under the specified key name.
	 * @param key
	 * @return
	 */
	public Object removeAttribute(Object key) {
		System.out.println("[" + this.getClass() + "] removeAttribute");
		touch();
		return attributes.remove(key);
	}

	/**
	 * Binds the specified value to this session, uniquely identified by the specified key name.
	 * @param key
	 * @param value
	 */
	public void setAttribute(Object key, Object value) {
		System.out.println("[" + this.getClass() + "] setAttribute");
		touch();
		attributes.put(key, value);
	}

	/**
	 * Sets the time in milliseconds that the session may remain idle before expiring.
	 * @param maxIdleTimeInMillis
	 */
	public void setTimeout(long maxIdleTimeInMillis) {
		System.out.println("[" + this.getClass() + "] setTimeout");
		this.timeout = maxIdleTimeInMillis;
	}

	/**
	 * Explicitly stops (invalidates) this session and releases all associated resources.
	 */
	public void stop() {
		System.out.println("[" + this.getClass() + "] stop");
		//TODO invalidate session
		attributes.clear();
		SessionManager.getInstance().getSessionDAO().onStop(this);
	}

	/**
	 * Explicitly updates the lastAccessTime of this session to the current time when this method is invoked.
	 */
	public void touch() {
		System.out.println("[" + this.getClass() + "] touch, elapsedTime = " + elapsedTime);
		elapsedTime = Calendar.getInstance().getTimeInMillis() - lastAccessedTime; 
		lastAccessedTime = Calendar.getInstance().getTimeInMillis();
	}

}
