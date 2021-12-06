//package se.iths.projektarbete.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import se.iths.projektarbete.dto.User;
//import se.iths.projektarbete.entity.RoleEntity;
//import se.iths.projektarbete.service.UserService;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class UserIntegrationTest {
//
//    @MockBean
//    UserService userService;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    private MockMvc mockMvc;
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @BeforeAll
//    public void setup() {
//
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
//
//    }
//
//    @Test
//    @Disabled
//    void callingURLForOneUserWithValidIdForExistingUserAndReturnRequestedUserAsJson() throws Exception {
//
//        //given
//        RoleEntity roleEntity = new RoleEntity("admin");
//        roleEntity.setId(1L);
//
//        User user = new User();
//        user.setUsername("Jannis");
//        user.setId(2L);
//        user.setRoles(Set.of(roleEntity));
//
//        given(userService.getUser(user.getId())).willReturn(user);
//
//        //expect
//        mockMvc.perform(
//                        MockMvcRequestBuilders.get("/users/2")
//                                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(asJsonString(user)));
//    }
//
//    @Test
//    @Disabled
//    void callingURLForAllUsersAndReturnRequestedUserAsJson() throws Exception {
//
//        //given
//        RoleEntity roleEntity = new RoleEntity("admin");
//        roleEntity.setId(1L);
//
//        User user = new User();
//        user.setUsername("Jannis");
//        user.setId(2L);
//        user.setRoles(Set.of(roleEntity));
//
//        User user1 = new User();
//        user1.setUsername("Albert");
//        user1.setId(1L);
//        user1.setRoles(Set.of(roleEntity));
//
//        List<User> users = new ArrayList<>();
//        users.add(user);
//        users.add(user1);
//
//
//        given(userService.getAllUsers()).willReturn(users);
//
//        //expect
//        mockMvc.perform(
//                        MockMvcRequestBuilders.get("/users")
//                                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(asJsonString(users)));
//    }
//
//
//}