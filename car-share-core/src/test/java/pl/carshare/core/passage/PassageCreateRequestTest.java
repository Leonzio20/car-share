package pl.carshare.core.passage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.carshare.core.user.User;
import pl.carshare.core.user.UserByIdGetter;

/**
 * @author leonzio
 */
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class PassageCreateRequestTest
{
  @Mock
  private UserByIdGetter userByIdGetter;

  private PassageCreateRequest request;

  @BeforeEach
  void setUp()
  {
    request = new PassageCreateRequest();
  }

  @Test
  void testCreateSuccess()
  {
    Long driverId = -987L;

    request.setUserId(driverId);
    request.setAvailableSeatsCount(2);
    request.setDestination("Warszawa");
    request.setOrigin("Kraków");
    request.setTimeOfDeparture(LocalDateTime.parse("2050-10-10T08:00:00"));

    User driver = mock(User.class);
    when(userByIdGetter.get(driverId)).thenReturn(driver);

    Passage createdPassage = request.create(userByIdGetter);

    assertNotNull(createdPassage);

    verify(userByIdGetter, times(1)).get(driverId);
  }

  @Test
  void testLoginValidationFails()
  {
    Exception exception = assertThrows(ConstraintViolationException.class, () -> request.create(userByIdGetter));

    String exceptionMessage = exception.getMessage();
    assertThat(exceptionMessage, containsString("availableSeatsCount: must be greater than 0"));
    assertThat(exceptionMessage, containsString("origin: must not be null"));
    assertThat(exceptionMessage, containsString("destination: must not be null"));
    assertThat(exceptionMessage, containsString("timeOfDeparture: must not be null"));
    assertThat(exceptionMessage, containsString("userId: must not be null"));
  }
}