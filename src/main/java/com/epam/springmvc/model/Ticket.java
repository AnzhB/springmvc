package com.epam.springmvc.model;

import com.epam.springmvc.storage.Base;

/**
 * Created by maksym_govorischev.
 */
public interface Ticket extends Base {
    public enum Category {STANDARD, PREMIUM, BAR}

    /**
     * Ticket Id. UNIQUE.
     * @return Ticket Id.
     */
    long getId();
    void setId(long id);
    long getEventId();
    void setEventId(long eventId);
    long getUserId();
    void setUserId(long userId);
    Category getCategory();
    void setCategory(Category category);
    int getPlace();
    void setPlace(int place);
}