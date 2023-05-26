package sk.stuba.fei.uim.vsa.pr1b;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Temporal;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

import static javafx.application.Platform.exit;

public class Project1B {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/VSA_PR1?\" + \"autoReconnect=true&useSSL=false", "vsa", "vsa");

        CarParkService carParkService = new CarParkService();

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        while (true) {
            System.out.println("Vyber si moznost:");
            System.out.println("1 - Create");
            System.out.println("2 - Get");
            System.out.println("3 - Update");
            System.out.println("4 - Delete");
            System.out.println("5 - Exit");
            System.out.println("Zadaj cislo:");
            int userName = Integer.parseInt(myObj.nextLine());  // Read user input
            while (userName >= 6 || userName <= 0) {
                System.out.println("Zadaj cislo:");
                userName = Integer.parseInt(myObj.nextLine());  // Read user input

            }
            switch (userName) {
                case 1:
                    System.out.println("Vyber si moznost:");
                    System.out.println("1 - create CarPark");
                    System.out.println("2 - create CarPark Floor");
                    System.out.println("3 - create Parking Spot");
                    System.out.println("4 - create Car");
                    System.out.println("5 - create User");
                    System.out.println("6 - create Reservation");
                    System.out.println("7 - create Car Type");
                    System.out.println("8 - create Car + Car Type");
                    System.out.println("9 - create Parking Spot + Car Type");
                    System.out.println("Zadaj cislo:");
                    userName = Integer.parseInt(myObj.nextLine());  // Read user input

                    while (userName >= 10 || userName <= 0) {
                        System.out.println("Zadaj cislo:");
                        userName = Integer.parseInt(myObj.nextLine());  // Read user input

                    }
                    switch (userName) {
                        case 1:
                            System.out.println("Zadaj meno CarParku:");
                            String name = myObj.nextLine();
                            System.out.println("Zadaj adresu CarParku:");
                            String address = myObj.nextLine();
                            System.out.println("Zadaj cenu za hodinu:");
                            int pricePerHour = myObj.nextInt();
                            System.out.println(carParkService.createCarPark(name, address, pricePerHour));
                            break;


                        case 2:
                            System.out.println("Zadaj id CarParku:");
                            int id = Integer.parseInt(myObj.nextLine());
                            System.out.println("Zadaj identifikator poschodia:");
                            String identifier = myObj.nextLine();
                            System.out.println(carParkService.createCarParkFloor((long) id, identifier));
                            break;


                        case 3:
                            System.out.println("Zadaj id CarParku:");
                            int cPId = Integer.parseInt(myObj.nextLine());
                            System.out.println("Zadaj identifikator poschodia:");
                            String floorId = myObj.nextLine();
                            System.out.println("Zadaj identifikator parkovacieho miesta:");
                            String spotIdentifier = myObj.nextLine();
                            System.out.println(carParkService.createParkingSpot((long) cPId, floorId, spotIdentifier));
                            break;


                        case 4:
                            System.out.println("Zadaj id Usera:");
                            int userid = Integer.parseInt(myObj.nextLine());
                            System.out.println("Zadaj znacku auta:");
                            String brand = myObj.nextLine();
                            System.out.println("Zadaj model auta:");
                            String model = myObj.nextLine();
                            System.out.println("Zadaj farbu auta:");
                            String colour = myObj.nextLine();
                            System.out.println("Zadaj špz auta:");
                            String vhp = myObj.nextLine();
                            System.out.println(carParkService.createCar((long) userid, brand, model, colour, vhp));
                            break;


                        case 5:
                            System.out.println("Zadaj krstne meno:");
                            String firstname = myObj.nextLine();
                            System.out.println("Zadaj priezvisko:");
                            String lastname = myObj.nextLine();
                            System.out.println("Zadaj email:");
                            String email = myObj.nextLine();
                            System.out.println(carParkService.createUser(firstname, lastname, email));
                            break;


                        case 6:
                            System.out.println("Zadaj id parkovacieho miesta:");
                            int parkspotid = Integer.parseInt(myObj.nextLine());
                            System.out.println("Zadaj id auta:");
                            int carId = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.createReservation((long) parkspotid, (long) carId));
                            break;


                        case 7:
                            System.out.println("Zadaj nazov typu auta:");
                            String carTypeName = myObj.nextLine();
                            System.out.println(carParkService.createCarType(carTypeName));
                            break;


                        case 8:
                            System.out.println("Zadaj id Usera:");
                            int usId = Integer.parseInt(myObj.nextLine());
                            System.out.println("Zadaj znacku auta:");
                            String br = myObj.nextLine();
                            System.out.println("Zadaj model auta:");
                            String mod = myObj.nextLine();
                            System.out.println("Zadaj farbu auta:");
                            String col = myObj.nextLine();
                            System.out.println("Zadaj špz auta:");
                            String spz = myObj.nextLine();
                            System.out.println("Zadaj id Usera:");
                            int cartypeId = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.createCar((long) usId, br, mod, col, spz, (long) cartypeId));
                            break;


                        case 9:
                            System.out.println("Zadaj id CarParku:");
                            int cpid = Integer.parseInt(myObj.nextLine());
                            System.out.println("Zadaj identifikator poschodia:");
                            String flId = myObj.nextLine();
                            System.out.println("Zadaj identifikator parkovacieho miesta:");
                            String spotid = myObj.nextLine();
                            System.out.println("Zadaj id typu:");
                            int ctid = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.createParkingSpot((long) cpid, flId, spotid, (long) ctid));
                            break;
                    }
                    break;


                case 2:

                    System.out.println("Vyber si moznost:");
                    System.out.println("1 - get CarPark - id");
                    System.out.println("2 - get CarPark - name");
                    System.out.println("3 - get CarParks");
                    System.out.println("4 - get CarPark Floor - CarParkID + FloorIdentifier");
                    System.out.println("5 - get CarPark Floors - CarParkID");
                    System.out.println("6 - get Parking Spot - id");
                    System.out.println("7 - get Parking Spots - CarParkID + FloorIdentifier");
                    System.out.println("8 - get Parking Spots - CarParkID");
                    System.out.println("9 - get Available Parking Spots - CarPark Name");
                    System.out.println("10 - get Occupied Parking Spots - CarPark Name");
                    System.out.println("11 - get Car - id");
                    System.out.println("12 - get Car - vehicleRegistrationPlate");
                    System.out.println("13 - get Cars - UserId");
                    System.out.println("14 - get User - id");
                    System.out.println("15 - get User - email");
                    System.out.println("16 - get Users");
                    System.out.println("17 - get Reservations - parkingspotId + Date");
                    System.out.println("18 - get My Reservations - userId");
                    System.out.println("19 - get Car Types");
                    System.out.println("20 - get Car Type - id");
                    System.out.println("21 - get Car Type - name");

                    System.out.println("Zadaj cislo:");
                    userName = Integer.parseInt(myObj.nextLine());  // Read user input

                    while (userName >= 22 || userName <= 0) {
                        System.out.println("Zadaj cislo:");
                        userName = Integer.parseInt(myObj.nextLine());  // Read user input

                    }

                    switch (userName) {
                        case 1:
                            System.out.println("Zadaj id CarParku:");
                            int cp = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.getCarPark((long) cp));
                            break;
                        case 2:
                            System.out.println("Zadaj nazov CarParku:");
                            String nameCarp = (myObj.nextLine());
                            System.out.println(carParkService.getCarPark(nameCarp));
                            break;
                        case 3:
                            System.out.println(carParkService.getCarParks());
                            break;
                        case 4:
                            System.out.println("Zadaj id CarParku:");
                            int idcp = Integer.parseInt(myObj.nextLine());
                            System.out.println("Zadaj floor identifier:");
                            String fi = (myObj.nextLine());
                            System.out.println(carParkService.getCarParkFloor((long) idcp, fi));
                            break;
                        case 5:
                            System.out.println("Zadaj id CarParku:");
                            int cpi = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.getCarParkFloors((long) cpi));
                            break;
                        case 6:
                            System.out.println("Zadaj id Parking Spotu:");
                            int psid = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.getParkingSpot((long) psid));
                            break;
                        case 7:
                            System.out.println("Zadaj id CarParku:");
                            int ctid = Integer.parseInt(myObj.nextLine());
                            System.out.println("Zadaj floor Identifier:");
                            String fIden = (myObj.nextLine());
                            System.out.println(carParkService.getParkingSpots((long) ctid, fIden));
                            break;
                        case 8:
                            System.out.println("Zadaj id CarParku:");
                            int carParkId = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.getParkingSpots((long) carParkId));
                            break;
                        case 9:
                            System.out.println("Zadaj nazov CarParku:");
                            String CarParkName = (myObj.nextLine());
                            System.out.println(carParkService.getAvailableParkingSpots(CarParkName));
                            break;
                        case 10:
                            System.out.println("Zadaj nazov CarParku:");
                            String CarParkname = (myObj.nextLine());
                            System.out.println(carParkService.getOccupiedParkingSpots(CarParkname));
                            break;
                        case 11:
                            System.out.println("Zadaj id auta:");
                            int cid = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.getCar((long) cid));
                            break;
                        case 12:
                            System.out.println("Zadaj spz auta:");
                            String spz = (myObj.nextLine());
                            System.out.println(carParkService.getCar(spz));
                            break;
                        case 13:
                            System.out.println("Zadaj id usera:");
                            int uid = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.getCars((long) uid));
                            break;
                        case 14:
                            System.out.println("Zadaj id usera:");
                            int userId = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.getUser((long) userId));
                            break;
                        case 15:
                            System.out.println("Zadaj email usera:");
                            String email = (myObj.nextLine());
                            System.out.println(carParkService.getUser(email));
                            break;
                        case 16:
                            System.out.println(carParkService.getUsers());
                            break;
                        case 17:
                            System.out.println("Zadaj id parkovacieho miesta:");
                            int parkingSpotid = Integer.parseInt(myObj.nextLine());
                            System.out.println("Zadaj den:");
                            String day = (myObj.nextLine());
                            System.out.println("Zadaj mesiac:");
                            String month = (myObj.nextLine());
                            System.out.println("Zadaj rok:");
                            String year = (myObj.nextLine());

                            String datum = year + "-" + month + "-" + day;


                            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(datum);


                            System.out.println(carParkService.getReservations((long) parkingSpotid, date1));
                            break;
                        case 18:
                            System.out.println("Zadaj id usera:");
                            int userid = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.getMyReservations((long) userid));
                            break;
                        case 19:
                            System.out.println(carParkService.getCarTypes());
                            break;
                        case 20:
                            System.out.println("Zadaj id CarType:");
                            int cartypeid = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.getCarType((long) cartypeid));
                            break;
                        case 21:
                            System.out.println("Zadaj nazov CarType:");
                            String cartypename = (myObj.nextLine());
                            System.out.println(carParkService.getCarType(cartypename));
                            break;

                    }


                    break;


                case 3:

                    System.out.println("Vyber si moznost:");
                    System.out.println("1 - update CarPark");
                    System.out.println("2 - update CarPark Floor");
                    System.out.println("3 - update Parking Spot");
                    System.out.println("4 - update Car");
                    System.out.println("5 - update User");
                    System.out.println("6 - update Reservation");
                    System.out.println("7 - update Car Type");
                    System.out.println("Zadaj cislo:");
                    userName = Integer.parseInt(myObj.nextLine());  // Read user input

                    while (userName >= 8 || userName <= 0) {
                        System.out.println("Zadaj cislo:");
                        userName = Integer.parseInt(myObj.nextLine());  // Read user input

                    }
                    break;
                case 4:
                    System.out.println("Vyber si moznost:");
                    System.out.println("1 - delete CarPark");
                    System.out.println("2 - delete CarPark Floor");
                    System.out.println("3 - delete Parking Spot");
                    System.out.println("4 - delete Car");
                    System.out.println("5 - delete User");
                    System.out.println("6 - end/delete Reservation");
                    System.out.println("7 - delete Car Type");
                    System.out.println("Zadaj cislo:");
                    userName = Integer.parseInt(myObj.nextLine());  // Read user input

                    while (userName >= 10 || userName <= 0) {
                        System.out.println("Zadaj cislo:");
                        userName = Integer.parseInt(myObj.nextLine());  // Read user input

                    }

                    switch (userName) {
                        case 1:
                            System.out.println("Zadaj id CarParku:");
                            int cp = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.deleteCarPark((long) cp));
                            break;
                        case 2:
                            System.out.println("Zadaj id CarParku:");
                            int c = Integer.parseInt(myObj.nextLine());
                            System.out.println("Zadaj identifikator podlazia:");
                            String fi = (myObj.nextLine());
                            System.out.println(carParkService.deleteCarParkFloor((long) c, fi));
                            break;
                        case 3:
                            System.out.println("Zadaj id parkovacieho miesta:");
                            int psid = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.deleteParkingSpot((long) psid));
                            break;
                        case 4:
                            System.out.println("Zadaj id auta:");
                            int cid = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.deleteCar((long) cid));
                            break;
                        case 5:
                            System.out.println("Zadaj id usera:");
                            int uid = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.deleteUser((long) uid));
                            break;
                        case 6:
                            System.out.println("Zadaj id rezervacie:");
                            int rid = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.endReservation((long) rid));
                            break;
                        case 7:
                            System.out.println("Zadaj id typu auta:");
                            int ctid = Integer.parseInt(myObj.nextLine());
                            System.out.println(carParkService.deleteCarType((long) ctid));
                            break;
                    }
                    break;


                case 5:
                    System.exit(0);
                    break;
            }
        }

        //carParkService.createCarPark("Mesto","Hasicska", 3);
        //carParkService.getCarPark(301L);
        //System.out.println(carParkService.getCarPark("Sobl"));
        //System.out.println(carParkService.getCarParks());
        //carParkService.deleteCarPark(6651L);
