import ngrams.TimeSeries;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TimeSeriesTest {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>();
        expectedYears.add(1991);
        expectedYears.add(1992);
        expectedYears.add(1994);
        expectedYears.add(1995);

        assertThat(totalPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedTotal = new ArrayList<>();
        expectedTotal.add(0.0);
        expectedTotal.add(100.0);
        expectedTotal.add(600.0);
        expectedTotal.add(500.0);

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }
    }

    @Test
    public void testEmptyBasic() {
        TimeSeries catPopulation = new TimeSeries();
        TimeSeries dogPopulation = new TimeSeries();

        assertThat(catPopulation.years()).isEmpty();
        assertThat(catPopulation.data()).isEmpty();

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);

        assertThat(totalPopulation.years()).isEmpty();
        assertThat(totalPopulation.data()).isEmpty();
    }

    @Test
    public void basicDivideTest() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);
        catPopulation.put(1995, 500.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1991, 2000.0);
        dogPopulation.put(1992, 50.0);
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.dividedBy(dogPopulation);
        // 1991: 0.0
        // 1992: 2.0
        // 1994: 0.5
        // 1995: 1.0
        List<Integer> expectedYear = new ArrayList<>();
        expectedYear.add(1991);
        expectedYear.add(1992);
        expectedYear.add(1994);
        expectedYear.add(1995);
        assertThat(totalPopulation.years()).isEqualTo(expectedYear);

        List<Double> expectedData = new ArrayList<>();
        expectedData.add(0.0);
        expectedData.add(2.0);
        expectedData.add(0.5);
        expectedData.add(1.0);

        for (int i = 0; i < expectedData.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedData.get(i));
        }
    }

    // TS has a year that is not in this TimeSeries
    @Test
    public void divideTSMissingTest() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 100.0);
        catPopulation.put(1992, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1991, 2000.0);
        dogPopulation.put(1992, 50.0);
        dogPopulation.put(1994, 200.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.dividedBy(dogPopulation);
        // 1991: 100 / 2000
        // 1992: 200 / 50

        List<Integer> expectedYear = new ArrayList<>();
        expectedYear.add(1991);
        expectedYear.add(1992);
        assertThat(totalPopulation.years()).isEqualTo(expectedYear);

        List<Double> expectedData = new ArrayList<>();
        expectedData.add(0.05); // 100 / 2000
        expectedData.add(4.0); // 200 / 50

        for (int i = 0; i < expectedData.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedData.get(i));
        }
    }
}