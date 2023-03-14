package pro.sky.telegrambot.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;
import pro.sky.telegrambot.model.NotificationTask;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@EnableScheduling
public class NotificationSchedulerService {

    TelegramBotUpdatesListener telegramBotUpdatesListener = new TelegramBotUpdatesListener();


    @Scheduled(cron = "0 0/1 * * * *")
    public void sendingNotifications(){
        List<NotificationTask> notificationTasks = telegramBotUpdatesListener.collectingNotifications(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        telegramBotUpdatesListener.sendingNotifications(notificationTasks);
    }

}
