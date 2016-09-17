package com.xieziming.stap.data.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Suny on 9/15/16.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
public class TestCaseTest {
    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/documents");

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).apply(documentationConfiguration(this.restDocumentation)).build();
    }

    @Test
    public void testRoot() throws Exception {
        this.mockMvc.perform(get("/testCase")).
            andExpect(status().isOk()).
                andDo(
                    document("testCase",
                        links(
                            linkWithRel("search").description("The <<resources-notes,Notes resource>>"),
                            linkWithRel("first").description("The <<resources-notes,Notes resource>>"),
                            linkWithRel("next").description("The <<resources-notes,Notes resource>>"),
                            linkWithRel("last").description("The <<resources-notes,Notes resource>>"),
                            linkWithRel("self").description("The <<resources-notes,Notes resource>>"),
                            linkWithRel("profile").description("The ALPS profile for the service")
                        ),
                        responseFields(
                            fieldWithPath("_links").description("<<resources-testCaseRoot-links,Links>> to other resources"),
                            fieldWithPath("_embedded").description("<<resources-testCaseRoot-links,Links>> to other resources"),
                            fieldWithPath("page").description("The ALPS profile for the service")
                        )
                    )
                );

    }

    @Test
    public void testNotExistRoot() throws Exception {
        this.mockMvc.perform(get("/testCases")).andExpect(status().isNotFound());
    }
}
