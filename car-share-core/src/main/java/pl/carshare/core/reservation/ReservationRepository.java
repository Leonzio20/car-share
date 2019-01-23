package pl.carshare.core.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author leonzio
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>
{
}
