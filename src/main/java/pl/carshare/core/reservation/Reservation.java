package pl.carshare.core.reservation;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.carshare.core.passage.Passage;
import pl.carshare.core.user.User;

/**
 * @author leonzio
 */
@Getter
@Setter(AccessLevel.PACKAGE)
@EqualsAndHashCode
@Entity
@Table(schema = "public", name = "reservation")
public class Reservation
{
  @Id
  @SequenceGenerator(name = "reservation_id_seq_gen", sequenceName = "reservation_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "reservation_id_seq_gen", strategy = GenerationType.SEQUENCE)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "passage_id", nullable = false, referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "reservation_passage_id_fk"))
  private Passage passage;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "traveler_id", nullable = false, referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "reservation_traveler_id_fk"))
  private User traveler;
}
