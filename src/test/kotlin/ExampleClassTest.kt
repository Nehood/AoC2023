import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ExampleClassTest {

    private val exampleClass = ExampleClass()

    @Test
    fun exampleMethod() {
        val result = exampleClass.exampleMethod()

        assertEquals(1, result)
    }
}