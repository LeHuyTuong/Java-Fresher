package sum25.SE196853.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sum25.SE196853.entity.SonyAccounts;
import sum25.SE196853.repository.SonyAccountsRepository;
import sum25.SE196853.services.SonyAccountsService;

import java.util.List;

@Service
public class SonyAccountsServiceImpl implements SonyAccountsService {

    @Autowired
    private SonyAccountsRepository repository;

    @Override
    public List<SonyAccounts> findAll() {
        return repository.findAll();
    }

    @Override
    public SonyAccounts create(SonyAccounts ss) {
        return repository.save(ss);
    }

    @Override
    public void update(int id, SonyAccounts ss) {
        SonyAccounts accounts = repository.findByAccountID(id);
        if (accounts != null) {
            accounts.setAccountID(ss.getAccountID());
            accounts.setPassword(ss.getPassword());
            accounts.setPhone(ss.getPhone());
            accounts.setRoleId(ss.getRoleId());
            repository.save(accounts);
        }
    }

    @Override
    public void delete(int id) {
        SonyAccounts accounts = repository.findByAccountID(id);
        if (accounts != null) {
            repository.delete(accounts);
        }
    }

    @Override
    public SonyAccounts getUser(String phone, String password) {
        return repository.findByPhoneAndPassword(phone, password);
    }


}
