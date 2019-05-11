package com.matrix.http

import com.fasterxml.jackson.databind.ObjectMapper
import com.matrix.core.MaximumHistogram
import com.matrix.core.SubMatrix
import com.matrix.core.SubMatrixCalculator
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ApplicationTest extends Specification {

    private MockMvc mockMvc
    private ObjectMapper objectMapper
    private SubMatrixCalculator calculator = new SubMatrixCalculator(new MaximumHistogram())

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new Application(calculator)).build();
        objectMapper = new ObjectMapper();
    }

    def "should integrate with calculator service"() {
        given:
        String inputJson = """
        [
                [1,1,1,0],
                [1,1,1,1]
        ]
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
