package uz.pdp.ChoparBot;

public interface BotState {
    String START = "start";

    String ORDER_TYPE = "order_type";

    String DELIVERY_TYPE = "delivery";
    String TAKE_AWAY_TYPE = "take_away";

    String SEND_LOCATION = "send_location";

    String LOCATION = "location";
}
