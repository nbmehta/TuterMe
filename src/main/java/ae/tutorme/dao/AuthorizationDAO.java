package ae.tutorme.dao;

import ae.tutorme.dto.AuthorizationDTO;
import ae.tutorme.model.Authorization;

/**
 * Created by almehairbi on 2/25/17.
 */
public interface AuthorizationDAO {
	
	Authorization saveAuthorization(AuthorizationDTO authorization);

    void saveAuthorization(Authorization authorization);

    Authorization getAuthorizationByUserName(String userName);

    Authorization getById(int id);
    
    void updateAuthorization(Authorization auth);
    
    void deleteAuthorization(int id);
}
