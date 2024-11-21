package com.bamdoliro.sinabro.infrastructure.persistence.notification;

import com.bamdoliro.sinabro.domain.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
