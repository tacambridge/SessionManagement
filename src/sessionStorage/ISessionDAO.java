package sessionStorage;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

import session.Session;
import sessionListener.ISessionListener;

/**
 * Provides an interface for session data access objects used to store data on saved sessions.
 * @author terrianne
 *
 */
public interface ISessionDAO extends ISessionListener {

	/**
	 * Inserts a new Session record into the underling EIS
	 * @param session
	 * @return
	 */
	Serializable create(Session session);

	/**
	 * Deletes the associated EIS record of the specified session.
	 * @param session
	 */
	void delete(Session session);

	/**
	 * Returns all sessions in the EIS that are considered active, meaning all sessions that haven't been stopped/expired.
	 * @return
	 */
	Map<UUID, Session> getActiveSessions();

	/**
	 * Retrieves the session from the EIS uniquely identified by the specified sessionId.
	 * @param sessionId
	 * @return
	 */
	Session readSession(Serializable sessionId); 

	/**
	 * Updates (persists) data from a previously created Session instance in the EIS identified by {@link Session#getId() session.getId()}.
	 * @param session
	 */
	void update(Session session);
}
