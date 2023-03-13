package pro.sky.telegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;
import pro.sky.telegrambot.model.NotificationTask;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class TelegramBotApplication {

	TelegramBotUpdatesListener telegramBotUpdatesListener = new TelegramBotUpdatesListener();

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotApplication.class, args);

	}

	@Scheduled(cron = "0 0/1 * * * *")
	public void sendingNotifications(){
		List<NotificationTask> notificationTasks = telegramBotUpdatesListener.collectingNotifications(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
		telegramBotUpdatesListener.sendingNotifications(notificationTasks);
	}
}
