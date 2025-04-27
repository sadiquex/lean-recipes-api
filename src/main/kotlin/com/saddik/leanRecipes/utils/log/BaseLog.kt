package com.saddik.leanRecipes.utils.log

class BaseLog {
    /**
     * message: indicates the current running operation
     * */
    var message: String? = ""
    /**
     * operationLevel: The position or stage of operation (e.g. controller)
     * */
    var operationLevel: OperationLevel = OperationLevel.OTHERS
    /**
     * entity: The exact class in which the operation occurs
     * */
    var entity: String? = ""
    /**
     * additionalInfo: other information to be added to the log, we pass extra data to this map
     * */
    var additionalInfo: MutableMap<String, Any>? = mutableMapOf()
}