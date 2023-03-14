package pro.sky.telegrambot.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.NotificationTask;

public interface Repository extends JpaRepository<NotificationTask, Integer> {
}
