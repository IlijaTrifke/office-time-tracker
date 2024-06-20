package rs.frm.officetimetracker.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.frm.officetimetracker.domain.CheckInOut;
import rs.frm.officetimetracker.domain.User;
import rs.frm.officetimetracker.repository.custom.CustomRepository;

import java.util.Optional;

public interface CheckInOutRepository extends CustomRepository<CheckInOut, Long> {

    @Query("SELECT c FROM CheckInOut c WHERE c.user = :user ORDER BY c.checkInTime DESC")
    Optional<CheckInOut> findLastCheckInByUser(@Param("user") User user);
}
