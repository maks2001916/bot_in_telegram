package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.request.SendMessage;
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

    private NotificationTaskRepository repository;

    TelegramBotUpdatesListener telegramBotUpdatesListener = new TelegramBotUpdatesListener();


    @Scheduled(cron = "0 0/1 * * * *")
    public void sendingNotifications(){
        List<NotificationTask> notificationTasks = collectingNotifications(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        sendingNotifications(notificationTasks);
    }

    public List<NotificationTask> collectingNotifications(LocalDateTime time){
        List<NotificationTask> allNotification = new ArrayList<>();
        repository.findAll().forEach(times -> {
            if(time.equals(times)){
                allNotification.add(times);
            }
        });
        return allNotification;
    }

    public void sendingNotifications(List<NotificationTask> notificationTasks){
        notificationTasks.forEach(task -> {
            SendMessage sendMessage = new SendMessage(task.getId(), task.getMessage());
            telegramBotUpdatesListener.setTelegramBot(sendMessage);
        });
    }
}
