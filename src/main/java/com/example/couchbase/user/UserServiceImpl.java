package com.example.couchbase.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.couchbase.CouchbaseUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CouchbaseUtils couchbaseUtils;

    @Override
    public List<User> findByName(String name) {
	return userRepository.findByName(name);
    }

    @Override
    public User save(@Valid User user) {

	if (user.getId() == null) {
	    user.setId(String.valueOf(couchbaseUtils.getNextId()));
	}

	return userRepository.save(user);
    }

    @Override
    public Iterable<User> findAll() {
	return userRepository.findAll();
    }

    @Override
    public void delete(Iterable<User> users) {
	userRepository.deleteAll(users);
    }

    @Override
    public Iterable<User> saveAll(@Valid Iterable<User> users) {
	for (User r : users) {
	    if (r.getId() == null) {
		r.setId(String.valueOf(couchbaseUtils.getNextId()));
	    }
	}
	return userRepository.saveAll(users);
    }

//    @Autowired
//    private BuildingRepository buildingRepository;
//
//    @Override
//    public List<Building> findByCompanyId(String companyId) {
//        return buildingRepository.findByCompanyId(companyId);
//    }
//
//    public List<Building> findByCompanyIdAndNameLike(String companyId, String name, int page) {
//        return buildingRepository.findByCompanyIdAndNameLikeOrderByName(companyId, name, new PageRequest(page, 20))
//                .getContent();
//    }
//
//    @Override
//    public Building findByCompanyAndAreaId(String companyId, String areaId) {
//        return buildingRepository.findByCompanyAndAreaId(companyId, areaId);
//    }
//
//    @Override
//    public List<Building> findByPhoneNumber(String telephoneNumber) {
//        return buildingRepository.findByPhoneNumber(telephoneNumber);
//    }
//
//    @Override
//    public Building findById(String buildingId) {
//        return buildingRepository.findOne(buildingId);
//    }
//
//    @Override
//    public Building save(@Valid Building building) {
//        return buildingRepository.save(building);
//    }
//
//    @Override
//    public Long countBuildings(String companyId) {
//        return buildingRepository.countBuildings(companyId);
//    }
}
