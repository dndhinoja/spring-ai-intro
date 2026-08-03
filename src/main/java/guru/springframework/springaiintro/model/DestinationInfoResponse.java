package guru.springframework.springaiintro.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record DestinationInfoResponse(
            @JsonPropertyDescription("This is city name") String destinationName,
            @JsonPropertyDescription("The population of city") Integer population,
            @JsonPropertyDescription("The region the city is located in") String region,
            @JsonPropertyDescription("The best hotel for staying") String hotel)
            {
}
