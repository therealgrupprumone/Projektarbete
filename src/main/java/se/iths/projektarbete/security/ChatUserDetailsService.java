package se.iths.projektarbete.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.iths.projektarbete.entity.UserEntity;
import se.iths.projektarbete.repo.UserRepo;

@Service
public class ChatUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public ChatUserDetailsService(UserRepo userRepo) {
        super();
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepo.findByUsername(username);

        if (userEntity == null)
            throw new UsernameNotFoundException("Can't find user with username: " + username);
        return new ChatUserPrincipal(userEntity);

    }
}
