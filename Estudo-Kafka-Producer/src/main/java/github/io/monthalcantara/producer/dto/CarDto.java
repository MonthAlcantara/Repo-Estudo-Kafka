package github.io.monthalcantara.producer.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CarDto {

    private UUID id;
    private String color;
    private String model;

    public CarDto(UUID id, String color, String model) {
        this.id = id;
        this.color = color;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
