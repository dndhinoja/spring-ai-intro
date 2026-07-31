package guru.springframework.springaiintro.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Objects;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.Capital;
import guru.springframework.springaiintro.model.CapitalRequest;
import guru.springframework.springaiintro.model.CapitalResposeRecord;
import guru.springframework.springaiintro.model.GetCapitalRequest;
import guru.springframework.springaiintro.model.Question;

@Service
public class OllamaServiceImpl implements OpenAIService{
    private final ChatModel chatModel;

    public OllamaServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/get-capital-with-info.st")
    private Resource getCapitalPromptWithInfo;

    @Value("classpath:templates/get-capital-prompt-json.st")
    private Resource getCapitalPromptJson;

    @Value("classpath:templates/get-capital-prompt-json-bean.st")
    private Resource getCapitalPromptJsonBean;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public CapitalResposeRecord getCapitalInJsonBean(CapitalRequest capitalRequest) {
        BeanOutputConverter<CapitalResposeRecord> beanOutputConverter = new BeanOutputConverter<>(CapitalResposeRecord.class);
        String format = beanOutputConverter.getFormat();
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPromptJsonBean);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capitalRequest.getStateOrCountry(),
                    "format", format));
        ChatResponse chatResponse =chatModel.call(prompt);
        return beanOutputConverter.convert(Objects.requireNonNull(chatResponse.getResult().getOutput().getText()));
    }

    @Override
    public Answer getCapitalJsonFormat(Capital capital) {
        PromptTemplate promptTemplate1 = new PromptTemplate(getCapitalPromptJson);
        Prompt prompt1 =promptTemplate1.create(Map.of("stateOrCountry", capital.getStateOrCountry()));
        ChatResponse chatResponse = chatModel.call(prompt1);
        String jsonString = "";
        try {
            JsonNode jsonNode = objectMapper.readTree(chatResponse.getResult().getOutput().getText());
            if(!jsonNode.isNull())
                jsonString = jsonNode.get("answer").toString();
        } catch (JsonProcessingException e){
            throw new RuntimeException();
        }
        return new Answer(jsonString);
    }

    @Override
    public Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPromptWithInfo);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));

        System.out.println("sevice...capitalinfo");
        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getText());
    }

    @Override
    public Answer getCapital(GetCapitalRequest getCapitalRequest) {

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));

        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getText());
    }

    @Override
    public Answer getAnswer(Question question) {
        System.out.println("I was called");
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();

        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getText());
    }

    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();

        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getText();
    }
}
