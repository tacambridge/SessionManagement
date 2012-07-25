package session;

import sessionListener.ISessionListener;

/**
 * 
 * @author terrianne
 *
 */
public class Execution implements ISessionListener {

	private Session session;
	
	public Execution() {
		
	}

	/**
	 * Creates a new session, if the boolean argument is true and if the session does not already exist.
	 * Returns null if the session does not exist and the boolean argument is false.
	 * If the session exists, the boolean argument is ignored and the session is returned immediately
	 * 	
	 * @param createNewSession
	 * @return
	 */
	public Session getSession(boolean createNewSession) {
		System.out.println("[" + this.getClass() + "] getSession");
		
		if(createNewSession && session == null) {
			session = new Session();
		}
		return session;
	}

	@Override
	public void onStart(Session session) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStop(Session session) {
		this.session = null;		
	}

	@Override
	public void onExpiration(Session session) {
		this.session = null;		
	}
}
