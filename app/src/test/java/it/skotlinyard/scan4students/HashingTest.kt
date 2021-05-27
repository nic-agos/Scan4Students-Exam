package it.skotlinyard.scan4students

import it.skotlinyard.scan4students.utils.Hashing
import org.junit.Assert
import org.junit.Test

class HashingTest {
    @Test
    fun testMd5() {
        val hash = Hashing()
        val exp = "9f96f4cdcbd6ed81382d0612d7eb2ef3".toUpperCase()
        val act = hash.md5("C:\\OneDriveTemp")
        System.out.println(act)
        Assert.assertEquals(exp, act)
    }
}