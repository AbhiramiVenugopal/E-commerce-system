package proj;

public class WebSocketFactory {

    public static DutchBidWebSocket createDutchBidWebSocket() {
        return new DutchBidWebSocket();
    }

    public static ForwardBidWebSocket createForwardBidWebSocket() {
        return new ForwardBidWebSocket();
    }

    public static BiddingWebSocket createBiddingWebSocket() {
        return new BiddingWebSocket();
    }
}