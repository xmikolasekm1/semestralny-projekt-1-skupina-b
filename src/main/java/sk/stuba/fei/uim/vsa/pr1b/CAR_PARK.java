package sk.stuba.fei.uim.vsa.pr1b;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CAR_PARK")
public class CAR_PARK implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAZOV", nullable = false)
    private String name;
    @Column(name = "ADRESA", nullable = false)
    private String adress;
    @Column(name = "CENA", nullable = false)
    private Integer pricePerHour;


    @OneToMany(mappedBy = "carPark",cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "idPoschodia",referencedColumnName = "idPoschodia")
    private List<CAR_PARK_FLOOR> CAR_PARK_FLOOR=new ArrayList<>();


    public List<CAR_PARK_FLOOR> getCAR_PARK_FLOOR() {
        return CAR_PARK_FLOOR;
    }

    public void setCAR_PARK_FLOOR(List<CAR_PARK_FLOOR> CAR_PARK_FLOOR) {
        this.CAR_PARK_FLOOR = CAR_PARK_FLOOR;
    }


    @Override
    public String toString() {
        return "CAR_PARK{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", pricePerHour=" + pricePerHour +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Integer getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Integer pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CAR_PARK)) {
            return false;
        }
        CAR_PARK other = (CAR_PARK) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }
}
