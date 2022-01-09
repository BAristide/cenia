package net.linksguard.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.linksguard.entities.NotificationSubscription;

public interface NotificationSubscRepository extends JpaRepository<NotificationSubscription, Long> {

}
