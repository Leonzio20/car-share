package pl.carshare.core.user;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author radziejoski
 **/
@Getter
@Setter(AccessLevel.PACKAGE)
@Entity
@Table(schema = "public", name = "user")
public class User
{
  @Id
  @SequenceGenerator(name = "user_id_seq_gen", sequenceName = "user_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "user_id_seq_gen", strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "user_name", unique = true, updatable = false, nullable = false)
  private String userName;

  @Column(name = "password", nullable = false)
  private String password;
}
