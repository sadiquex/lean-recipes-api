package com.saddik.leanRecipes.config.com.saddik.leanRecipes.service.impl

import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.ApiResponseDto
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.RegisterRequest
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.UserDto
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.repository.UserRepository
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.repository.dao.IAuthDao
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.service.IAuthService
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.utils.JwtUtil
import com.saddik.leanRecipes.controller.dto.auth.LoginRequestDto
import com.saddik.leanRecipes.controller.dto.auth.LoginResponseDto
import com.saddik.leanRecipes.exceptions.ResourceAlreadyExistsException
import com.saddik.leanRecipes.exceptions.ResourceNotFoundException
import com.saddik.leanRecipes.utils.log.BaseLog
import com.saddik.leanRecipes.utils.log.LogUtil
import com.saddik.leanRecipes.utils.log.OperationLevel
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

//import com.saddik.leanRecipes.security.JwtUtil
//import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

@Service
class AuthServiceImpl(
    val authDao: IAuthDao, private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil,
//    private val authenticationManager: AuthenticationManager

) : IAuthService {
    private val logUtil = LogUtil(OperationLevel.REPOSITORY, this::class.java)
    private val baseLog = BaseLog()

    override fun createUser(dto: UserDto): UserDto {
        baseLog.message = "Creating user service started"

        return try {

            val createdUser = authDao.createUser(dto)

            baseLog.message = "User successfully created"

            createdUser


        } catch (ex: ResourceAlreadyExistsException) {
            baseLog.message = ex.message
            logUtil.logE(baseLog, ex)
            throw ex
        } catch (ex: Exception) {
            baseLog.message = "Failed to create user: ${ex.message}"
            logUtil.logE(baseLog, ex)
            throw RuntimeException("Unable to create user at this time. Please try again later")
        }


    }

    //    TODO: remove any after
    override fun loginUser(dto: LoginRequestDto): ResponseEntity<Any> {
        try {
//            val auth = UsernamePasswordAuthenticationToken(dto.email, dto.password)
//            authenticationManager.authenticate(auth)

            val user = userRepository.findByEmail(dto.email)
                ?: throw ResourceNotFoundException("User not found with email ${dto.email}")


            if (!user.comparePassword(dto.password)) {
                val apiResponse = ApiResponseDto(
                    message = "Invalid credentials!",
                    code = 404,
                    body = user
                )
//                return ResponseEntity.badRequest().body(Message("invalid password!"))
                return ResponseEntity.status(404).body(apiResponse)
            }

            val token = jwtUtil.generateToken(user.email)

            baseLog.message = "Login service completed for ${dto.email}"
            logUtil.log(baseLog)

            val apiResponse = LoginResponseDto(
                token = token,
                userId = user.id!!,
                email = user.email
            )

            return ResponseEntity.status(200).body(apiResponse)


        } catch (ex: Exception) {
            baseLog.message = "Login failed for ${dto.email}: ${ex.message}"
            logUtil.logE(baseLog, ex)
            throw RuntimeException("Login failed: Invalid credentials or user not found")
        }
    }


}