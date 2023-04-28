package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.QuestionComponent;
import fr.uga.l3miage.example.component.ResponseComponent;
import fr.uga.l3miage.example.exception.technical.QuestionAlreadyExistsException;
import fr.uga.l3miage.example.exception.technical.QuestionNotFoundException;
import fr.uga.l3miage.example.exception.technical.ResponseAlreadyExistsException;
import fr.uga.l3miage.example.exception.technical.ResponseEntityNotFoundException;
import fr.uga.l3miage.example.mapper.QuestionMapper;
import fr.uga.l3miage.example.mapper.ResponseMapper;
import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.models.Response;
import fr.uga.l3miage.example.repository.QuestionRepository;
import fr.uga.l3miage.example.repository.ResponseRepository;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.request.CreateResponseRequest;
import fr.uga.l3miage.example.response.QuestionDTO;
import fr.uga.l3miage.example.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceResponse {
    private static final String ERROR_DETECTED = "Une erreur lors de la création de l'entité Response à été détecté.";
    private final ResponseMapper responseMapper;
    private final ResponseComponent responseComponent ;

    public ResponseDTO getResponseById(final Long id) throws ResponseEntityNotFoundException {
        try {
            return responseMapper.entityToDTO(responseComponent.getResponse(id));
        } catch (ResponseEntityNotFoundException ex) {
            throw new ResponseEntityNotFoundException("Impossible de charger l'entité."+id);
        }
    }

    public void createResponse(final CreateResponseRequest request) throws ResponseAlreadyExistsException {
        Response newResponse = responseMapper.dtoToEntity(request);
        try {
            responseComponent.createResponse(newResponse);
        }catch (ResponseAlreadyExistsException ex) {
            throw new ResponseAlreadyExistsException("la question "+newResponse.getId() +" existe deja");
        }
    }

    public void deleteResponse(final Long id) throws ResponseEntityNotFoundException {
        if (id != null) {
            responseComponent.deleteResponse(id);
        }else {
            throw  new ResponseEntityNotFoundException("ce "+ id+" ne correspond a aucune reponse  existant");
        }

    }

    public void updateResposne(final Long id , final String label) throws ResponseEntityNotFoundException{
        if(id != null){
            responseComponent.updateResponse(id,label);
        }else{
            throw new ResponseEntityNotFoundException("cet id + id+ ne correspond à aucune reponses existante");
        }
    }

    public void updateResponseValid(final Long id , final boolean valid) throws ResponseEntityNotFoundException{
        if(id != null){
            responseComponent.updateResponseValid(id,valid);
        }else{
            throw new ResponseEntityNotFoundException("cet id + id+ ne correspond à aucune reponses existante");
        }
    }
}