//        carParkService.createCarParkFloor(11001L,"Prve");
//        carParkService.createCarParkFloor(11001L,"Tretie");
//        carParkService.createCarParkFloor(11001L,"Druhe");
//        carParkService.createCarParkFloor(11001L,"Xte");
        //System.out.println(carParkService.getCarParkFloor(9451L,"Tretie"));
        //System.out.println(carParkService.getCarParkFloors(9451L));
        //System.out.println(carParkService.deleteCarParkFloor(9451L,"Xte"));

//        System.out.println(carParkService.createParkingSpot(9451L,"Prve","X5"));
//        System.out.println(carParkService.createParkingSpot(9451L,"Prve","X6"));
//        System.out.println(carParkService.createParkingSpot(9451L,"Prve","X7"));
//        System.out.println(carParkService.createParkingSpot(9451L,"Prve","X8"));
        //System.out.println(carParkService.getParkingSpot(9501L));
        //System.out.println(carParkService.getParkingSpots(9451L,"Prve"));
        //System.out.println(carParkService.getParkingSpots(9451L));

        //System.out.println(carParkService.getMyReservations(7652L));
        //System.out.println(carParkService.deleteUser(9351L));
        //System.out.println(carParkService.getReservations(9401L, Date.valueOf(LocalDate.now())));
        //System.out.println(carParkService.endReservation(10051L));
        //System.out.println(carParkService.deleteParkingSpot(8701L));
        //System.out.println(carParkService.getAvailableParkingSpots("Mesto"));
        //System.out.println(carParkService.getOccupiedParkingSpots("Mesto"));
        //System.out.println(carParkService.createReservation(11102L,11651L));

        //System.out.println(carParkService.createCarType("Benzin"));
        //System.out.println(carParkService.getCarTypes());
        //System.out.println(carParkService.deleteCarType(10901L));
        //System.out.println(carParkService.getCarType("Elektro"));
        //System.out.println(carParkService.createCar(9401L,"Skoda","Octavia","Modra","TN123AB",10851L));
        //System.out.println(carParkService.createParkingSpot(11001L,"Prve","X5",10851L));


        /**
         * OSETRIT VSTUPY a KRAJNE PRIPADY
         * */


        //System.out.println(carParkService.deleteCar(6551L));
        //System.out.println(carParkService.createUser("Mario", "Mikolasek", "mario.m@gmail.com"));
