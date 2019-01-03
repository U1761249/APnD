package arrayGenerator;

/**
 * @author Hugh Osborne
 * @version September 2018
 */

class CleverRandomListingGeneratorTest extends ListingGeneratorTest {
    @Override
    protected ListingGenerator createArrayGenerator(int size) {
        return new CleverRandomListingGenerator(size);
    }
}