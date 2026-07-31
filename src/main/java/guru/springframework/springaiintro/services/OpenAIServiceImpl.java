//package guru.springframework.springaiintro.services;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import guru.springframework.springaiintro.model.Answer;
//import guru.springframework.springaiintro.model.Capital;
//import guru.springframework.springaiintro.model.CapitalRequest;
//import guru.springframework.springaiintro.model.CapitalResposeRecord;
//import guru.springframework.springaiintro.model.GetCapitalRequest;
//import guru.springframework.springaiintro.model.Question;
////import org.springframework.ai.chat.model.ChatModel;
////import org.springframework.ai.chat.model.ChatResponse;
////import org.springframework.ai.chat.prompt.Prompt;
////import org.springframework.ai.chat.prompt.PromptTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ResourceUtils;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.Map;
//
///**
// * Created by jt, Spring Framework Guru.
// */
//@Service
//public class OpenAIServiceImpl implements OpenAIService {
//
//    private final ChatLanguageModel chatModel;
//
//    public OpenAIServiceImpl(ChatLanguageModel chatModel) {
//        this.chatModel = chatModel;
//    }
//
//    @Value("classpath:templates/get-capital-prompt.st")
//    private Resource getCapitalPrompt;
//
//    @Value("classpath:templates/get-capital-with-info.st")
//    private Resource getCapitalPromptWithInfo;
//
//    @Value("classpath:templates/get-capital-prompt-json.st")
//    private Resource getCapitalPromptJson;
//
//    @Value("classpath:templates/get-capital-prompt-json-bean.st")
//    private Resource getCapitalPromptJsonBean;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Override
//    public CapitalResposeRecord getCapitalInJsonBean    (CapitalRequest capitalRequest) throws JsonProcessingException {
//        //There is no beanConverter method available for local ollama - like openAI does.
//        return new  CapitalResposeRecord("");
//    }
//
//    @Override
//    public Answer getCapitalJsonFormat(Capital capital) throws IOException {
//
//        File file = ResourceUtils.getFile(getCapitalPromptJson.getURI());
//        String templateText = Files.readString(file.toPath());
//        PromptTemplate promptTemplate = PromptTemplate.from(templateText);
//        Prompt prompt = promptTemplate.apply(Map.of("stateOrCountry", capital.getStateOrCountry()));
//
//        ChatResponse chatResponse = chatModel.chat(prompt.toUserMessage());
//        String jsonString = "";
//        try {
//            JsonNode jsonNode = objectMapper.readTree(chatResponse.aiMessage().text());
//            if(!jsonNode.isNull())
//                jsonString = jsonNode.get("answer").toString();
//        } catch (JsonProcessingException e){
//            throw new RuntimeException();
//        }
//        return new Answer(jsonString);
//    }
//
//    @Override
//    public Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest) throws IOException {
////        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPromptWithInfo);
//        File file = ResourceUtils.getFile(getCapitalPromptWithInfo.getURL());
//        String templateText = Files.readString(file.toPath());
//        PromptTemplate promptTemplate = PromptTemplate.from(templateText);
////        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
//        Prompt prompt = promptTemplate.apply(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
//
////        System.out.println("sevice...capitalinfo");
////        ChatResponse response = chatModel.call(prompt);
//        ChatResponse chatResponse = chatModel.chat(prompt.toUserMessage());
////        return new Answer(response.getResult().getOutput().getText());
//        return new Answer(chatResponse.aiMessage().text());
//    }
//
//    @Override
//    public Answer getCapital(GetCapitalRequest getCapitalRequest) throws IOException {
////        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
//        File file = ResourceUtils.getFile("classpath:templates/get-capital-prompt.st");
//
//        // 2. Read the raw text template from the file
//        String templateText = Files.readString(file.toPath());
//
//        // 3. Initialize the LangChain4j template
//        PromptTemplate promptTemplate = PromptTemplate.from(templateText);
////        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
//        Prompt prompt = promptTemplate.apply(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
////        ChatResponse response = chatModel.call(prompt);
//        ChatResponse chatResponse = chatModel.chat(prompt.toUserMessage());
////        return new Answer(response.getResult().getOutput().getText());
//        return new Answer(chatResponse.aiMessage().text());
//    }
//
//    @Override
//    public Answer getAnswer(Question question) {
//        System.out.println("I was called");
//
//        //PromptTemplate promptTemplate = new PromptTemplate(question.question());
//        PromptTemplate promptTemplate = new PromptTemplate(question.question());
//        //Prompt prompt = promptTemplate.create();
//        Prompt prompt = promptTemplate.apply(Map.of());
//        //ChatResponse response = chatModel.call(prompt);
//        ChatResponse response = chatModel.chat(prompt.toUserMessage());
//
//        //return new Answer(response.getResult().getOutput().getText());
//        return new Answer(response.aiMessage().text());
//    }
//
//    @Override
//    public String getAnswer(String question) {
//        PromptTemplate promptTemplate = new PromptTemplate(question);
//        //Prompt prompt = promptTemplate.create();
//        Prompt prompt = promptTemplate.apply(Map.of());
//        //ChatResponse response = chatModel.call(prompt);
//        ChatResponse chatResponse = chatModel.chat(prompt.toUserMessage());
//
//        //return response.getResult().getOutput().getText();
//        return chatResponse.aiMessage().text();
//    }
//}
