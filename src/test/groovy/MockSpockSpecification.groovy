import spock.lang.Specification

public class MockSpockSpecification extends Specification {

    def "size method should be executed one time"() {
        given:
        final List list = Mock()

        when:
        list.add(1)
        list.add("")
        list.size()
        then:
        1 * list.size()

        // explicit parameter value
        1 * list.add(1)

        // wildcard as parameter
        1 * list.add(!null)
    }

    def "verify number of calls"() {
        given:
        final List list = Mock()

        when:
        list.size()
        then:
        // should not be invoked at all
        // 0 * list.size()
        // should be invoked at least one time
        (1.._) * list.size()
        // should be invoked at most one time
        (_..1) * list.size()
        // any number of calls
        _ * list.size()
    }

    def "check the order of calls"() {
        given:
        final List list = Mock()

        when:
        list.add("String")
        list.size()
        then:
        1 * list.add(_)
        then:
        1 * list.size()
    }
}