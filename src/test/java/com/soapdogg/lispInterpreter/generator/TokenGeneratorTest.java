package com.soapdogg.lispInterpreter.generator;

import com.soapdogg.lispInterpreter.constants.TokenValueConstants;
import com.soapdogg.lispInterpreter.datamodels.Token;
import com.soapdogg.lispInterpreter.datamodels.TokenKind;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TokenGeneratorTest {

    private String value;

    private TokenGenerator tokenGenerator;

    @BeforeEach
    void setup() {
        value = "value";

        tokenGenerator = TokenGenerator.newInstance();
    }

    @Test
    void generateCloseTokenTest() {
        Token actual = tokenGenerator.generateCloseToken();

        Assertions.assertEquals(TokenKind.CLOSE_TOKEN, actual.getTokenKind());
        Assertions.assertEquals(String.valueOf(TokenValueConstants.CLOSE_PARENTHESES), actual.getValue());
    }

    @Test
    void generateOpenTokenTest() {
        Token actual = tokenGenerator.generateOpenToken();

        Assertions.assertEquals(TokenKind.OPEN_TOKEN, actual.getTokenKind());
        Assertions.assertEquals(String.valueOf(TokenValueConstants.OPEN_PARENTHESES), actual.getValue());
    }

    @Test
    void generateNumericTokenTest() {
        Token actual = tokenGenerator.generateNumericToken(value);

        Assertions.assertEquals(TokenKind.NUMERIC_TOKEN, actual.getTokenKind());
        Assertions.assertEquals(value, actual.getValue());
    }

    @Test
    void generateLiteralTokenTest() {
        Token actual = tokenGenerator.generateLiteralToken(value);

        Assertions.assertEquals(TokenKind.LITERAL_TOKEN, actual.getTokenKind());
        Assertions.assertEquals(value, actual.getValue());
    }
}
