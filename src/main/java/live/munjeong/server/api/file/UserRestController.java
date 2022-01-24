package live.munjeong.server.api.file;

import live.munjeong.server.api.common.Result;
import live.munjeong.server.api.common.ResultCode;
import live.munjeong.server.api.req.CreateUserRequest;
import live.munjeong.server.api.req.LoginUserRequest;
import live.munjeong.server.api.res.CreateUserResponse;
import live.munjeong.server.app.domain.User;
import live.munjeong.server.app.exception.NonSearchUserException;
import live.munjeong.server.app.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static live.munjeong.server.api.common.ResultCode.SUCCESS;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserService userService;

    @PostMapping("/join")
    public Result<CreateUserResponse> join(@Valid CreateUserRequest createUserRequest) throws NonSearchUserException {
        log.debug("createUser req [{}]", createUserRequest);
        Long saveUserId = userService.saveUser(createUserRequest.createUser());

        User user = userService.findUser(saveUserId);
        CreateUserResponse createUserResponse = new CreateUserResponse(user);
        return new Result<>(createUserResponse);
    }

    @PostMapping
    public Result<ResultCode> login(@Valid LoginUserRequest loginUserRequest) throws NonSearchUserException {
        log.debug("loginUser req [{}]", loginUserRequest);
        userService.loginUser(loginUserRequest.getEmail(), loginUserRequest.getPw());
        return new Result<>(SUCCESS);
    }
}
