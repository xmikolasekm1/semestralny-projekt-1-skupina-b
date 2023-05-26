package sk.stuba.fei.uim.vsa.pr1b;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CAR_TYPE")
public class CAR_TYPE implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "carType", nullable = false, unique = true)
    private String carType;

    @OneToOne(mappedBy = "carType")
    private CAR car;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CAR getCar() {
        return car;
    }

    public void setCar(CAR car) {
        this.car = car;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }


    @Override
    public String toString() {
        return "CAR_TYPE{" +
                "id=" + id +
                ", carType='" + carType + '\'' +
                '}';
    }
}
