package com.matrix.http

import com.fasterxml.jackson.databind.ObjectMapper
import com.matrix.core.MaximumHistogram
import com.matrix.core.SubMatrix
import com.matrix.core.SubMatrixCalculator
import com.matrix.http.validator.MatrixInputValidator
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.method.HandlerMethod
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod
import spock.lang.Specification

import java.lang.reflect.Method

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ApplicationTest extends Specification {

    private MockMvc mockMvc
    private ObjectMapper objectMapper
    private SubMatrixCalculator calculator = new SubMatrixCalculator(new MaximumHistogram())
    private MatrixInputValidator validator = new MatrixInputValidator()
    Application application = new Application(calculator, validator)

    def setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(application)
                .setHandlerExceptionResolvers(withExceptionControllerAdvice())
                .build()
        objectMapper = new ObjectMapper();
    }

    ExceptionHandlerExceptionResolver withExceptionControllerAdvice() {
        final ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
            @Override
            protected ServletInvocableHandlerMethod getExceptionHandlerMethod(final HandlerMethod handlerMethod, final Exception exception) {
                Method method = new ExceptionHandlerMethodResolver(InvalidMatrixHandlerAdvice.class).resolveMethod(exception)
                if (method != null) {
                    return new ServletInvocableHandlerMethod(new InvalidMatrixHandlerAdvice(), method)
                }
                return super.getExceptionHandlerMethod(handlerMethod, exception)
            }
        }
        exceptionResolver.afterPropertiesSet();
        return exceptionResolver;
    }

    def "should throw bad request for Invalid Request"() {
        given:
        String inputJson = """
        {
            "matrix": [
                    [1,1,1],
                    [1,1,1,1]
            ],
            "rowLength": 4,
            "colLength": 2
        }
        """

        when:
        def resultActions = mockMvc.perform(post("/get-submatrix-of-1")
                    .contentType(APPLICATION_JSON)
                    .content(inputJson))

        then:
        def response = resultActions.andExpect(status().is(HttpStatus.BAD_REQUEST.value())).andReturn().getResponse().getContentAsString()
        response == "Matrix of invalid data provided"
    }

    def "should integrate with calculator service"() {
        given:
        String inputJson = """
        {
            "matrix": [
                [1,1,1,0],
                [1,1,1,1]
            ],
            "rowLength": 4,
            "colLength": 2
        }
        """
        final SubMatrix expectedOut = new SubMatrix(2,3,0,0,6)

        when:
        def resultActions = mockMvc.perform(post("/get-submatrix-of-1")
                .contentType(APPLICATION_JSON)
                .content(inputJson))

        then:
        def responseJson = resultActions.andExpect(status().isOk()).andReturn().getResponse().getContentAsString()
        SubMatrix actualResponse = objectMapper.readValue(responseJson, SubMatrix.class)
        actualResponse == expectedOut
    }


}
