package pro.sky.telegrambot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class NotificationTask {

    @Id
    @GeneratedValue
    private long id;
    private String message;
    private LocalDateTime dateAndTime;

    public NotificationTask(long id, String message, LocalDateTime dateAndTime){
        this.id = id;
        this.message = message;
        this.dateAndTime = dateAndTime;
    }

    public NotificationTask() {

    }

    public Long getId(){
        return id;
    }

    public String getMessage(){
        return message;
    }

    public LocalDateTime getDateAndTime(){
        return dateAndTime;
    }

    public void SetId(Long id){
        this.id = id;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setDateAndTime(LocalDateTime dateAndTime){
        this.dateAndTime = dateAndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                '}';
    }
}
