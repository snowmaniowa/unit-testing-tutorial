import spock.lang.Specification

public class SimpleSpockSpecification extends Specification {

    def "map should contain 2 elements and EN value for the USA key"() {
        // defines context, components under the test
        given:
        final HashMap<String, String> map = new HashMap<>();
        // what we want to test, some interaction with tested objects

        when:
        map.put("USA", "en")
        map.put("France", "fr")
        // verify components
        then:
        2 == map.size()
        def language = map.get("USA");
        "EN".equalsIgnoreCase(language)
    }

    def "should round to 2"() {
        // allows to create tiny tests
        expect:
        Math.round(2.4) == 2
    }

    def "length of Spock's and his friends' names"() {
        expect:
        name.size() == length

        where:
        name     | length
        "Spock"  | 5
        "Kirk"   | 4
        "Scotty" | 6
    }

    def "this will run three tests"() {
        expect:
        Math.pow(base, 2) == expectedResult

        where:
        base || expectedResult
        2    || 4
        3    || 9
        10   || 100
    }

}