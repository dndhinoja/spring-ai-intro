package guru.springframework.springaiintro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CapitalResposeRecord(@JsonProperty("answer") String answer) {
}
