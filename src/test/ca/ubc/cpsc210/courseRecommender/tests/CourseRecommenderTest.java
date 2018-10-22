package ca.ubc.cpsc210.courseRecommender.tests;

import ca.ubc.cpsc210.courseRecommender.Course;
import ca.ubc.cpsc210.courseRecommender.CourseRecommender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CourseRecommenderTest {
    private CourseRecommender testRecommender;
    private Course c101;
    private Course c110;
    private Course c210;
    private Course c121;

    @BeforeEach
    public void setUp() throws Exception {
        testRecommender = new CourseRecommender();
        c101 = new Course("CPSC", 101);
        c110 = new Course("CPSC", 110);
        c121 = new Course("CPSC", 121);
        c210 = new Course("CPSC", 210);
    }

    @Test
    public void testNoRecommendation() {
        assertEquals(null, testRecommender.recommended(c101));
    }

    @Test
    public void testCourseWithSingleRecommendation() {
        testRecommender.addRecommendation(c110, c210);
        Set<Course> recommended = testRecommender.recommended(c110);
        assertEquals(1, recommended.size());
        assertTrue(recommended.contains(c210));
    }

    @Test
    public void testCourseWithMultipleRecommendations() {
        testRecommender.addRecommendation(c110, c210);
        testRecommender.addRecommendation(c110, c121);
        Set<Course> recommended = testRecommender.recommended(c110);
        assertEquals(2, recommended.size());
        assertTrue(recommended.contains(c121));
        assertTrue(recommended.contains(c210));
    }

    @Test
    public void testMultipleCoursesWithRecommendations() {
        testRecommender.addRecommendation(c101, c110);
        testRecommender.addRecommendation(c110, c210);
        testRecommender.addRecommendation(c110, c121);
        Set<Course> recommended101 = testRecommender.recommended(c101);
        assertEquals(1, recommended101.size());
        assertTrue(recommended101.contains(c110));
        Set<Course> recommended110 = testRecommender.recommended(c110);
        assertEquals(2, recommended110.size());
        assertTrue(recommended110.contains(c121));
        assertTrue(recommended110.contains(c210));
    }

    @Test
    public void testCourseWithSingleRecommendationAlternate() {
        testRecommender.addRecommendation(c110, c210);
        Set<Course> recommended = testRecommender.recommended(c110);
        assertEquals(1, recommended.size());
        assertTrue(recommended.contains(new Course("CPSC", 210)));
    }
}
