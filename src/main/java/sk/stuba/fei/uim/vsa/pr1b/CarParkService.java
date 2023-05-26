package sk.stuba.fei.uim.vsa.pr1b;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CarParkService extends AbstractCarParkService {
    /**
     * OK
     */
    @Override
    public Object createCarPark(String name, String address, Integer pricePerHour) {
        if (name == null || address == null || pricePerHour == null) return null;
        CAR_PARK car_park = new CAR_PARK();
        car_park.setName(name);
        car_park.setAdress(address);
        car_park.setPricePerHour(pricePerHour);
        persist(car_park);
        return car_park;
    }

    /**
     * OK
     */
    @Override
    public Object getCarPark(Long carParkId) {
        EntityManager em = emf.createEntityManager();
        if (carParkId != null && checkCarPark(carParkId)) {
            return em.find(CAR_PARK.class, carParkId);
        }
        return null;
    }

    /**
     * OK
     */
    @Override
    public Object getCarPark(String carParkName) {
        EntityManager em = emf.createEntityManager();
        if (carParkName == null) return null;
        Query q = em.createNativeQuery("select * from CAR_PARK where NAZOV='" + (carParkName) + "'", CAR_PARK.class);
        CAR_PARK car_parks = (CAR_PARK) q.getSingleResult();
        return car_parks;

    }

    /**
     * OK
     */
    @Override
    public List<Object> getCarParks() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNativeQuery("select * from CAR_PARK", CAR_PARK.class);
        List<CAR_PARK> car_parks = q.getResultList();

        return Arrays.asList(car_parks.toArray());
    }

    /**
     * OK
     */
    @Override
    public Object updateCarPark(Object carPark) {
        EntityManager em = emf.createEntityManager();
        CAR_PARK cp = (CAR_PARK) carPark;
        CAR_PARK car_park = em.find(CAR_PARK.class, cp.getId());
        if (car_park.getCAR_PARK_FLOOR() == null) {
            em.getTransaction().begin();
            em.merge(car_park);
            em.getTransaction().commit();
            em.close();
            return car_park;
        }
        return null;

    }

    /**
     * OK
     */
    @Override
    public Object deleteCarPark(Long carParkId) {
        EntityManager em = emf.createEntityManager();
        if (carParkId == null || checkCarPark(carParkId) == false) return null;
        CAR_PARK carPark = em.getReference(CAR_PARK.class, carParkId);
        em.getTransaction().begin();
        em.remove(carPark);
        em.getTransaction().commit();
        em.close();
        return carPark;
    }

    /**
     * OK
     */
    @Override
    public Object createCarParkFloor(Long carParkId, String floorIdentifier) {
        EntityManager em = emf.createEntityManager();

        if (carParkId == null || floorIdentifier == null || checkCarPark(carParkId) == false) return null;

        CAR_PARK car_park = em.find(CAR_PARK.class, carParkId);
        if (car_park == null) {
            return null;
        }

        CAR_PARK_FLOOR car_park_floor = new CAR_PARK_FLOOR();

        car_park_floor.setidPark(carParkId);
        car_park_floor.setFloorIdentifier(floorIdentifier);
        car_park_floor.setCarPark(car_park);

        List<CAR_PARK_FLOOR> car_park_floors = car_park.getCAR_PARK_FLOOR();
        car_park_floors.add(car_park_floor);

        car_park.setCAR_PARK_FLOOR(car_park_floors);

        em.getTransaction().begin();
        try {

            em.persist(car_park);
            em.persist(car_park_floor);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return car_park_floor;
    }

    /**
     * OK
     */
    @Override
    public Object getCarParkFloor(Long carParkId, String floorIdentifier) {
        EntityManager em = emf.createEntityManager();
        if (carParkId == null || floorIdentifier == null) return null;
        Query q = em.createNativeQuery("select * from CAR_PARK_FLOOR where idPark='" + (carParkId) + "' and floorIdentifier='" + (floorIdentifier) + "'", CAR_PARK_FLOOR.class);
        try {
            CAR_PARK_FLOOR cpf = (CAR_PARK_FLOOR) q.getSingleResult();
            return cpf;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * OK
     */
    @Override
    public Object getCarParkFloor(Long carParkFloorId) {
        return null;
    }

    /**
     * OK
     */
    @Override
    public List<Object> getCarParkFloors(Long carParkId) {
        EntityManager em = emf.createEntityManager();
        if (carParkId == null || checkCarPark(carParkId) == false) return null;
        Query q = em.createNativeQuery("select * from CAR_PARK_FLOOR where idPark='" + (carParkId) + "'", CAR_PARK_FLOOR.class);
        try {
            List<CAR_PARK_FLOOR> cpf = q.getResultList();
            return Arrays.asList(cpf.toArray());
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();

        }

    }

    /**
     * OK
     */
    @Override
    public Object updateCarParkFloor(Object carParkFloor) {

        return null;
    }

    /**
     * OK
     */
    @Override
    public Object deleteCarParkFloor(Long carParkId, String floorIdentifier) {
        EntityManager em = emf.createEntityManager();
        if (carParkId == null || floorIdentifier == null || checkCarPark(carParkId) == false) return null;
        Query q = em.createNativeQuery("select * from CAR_PARK_FLOOR where idPark='" + (carParkId) + "' and floorIdentifier='" + (floorIdentifier) + "'", CAR_PARK_FLOOR.class);
        try {
            CAR_PARK_FLOOR object = (CAR_PARK_FLOOR) q.getSingleResult();
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction().commit();
            return object;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }


    }

    /**
     * OK
     */
    @Override
    public Object deleteCarParkFloor(Long carParkFloorId) {
        return null;
    }

    /**
     * OK
     */
    @Override
    public Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier) {
        EntityManager em = emf.createEntityManager();
        if (carParkId == null || floorIdentifier == null || spotIdentifier == null || checkCarPark(carParkId) == false) {
            return null;
        }
        if (checkParkSpotName(carParkId,spotIdentifier)==false)return null;
        Query q = em.createNativeQuery("select * from CAR_PARK_FLOOR where idPark='" + (carParkId) + "' and floorIdentifier='" + (floorIdentifier) + "'", CAR_PARK_FLOOR.class);
        try {
            CAR_PARK_FLOOR cpf = (CAR_PARK_FLOOR) q.getSingleResult();
            PARKING_SPOT parking_spot = new PARKING_SPOT();
            parking_spot.setSpotIdentifier(spotIdentifier);
            parking_spot.setCar_park_floor(cpf);
            List<PARKING_SPOT> ps = cpf.getParking_spots();
            ps.add(parking_spot);
            cpf.setParking_spots(ps);
            parking_spot.setCarType("Benzin");
            em.getTransaction().begin();
            em.persist(parking_spot);
            em.persist(cpf);
            em.getTransaction().commit();
            return parking_spot;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return null;
    }

    /**
     * OK
     */
    @Override
    public Object getParkingSpot(Long parkingSpotId) {
        EntityManager em = emf.createEntityManager();
        if (parkingSpotId == null || checkParkSpot(parkingSpotId) == false) return null;
        Query q = em.createNativeQuery("select * from PARKING_SPOT where idMiesta='" + (parkingSpotId) + "'", PARKING_SPOT.class);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    /**
     * OK
     */
    @Override
    public List<Object> getParkingSpots(Long carParkId, String floorIdentifier) {
        EntityManager em = emf.createEntityManager();
        if (carParkId == null || floorIdentifier == null || checkCarPark(carParkId) == false) return null;
        Query q = em.createNativeQuery("select * from PARKING_SPOT where idCarParku='" + (carParkId) + "' and idParkingFloor='" + (floorIdentifier) + "'", PARKING_SPOT.class);
        try {
            List<PARKING_SPOT> ps = q.getResultList();
            return Arrays.asList(ps.toArray());
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }

    }

    /**
     * OK
     */
    @Override
    public Map<String, List<Object>> getParkingSpots(Long carParkId) {
        EntityManager em = emf.createEntityManager();
        if (carParkId == null || checkCarPark(carParkId) == false) return null;
        CAR_PARK cp = em.find(CAR_PARK.class, carParkId);
        if (cp == null) return null;
        Map<String, List<Object>> map = new HashMap<>();
        List<CAR_PARK_FLOOR> cpf = cp.getCAR_PARK_FLOOR();
        for (CAR_PARK_FLOOR c : cpf) {
            String menoP = c.getFloorIdentifier();
            List<Object> o = Collections.singletonList(c.getParking_spots());
            map.put(menoP, o);
        }
        return map;
    }

    /**
     * OK
     */
    @Override
    public Map<String, List<Object>> getAvailableParkingSpots(String carParkName) {
        EntityManager em = emf.createEntityManager();
        if (carParkName == null) return null;
        Query q = em.createNativeQuery("select * from CAR_PARK where NAZOV='" + (carParkName) + "';", CAR_PARK.class);
        Map<String, List<Object>> map = new HashMap<>();
        try {
            CAR_PARK car_park = (CAR_PARK) q.getSingleResult();
            List<CAR_PARK_FLOOR> carParkFloors = car_park.getCAR_PARK_FLOOR();
            for (CAR_PARK_FLOOR cpf : carParkFloors) {
                if (!cpf.getParking_spots().isEmpty()) {
                    Query q2 = em.createQuery("select park from PARKING_SPOT park where park.car_park_floor.floorIdentifier='" + (cpf.getFloorIdentifier()) + "'", PARKING_SPOT.class);
                    List<PARKING_SPOT> parkingSpots = q2.getResultList();
                    List<PARKING_SPOT> ps = new ArrayList<>();
                    for (PARKING_SPOT spot : parkingSpots) {
                        RESERVATION reservation = spot.getReservation();
                        if (reservation == null) {
                            ps.add(spot);
                        }
                    }

                    map.put(cpf.getFloorIdentifier(), Collections.singletonList(ps));
                }
            }
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
        return map;

    }

    /**
     * OK
     */
    @Override
    public Map<String, List<Object>> getOccupiedParkingSpots(String carParkName) {
        EntityManager em = emf.createEntityManager();
        if (carParkName == null) return null;
        Query q = em.createNativeQuery("select * from CAR_PARK where NAZOV='" + (carParkName) + "';", CAR_PARK.class);
        Map<String, List<Object>> map = new HashMap<>();
        try {
            CAR_PARK car_park = (CAR_PARK) q.getSingleResult();
            List<CAR_PARK_FLOOR> carParkFloors = car_park.getCAR_PARK_FLOOR();
            for (CAR_PARK_FLOOR cpf : carParkFloors) {
                if (!cpf.getParking_spots().isEmpty()) {
                    Query q2 = em.createQuery("select park from PARKING_SPOT park where park.car_park_floor.floorIdentifier='" + (cpf.getFloorIdentifier()) + "'", PARKING_SPOT.class);
                    List<PARKING_SPOT> parkingSpots = q2.getResultList();
                    List<PARKING_SPOT> ps = new ArrayList<>();
                    for (PARKING_SPOT spot : parkingSpots) {
                        RESERVATION reservation = spot.getReservation();
                        if (reservation != null) {
                            ps.add(spot);
                        }
                    }

                    map.put(cpf.getFloorIdentifier(), Collections.singletonList(ps));
                }
            }
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
        return map;
    }

    @Override
    public Object updateParkingSpot(Object parkingSpot) {
        return null;
    }

    /**
     * OK
     */
    @Override
    public Object deleteParkingSpot(Long parkingSpotId) {
        EntityManager em = emf.createEntityManager();
        if (parkingSpotId == null || checkParkSpot(parkingSpotId) == false) return null;
        Query q = em.createNativeQuery("select * from PARKING_SPOT where idMiesta='" + (parkingSpotId) + "'", PARKING_SPOT.class);
        try {
            PARKING_SPOT ps = (PARKING_SPOT) q.getSingleResult();
            em.getTransaction().begin();
            em.remove(ps);
            em.getTransaction().commit();
            return ps;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * OK
     */
    @Override
    public Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate) {
        EntityManager em = emf.createEntityManager();

        if (userId == null || brand == null || model == null || colour == null || vehicleRegistrationPlate == null)
            return null;

        USER user = em.find(USER.class, userId);
        if (user == null) {
            return null;
        }

        List<CAR> carList = user.getAuto();
        CAR car = new CAR();

        car.setZnacka(brand);
        car.setModel(model);
        car.setFarba(colour);
        car.setEcv(vehicleRegistrationPlate);
        car.setUser(user);

        CAR_TYPE type = (CAR_TYPE) getCarType("Benzin");
        if(type == null) {
            type = (CAR_TYPE) createCarType("Benzin");
        }
        car.setCarType(type);


        carList.add(car);
        user.setAuto(carList);

        em.getTransaction().begin();
        try {

            em.persist(user);
            em.persist(car);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return car;
    }

    /**
     * OK
     */
    @Override
    public Object getCar(Long carId) {
        EntityManager em = emf.createEntityManager();
        if (carId == null || checkCar(carId) == false) return null;
        CAR car = em.find(CAR.class, carId);
        if (car != null) return car;
        return null;
    }

    /**
     * OK
     */
    @Override
    public Object getCar(String vehicleRegistrationPlate) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNativeQuery("select * from CAR where ecv='" + (vehicleRegistrationPlate) + "'", CAR.class);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * OK
     */
    @Override
    public List<Object> getCars(Long userId) {
        EntityManager em = emf.createEntityManager();
        if (userId == null || checkUser(userId) == false) return null;
        Query q = em.createNativeQuery("select * from CAR where USER_id='" + (userId) + "'", CAR.class);
        try {
            List<CAR> car = q.getResultList();
            return Arrays.asList(car.toArray());
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Object updateCar(Object car) {

        return null;
    }

    /**
     * OK
     */
    @Override
    public Object deleteCar(Long carId) {
        EntityManager em = emf.createEntityManager();
        if (carId == null || checkCar(carId)) return null;
        Query q = em.createNativeQuery("select * from CAR where id='" + (carId) + "'", CAR.class);
        try {
            CAR auto = (CAR) q.getSingleResult();
            endReservation(auto.getReservation().getId());
            em.getTransaction().begin();
            em.remove(auto);
            em.getTransaction().commit();
            return auto;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * OK
     */
    @Override
    public Object createUser(String firstname, String lastname, String email) {
        if (firstname == null || lastname == null || email == null) return null;

        USER user = new USER();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        persist(user);
        return user;
    }

    /**
     * OK
     */
    @Override
    public Object getUser(Long userId) {
        EntityManager em = emf.createEntityManager();
        if (userId == null || checkUser(userId) == false) return null;
        USER user = em.find(USER.class, userId);
        return user;
    }

    /**
     * OK
     */
    @Override
    public Object getUser(String email) {
        EntityManager em = emf.createEntityManager();
        if (email == null) return null;
        Query q = em.createNativeQuery("select * from USER", USER.class);
        List<USER> users = q.getResultList();
        for (USER c : users) {
            if (c.getEmail().matches(email)) {
                return c;
            }
        }
        return null;
    }

    /**
     * OK
     */
    @Override
    public List<Object> getUsers() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNativeQuery("select * from USER", USER.class);
        try {
            List<USER> user = q.getResultList();
            return Arrays.asList(user.toArray());
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * OK
     */
    @Override
    public Object updateUser(Object user) {
        EntityManager em = emf.createEntityManager();

        USER user1 = (USER) user;
        USER us = em.find(USER.class, user1.getId());
        if (us == null) {
            return null;
        }
        if (us.getEmail() == null) return null;
        else {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            return user1;
        }

    }

    /**
     * OK
     */
    @Override
    public Object deleteUser(Long userId) {
        EntityManager em = emf.createEntityManager();
        if (userId == null || checkUser(userId) == false) return null;
        Query q = em.createNativeQuery("select * from USER where id='" + (userId) + "'", USER.class);
        Query q2 = em.createNativeQuery("select * from RESERVATION where IDUSER='" + (userId) + "'", RESERVATION.class);
        try {
            USER user = (USER) q.getSingleResult();
            List<RESERVATION> r = q2.getResultList();
            for (RESERVATION re : r) {
                endReservation(re.getId());
            }
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }


    }

    /**
     * OK
     */
    @Override
    public Object createReservation(Long parkingSpotId, Long cardId) {
        EntityManager em = emf.createEntityManager();
        if (parkingSpotId == null || cardId == null) return null;
        RESERVATION reservation = new RESERVATION();
        if (checkId(parkingSpotId)) {
            if (checkType(parkingSpotId, cardId)) {
                if (checkCarAndSpot(cardId)) {
                    CAR c = em.find(CAR.class, cardId);
                    reservation.setIdUser(c.getUser().getId());

                    PARKING_SPOT ps = em.find(PARKING_SPOT.class, parkingSpotId);
                    ps.setReservation(reservation);

                    c.setReservation(reservation);
                    reservation.setCar(c);


                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    reservation.setDatum(Timestamp.valueOf(sdf.format(timestamp)));
                    reservation.setParkingSpot(ps);


                    em.getTransaction().begin();
                    try {
                        em.persist(reservation);
                        em.merge(ps);
                        em.getTransaction().commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                        em.getTransaction().rollback();
                    } finally {
                        em.close();
                    }

                    return reservation;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
        return null;
    }

    /**
     * OK
     */
    @Override
    public Object endReservation(Long reservationId) {
        EntityManager em = emf.createEntityManager();
        if (reservationId == null) return null;
        Query q = em.createNativeQuery("select * from RESERVATION where id='" + (reservationId) + "';", RESERVATION.class);
        try {
            RESERVATION reservation = (RESERVATION) q.getSingleResult();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            reservation.setKoniec(Timestamp.valueOf(sdf.format(timestamp)));


            long difference = reservation.getKoniec().getTime() - reservation.getDatum().getTime();
            double hours = (double) ((difference / (1000 * 60 * 60)) % 24);
            System.out.println(hours);
            double cena = 0;


            Query q1 = em.createNativeQuery("select * from CAR_PARK_FLOOR;", CAR_PARK_FLOOR.class);
            List<CAR_PARK_FLOOR> cpf = q1.getResultList();

            for (CAR_PARK_FLOOR cp : cpf) {
                List<PARKING_SPOT> parkingSpots = cp.getParking_spots();
                for (PARKING_SPOT ps : parkingSpots) {
                    if (ps.getIdMiesta().equals(reservation.getParkingSpot().getIdMiesta())) {
                        cena = cp.getCarPark().getPricePerHour();
                    }
                }
            }

            reservation.setCelkovaCena(cena * hours);
            CAR car = em.find(CAR.class, reservation.getCar().getId());
            car.setReservation(null);

            PARKING_SPOT parking_spot = em.find(PARKING_SPOT.class, reservation.getParkingSpot().getIdMiesta());
            parking_spot.setReservation(null);

            em.getTransaction().begin();
            em.merge(reservation);
            em.getTransaction().commit();

            return reservation;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }

    }

    /**
     * OK
     */
    @Override
    public List<Object> getReservations(Long parkingSpotId, Date date) {
        EntityManager em = emf.createEntityManager();
        if (parkingSpotId == null || date == null) return null;

        long den = date.getTime();
        System.out.println(den);
        Query q = em.createNativeQuery("select * from RESERVATION where PARKINGSPOT_idMiesta='" + (parkingSpotId) + "'", RESERVATION.class);
        try {
            List<RESERVATION> reservations = q.getResultList();
            for (RESERVATION r : reservations) {
                if (r.getDatum().getTime() >= den && r.getDatum().getTime() <= (den + 86400000)) {
                    List<RESERVATION> reservationList = new ArrayList<>();
                    reservationList.add(r);
                    return Arrays.asList(reservationList.toArray());
                }
            }
            return Arrays.asList(Collections.emptyList().toArray());
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }


    }

    /**
     * OK
     */
    @Override
    public List<Object> getMyReservations(Long userId) {
        EntityManager em = emf.createEntityManager();
        if (userId == null || checkUser(userId) == false) return null;
        Query q = em.createNativeQuery("select * from RESERVATION where IDUSER='" + (userId) + "'", RESERVATION.class);
        try {
            List<RESERVATION> reservations = q.getResultList();
            return Arrays.asList(reservations.toArray());
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }

    }

    /**
     * OK
     */
    @Override
    public Object updateReservation(Object reservation) {


        return null;
    }

    /**
     * OK
     */
    @Override
    public Object createCarType(String name) {
        if (name == null) return null;
        CAR_TYPE car_type = new CAR_TYPE();
        car_type.setCarType(name);
        persist(car_type);
        return car_type;
    }

    /**
     * OK
     */
    @Override
    public List<Object> getCarTypes() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNativeQuery("select * from CAR_TYPE;", CAR_TYPE.class);
        try {
            List<CAR_TYPE> carTypes = q.getResultList();
            return Arrays.asList(carTypes.toArray());
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }

    }

    /**
     * OK
     */
    @Override
    public Object getCarType(Long carTypeId) {
        EntityManager em = emf.createEntityManager();
        if (carTypeId == null) return null;
        Query q = em.createNativeQuery("select * from CAR_TYPE where id='" + (carTypeId) + "';", CAR_TYPE.class);
        try {
            CAR_TYPE carTypes = (CAR_TYPE) q.getSingleResult();
            return carTypes;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * OK
     */
    @Override
    public Object getCarType(String name) {
        EntityManager em = emf.createEntityManager();
        if (name == null) return null;
        Query q = em.createNativeQuery("select * from CAR_TYPE where carType='" + (name) + "';", CAR_TYPE.class);
        try {
            CAR_TYPE carTypes = (CAR_TYPE) q.getSingleResult();
            return carTypes;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * OK
     */
    @Override
    public Object deleteCarType(Long carTypeId) {
        EntityManager em = emf.createEntityManager();
        if (carTypeId == null) return null;
        Query q = em.createNativeQuery("select * from CAR_TYPE where id='" + (carTypeId) + "'", CAR_TYPE.class);
        try {
            CAR_TYPE auto = (CAR_TYPE) q.getSingleResult();
            em.getTransaction().begin();
            em.remove(auto);
            em.getTransaction().commit();
            return auto;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }

    }

    /**
     * OK
     */
    @Override
    public Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate, Long carTypeId) {
        EntityManager em = emf.createEntityManager();

        if (userId == null || checkUser(userId) == false || brand == null || model == null || colour == null || vehicleRegistrationPlate == null || carTypeId == null)
            return null;

        USER user = em.find(USER.class, userId);
        if (user == null) {
            return null;
        }

        List<CAR> carList = user.getAuto();
        CAR car = new CAR();

        car.setZnacka(brand);
        car.setModel(model);
        car.setFarba(colour);
        car.setEcv(vehicleRegistrationPlate);
        car.setUser(user);

        CAR_TYPE carType = em.find(CAR_TYPE.class, carTypeId);
        car.setCarType(carType);
        carType.setCar(car);


        carList.add(car);
        user.setAuto(carList);

        em.getTransaction().begin();
        try {

            em.persist(user);
            em.persist(car);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return car;
    }

    /**
     * OK
     */
    @Override
    public Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier, Long carTypeId) {
        EntityManager em = emf.createEntityManager();
        if (carParkId == null || floorIdentifier == null || spotIdentifier == null || carParkId == null) return null;
        if (checkParkSpotName(carParkId,spotIdentifier)==false)return null;
        Query q = em.createNativeQuery("select * from CAR_PARK_FLOOR where idPark='" + (carParkId) + "' and floorIdentifier='" + (floorIdentifier) + "'", CAR_PARK_FLOOR.class);
        try {
            CAR_PARK_FLOOR cpf = (CAR_PARK_FLOOR) q.getSingleResult();
            PARKING_SPOT parking_spot = new PARKING_SPOT();
            parking_spot.setSpotIdentifier(spotIdentifier);

            CAR_TYPE car_type = em.find(CAR_TYPE.class, carTypeId);
            parking_spot.setCarType(car_type.getCarType());


            parking_spot.setCar_park_floor(cpf);
            List<PARKING_SPOT> ps = cpf.getParking_spots();
            ps.add(parking_spot);
            cpf.setParking_spots(ps);
            em.getTransaction().begin();
            em.persist(parking_spot);
            em.persist(cpf);
            em.getTransaction().commit();
            return parking_spot;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return null;
    }

    public boolean checkId(Long parkSpotId) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNativeQuery("select * from PARKING_SPOT where idMiesta='" + (parkSpotId) + "'", PARKING_SPOT.class);
        try {
            PARKING_SPOT ps = (PARKING_SPOT) q.getSingleResult();
            if (ps != null && ps.getReservation() == null) {
                return true;
            }
        } catch (NoResultException e) {
            return false;
        } finally {
            em.close();
        }
        return false;

    }

    public boolean checkType(Long parkSpotId, Long carId) {
        EntityManager em = emf.createEntityManager();
        CAR car = em.find(CAR.class, carId);
        String type = car.getCarType().getCarType();
        PARKING_SPOT ps = em.find(PARKING_SPOT.class, parkSpotId);
        if (Objects.equals(type, ps.getCarType())) {
            return true;
        }
        return false;
    }

    public boolean checkCarAndSpot(Long carId) {
        EntityManager em = emf.createEntityManager();
        CAR car = em.find(CAR.class, carId);
        if (car.getReservation() == null) {
            return true;
        } else {
            return false;
        }

    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsa-project-b");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public boolean checkCarPark(Long carparkid) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNativeQuery("select * from CAR_PARK where ID='" + (carparkid) + "';", CAR_PARK.class);
        CAR_PARK cp = (CAR_PARK) q.getSingleResult();
        if (cp != null) {
            return true;
        }
        return false;
    }

    public boolean checkParkSpot(Long parkspotid) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNativeQuery("select * from PARKING_SPOT where idMiesta='" + (parkspotid) + "'", PARKING_SPOT.class);
        PARKING_SPOT cp = (PARKING_SPOT) q.getSingleResult();
        if (cp != null) {
            return true;
        }
        return false;
    }

    public boolean checkUser(Long userId) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNativeQuery("select * from USER where id='" + (userId) + "'", USER.class);
        USER cp = (USER) q.getSingleResult();
        if (cp != null) {
            return true;
        }
        return false;
    }

    public boolean checkCar(Long carId) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNativeQuery("select * from CAR where id='" + (carId) + "'", CAR.class);
        CAR cp = (CAR) q.getSingleResult();
        if (cp != null) {
            return true;
        }
        return false;
    }

    public boolean checkParkSpotName(Long cpId ,String name) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNativeQuery("select * from PARKING_SPOT where idCarParku='" + (cpId) + "' and SPOTIDENTIFIER='" + (name) + "'", PARKING_SPOT.class);
        try {
            PARKING_SPOT ps = (PARKING_SPOT) q.getSingleResult();
            if (ps != null) {
                return false;
            } else {
                return true;
            }
        } catch (NoResultException e) {
            return true;
        } finally {
            em.close();
        }
    }


}
