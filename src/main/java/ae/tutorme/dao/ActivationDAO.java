package ae.tutorme.dao;

import ae.tutorme.model.Activation;

/**
 * Created by almehairbi on 2/25/17.
 */
public interface ActivationDAO {

    void saveActivation(Activation activation);

    void updateActivation(Activation activation);
}
