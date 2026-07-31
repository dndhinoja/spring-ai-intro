package guru.springframework.springaiintro.model;

public record GetCapitalRequest(String stateOrCountry) {
    public String toString() {
        return "GetCapitalRequest{" + "stateOrCountry='" + stateOrCountry + '\'' + '}';
    }
}
