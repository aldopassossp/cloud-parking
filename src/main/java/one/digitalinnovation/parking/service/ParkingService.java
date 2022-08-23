package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.service.exception.ParkingNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap<>();

    @GetMapping
    public List<Parking> findAll(){
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if(parking == null){
            throw new ParkingNotFoundException(id);
        }
        return parkingMap.get(id);
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;
    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }

    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parkingMap.replace(id, parking);
        return parking;
    }

    public Parking checkOut(String id) {
        return null;
    }
}
