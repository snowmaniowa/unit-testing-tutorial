import spock.lang.Specification;

/**
 * A mock is a dummy class replacing a real one,
 * returning something like null or 0 for each method call.
 * You use a mock if you need a dummy instance of a complex
 * class which would otherwise use external resources like network connections,
 * files or databases or maybe use dozens of other objects.
 * The advantage of mocks is that you can isolate the class
 * under test from the rest of the system.
 * <p>
 * A stub is also a dummy class providing some more specific,
 * prepared or pre-recorded, replayed results to certain requests under test.
 * You could say a stub is a fancy mock.
 * In Spock you will often read about stub methods.
 * <p>
 * A spy is kind of a hybrid between real object and stub,
 * i.e. it is basically the real object with some (not all)
 * methods shadowed by stub methods. Non-stubbed methods are just routed
 * through to the original object. This way you can have original behaviour for "cheap"
 * or trivial methods and fake behaviour for "expensive" or complex methods.
 */
public class StubSpockSpecification extends Specification {

    def "ways to create simple stubs"() {
        given:
        final List list = Stub(List)
        final List list2 = Stub() // best way
        final def list3 = Stub(List)
    }

    def "should return 3 as size when needed"() {
        given:
        final List list = Stub()
        list.size() >> 3

        expect:
        list.size() == 3
    }

    def "specifying that exception should be thrown"() {
        given:
        final List list = Stub()
        list.size() >> { throw new IllegalStateException() }

        when:
        list.size()
        then:
        thrown IllegalStateException
    }

    def "should return different values"() {
        given:
        final List list = Stub()
        list.size() >>> [1, 2, 3]

        expect:
        list.size() == 1
        list.size() == 2
        list.size() == 3
    }

    def "should return different values or throw exception"() {
        given:
        final List list = Stub()
        list.size() >> { throw new IllegalStateException() } >>> [1, 2, 3] >>
                { throw new IllegalStateException() } >>> [5, 6]
        when:
        list.size()
        then:
        thrown IllegalStateException

        when:
        final int size = list.size()
        then:
        notThrown IllegalStateException
        size == 1
    }

    def "should return 2 for method parameter equal to 2"() {
        given:
        final List list = Stub()
        list.get(0) >> 0
        list.get(1) >> { throw new IllegalArgumentException() }
        list.get(2) >> 2

        expect:
        list.get(2) == 2
    }

    def "should throw exception if an Integer is added to the list"() {
        given:
        final List list = Stub()
        list.add(_ as Integer) >> { throw new IllegalArgumentException() }

        when:
        list.add(2)
        then:
        thrown(IllegalArgumentException)

        when:
        list.add("String")
        then:
        notThrown(IllegalArgumentException)
    }

}
