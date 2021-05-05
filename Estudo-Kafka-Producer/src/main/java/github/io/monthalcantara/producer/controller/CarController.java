package github.io.monthalcantara.producer.controller;

import github.io.monthalcantara.producer.dto.CarDto;
import github.io.monthalcantara.producer.producer.CarProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/cars")
public class CarController {

    private final CarProducer carProducer;

    public CarController(CarProducer carProducer) {
        this.carProducer = carProducer;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CarDto carDto){
        CarDto dto = CarDto.builder().id(UUID.randomUUID()).color(carDto.getColor()).model(carDto.getModel()).build();
        carProducer.send(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }


}
