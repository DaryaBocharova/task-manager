package ru.bocharova.tm.endpoint;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import ru.bocharova.tm.api.endpoint.ISessionEndpoint;
import ru.bocharova.tm.api.endpoint.IUserEndpoint;
import ru.bocharova.tm.enumerate.Role;
import ru.bocharova.tm.exception.AuthenticationSecurityException;
import ru.bocharova.tm.exception.DataValidateException;
import ru.bocharova.tm.model.dto.SessionDTO;
import ru.bocharova.tm.model.dto.UserDTO;
import ru.bocharova.tm.util.DataGenerator;
import ru.bocharova.tm.util.HashUtil;

import javax.inject.Inject;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(CdiTestRunner.class)
public class UserEndpointTest {

    @Inject
    private IUserEndpoint userEndpoint;

    @Inject
    private ISessionEndpoint sessionEndpoint;

    @Inject
    private DataGenerator dataGenerator;

    @NotNull
    private SessionDTO currentSession;

    @Before
    public void setUp(
    ) throws DataValidateException, AuthenticationSecurityException {
        dataGenerator.generate();
        currentSession = sessionEndpoint.openSession("admin", HashUtil.md5("admin"));
    }

    @After
    public void tearDown(
    ) throws DataValidateException, AuthenticationSecurityException {
        sessionEndpoint.closeSession(currentSession);
        dataGenerator.cleanUp();
        currentSession = null;
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void userCRUID(
    ) throws DataValidateException, AuthenticationSecurityException {
        assertNotNull(currentSession);
        @NotNull final UserDTO user = new UserDTO();
        user.setId(UUID.randomUUID().toString());
        user.setLogin("new_user");
        user.setPassword(HashUtil.md5("new_user"));
        user.setRole(Role.ADMIN);
        userEndpoint.createUser(currentSession, user);//CREATE
        assertEquals(user.getId(), userEndpoint.findUserById(currentSession, user.getId()).getId());//READ
        user.setLogin("Update_login");
        user.setDescription("Update_description");
        userEndpoint.editUserProfile(currentSession, user);//UPDATE
        assertEquals(user.getLogin(), userEndpoint.findUserByLogin(currentSession, "Update_login").getLogin());
        assertEquals(user.getDescription(), userEndpoint.findUserById(currentSession, user.getId()).getDescription());
        userEndpoint.removeOneUser(currentSession, user.getId());//DELETE
        thrown.expect(DataValidateException.class);
        userEndpoint.findUserByLogin(currentSession, "Update_login");
    }
}