//        System.out.println(carParkService.createCar(9401L,"Skoda","Octavia","Modra","TN432AB"));
//        System.out.println(carParkService.createCar(9401L,"BMW","neviem","zlta","TN421AB"));
//        System.out.println(carParkService.createCar(9401L,"AUDI","netusim","biely","TN472AB"));
        //System.out.println(carParkService.getCar(7602L));
        //System.out.println(carParkService.getCar("TN432AB"));
        // System.out.println(carParkService.getUser(6251L));
        //System.out.println(carParkService.getUser("mario.m@gmail.com"));
        //System.out.println(carParkService.getUsers());
        // System.out.println(carParkService.deleteUser(7602L));
        //System.out.println(carParkService.getCars(6501L));

        //System.out.println(carParkService.getCarParkFloors(5101L));

        //System.out.println(carParkService.getCarParks());

        //System.out.println(carParkService.deleteCarParkFloor(5101L,"Tretie"));

        //carParkService.createCar(1L,"Skoda","Fabia","modra","TN321AT");


//        CAR_PARK_FLOOR car_park_floor= new CAR_PARK_FLOOR();
//        car_park_floor.setFloorIdentifier("Prve");
//        persist(car_park_floor);

//        Statement statement = con.createStatement();
//        statement.executeUpdate("CREATE TABLE CAR_PARK_FLOOR" +       //vytvorenie databazy
//                "(idPoschodia INTEGER not NULL," +
//                "floorIdentifier VARCHAR(255)," +
//                "PRIMARY KEY (idPoschodia))");
//        statement.close();


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

}
