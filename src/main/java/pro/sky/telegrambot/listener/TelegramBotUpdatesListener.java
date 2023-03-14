package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repositiries.NotificationTaskRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class
TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private NotificationTaskRepository notificationTaskRepository;
    private long id = 1;
    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {

        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            if(update.message().text().equals("/start")) {
                SendMessage message = new SendMessage(update.message().text(), "будет сделано");
                telegramBot.execute(message);
                save(updates);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public int save(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            Pattern dateAndTime = Pattern.compile("([0-9\\.\\:\\s]{16})");
            Pattern text = Pattern.compile("(\\s)([\\W+]+)");

            Matcher matcher = dateAndTime.matcher(update.message().text());
            Matcher matcherText = text.matcher(update.message().text());
            if(matcher.matches() && matcherText.matches()){
                String date = matcher.group(1);
                LocalDateTime dates = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
                String texts = matcherText.group(2);
                NotificationTask notificationTask = new NotificationTask(id, update.message().messageId(), texts, dates);
                notificationTaskRepository.save(notificationTask);
            }

        });
        id++;
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public List<NotificationTask> collectingNotifications(LocalDateTime time){
        List<NotificationTask> allNotification = notificationTaskRepository.findAll();
        notificationTaskRepository.findAll().forEach(times -> {
            if(time.equals(times)){
                allNotification.add(times);
            }
        });
        return allNotification;
    }

    public void sendingNotifications(List<NotificationTask> notificationTasks){
        notificationTasks.forEach(task -> {
            SendMessage sendMessage = new SendMessage(task.getId(), task.getMessage());
            telegramBot.execute(sendMessage);
        });
    }

}
