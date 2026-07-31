package guru.springframework.springaiintro.services;

import java.io.IOException;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.Capital;
import guru.springframework.springaiintro.model.GetCapitalRequest;
import guru.springframework.springaiintro.model.Question;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface OpenAIService {

    Answer getCapitalJsonFormat(Capital capital) throws IOException;

    Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest) throws IOException;

    Answer getCapital(GetCapitalRequest getCapitalRequest) throws IOException;

    String getAnswer(String question);

    Answer getAnswer(Question question);
}
