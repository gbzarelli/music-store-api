package br.com.beblue.musicstore.controller.rest;

import br.com.beblue.musicstore.exception.NoValuePresentException;
import br.com.beblue.musicstore.service.DiscService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DiscRestController.class)
class DiscRestControllerTest {

    private static final String GENRE_ROCK = "rock";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DiscService discService;

    @Test
    void shouldNotAllowedPostMethod() throws Exception {
        mockMvc.perform(post(DiscRestController.ROOT_PATH))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void shouldReturnStatusOkByGetDiscByID() throws Exception {
        mockMvc.perform(get(DiscRestController.ROOT_PATH + DiscRestController.PATH_BY_ID, 1))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNoValueExceptionWhenCallMethodGetByIDWithNotFoundID() throws Exception {
        int discID = 555;
        NoValuePresentException exception = new NoValuePresentException("no value for id=" + discID);
        when(discService.getDiscByID(discID)).thenThrow(exception);

        mockMvc.perform(get(DiscRestController.ROOT_PATH + DiscRestController.PATH_BY_ID, discID))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(exception.getMessage())));
    }

    @Test
    void shouldReturnOKWhenGetDiscsCalled() throws Exception {
        mockMvc.perform(get(DiscRestController.ROOT_PATH))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOKWhenGetDiscsByGenresCalled() throws Exception {
        mockMvc.perform(get(DiscRestController.ROOT_PATH + DiscRestController.PATH_BY_GENRE, GENRE_ROCK))
                .andExpect(status().isOk());
    }

}