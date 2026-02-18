package com.cmpt276.staff_rater;

import com.cmpt276.staff_rater.model.RoleType;
import com.cmpt276.staff_rater.model.StaffRating;
import com.cmpt276.staff_rater.model.StaffRatingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StaffRaterApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StaffRatingRepository repo;

    private final Validator validator;

    StaffRaterApplicationTests() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }


    @Test
    void invalidEmailRejected() {
        StaffRating r = makeValid();
        r.setEmail("not-an-email");
        Set<ConstraintViolation<StaffRating>> v = validator.validate(r);
        assertThat(v).anyMatch(x -> x.getPropertyPath().toString().equals("email"));
    }

    @Test
    void outOfRangeScoreRejected() {
        StaffRating r = makeValid();
        r.setClarity(11);
        Set<ConstraintViolation<StaffRating>> v = validator.validate(r);
        assertThat(v).anyMatch(x -> x.getPropertyPath().toString().equals("clarity"));
    }

    @Test
    void zeroScoreRejected() {
        StaffRating r = makeValid();
        r.setNiceness(0);
        Set<ConstraintViolation<StaffRating>> v = validator.validate(r);
        assertThat(v).anyMatch(x -> x.getPropertyPath().toString().equals("niceness"));
    }

    @Test
    void missingNameRejected() {
        StaffRating r = makeValid();
        r.setName("");
        Set<ConstraintViolation<StaffRating>> v = validator.validate(r);
        assertThat(v).anyMatch(x -> x.getPropertyPath().toString().equals("name"));
    }

    @Test
    void commentTooLongRejected() {
        StaffRating r = makeValid();
        r.setComment("a".repeat(301));
        Set<ConstraintViolation<StaffRating>> v = validator.validate(r);
        assertThat(v).anyMatch(x -> x.getPropertyPath().toString().equals("comment"));
    }

    @Test
    void validRatingPasses() {
        StaffRating r = makeValid();
        Set<ConstraintViolation<StaffRating>> v = validator.validate(r);
        assertThat(v).isEmpty();
    }


    @Test
    void getIndexReturns200() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ratings"))
                .andExpect(view().name("index"));
    }

    @Test
    void getCreateFormReturns200() throws Exception {
        mockMvc.perform(get("/ratings/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("create"));
    }

    @Test
    void postCreateSuccessRedirects() throws Exception {
        mockMvc.perform(post("/ratings/create")
                        .param("name", "Test Person")
                        .param("email", "unique-test-" + System.currentTimeMillis() + "@example.com")
                        .param("roleType", "TA")
                        .param("clarity", "8")
                        .param("niceness", "7")
                        .param("knowledge", "9")
                        .param("comment", "Great"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void postCreateInvalidShowsFormAgain() throws Exception {
        mockMvc.perform(post("/ratings/create")
                        .param("name", "")
                        .param("email", "bad-email")
                        .param("clarity", "99"))
                .andExpect(status().isOk())
                .andExpect(view().name("create"));
    }


    @Test
    void saveAndRetrieveWorks() {
        StaffRating r = makeValid();
        r.setEmail("save-test-" + System.currentTimeMillis() + "@test.com");
        StaffRating saved = repo.save(r);

        StaffRating found = repo.findById(saved.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Jane Doe");
        assertThat(found.getClarity()).isEqualTo(8);
    }

    @Test
    void deleteRemovesEntry() {
        StaffRating r = makeValid();
        r.setEmail("delete-test-" + System.currentTimeMillis() + "@test.com");
        StaffRating saved = repo.save(r);
        int id = saved.getId();

        repo.deleteById(id);
        assertThat(repo.findById(id)).isEmpty();
    }


    private StaffRating makeValid() {
        StaffRating r = new StaffRating();
        r.setName("Jane Doe");
        r.setEmail("jane@example.com");
        r.setRoleType(RoleType.TA);
        r.setClarity(8);
        r.setNiceness(7);
        r.setKnowledge(9);
        r.setComment("Good");
        return r;
    }
}
