package sk.stuba.fei.uim.vsa.pr1b;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CAR")
public class CAR implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "znacka")
    private String znacka;

    @Column(name = "model")
    private String model;

    @Column(name = "ecv",unique = true)
    private String ecv;

    @Column(name = "farba")
    private String farba;


    @ManyToOne
    private USER user;

    @OneToOne
    private CAR_TYPE carType;

    @OneToOne(mappedBy = "car")
    private RESERVATION reservation;

    public RESERVATION getReservation() {
        return reservation;
    }

    public void setReservation(RESERVATION reservation) {
        this.reservation = reservation;
    }

    public void setCarType(CAR_TYPE carType) {
        this.carType = carType;
    }

    public CAR_TYPE getCarType() {
        return carType;
    }

    public USER getUser() {
        return user;
    }

    public void setUser(USER user) {
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZnacka() {
        return znacka;
    }

    public void setZnacka(String znacka) {
        this.znacka = znacka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEcv() {
        return ecv;
    }

    public void setEcv(String ecv) {
        this.ecv = ecv;
    }

    public String getFarba() {
        return farba;
    }

    public void setFarba(String farba) {
        this.farba = farba;
    }

    @Override
    public String toString() {
        return "CAR{" +
                "id=" + id +
                ", znacka='" + znacka + '\'' +
                ", model='" + model + '\'' +
                ", ecv='" + ecv + '\'' +
                ", farba='" + farba + '\'' +
                '}';
    }
}
