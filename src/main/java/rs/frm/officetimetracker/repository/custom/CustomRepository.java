package rs.frm.officetimetracker.repository.custom;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomRepository<T, R> extends JpaRepository<T, R> {
}
