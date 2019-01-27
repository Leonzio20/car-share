package pl.carshare.core.passage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

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
import pl.carshare.core.user.User;
import pl.carshare.core.user.UserService;

@ExtendWith({ SpringExtension.class, MockitoExtension.class })
@ContextConfiguration(classes = { ApplicationConfig.class })
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class PassageServiceImplTest
{
  @MockBean
  private PassageRepository passageRepository;

  @MockBean
  private UserService userService;

  @Autowired
  private PassageServiceImpl passageService;

  @Test
  void testSearchSuccess()
  {
    List<Passage> passages = mock(List.class);

    when(passageRepository.findAll()).thenReturn(passages);

    List<Passage> passageList = passageService.search();

    assertEquals(passages, passageList);

    verify(passageRepository, times(1)).findAll();
  }

  @Test
  void testCreateSuccess()
  {
    Long userId = -54685L;

    PassageCreateRequest request = new PassageCreateRequest();
    request.setUserId(userId);
    request.setAvailableSeatsCount(3);
    request.setOrigin("Kraków");
    request.setDestination("Wrocław");
    request.setTimeOfDeparture(LocalDateTime.parse("2030-10-10T10:20:00"));

    User user = mock(User.class);
    when(userService.getById(userId)).thenReturn(user);

    ArgumentCaptor<Passage> passageArgumentCaptor = ArgumentCaptor.forClass(Passage.class);
    Passage passage = mock(Passage.class);
    when(passageRepository.save(passageArgumentCaptor.capture())).thenReturn(passage);

    Passage createdPassage = passageService.create(request);

    assertEquals(passage, createdPassage);

    verify(userService, times(1)).getById(userId);
    verify(passageRepository, times(1)).save(passageArgumentCaptor.getValue());
  }

  @Test
  void testGetByIdSuccess()
  {
    Long passageId = -46589L;

    Passage passage = mock(Passage.class);
    when(passageRepository.getOne(passageId)).thenReturn(passage);

    Passage passageById = passageService.getById(passageId);

    assertEquals(passage, passageById);

    verify(passageRepository, times(1)).getOne(passageId);
  }
}