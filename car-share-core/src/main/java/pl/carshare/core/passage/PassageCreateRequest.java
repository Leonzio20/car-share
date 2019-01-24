package pl.carshare.core.passage;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import pl.carshare.core.bean.BeanValidation;
import pl.carshare.core.user.User;
import pl.carshare.core.user.UserByIdGetter;

/**
 * @author leonzio
 */
@Setter
@Validated
public class PassageCreateRequest
{
  private @NotNull Long userId;
  private @NotNull String origin;
  private @NotNull String destination;
  private @Positive int availableSeatsCount;
  private @NotNull @Future LocalDateTime timeOfDeparture;

  Passage create(UserByIdGetter userByIdGetter)
  {
    BeanValidation.validate(this);

    User createdBy = userByIdGetter.get(userId);

    Passage passage = new Passage();
    passage.setAuthor(createdBy);
    passage.setOrigin(origin);
    passage.setDestination(destination);
    passage.setTimeOfDeparture(timeOfDeparture);
    passage.setAvailableSeatsCount(availableSeatsCount);
    return passage;
  }
}