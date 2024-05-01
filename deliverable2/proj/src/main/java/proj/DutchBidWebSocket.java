package proj;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/d")
public class DutchBidWebSocket extends BiddingWebSocket {

 
    public static void broadcastDutchBidSold(int itemId) {
        for (Session session : getSessions()) {
            try {
                // Construct the JSON message
                String jsonMessage = "{\"type\":\"dutchBidSold\",\"itemId\":\"" + itemId +
                                     "\",\"message\":\"Item no longer available\"}";

                // Send JSON message to the client
                session.getBasicRemote().sendText(jsonMessage);
            } catch (IOException e) {
                // Handle any exceptions, e.g., session is closed
                e.printStackTrace();
            }
        }
    
    }
}


