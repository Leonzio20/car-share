package pl.carshare.core.user;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author radziejoski
 **/
@Setter
@Getter
@Entity
@Table(name = "user", schema = "public")
public class User
{
  @Id
  @SequenceGenerator(name = "user_id_seq_gen", sequenceName = "user_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "user_id_seq_gen", strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "login", unique = true, updatable = false, nullable = false)
  private String login;

  @Column(name = "password", nullable = false)
  private String password;
}
