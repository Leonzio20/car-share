package pl.carshare.core.passage;

import java.util.List;

public interface PassageService
{
  List<Passage> search();

  Passage create(PassageCreateRequest request);

  Passage getById(Long id);
}
