package com.assesment.campaigns.service;

import com.assesment.campaigns.domain.User;
import com.assesment.campaigns.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        this.userService = new UserService(userRepository);
    }

    @Test
    public void getUser_returnUserCredentials() throws Exception {
        User user = new User("100000001L", "test", "1234abc");

        given(userRepository.findByUsernameAndPassword( "test", "1234abc")).willReturn(user);
        User fetchedUser = userService.getUser(user);

        assertThat(fetchedUser.getId()).isEqualTo("100000001L");
        assertThat(fetchedUser.getUsername()).isEqualTo("test");
        assertThat(fetchedUser.getPassword()).isEqualTo("1234abc");
    }

    @Test
    public void saveUser_returnUserCredentials() throws Exception {
        User user = new User("100000001L", "test", "1234abc");

        given(userRepository.save(user)).willReturn(user);
        User savedUser = userService.saveUser(user);

        assertThat(savedUser.getId()).isEqualTo("100000001L");
        assertThat(savedUser.getUsername()).isEqualTo("test");
        assertThat(savedUser.getPassword()).isEqualTo("1234abc");
    }
}
