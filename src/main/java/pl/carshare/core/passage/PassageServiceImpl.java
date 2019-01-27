package pl.carshare.core.passage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.carshare.core.user.UserService;

@Service("passageService")
@Transactional
public class PassageServiceImpl implements PassageService
{
  @Autowired
  private PassageRepository passageRepository;

  @Autowired
  private UserService userService;

  @Override
  public List<Passage> search()
  {
    return passageRepository.findAll();
  }

  @Override
  public Passage create(PassageCreateRequest request)
  {
    Passage passage = request.create(userService::getById);
    return passageRepository.save(passage);
  }

  @Override
  public Passage getById(Long id)
  {
    return passageRepository.getOne(id);
  }
}
