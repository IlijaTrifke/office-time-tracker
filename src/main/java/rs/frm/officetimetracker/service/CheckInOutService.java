package rs.frm.officetimetracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.frm.officetimetracker.domain.CheckInOut;
import rs.frm.officetimetracker.domain.User;
import rs.frm.officetimetracker.repository.CheckInOutRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CheckInOutService {
    @Autowired
    private CheckInOutRepository checkInOutRepository;

    public CheckInOut save(CheckInOut checkInOut) {
        return checkInOutRepository.save(checkInOut);
    }

    public Optional<CheckInOut> findLastCheckInByUser(User user) {
        return checkInOutRepository.findLastCheckInByUser(user);
    }
}