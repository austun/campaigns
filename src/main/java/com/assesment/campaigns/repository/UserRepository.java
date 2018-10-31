package com.assesment.campaigns.repository;

import com.assesment.campaigns.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsernameAndPassword(final String username, final String password);
}
