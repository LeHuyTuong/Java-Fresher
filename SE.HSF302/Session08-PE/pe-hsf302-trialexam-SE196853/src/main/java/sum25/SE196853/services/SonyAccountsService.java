package sum25.SE196853.services;

import org.springframework.stereotype.Service;
import sum25.SE196853.entity.SonyAccounts;

import java.util.List;

public interface SonyAccountsService {
    List<SonyAccounts> findAll();
    SonyAccounts create(SonyAccounts ss);
    void update(int id, SonyAccounts ss);
    void delete(int id);

    SonyAccounts getUser(String phone , String password);
}
