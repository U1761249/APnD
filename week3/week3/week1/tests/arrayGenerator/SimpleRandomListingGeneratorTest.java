package arrayGenerator;

/**
 * @author Hugh Osborne
 * @version September 2018
 */

class SimpleRandomListingGeneratorTest extends ListingGeneratorTest {
    @Override
    protected ListingGenerator createArrayGenerator(int size) {
        return new SimpleRandomListingGenerator(size);
    }
}