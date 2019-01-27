package pl.carshare.core.user;

public class NoSuchUserException extends RuntimeException
{
  private NoSuchUserException(String message)
  {
    super(message);
  }

  public static NoSuchUserException of(Long id)
  {
    return new NoSuchUserException("No user with id: " + id + " found.");
  }
}
