package LearningSystem.Service.Serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LearningSystem.Entity.User;
import LearningSystem.Repository.UserRepository;
import LearningSystem.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository urepo;


    
    public User signup(User user) {

        User existingUser = urepo.findByEmail(user.getEmail()).orElse(null);

        if (existingUser != null) {
            return null;
        }

        return urepo.save(user);
    }


    
    public User signin(String email, String password) {

        User user = urepo.findByEmail(email).orElse(null);

        if (user == null) {
            return null;
        }

        if (!user.getPassword().equals(password)) {
            return null;
        }

        return user;
    }

    
    public String logout() {

        return "Logout successful";

    }

}