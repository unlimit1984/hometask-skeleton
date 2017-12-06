package ua.epam.spring.hometask.domain.to;

import java.time.LocalDateTime;

/**
 * Created by Vladimir_Vysokomorny on 05-Dec-17.
 */
public class BookingTicketDTO {
    private Long id;
    private long userId;
    private long eventId;
    private LocalDateTime dateTime;
    private long seat;

    public BookingTicketDTO() {
    }

    public BookingTicketDTO(Long id, long userId, long eventId, LocalDateTime dateTime, long seat) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.dateTime = dateTime;
        this.seat = seat;
    }
public BookingTicketDTO(long userId, long eventId, LocalDateTime dateTime, long seat) {
    this.userId = userId;
    this.eventId = eventId;
    this.dateTime = dateTime;
    this.seat = seat;
}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getSeat() {
        return seat;
    }

    public void setSeat(long seat) {
        this.seat = seat;
    }
}
