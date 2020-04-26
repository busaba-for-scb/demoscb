package com.example.scb.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class StringServiceTest {

    @InjectMocks
    StringService stringService;

    @Test
    public void testDecodeBase64String_Success() {
        String actualString = stringService.decodeBase64String("QSBIRU4gIEhBUyAgTUFOWSAgIENISUNLUw==");
        assertEquals("A HEN  HAS  MANY   CHICKS" ,actualString);

        actualString = stringService.decodeBase64String("VEhFIFdPUkxE");
        assertEquals("THE WORLD" ,actualString);
    }

    @Test
    public void testDecodeBase64String_Error_CannotDecode() {
        assertThrows(IllegalArgumentException.class, () -> {
            stringService.decodeBase64String("ajhsglkajdf");
        });
    }

    @Test
    public void testIsValidateString() {
        assertTrue(stringService.isValidateString("THE WORLD"));
        assertTrue(stringService.isValidateString("A HEN  HAS  MANY   CHICKS"));
        assertTrue(stringService.isValidateString("A"));
        assertTrue(stringService.isValidateString("AAAAAAAAAA"));
        assertTrue(stringService.isValidateString("A "));
        assertTrue(stringService.isValidateString("A A"));
        assertTrue(stringService.isValidateString("A A     "));

        assertFalse(stringService.isValidateString(""));
        assertFalse(stringService.isValidateString(" "));
        assertFalse(stringService.isValidateString("สวัสดี"));
        assertFalse(stringService.isValidateString("skcihc3ynam2sah2neh1a"));
    }

    @Test
    public void testResultTheString() {
        assertEquals("the1world", stringService.resultTheString("THE WORLD").toString());
        assertEquals("a1hen2has2many3chicks", stringService.resultTheString("A HEN  HAS  MANY   CHICKS").toString());
        assertEquals("4a1hen2has2many3chicks5", stringService.resultTheString("    A HEN  HAS  MANY   CHICKS     ").toString());
        assertEquals("a1hen2has2many3chicks5", stringService.resultTheString("A HEN  HAS  MANY   CHICKS     ").toString());
        assertEquals("4a1hen2has2many3chicks", stringService.resultTheString("    A HEN  HAS  MANY   CHICKS").toString());
        assertEquals("t1es2t", stringService.resultTheString("t es  t").toString());
        assertEquals("a2aa6a", stringService.resultTheString("a  aa      a").toString());

        assertEquals("a", stringService.resultTheString("A").toString());
    }

    @Test
    public void testReverseString_Success() {
        assertEquals("dlrow1eht", stringService.reverseString("the1world"));
        assertEquals("skcihc3ynam2sah2neh1a", stringService.reverseString("a1hen2has2many3chicks"));
    }

    @Test
    public void testEncodeBase64String_Success() {
        stringService.encodeBase64String("the1world");
        stringService.encodeBase64String("a1hen2has2many3chicks");
    }

}
