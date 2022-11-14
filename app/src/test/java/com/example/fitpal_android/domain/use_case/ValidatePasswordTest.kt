package com.example.fitpal_android.domain.use_case

import org.junit.Assert
import org.junit.Test

internal class ValidatePasswordTest{
    @Test
    fun passwordTest(){
        val validatePassword= ValidatePassword()
        val result= validatePassword.execute("12345678As")
        Assert.assertEquals(true, result.successful)
    }
}