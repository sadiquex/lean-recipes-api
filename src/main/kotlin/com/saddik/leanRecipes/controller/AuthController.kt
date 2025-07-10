package com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller

import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.AuthResponse
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.RegisterRequest
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.ApiResponseDto
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.UserDto
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.repository.UserRepository
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.service.IAuthService
import com.saddik.leanRecipes.controller.RecipeController
import com.saddik.leanRecipes.controller.dto.auth.LoginRequestDto
import com.saddik.leanRecipes.controller.dto.auth.LoginResponseDto
import com.saddik.leanRecipes.utils.log.BaseLog
import com.saddik.leanRecipes.utils.log.LogUtil
import com.saddik.leanRecipes.utils.log.OperationLevel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = ["http://localhost:5173"])
class AuthController(
    val authService: IAuthService
) {
    val logUtil = LogUtil(
        OperationLevel.CONTROLLER, RecipeController::class.java
    )

    val baseLog = BaseLog()

    @PostMapping("/register")
    fun createUser(@RequestBody payload: UserDto): ResponseEntity<ApiResponseDto<UserDto>> {
        val createUserResponse = authService.createUser(payload)

        baseLog.message = "User created with response $createUserResponse"
        logUtil.log(baseLog)

        val code = if (createUserResponse != null) 201 else 400
        val message = if (createUserResponse != null) "User created successfully" else "Failed to create user"

        val apiResponse = ApiResponseDto(
            message = message,
            code = code,
            body = createUserResponse
        )
        return ResponseEntity.status(code).body(apiResponse)

    }

    @PostMapping("/login")
//    fun loginUser(@RequestBody payload:LoginRequestDto):ResponseEntity<ApiResponseDto<LoginResponseDto>>{
    fun loginUser(@RequestBody payload: LoginRequestDto): ResponseEntity<Any> {
        val loginUserResponse = authService.loginUser(payload)

        baseLog.message = "User login with response $loginUserResponse"
        logUtil.log(baseLog)

        val code = if (loginUserResponse != null) 201 else 400
        val message = if (loginUserResponse != null) "User login successfully" else "Failed to create user"

        val apiResponse = ApiResponseDto(
            message = message,
            code = code,
            body = loginUserResponse
        )
        return ResponseEntity.status(code).body(apiResponse)
    }
}