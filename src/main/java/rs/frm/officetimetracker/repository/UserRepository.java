package rs.frm.officetimetracker.repository;

//import rs.frm.officetimetracker.domain.CheckIn;

import rs.frm.officetimetracker.domain.User;
import rs.frm.officetimetracker.repository.custom.CustomRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CustomRepository<User, Long> {
    Optional<User> findByEmail(String email);

//    List<User> findByCheckInTimeBetween(Date start, Date end);

//    CheckIn findTopByUserOrderByCheckInTimeDesc(User user);

}
