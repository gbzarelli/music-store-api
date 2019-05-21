package br.com.beblue.musicstore.controller;

import br.com.beblue.musicstore.dto.GenreDTO;
import br.com.beblue.musicstore.service.GenreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GenreController.class)
class GenreControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GenreService genreService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void test_not_allowed_post_method() throws Exception {
        mockMvc.perform(post(GenreController.ROOT_PATH))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void test_allowed_get_method() throws Exception {
        mockMvc.perform(get(GenreController.ROOT_PATH))
                .andExpect(status().isOk());
    }

    @Test
    void test_list_genres() throws Exception {
        List<GenreDTO> genres = getFakeGenres();
        Mockito.when(genreService.getAllGenres()).thenReturn(genres);
        mockMvc.perform(get(GenreController.ROOT_PATH))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(genres)));
    }

    private List<GenreDTO> getFakeGenres() {
        List<GenreDTO> genres = new ArrayList<>();
        genres.add(new GenreDTO(1, "n1"));
        genres.add(new GenreDTO(2, "n2"));
        return genres;
    }

}
