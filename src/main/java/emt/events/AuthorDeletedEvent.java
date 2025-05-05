package emt.events;
import emt.model.domain.Author;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class AuthorDeletedEvent extends ApplicationEvent {
    private final LocalDateTime when;

    public AuthorDeletedEvent(Author source) {
        super(source);
        this.when = LocalDateTime.now();
    }
}