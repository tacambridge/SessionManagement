package driver;

import session.Execution;
import session.Session;

public class Driver {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Execution currentExecution = new Execution();
		Session session = currentExecution.getSession(true);
		String username = "tacambridge";
		session.setAttribute("username", username);
		
		Thread.sleep(200);
		
		System.out.println("username = " + session.getAttribute("username"));
		
		Thread.sleep(1000);
		
		System.out.println("username = " + session.getAttribute("username"));
	}

}
