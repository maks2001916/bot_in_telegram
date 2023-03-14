package pro.sky.telegrambot.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repositiries.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@EnableScheduling
public class NotificationSchedulerService {

    NotificationTaskRepository notificationTaskRepository;

    TelegramBotUpdatesListener telegramBotUpdatesListener = new TelegramBotUpdatesListener();


    @Scheduled(cron = "0 0/1 * * * *")
    public void sendingNotifications(){
        List<NotificationTask> notificationTasks = collectingNotifications(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        telegramBotUpdatesListener.sendingNotifications(notificationTasks);
    }

    @Scheduled(fixedDelay = 1L)
    public void sendingNotificationsCurrentTime(){
        List<NotificationTask> notificationTasks = collectingNotifications(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        telegramBotUpdatesListener.sendingNotifications(notificationTasks);
    }

    public List<NotificationTask> collectingNotifications(LocalDateTime time){
        List<NotificationTask> allNotification = new ArrayList<>();
        notificationTaskRepository.findAll().forEach(times -> {
            if(time.equals(times)){
                allNotification.add(times);
            }
        });
        return allNotification;
    }
}
