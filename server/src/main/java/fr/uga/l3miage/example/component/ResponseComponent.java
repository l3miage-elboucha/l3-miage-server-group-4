package fr.uga.l3miage.example.component;


import fr.uga.l3miage.example.exception.technical.QuestionNotFoundException;
import fr.uga.l3miage.example.mapper.ResponseMapper;
import fr.uga.l3miage.example.models.Response;
import fr.uga.l3miage.example.repository.ResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResponseComponent {
    private final ResponseRepository responseRepository;
    private final ResponseMapper responseMapper;

    public Response getResponse(final Long id ) throws QuestionNotFoundException {
        return responseRepository.getResponseById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Aucune question ne possede" + id ));
    }

    public void createResponse(final Response response) throws QuestionNotFoundException {
        if (responseRepository.getResponseById(response.getId()).isPresent()) {
            throw new QuestionNotFoundException("La question suivant "+ response.getId()+"deja existante.");
        } else {
            responseRepository.save(response);
        }
    }

    public void deleteResponse(final Long id) throws QuestionNotFoundException {
        Long deleted = responseRepository.deleteResponseById(id);
        if (deleted == 0)
            throw new QuestionNotFoundException("Aucune question ne possede"+ id);

    }

    public void updateResponse(final Long id, final String label){
        if(responseRepository.getResponseById(id).isPresent()){
            Response updatedResponse =  responseRepository.findResponseById(id);
            updatedResponse.setLabel(label);
        }else{
            throw new QuestionNotFoundException("aucune reponse ne possede  cet "+id);
        }
    }

    public void updateResponseValid(final Long id, final Boolean valid){
        if(responseRepository.getResponseById(id).isPresent()){
            Response updatedResponse =  responseRepository.findResponseById(id);
            updatedResponse.setEstValide(valid);
        }else{
            throw new QuestionNotFoundException("aucune reponse ne possede  cet "+id);
        }
    }

}
