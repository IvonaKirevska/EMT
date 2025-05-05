package emt.listeners;

import emt.events.AuthorChangedEvent;
import emt.events.AuthorCreatedEvent;
import emt.events.AuthorDeletedEvent;
import emt.service.application.CountryApplicationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthorEventHandlers {
    private final CountryApplicationService countryApplicationService;

    public AuthorEventHandlers(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }
    @EventListener
    public void onAuthorCreated(AuthorCreatedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
    @EventListener
    public void onAuthorDeleted(AuthorDeletedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
    @EventListener
    public void onAuthorChanged(AuthorChangedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
}
