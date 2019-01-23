package pl.carshare.core.passage;

import java.time.LocalDateTime;

import lombok.Setter;
import pl.carshare.core.user.User;
import pl.carshare.core.user.UserByIdGetter;

/**
 * @author leonzio
 */
@Setter
public class PassageCreateRequest
{
  private Long userId;
  private String origin;
  private String destination;
  private int availableSeatsCount;
  private LocalDateTime timeOfDeparture;

  Passage create(UserByIdGetter userByIdGetter)
  {
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