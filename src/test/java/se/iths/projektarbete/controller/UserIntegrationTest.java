package se.iths.projektarbete.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import se.iths.projektarbete.entity.RoleEntity;
import se.iths.projektarbete.entity.UserEntity;
import se.iths.projektarbete.service.UserService;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserIntegrationTest {

    @MockBean
    UserService userService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeAll
    public void setup() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

    }


    @Test
    void callingURLForOneUserWithValidIdForExistingUserAndReturnRequestedUserAsJson() throws Exception {

        //given
        UserEntity userEntity = new UserEntity();
        RoleEntity roleEntity = new RoleEntity("admin");
        roleEntity.setId(1L);
        userEntity.setId(2L);
        userEntity.setUsername("Jannis");
        userEntity.addRole(roleEntity);

        System.out.println(asJsonString(roleEntity));

        Optional<UserEntity> optionalUserEntity = Optional.of(userEntity);

        given(userService.findById(optionalUserEntity.get().getId())).willReturn(optionalUserEntity);

        //expect
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/users/2")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(userEntity)));
    }

    @Test
    void callingURLForAllUsersAndReturnRequestedUserAsJson() throws Exception {

        //given
        RoleEntity roleEntity = new RoleEntity("admin");
        roleEntity.setId(1L);


        UserEntity userEntity1 = new UserEntity();
        userEntity1.setId(1L);
        userEntity1.setUsername("Jannis");
        userEntity1.addRole(roleEntity);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setId(2L);
        userEntity2.setUsername("albert");
        userEntity2.addRole(roleEntity);

        Iterable<UserEntity> users = List.of(userEntity1, userEntity2);

        System.out.println(asJsonString(users));

        given(userService.findAll()).willReturn(users);

        //expect
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/users")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(users)));
    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}