package com.saddik.leanRecipes.config.com.saddik.leanRecipes.service.impl

import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.LoginRequest
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.RegisterRequest
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.UserDto
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.repository.UserRepository
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.repository.dao.IAuthDao
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.service.IAuthService
import com.saddik.leanRecipes.exceptions.ResourceAlreadyExistsException
import com.saddik.leanRecipes.utils.log.BaseLog
import com.saddik.leanRecipes.utils.log.LogUtil
import com.saddik.leanRecipes.utils.log.OperationLevel
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    val authDao: IAuthDao, val userRepository: UserRepository
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

    override fun loginUser(dto: LoginRequest): LoginRequest {
        TODO("Not yet implemented")
    }


}