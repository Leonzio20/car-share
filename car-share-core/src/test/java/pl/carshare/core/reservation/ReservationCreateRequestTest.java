package pl.carshare.core.reservation;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.carshare.core.passage.Passage;
import pl.carshare.core.user.User;
import pl.carshare.core.user.UserByIdGetter;

/**
 * @author leonzio
 */
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ReservationCreateRequestTest
{
  @Mock
  private UserByIdGetter userByIdGetter;

  @Mock
  private ReservationCreateRequest.PassageByIdGetter passageByIdGetter;

  private ReservationCreateRequest request;

  @BeforeEach
  void setUp()
  {
    request = new ReservationCreateRequest();
  }

  @Test
  void testCreateSuccess()
  {
    Long passageId = -814L;
    Long travelerId = -987L;

    User traveler = mock(User.class);
    Passage passage = mock(Passage.class);

    when(userByIdGetter.get(travelerId)).thenReturn(traveler);
    when(passageByIdGetter.get(passageId)).thenReturn(passage);

    request.setPassageId(passageId);
    request.setTravelerId(travelerId);

    Reservation createdReservation = request.create(userByIdGetter, passageByIdGetter);

    assertNotNull(createdReservation);
    assertEquals(passage, createdReservation.getPassage());
    assertEquals(traveler, createdReservation.getTraveler());

    verify(userByIdGetter, times(1)).get(travelerId);
    verify(passageByIdGetter, times(1)).get(passageId);
  }

  @Test
  void testCreateValidationFails()
  {
    Exception exception = assertThrows(ConstraintViolationException.class,
      () -> request.create(userByIdGetter, passageByIdGetter));

    String exceptionMessage = exception.getMessage();
    assertThat(exceptionMessage, containsString("passageId: must not be null"));
    assertThat(exceptionMessage, containsString("travelerId: must not be null"));
  }
}