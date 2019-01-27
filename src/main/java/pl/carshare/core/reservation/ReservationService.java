package pl.carshare.core.reservation;

import javax.validation.Valid;

public interface ReservationService
{
  Reservation create(@Valid ReservationCreateRequest request);
}
