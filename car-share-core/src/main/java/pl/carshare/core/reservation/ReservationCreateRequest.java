package pl.carshare.core.reservation;

import lombok.Setter;
import pl.carshare.core.passage.Passage;
import pl.carshare.core.user.User;
import pl.carshare.core.user.UserByIdGetter;

/**
 * @author leonzio
 */
@Setter
public class ReservationCreateRequest
{
  private Long passageId;
  private Long travelerId;

  Reservation create(UserByIdGetter userByIdGetter, PassageByIdGetter passageByIdGetter)
  {
    User traveler = userByIdGetter.get(travelerId);
    Passage passage = passageByIdGetter.get(passageId);

    Reservation reservation = new Reservation();
    reservation.setPassage(passage);
    reservation.setTraveler(traveler);
    return reservation;
  }

  @FunctionalInterface
  interface PassageByIdGetter
  {
    Passage get(Long id);
  }
}
