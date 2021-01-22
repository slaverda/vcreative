package com.vcreaative;

import com.vcreative.core.api.service.impl.LookupService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LookupControllerTest {
    @Mock
    private LookupService lookupService;

    @Test
    @DisplayName("Should return hello world")
    public void returnHelloWorld() {
        var expectedResponse = "Hello World !!!";
        when(lookupService.test()).thenReturn("Hello World !!!");
        var response = lookupService.test();

        assertEquals(response, expectedResponse);
    }
}
