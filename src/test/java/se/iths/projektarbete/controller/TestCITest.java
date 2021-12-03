package se.iths.projektarbete.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCITest {

    TestCI testCI = new TestCI();

    @Test
    void testThatCiWorksWithABasicAddMethod() {
        assertEquals(40, testCI.add(10, 10));
    }
}