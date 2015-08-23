import spock.lang.Specification

public class SpySpockSpecification extends Specification {

    def "simple spy behavior"() {
        given:
        final List<String> list = Spy(ArrayList)
        list.contains(_) >> true

        when:
        list.add("Value")
        then:
        list.size() == 1
        list.contains("Another value")
    }
}