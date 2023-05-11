package util;

import java.io.IOException;

import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import model.implementation.UserImpl;
import model.implementation.TicketImpl;
import model.implementation.EventImpl;
import storage.BookingStorage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class StorageInitializer implements BeanPostProcessor {
    private String userDataPath;
    private String ticketDataPath;
    private String eventDataPath;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("Execute postProcessBeforeInitialization for bean {}", beanName);
        if (bean instanceof BookingStorage bookingStorage) {
            try {
                bookingStorage.loadData(UserImpl.class, userDataPath);
                bookingStorage.loadData(TicketImpl.class, ticketDataPath);
                bookingStorage.loadData(EventImpl.class, eventDataPath);
            } catch (IOException e) {
                throw new RuntimeException("Error in bean post processor");
            }
        }
        return bean;
    }
}