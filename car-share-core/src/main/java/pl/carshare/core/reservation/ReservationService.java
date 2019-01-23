package pl.carshare.core.reservation;

/**
 * @author leonzio
 */
public interface ReservationService
{
  Reservation create(ReservationCreateRequest request);
}
