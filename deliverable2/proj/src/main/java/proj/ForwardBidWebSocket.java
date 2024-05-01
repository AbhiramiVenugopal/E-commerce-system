package proj;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/f")
public class ForwardBidWebSocket extends BiddingWebSocket{

    public static void broadcastBidUpdate(BidRequest bidRequest) {
        // Broadcast the bid update to all connected clients
        for (Session session : getSessions()) {
            try {
                // Construct the JSON message
                String jsonMessage = "{\"type\":\"bidUpdate\",\"bidAmount\":\"" + bidRequest.getBidAmount() +
                                     "\",\"highestBidder\":\"" + bidRequest.getUserName() + "\"}";

                // Send JSON message to the client
                session.getBasicRemote().sendText(jsonMessage);
            } catch (IOException e) {
                // Handle any exceptions, e.g., session is closed
                e.printStackTrace();
            }
        }
}
}

