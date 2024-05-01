package proj;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/f")
public class BiddingWebSocket {
	 private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

	    public static Set<Session> getSessions() {
		return sessions;
	}

	public static void setSessions(Set<Session> sessions) {
		BiddingWebSocket.sessions = sessions;
	}

		@OnOpen
	    public void onOpen(Session session) {
	    	sessions.add(session);
	    }

	    @OnClose
	    public void onClose(Session session) {
	    	sessions.remove(session);
	    }

	    @OnError
	    public void onError(Session session, Throwable throwable) {
	        // Handle error
	    }

	    @OnMessage
	    public void onMessage(String message, Session session) {
	        // Handle incoming messages
	    }
}


