package pl.carshare.core.reservation;

import javax.validation.Valid;

/**
 * @author leonzio
 */
public interface ReservationService
{
  Reservation create(@Valid ReservationCreateRequest request);
}
