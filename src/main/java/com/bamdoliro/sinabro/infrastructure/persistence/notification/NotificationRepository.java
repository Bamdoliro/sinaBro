package com.bamdoliro.sinabro.infrastructure.persistence.notification;

import com.bamdoliro.sinabro.domain.notification.domain.Notification;
import com.bamdoliro.sinabro.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByUser(User user);
}
