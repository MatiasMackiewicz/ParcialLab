package utn.telefonica.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.telefonica.app.model.Bill;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name ="phonelines")
public class PhoneLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_line")
    private int id; //Entity
    @Column(name = "lineType")
    private String lineType; //todo esto debe ser un enumerador.
    @Column(name = "lineNumber")
    private long lineNumber;


    @OneToMany(mappedBy = "phoneLine")
    private List<Bill> bills;

    @OneToMany(mappedBy = "phoneLine")
    private List<Call> callList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Customer customer;
}
