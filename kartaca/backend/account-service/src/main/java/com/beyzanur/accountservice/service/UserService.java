package com.beyzanur.accountservice.service;


import com.beyzanur.accountservice.VO.Calendar;
import com.beyzanur.accountservice.VO.ResponseTemplateVO;
import com.beyzanur.accountservice.exception.UserNotFoundException;
import com.beyzanur.accountservice.model.User;
import com.beyzanur.accountservice.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return MyUserDetails.buildUser(user);
    }


    public User createNewUser(User user) {
        return userRepository.save(user);
    }


    public User findByUsername(String username) {
        Objects.requireNonNull(username, "username cannot be null");
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }


    public void deleteUsers(Long userId) {
        userRepository.deleteById(userId);
    }


    public ResponseTemplateVO getUserWithCalendar(Long userId) {

        ResponseTemplateVO vo = new ResponseTemplateVO();
        Optional<User> user = userRepository.findById(userId);

        Calendar calendar =
                restTemplate.getForObject("http://calendar-service/calendars/" + user.get().getId()
                        ,Calendar.class);

        vo.setUser(user);
        vo.setCalendar(calendar);

        return  vo;
    }
}
