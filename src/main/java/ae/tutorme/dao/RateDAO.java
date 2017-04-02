package ae.tutorme.dao;

import ae.tutorme.dto.RateDTO;
import ae.tutorme.model.Rate;

/**
 * Created by almehairbi on 2/25/17.
 */
public interface RateDAO {

    void saveRate(Rate rate);
    
    void updateRate(Rate rate);

    Rate getById(int id);
    
    RateDTO getRateDTOById(int id);

    RateDTO updateRate(int id, RateDTO rate);
    
    void deleteRate(int id);

}
