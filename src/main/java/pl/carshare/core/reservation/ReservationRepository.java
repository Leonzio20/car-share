package pl.carshare.core.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author leonzio
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long>
{
}
