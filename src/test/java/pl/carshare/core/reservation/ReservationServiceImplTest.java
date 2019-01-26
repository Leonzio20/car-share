package pl.carshare.core.reservation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.carshare.ApplicationConfig;
import pl.carshare.core.passage.Passage;
import pl.carshare.core.passage.PassageService;
import pl.carshare.core.user.User;
import pl.carshare.core.user.UserService;

/**
 * @author leonzio
 */
@ExtendWith({ SpringExtension.class, MockitoExtension.class })
@ContextConfiguration(classes = { ApplicationConfig.class })
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ReservationServiceImplTest
{
  @MockBean
  private ReservationRepository reservationRepository;

  @MockBean
  private UserService userService;

  @MockBean
  private PassageService passageService;

  @Autowired
  private ReservationServiceImpl reservationService;

  @Test
  void testCreateSuccess()
  {
    Long passageId = -12546L;
    Long travelerId = -96528L;

    ReservationCreateRequest request = new ReservationCreateRequest();
    request.setPassageId(passageId);
    request.setTravelerId(travelerId);

    User traveler = mock(User.class);
    Passage passage = mock(Passage.class);

    given(userService.getById(travelerId)).willReturn(traveler);
    given(passageService.getById(passageId)).willReturn(passage);

    ArgumentCaptor<Reservation> reservationArgumentCaptor = ArgumentCaptor.forClass(Reservation.class);
    Reservation reservation = mock(Reservation.class);
    given(reservationRepository.save(reservationArgumentCaptor.capture())).willReturn(reservation);

    Reservation createdReservation = reservationService.create(request);

    assertNotNull(createdReservation);
    assertEquals(reservation, createdReservation);

    verify(userService, times(1)).getById(travelerId);
    verify(passageService, times(1)).getById(passageId);
    verify(reservationRepository, times(1)).save(reservationArgumentCaptor.getValue());
  }
}