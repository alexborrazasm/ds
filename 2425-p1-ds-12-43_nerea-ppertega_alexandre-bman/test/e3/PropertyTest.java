package e3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    private static Property p1, p2, p3, p4, p5, p6;
    private static List<String> list;

    @BeforeAll
    static void setUp() {

        p1 = new Property(PropertyType.APARTMENT,
                "01234567890123456789",
                "Aurelio Aguirre Galarraga 100, 1-A, A Coruna",
                "15190",
                80,
                2,
                1
        );

        p2 = new Property(PropertyType.APARTMENT,
                "56789012345678901234",
                "Aurelio Aguirre Galarraga 202, 5-D, A Coruna",
                "15190",
                100,
                3,
                2
        );

        /* Same cadaster as h2 but the entered address and meters are different. */
        p3 = new Property(PropertyType.APARTMENT,
                "56789012345678901234",
                "Aurelio Aguirre Galarraga 202, 5º D (A Coruna)",
                "15190",
                95,
                3,
                2
        );

        p4 = new Property(PropertyType.LOCAL,
                "56789012345678901234",
                "Aurelio Aguirre Galarraga 202, 5º D (A Coruna)",
                "15190",
                95,
                3,
                2
        );

        p5 = new Property(PropertyType.LOCAL,
                "56789012345678901234",
                null,
                "15190",
                95,
                3,
                2
        );

        p6 = new Property(PropertyType.LOCAL,
                null,
                "15008",
                "15190",
                95,
                3,
                2
        );

        list = new ArrayList<>();
    }

    /* Equality is defined by the cadaster. */

    @Test
    void testEquals() {
        assertEquals(p2, p3);
        assertNotEquals(p1, null);
        assertNotEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertEquals (p3, p4); // Same id, different PropertyType
        assertNotEquals(p1, p5);
        assertNotEquals(p3, p6); // p6 id is null
        assertNotEquals(p3, list);
    }

    @Test
    void testHashCode() {
        assertEquals(p2.hashCode(), p3.hashCode());
        assertNotEquals(p2.hashCode(), p1.hashCode());
    }

    @Test
    void testToString() {
        String expected = """
                APARTMENT
                01234567890123456789
                Aurelio Aguirre Galarraga 100, 1-A, A Coruna
                15190
                80 meters, 2 rooms, 1 bathrooms
                """;
        String actual = p1.toString();
        assertEquals(expected, actual);
    }
}