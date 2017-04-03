package ae.tutorme.dao;

import ae.tutorme.dto.ActivationDTO;
import ae.tutorme.model.Activation;

/**
 * Created by almehairbi on 2/25/17.
 */
public interface ActivationDAO {

    void saveActivation(Activation activation);
    
    Activation saveActivation(ActivationDTO activation);

    void updateActivation(Activation activation);
    
    void deleteActivation(int id);
    
    Activation getById(int id);
}
