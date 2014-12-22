package cz.uhk.restaurace.service;

import cz.uhk.restaurace.model.TempCustomerInfo;
import org.springframework.stereotype.Service;

/**
 * Created by dann on 22.12.2014.
 */
public interface TempCustomerInfoService {

    /**
     * Add temporary additional info about order of unregistered user into db
     * @param info
     */
    void addTempCustomerInfo(TempCustomerInfo info);
}
