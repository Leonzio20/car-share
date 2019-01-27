package pl.carshare.core.reservation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import pl.carshare.core.bean.BeanValidation;
import pl.carshare.core.passage.Passage;
import pl.carshare.core.user.User;
import pl.carshare.core.user.UserByIdGetter;

@Setter
@Validated
public class ReservationCreateRequest
{
  private @NotNull Long passageId;
  private @NotNull Long travelerId;

  Reservation create(@NotNull @Valid UserByIdGetter userByIdGetter, @NotNull @Valid PassageByIdGetter passageByIdGetter)
  {
    BeanValidation.validate(this);

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
