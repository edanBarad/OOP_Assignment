import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LineTests {

    // Helper method to create a rectangle for tests
    private Rectangle createTestRectangle() {
        // Rectangle from (100,100) to (300,200)
        Point upperLeft = new Point(100, 100);
        double width = 200;
        double height = 100;
        return new Rectangle(upperLeft, width, height);
    }

    // Helper method for fuzzy point comparison due to double precision
    private void assertPointsEquals(Point expected, Point actual, String message) {
        if (expected == null && actual == null) {
            return; // Both are null, considered equal
        }
        if (expected == null || actual == null) {
            fail(message + ": One point is null, the other is not. Expected: " + expected + ", Actual: " + actual);
        }
        double EPSILON = 0.001; // Tolerance for double comparisons
        assertTrue(Math.abs(expected.getX() - actual.getX()) < EPSILON, message + ": X coordinates differ. Expected: " + expected.getX() + ", Actual: " + actual.getX());
        assertTrue(Math.abs(expected.getY() - actual.getY()) < EPSILON, message + ": Y coordinates differ. Expected: " + expected.getY() + ", Actual: " + actual.getY());
    }

    // --- Test Cases for closestIntersectionToStartOfLine ---

    @Test
    void test_01_LineDoesNotIntersectRectangle() {
        System.out.println("--- Running Test 01: Line Does Not Intersect Rectangle ---");
        Rectangle rect = createTestRectangle(); // (100,100)-(300,200)

        // Line completely outside, not touching
        Line line = new Line(0, 0, 50, 50); // From (0,0) to (50,50)
        Point intersection = line.closestIntersectionToStartOfLine(rect);
        assertNull(intersection, "Should return null if line does not intersect.");
        System.out.println("  Sub-test 01a Passed: Line outside, no intersection.");

        // Line completely outside, far away
        line = new Line(400, 150, 450, 150); // From (400,150) to (450,150)
        intersection = line.closestIntersectionToStartOfLine(rect);
        assertNull(intersection, "Should return null if line does not intersect.");
        System.out.println("  Sub-test 01b Passed: Line outside, far away.");

        // Line completely inside (if start is inside, it should also be null as it won't hit a wall)
        line = new Line(150, 150, 175, 175);
        intersection = line.closestIntersectionToStartOfLine(rect);
        assertNull(intersection, "Should return null if line is fully inside (no intersection with boundary).");
        System.out.println("  Sub-test 01c Passed: Line fully inside.");
        System.out.println("--- Test 01 Passed ---");
    }

    @Test
    void test_02_LineStartsInsideExitsOneWall() {
        System.out.println("--- Running Test 02: Line Starts Inside, Exits One Wall ---");
        Rectangle rect = createTestRectangle(); // (100,100)-(300,200)

        // Line starts inside, exits through the Right wall
        Line line = new Line(150, 150, 350, 150); // From (150,150) to (350,150)
        Point expected = new Point(300, 150); // Should hit Right wall at (300,150)
        Point actual = line.closestIntersectionToStartOfLine(rect);
        assertPointsEquals(expected, actual, "Line starts inside, exits Right wall.");
        System.out.println("  Sub-test 02a Passed: Exits Right wall.");

        // Line starts inside, exits through the Bottom wall
        line = new Line(150, 150, 150, 250); // From (150,150) to (150,250)
        expected = new Point(150, 200); // Should hit Bottom wall at (150,200)
        actual = line.closestIntersectionToStartOfLine(rect);
        assertPointsEquals(expected, actual, "Line starts inside, exits Bottom wall.");
        System.out.println("  Sub-test 02b Passed: Exits Bottom wall.");
        System.out.println("--- Test 02 Passed ---");
    }

    @Test
    void test_03_LineIntersectsTwoWalls_EntryThenExit() {
        System.out.println("--- Running Test 03: Line Intersects Two Walls (Entry/Exit) ---");
        Rectangle rect = createTestRectangle(); // (100,100)-(300,200)

        // Line enters Top, exits Right (standard case)
        Line line = new Line(250, 50, 350, 150); // From (250,50) to (350,150)
        Point expected = new Point(250, 100); // Should hit Top wall at (250,100) first
        Point actual = line.closestIntersectionToStartOfLine(rect);
        assertPointsEquals(expected, actual, "Line enters Top, exits Right.");
        System.out.println("  Sub-test 03a Passed: Enters Top, exits Right.");

        // Line enters Left, exits Bottom (standard case)
        line = new Line(50, 150, 150, 250); // From (50,150) to (150,250)
        expected = new Point(100, 175); // Should hit Left wall at (100,175) first
        actual = line.closestIntersectionToStartOfLine(rect);
        assertPointsEquals(expected, actual, "Line enters Left, exits Bottom.");
        System.out.println("  Sub-test 03b Passed: Enters Left, exits Bottom.");
        System.out.println("--- Test 03 Passed ---");
    }

    @Test
    void test_04_LineIntersectsTwoWalls_HitsCornerPrecisely() {
        System.out.println("--- Running Test 04: Line Intersects Two Walls (Hits Corner) ---");
        Rectangle rect = createTestRectangle(); // (100,100)-(300,200)

        // Line hits bottom-left corner
        Line line = new Line(50, 175, 125, 225); // From (50,175) -> (125,225)
        Point expected = new Point(100, 200); // Should hit (100,200) - the corner
        Point actual = line.closestIntersectionToStartOfLine(rect);
        assertPointsEquals(expected, actual, "Line hits bottom-left corner.");
        System.out.println("  Sub-test 04a Passed: Hits bottom-left corner.");
        System.out.println("--- Test 04 Passed ---");
    }

    @Test
    void test_05_ClosestIntersectionIsMissedByPrematureReturn() {
        System.out.println("--- Running Test 05: Closest Intersection Missed by Premature Return (CRITICAL!) ---");
        Rectangle rect = createTestRectangle(); // (100,100)-(300,200)

        // Line S = ((25, 250), (375, 75))
        //   - P_Left (100, 212.5), dist ~80.15 (found first in your order)
        //   - P_Top (325, 100), dist ~300.7 (found second in your order)
        //   - P_Bottom (125, 200), dist ~55.9 (THE TRUE CLOSEST, but found last)
        // Your code will find P_Left and P_Top, then return P_Left (dist ~80.15),
        // completely missing P_Bottom (dist ~55.9).

        Line line = new Line(25, 250, 375, 75); // Starts (25,250), ends (375,75)

        Point expectedClosest = new Point(125, 200); // The true closest point (on Bottom wall)
        Point actualResult = line.closestIntersectionToStartOfLine(rect);

        assertPointsEquals(expectedClosest, actualResult, "Closest point on Bottom wall should have been found, but was missed by early return.");
        System.out.println("  Sub-test 05a Passed: Closest point found correctly (if your code is fixed).");
        System.out.println("  *** This test is designed to FAIL with your current code ***");
        System.out.println("--- Test 05 Passed ---"); // This will only print if the assertion passes
    }


    @Test
    void test_06_SingleIntersection_CorrectlyReturned() {
        System.out.println("--- Running Test 06: Single Intersection Correctly Returned ---");
        Rectangle rect = createTestRectangle(); // (100,100)-(300,200)

        // Line starts inside, exits Bottom (will cause index to be 1 at the end)
        Line line = new Line(150, 150, 150, 250);
        Point expected = new Point(150, 200); // Bottom wall
        Point actual = line.closestIntersectionToStartOfLine(rect);
        assertPointsEquals(expected, actual, "Single intersection on Bottom wall should be returned.");
        System.out.println("  Sub-test 06a Passed: Single intersection found.");
        System.out.println("--- Test 06 Passed ---");
    }
}