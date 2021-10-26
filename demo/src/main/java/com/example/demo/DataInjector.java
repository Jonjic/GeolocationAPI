package com.example.demo;

import com.example.demo.action.Action;
import com.example.demo.action.ActionRepository;
import com.example.demo.bus.Bus;
import com.example.demo.bus.BusRepository;
import com.example.demo.business.GpsDataService;
import com.example.demo.station.Station;
import com.example.demo.station.StationRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Component
public class DataInjector implements CommandLineRunner{
    ActionRepository actionRepository;
    BusRepository busRepository;
    UserRepository userRepository;
    StationRepository stationRepository;
    GpsDataService gpsDataService;

    @Autowired
    public DataInjector(ActionRepository actionRepository, BusRepository busRepository, UserRepository userRepository,
                        StationRepository stationRepository, GpsDataService gpsDataService) {
        this.actionRepository = actionRepository;
        this.busRepository = busRepository;
        this.userRepository = userRepository;
        this.stationRepository = stationRepository;
        this.gpsDataService = gpsDataService;
    }

    public void run (String... args) {
        Bus petnaestica = new Bus(
                15,
                "Mercedes-benz",
                "ST9050T"
        );
        Bus trica = new Bus(
                3,
                "Mercedes-benz",
                "ST3145A"
        );

        Bus secondPetnaestica = new Bus(
                15,
                "MAN",
                "ST4554DU"
        );

        busRepository.saveAll(List.of(petnaestica, trica, secondPetnaestica));

        User ante = new User(
                "Ante",
                "Jonjic",
                LocalDate.of(1998, Month.JANUARY, 15),
                "ante.jonjic98@gmail.com,",
                "1234",
                "0993219272");

        User mirko = new User(
                "Mirko",
                "Lovric",
                LocalDate.of(1995, Month.FEBRUARY,15),
                "mirko.lovric@gmail.com,",
                "123",
                "0913244987");

        User luka = new User(
                "Luka",
                "Saric",
                LocalDate.of(1997, Month.JULY,23),
                "luka.saric@gmail.com,",
                "12345",
                "0913874424");

        User andjela = new User(
                "Andjela",
                "Bilic",
                LocalDate.of(1997, Month.JUNE,4),
                "andjela.bilic@gmail.com,",
                "12345",
                "0951774564");

        User petar = new User(
                "Petar",
                "Perkovic",
                LocalDate.of(1996, Month.FEBRUARY,3),
                "petar.perkovic@gmail.com,",
                "12345",
                "0994563242");

        userRepository.saveAll(List.of(ante, mirko, luka, andjela, petar));

        Station station1 = new Station(
                43.509091,
                16.474513,
                "Poljicka cesta - Gradska sigurnost Split"
        );

        Station station2 = new Station(
                43.507924,
                16.472074,
                "Poljicka cesta - Meduza Trstenik"
        );

        Station station3 = new Station(
                43.507698,
                16.470518,
                "Poljicka cesta - Lidl"
        );

        Station station4 = new Station(
                43.506619,
                16.465689,
                "Poljicka cesta - Krstarica"
        );

        Station station5 = new Station(
                43.506569,
                16.463640,
                "Poljicka cesta - Ora"
        );

        Station station6 = new Station(
                43.507220,
                16.464595,
                "Bruna Busica - Pazaric"
        );

        Station station7 = new Station(
                43.510014,
                16.463450,
                "Bruna Busica - Mioc"
        );

        Station station8 = new Station(
                43.512261,
                16.460001,
                "Vukovarska - Trafostanica Plokite"
        );

        stationRepository.saveAll(List.of(station1, station2, station3, station4, station5, station6, station7, station8));

        Action firstAction = new Action(
                43.507742,
                16.470604
        );


        Action secondAction = new Action(
                43.507742,
                16.470604
        );

        Action thirdAction = new Action(
                43.507427,
                16.464431,
                false
        );

        Action fourthAction = new Action(
                43.507527,
                16.464431
        );


        ante.addAction(petnaestica, firstAction);
        mirko.addAction(petnaestica, secondAction);
        ante.addAction(petnaestica, thirdAction);
        luka.addAction(trica, fourthAction);

        Action poljicka1 = new Action(43.509102, 16.474650);

        Action lidl = new Action(43.507686, 16.470511);

        Action pazaric = new Action(43.507282, 16.464569);

        Action mioc = new Action(43.509982, 16.463413, LocalDateTime.now().plusMinutes(2), true);

        Action lokve = new Action(43.512258, 16.460042, LocalDateTime.now().plusMinutes(5), true);

        Action lidlNasuprot = new Action(43.507933, 16.472043);

        Action poljicka2 = new Action(43.509102, 16.474650);

        Action poljicka3 = new Action(43.509102, 16.474650);

        Action poljicka4 = new Action(43.509102, 16.474650);

        Action meduza = new Action(43.507933, 16.472043);

        Action meduza2 = new Action(43.507933, 16.472043);
        //
        Action poljickax = new Action(43.509102, 16.474650, LocalDateTime.now().plusMinutes(27), true);

        Action lidlx = new Action(43.507686, 16.470511, LocalDateTime.now().plusMinutes(29), true);

        Action pazaricx = new Action(43.507282, 16.464569, LocalDateTime.now().plusMinutes(31), true);

        Action miocx = new Action(43.509982, 16.463413, LocalDateTime.now().plusMinutes(33), true);

        Action lokvex = new Action(43.512258, 16.460042, LocalDateTime.now().plusMinutes(38), true);

        Action poljicka5 = new Action (43.509102, 16.474650, LocalDateTime.now().plusMinutes(95), true);



        andjela.addAction(petnaestica, poljicka1);
        andjela.addAction(petnaestica, lidl);
        andjela.addAction(petnaestica, pazaric);
        andjela.addAction(petnaestica, mioc);
        petar.addAction(petnaestica, lokve);
        luka.addAction(secondPetnaestica, lidlNasuprot);
        luka.addAction(petnaestica, poljicka2);
        luka.addAction(petnaestica, poljicka3);
        luka.addAction(petnaestica, poljicka4);
        mirko.addAction(secondPetnaestica, meduza);
        mirko.addAction(secondPetnaestica, meduza2);
        mirko.addAction(petnaestica, poljickax);
        mirko.addAction(petnaestica, lidlx);
        mirko.addAction(petnaestica, pazaricx);
        mirko.addAction(petnaestica, miocx);
        mirko.addAction(petnaestica, lokvex);
        mirko.addAction(petnaestica, poljicka5);

        actionRepository.saveAll(List.of(firstAction, secondAction, thirdAction,
                fourthAction, poljicka1, lidl, pazaric, mioc, lokve, lidlNasuprot, poljicka2, poljicka3, poljicka4, meduza2, meduza, poljickax, lidlx, pazaricx, miocx, lokvex, poljicka5));

        gpsDataService.mapActionToStation(poljicka1);
        gpsDataService.mapActionToStation(pazaric);
        gpsDataService.mapActionToStation(mioc);
        gpsDataService.mapActionToStation(lokve);
        gpsDataService.mapActionToStation(poljicka2);
        gpsDataService.mapActionToStation(poljicka3);
        gpsDataService.mapActionToStation(poljicka4);
        gpsDataService.mapActionToStation(meduza);
        gpsDataService.mapActionToStation(poljickax);
        gpsDataService.mapActionToStation(lidlx);
        gpsDataService.mapActionToStation(pazaricx);
        gpsDataService.mapActionToStation(miocx);
        gpsDataService.mapActionToStation(lokvex);
        gpsDataService.mapActionToStation(poljicka5);

    }
}
