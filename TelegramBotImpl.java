package uz.pdp.ChoparBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TelegramBotImpl implements TelegramService {
    public SendPhoto startMethod(Update update) {
        Long chatId;
        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
        } else {
             chatId = update.getMessage().getChatId();
        }

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(new File("D:\\Bootcamp Online G2\\telegram-app\\src\\main\\resources\\chopar\\menyu.jpg"));
        sendPhoto.setChatId(chatId);

        return sendPhoto;
    }

    public SendMessage menuButtons(Update update) {
        SendMessage sendMessage = new SendMessage();
        Long chatId = update.getMessage().getChatId();

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();

        row1.add(new KeyboardButton(Constant.ORDER));
        row2.add(new KeyboardButton(Constant.ORDER_HISTORY));
        row2.add(new KeyboardButton(Constant.CONTACT));
        row3.add(new KeyboardButton(Constant.SETTINGS));
        row4.add(new KeyboardButton(Constant.ABOUT));

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setText(Constant.MENU);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage deliveryButtons(Update update) {
        SendMessage sendMessage = new SendMessage();
        Long chatId = update.getMessage().getChatId();

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();

        row1.add(new KeyboardButton(Constant.FIND_FILIAL).setRequestLocation(true));
        row2.add(new KeyboardButton(Constant.MY_ADDRESSES));
        row3.add(new KeyboardButton(Constant.BACK));

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setText(Constant.DELIVERY_TEXT);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    @Override
    public SendMessage getLocation(Update update) {
        Location location = update.getMessage().getLocation();
        Float latitude = location.getLatitude();
        Float longitude = location.getLongitude();
        String address = Service.getAddress(latitude, longitude);

        System.out.println(address);


        SendMessage sendMessage = new SendMessage();
        Long chatId = update.getMessage().getChatId();

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();

        row1.add(new KeyboardButton(Constant.RE_CHOOSE_LOCATION));
        row2.add(new KeyboardButton(Constant.CONFIRM_LOCATION));
        row3.add(new KeyboardButton(Constant.BACK));

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setText(Constant.FIND_LOCATION_TEXT + address);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;

    }

    @Override
    public SendMessage takeAwayButtons(Update update) {
        SendMessage sendMessage = new SendMessage();
        Long chatId = update.getMessage().getChatId();

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();

        row1.add(new KeyboardButton(Constant.FIND_FILIAL));
        row2.add(new KeyboardButton(Constant.BACK));

        rows.add(row1);
        rows.add(row2);

        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setText(Constant.TAKE_AWAY_TEXT);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;

    }

    @Override
    public SendMessage settings(Update update) {
        SendMessage sendMessage = new SendMessage();
        Long chatId = update.getMessage().getChatId();

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();

        row1.add(new KeyboardButton(Constant.CHANGE_NAME));
        row1.add(new KeyboardButton(Constant.CHANGE_PHONE));
        row2.add(new KeyboardButton(Constant.CHANGE_CITY));
        row2.add(new KeyboardButton(Constant.CHANGE_LANGUAGE));
        row3.add(new KeyboardButton(Constant.CHANGE_FILIAL_INFO));
        row4.add(new KeyboardButton(Constant.BACK));

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);

        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setText(Constant.SETTINGS_TEXT);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;

    }

    @Override
    public SendMessage changeFilial(Update update) {
        SendMessage sendMessage = new SendMessage();

        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        sendMessage.setChatId(chatId);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(); //inlin un keyboard

        List<List<InlineKeyboardButton>> rowsList = new ArrayList<>(); //xammasini orab turvchi xamma qatorlarni orab turvhic

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(new InlineKeyboardButton(Constant.FILIAL_N1).setCallbackData("f-1"));
        row.add(new InlineKeyboardButton(Constant.FILIAL_N2).setCallbackData("f-2"));

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(new InlineKeyboardButton(Constant.FILIAL_N3).setCallbackData("f-3"));
        row2.add(new InlineKeyboardButton(Constant.FILIAL_N4).setCallbackData("f-4"));

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(new InlineKeyboardButton(Constant.BACK).setCallbackData("backToSettings"));


        rowsList.add(row);
        rowsList.add(row2);
        rowsList.add(row3);

        inlineKeyboardMarkup.setKeyboard(rowsList);
        sendMessage.setChatId(chatId);
        sendMessage.setText(Constant.FIlIAL_TEXT);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    @Override
    public EditMessageText changeFilialInline(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        EditMessageText sendMessage = new EditMessageText();
        sendMessage.setChatId(chatId); //qaysi chatga
        sendMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId()); //qaysi messageni edit qilay

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(); //inlin un keyboard

        List<List<InlineKeyboardButton>> rowsList = new ArrayList<>(); //xammasini orab turvchi xamma qatorlarni orab turvhic

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(new InlineKeyboardButton(Constant.FILIAL_N1).setCallbackData("f-1"));
        row.add(new InlineKeyboardButton(Constant.FILIAL_N2).setCallbackData("f-2"));

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(new InlineKeyboardButton(Constant.FILIAL_N3).setCallbackData("f-3"));
        row2.add(new InlineKeyboardButton(Constant.FILIAL_N4).setCallbackData("f-4"));

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(new InlineKeyboardButton(Constant.BACK).setCallbackData("backToSettings"));


        rowsList.add(row);
        rowsList.add(row2);
        rowsList.add(row3);

        inlineKeyboardMarkup.setKeyboard(rowsList);
        sendMessage.setChatId(chatId);
        sendMessage.setText(Constant.FIlIAL_TEXT);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    @Override
    public EditMessageText chooseFilial(Update update) {

        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        EditMessageText sendMessage = new EditMessageText();
        sendMessage.setChatId(chatId); //qaysi chatga
        sendMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId()); //qaysi messageni edit qilay

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(); //inlin un keyboard

        List<List<InlineKeyboardButton>> rowsList = new ArrayList<>(); //xammasini orab turvchi xamma qatorlarni orab turvhic

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(new InlineKeyboardButton(Constant.BACK).setCallbackData("backToFilials"));

        rowsList.add(row3);

        inlineKeyboardMarkup.setKeyboard(rowsList);
        sendMessage.setChatId(chatId);
        sendMessage.setText(Constant.NOT_FOUND_TEXT);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    @Override
    public SendPhoto filialPhoto(Update update) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto("D:\\Bootcamp Online G2\\telegram-app\\src\\main\\resources\\test.jpg");
        sendPhoto.setChatId(update.getCallbackQuery().getMessage().getChatId());
        return sendPhoto;
    }

    @Override
    public SendMessage settingsInlineMode(Update update) {
        Long chatId;
        SendMessage sendMessage = new SendMessage();

        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
        } else {
            chatId = update.getMessage().getChatId();
        }
        sendMessage.setChatId(chatId);

        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        sendMessage.setReplyMarkup(replyKeyboardRemove);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(); //inlin un keyboard

        List<List<InlineKeyboardButton>> rowsList = new ArrayList<>(); //xammasini orab turvchi xamma qatorlarni orab turvhic

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(new InlineKeyboardButton(Constant.CHANGE_NAME).setCallbackData("changeName"));
        row.add(new InlineKeyboardButton(Constant.CHANGE_PHONE).setCallbackData("changePhone"));

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(new InlineKeyboardButton(Constant.CHANGE_CITY).setCallbackData("changeCity"));
        row2.add(new InlineKeyboardButton(Constant.CHANGE_LANGUAGE).setCallbackData("changeLang"));

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(new InlineKeyboardButton(Constant.CHANGE_FILIAL_INFO).setCallbackData("changeFilial"));

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(new InlineKeyboardButton(Constant.BACK).setCallbackData("back"));


        rowsList.add(row);
        rowsList.add(row2);
        rowsList.add(row3);
        rowsList.add(row4);

        inlineKeyboardMarkup.setKeyboard(rowsList);
        sendMessage.setChatId(chatId);
        sendMessage.setText(Constant.SETTINGS_TEXT);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    @Override
    public EditMessageText settingsInlineModeEdit(Update update) {
        Long chatId;
        EditMessageText sendMessage = new EditMessageText();

        chatId = update.getCallbackQuery().getMessage().getChatId();
        sendMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        sendMessage.setChatId(chatId);


        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(); //inlin un keyboard

        List<List<InlineKeyboardButton>> rowsList = new ArrayList<>(); //xammasini orab turvchi xamma qatorlarni orab turvhic

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(new InlineKeyboardButton(Constant.CHANGE_NAME).setCallbackData("changeName"));
        row.add(new InlineKeyboardButton(Constant.CHANGE_PHONE).setCallbackData("changePhone"));

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(new InlineKeyboardButton(Constant.CHANGE_CITY).setCallbackData("changeCity"));
        row2.add(new InlineKeyboardButton(Constant.CHANGE_LANGUAGE).setCallbackData("changeLang"));

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(new InlineKeyboardButton(Constant.CHANGE_FILIAL_INFO).setCallbackData("changeFilial"));

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(new InlineKeyboardButton(Constant.BACK).setCallbackData("back"));


        rowsList.add(row);
        rowsList.add(row2);
        rowsList.add(row3);
        rowsList.add(row4);

        inlineKeyboardMarkup.setKeyboard(rowsList);
        sendMessage.setChatId(chatId);
        sendMessage.setText(Constant.SETTINGS_TEXT);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }


    public SendMessage menuOrderButtons(Update update) {
        SendMessage sendMessage = new SendMessage();
        Long chatId = update.getMessage().getChatId();

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();

        row1.add(new KeyboardButton(Constant.DELIVERY));
        row1.add(new KeyboardButton(Constant.TAKE_AWAY));
        row2.add(new KeyboardButton(Constant.BACK));

        rows.add(row1);
        rows.add(row2);

        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setText(Constant.MENU_ORDER_TEXT);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;

    }
    //logic
}
