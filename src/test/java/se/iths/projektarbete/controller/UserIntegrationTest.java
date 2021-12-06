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
    void callingURLForOneUserWithValidIdForExistingDirectorAndReturnRequestedUserAsJson() throws Exception {

        //given
        UserEntity userEntity = new UserEntity();
        RoleEntity roleEntity = new RoleEntity("admin");
        roleEntity.setId(1L);
        userEntity.setId(1L);
        userEntity.setUsername("Jannis");
        userEntity.addRole(roleEntity);

        System.out.println(asJsonString(roleEntity));

        Optional<UserEntity> optionalUserEntity = Optional.of(userEntity);

        given(userService.findById(optionalUserEntity.get().getId())).willReturn(optionalUserEntity);

        //expect
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/users/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(userEntity)));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



//    @Test
//    void callingURLForOneUserWithInvalidIdAndReturn404Exception() throws Exception {
//
//        when(userService.findById(1L))
//                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        mockMvc.perform(
//                        MockMvcRequestBuilders.get("/users/1")
//                                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//

}