package uz.pdp.ChoparBot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ChoparBot extends TelegramLongPollingBot {
//    public static final String botToken = "1506787974:AAEIktaJQkGTHeRvM3lFIHqTa223Je23RQA"; //botni uniqal kaliti
//    public static final String botUserName = "bootcamp_G2_bot";

    public static String botUserName = "bootcamp_g11_bot";
    public static String botToken = "1815513569:AAGPN_axe1uNEMFDcm0gmlsyAoSUYksWRzU";

    public String getBotUsername() {
        return botUserName;
    }

    public String getBotToken() {
        return botToken;
    }

    TelegramBotImpl telegramBot = new TelegramBotImpl();
    String state = null;

    @SneakyThrows
    public void onUpdateReceived(Update update) {
        //ketma-ketlikni nazorat qilamz

        if (update.hasMessage()) {
            Message message = update.getMessage();
            String text = update.getMessage().getText(); ///start
            Long chatId = update.getMessage().getChatId();
            if (message.hasText()) {
                if (text.equals("/start")) {
                    execute(telegramBot.menuButtons(update)); //klaviatura
                    execute(telegramBot.startMethod(update)); //rasm
                    state = BotState.START;
                } else {
                    switch (state) {
                        case BotState.START:
                            startStates(text, update);
                            break;
                        case BotState.ORDER_TYPE:
                            if (text.equals(Constant.BACK)) {
                                state = BotState.START;
                                execute(telegramBot.menuButtons(update));
                                execute(telegramBot.startMethod(update));
                            } else if (text.equals(Constant.DELIVERY)) {
                                state = BotState.DELIVERY_TYPE;
                                execute(telegramBot.deliveryButtons(update));
                            } else if (text.equals(Constant.TAKE_AWAY)) {
                                state = BotState.TAKE_AWAY_TYPE;
                                execute(telegramBot.takeAwayButtons(update));
                            }
                            break;
                        case BotState.DELIVERY_TYPE:
                            if (text.equals(Constant.BACK)) {
                                state = BotState.ORDER_TYPE;
                                execute(telegramBot.menuOrderButtons(update));
                            }
                            break;
                        case BotState.SEND_LOCATION:
                            if (text.equals(Constant.BACK)) {
                                state = BotState.ORDER_TYPE;
                                execute(telegramBot.menuOrderButtons(update));
                            }
                            break;
                        case BotState.TAKE_AWAY_TYPE:
                            if (text.equals(Constant.BACK)) {
                                state = BotState.ORDER_TYPE;
                                execute(telegramBot.menuOrderButtons(update));
                            }
                            break;
                    }
                }
            } else if (message.hasLocation()) {
                Location location = update.getMessage().getLocation();
                Float latitude = location.getLatitude();
                Float longitude = location.getLongitude();
                String address = Service.getAddress(latitude, longitude);

                System.out.println(address);
                //shu joyiga yechim topaylik
                switch (state) {
                    case BotState.DELIVERY_TYPE:
                        state = BotState.SEND_LOCATION;
                        execute(telegramBot.getLocation(update));
                        break;
                }
            }
        } else if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData(); //inline keyboard callbackData
            String textINLINE = update.getCallbackQuery().getMessage().getText();
            Long chatIdINLINE = update.getCallbackQuery().getMessage().getChatId();


            if (data.equals("f-1") || data.equals("f-2") || data.equals("f-3") || data.equals("f-4")) {
//                execute(telegramBot.filialPhoto(update));
                execute(telegramBot.startMethod(update));
                execute(telegramBot.chooseFilial(update));
            }
            switch (data) {
                case "changeFilial":
//                    execute(telegramBot.changeFilial(update));
                    execute(telegramBot.changeFilialInline(update));
                    break;
                case "backToSettings":
                    execute(telegramBot.settingsInlineModeEdit(update));
                    break;
                case "backToFilials":
                    execute(telegramBot.changeFilialInline(update));
                    break;
                default:
                    break;
            }

        }
    }

    public void startStates(String text, Update update) throws TelegramApiException {
        switch (text) {
            case Constant.ORDER:
                execute(telegramBot.menuOrderButtons(update));
                state = BotState.ORDER_TYPE;
                break;
            case Constant.ORDER_HISTORY:
//                execute(telegramBot.menuOrderButtons(update)); //boshqa metod
                break;
            case Constant.CONTACT:
//                execute(telegramBot.menuOrderButtons(update));// boshqa
                break;
            case Constant.SETTINGS:
//                execute(telegramBot.settings(update));//bu keyboard
                execute(telegramBot.settingsInlineMode(update));//bu inline keyboard
                break;
            case Constant.ABOUT:
//                execute(telegramBot.menuOrderButtons(update));
                break;
        }
    }
}
