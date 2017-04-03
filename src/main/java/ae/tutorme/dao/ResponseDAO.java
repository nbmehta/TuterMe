package ae.tutorme.dao;

import ae.tutorme.dto.ResponseDTO;
import ae.tutorme.model.Response;

/**
 * Created by almehairbi on 2/25/17.
 */
public interface ResponseDAO  {

    Response saveResponse(Response response);

    void updateResponse(Response response);

    Response getById(int id);
    
    ResponseDTO getResponseDTOById(int id);

    ResponseDTO updateResponse(int id, ResponseDTO response);
    
    void deleteResponse(int id);
}
