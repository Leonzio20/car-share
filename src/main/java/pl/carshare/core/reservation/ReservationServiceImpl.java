package pl.carshare.core.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.carshare.core.passage.PassageService;
import pl.carshare.core.user.UserService;

/**
 * @author leonzio
 */
@Service("reservationService")
@Transactional
public class ReservationServiceImpl implements ReservationService
{
  @Autowired
  private ReservationRepository reservationRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private PassageService passageService;

  @Override
  public Reservation create(ReservationCreateRequest request)
  {
    Reservation reservation = request.create(userService::getById, passageService::getById);
    return reservationRepository.save(reservation);
  }
}
