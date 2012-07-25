package sessionStorage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import session.Session;

public class SessionDAOMemory implements ISessionDAO {

	private Map<UUID, Session> activeSessions = new HashMap<UUID, Session>();

	@Override
	public Serializable create(Session session) {
		activeSessions.put(session.getId(), session);
		return null;
	}

	@Override
	public void delete(Session session) {
		activeSessions.remove(session.getId());
	}

	@Override
	public Map<UUID, Session> getActiveSessions() {
		return activeSessions;
	}

	@Override
	public Session readSession(Serializable sessionId) {
		return activeSessions.get(sessionId);
	}

	@Override
	public void update(Session session) {
		create(session);
	}

	@Override
	public void onStart(Session session) {
		System.out.println("[" + this.getClass() + "] onStart");
		create(session);
	}

	@Override
	public void onStop(Session session) {
		System.out.println("[" + this.getClass() + "] onStop");
		delete(session);		
	}

	@Override
	public void onExpiration(Session session) {
		System.out.println("[" + this.getClass() + "] onExpiration");
		delete(session);		
	}

}
