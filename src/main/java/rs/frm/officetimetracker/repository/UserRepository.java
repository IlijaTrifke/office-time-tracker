package rs.frm.officetimetracker.repository;

import rs.frm.officetimetracker.domain.CheckIn;
import rs.frm.officetimetracker.domain.User;
import rs.frm.officetimetracker.repository.custom.CustomRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends CustomRepository<User, Long> {
    List<User> findByDevice(String device);

    List<User> findByCheckInTimeBetween(Date start, Date end);

    CheckIn findTopByUserOrderByCheckInTimeDesc(User user);

}
