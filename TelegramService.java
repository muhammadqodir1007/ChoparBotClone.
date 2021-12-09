package uz.pdp.ChoparBot;

import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.UserProfilePhotos;

public interface TelegramService {
    //functional

    SendPhoto startMethod(Update update);

    SendMessage menuButtons(Update update);

    SendMessage menuOrderButtons(Update update);

    SendMessage deliveryButtons(Update update);

    SendMessage getLocation(Update update);

    SendMessage takeAwayButtons(Update update);

    SendMessage settings(Update update);

    SendMessage changeFilial(Update update);

    EditMessageText changeFilialInline(Update update);

    EditMessageText chooseFilial(Update update);

    SendPhoto filialPhoto(Update update);

    SendMessage settingsInlineMode(Update update);

    EditMessageText settingsInlineModeEdit(Update update);

}
