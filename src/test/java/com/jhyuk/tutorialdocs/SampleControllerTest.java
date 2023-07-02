package com.jhyuk.tutorialdocs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SampleController sampleController;

    @Test
    void getMember() {
    }

    @Test
    void testGetMember() {
    }

    @Test
    void addMember() throws Exception {
        final Member member = new Member(1L, "hong gildong", "1010");
        when(sampleController.addMember(any())).thenReturn(ResponseEntity.ok().body(member));

        this.mockMvc.perform(post("/members")
                        .content("""
                                {
                                  "name":"hong gildong",
                                  "phone":"010"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(document("member add",
                        requestFields(fieldWithPath("name").description("이름"),
                                fieldWithPath("phone").description("전화번호"))));

    }

    @Test
    void editMember() {
    }

    @Test
    void deleteMember() {
    }
}