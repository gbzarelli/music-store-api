package br.com.beblue.musicstore.controller;

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

@WebMvcTest(DiscController.class)
class DiscControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DiscService discService;

    @Test
    void test_not_allowed_post_method() throws Exception {
        mockMvc.perform(post(DiscController.ROOT_PATH))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void test_method_get_by_id() throws Exception {
        mockMvc.perform(get(DiscController.ROOT_PATH + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    void test_method_get_by_id_with_no_value_exception() throws Exception {
        int userId = 555;
        NoValuePresentException exception = new NoValuePresentException("no value for id=" + userId);
        when(discService.getDisc(userId)).thenThrow(exception);

        mockMvc.perform(get(DiscController.ROOT_PATH + "/" + userId))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(exception.getMessage())));
    }

    @Test
    void test_method_get_all() throws Exception {
        mockMvc.perform(get(DiscController.ROOT_PATH))
                .andExpect(status().isOk());
    }

    @Test
    void test_method_get_by_genre() throws Exception {
        mockMvc.perform(get(DiscController.ROOT_PATH + "/genre/rock"))
                .andExpect(status().isOk());
    }

}