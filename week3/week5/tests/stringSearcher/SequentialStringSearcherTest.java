package stringSearcher;

class SequentialStringSearcherTest extends StringSearcherTest {

    @Override
    StringSearcher getSearcher(String string) {
        return new SequentialStringSearcher(string);
    }
}