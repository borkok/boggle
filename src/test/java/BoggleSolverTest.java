import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.of;

class BoggleSolverTest {

    private static Stream<Arguments> paramsAll() {
        return Stream.of(
                of(new BoggleBoard(new char[][]{{'A'}}), List.of())
                ,of(new BoggleBoard(new char[][]{{'A','B'}}), List.of())
                ,of(new BoggleBoard(new char[][]{{'A'},{'B'}}), List.of())
                ,of(new BoggleBoard(new char[][]{{'A','B','C'}}), List.of())
                ,of(new BoggleBoard(new char[][]{{'A','N','T'}}), List.of("ANT"))
                ,of(new BoggleBoard(new char[][]{{'A','N','T','S'}}), List.of("ANT","ANTS"))
                ,of(new BoggleBoard(new char[][]{{'A','N','T','S'}}), List.of("ANT","ANTS"))
                ,of(new BoggleBoard(new char[][]{{'X','A','N','T'}}), List.of("ANT"))
                ,of(new BoggleBoard(new char[][]{{'T','N','A'}}), List.of("ANT"))
                ,of(new BoggleBoard(new char[][]{{'C','A','T','N','A'}}), List.of("ANT","CAT","ATN"))
                ,of(new BoggleBoard(new char[][]{{'C','A'},{'N','T'}}), List.of("ANT","CAT","ATN"))
                ,of(new BoggleBoard(new char[][]{{'C'},{'A'},{'T'},{'N'},{'A'}}), List.of("ANT","CAT","ATN"))
                ,of(new BoggleBoard(new char[][]{{'Q','E'},{'E','N'}}), List.of("QUEEN"))
        );
    }

    @ParameterizedTest
    @MethodSource("paramsAll")
    void getAllValidWords(BoggleBoard input, List<String> expected) {
        BoggleSolver testee = new BoggleSolver(new String[]{"AN", "ANT", "ANTS", "CAT", "ATN", "FORCE", "FORCE", "INDEPENDENCE", "QUEEN"});
        assertThat(testee.getAllValidWords(input)).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void getAllValidWords3x3() {
        String[] dictionary = {
                "CAT", "CAN", "ANT", "ANTS", "ATN", "FORCE", "FORCE", "INDEPENDENCE",
                "CAR", "CURSE", "CESR", "CRSE", "CSENAT", "SENAT", "CNAR", "CRAT", "ECR", "RCE", "RUCT", "NCU", "UAT", "TACT", "SECURS"
        };
        BoggleSolver testee = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(new char[][]{
                {'T','A','R'},
                {'N','C','U'},
                {'E','S','R'}
        });
        List<String> expected = List.of("CAT", "CAN", "ANT", "ATN",
                "CAR", "CURSE", "CESR", "CRSE", "CSENAT", "SENAT", "CNAR", "CRAT",
                "ECR", "RCE", "RUCT", "NCU", "UAT");
        assertThat(testee.getAllValidWords(board)).containsExactlyInAnyOrderElementsOf(expected);
    }

    private static Stream<Arguments> params() {
        return Stream.of(
                of(new String[]{}, "A", 0)
                , of(new String[]{"A"}, "B", 0)
                , of(new String[]{"A"}, "A", 0)
                , of(new String[]{"ANT", "ANTS"}, "ANT", 1)
                , of(new String[]{"ANT", "ANTS"}, "ANTS", 1)
                , of(new String[]{"ANT", "ANTS", "FORCE"}, "FORCE", 2)
                , of(new String[]{"ANT", "ANTS", "FORCE", "FORCES"}, "FORCES", 3)
                , of(new String[]{"ANT", "ANTS", "FORCE", "FORCES", "METANOL"}, "METANOL", 5)
                , of(new String[]{"ANT", "ANTS", "FORCE", "FORCE", "METANOLS"}, "METANOLS", 11)
                , of(new String[]{"ANT", "ANTS", "FORCE", "FORCE", "INDEPENDENCE"}, "INDEPENDENCE", 11)
        );
    }

    @ParameterizedTest
    @MethodSource("params")
    void scoreOf(String[] dictionary, String word, int expected) {
        assertThat(new BoggleSolver(dictionary).scoreOf(word)).isEqualTo(expected);
    }

    @Test
    void scoreOf_exceptions() {
        assertThatThrownBy(() -> new BoggleSolver(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new BoggleSolver(new String[]{}).scoreOf(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new BoggleSolver(new String[]{}).scoreOf("")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new BoggleSolver(new String[]{}).scoreOf("Abc")).isInstanceOf(IllegalArgumentException.class);
    }
}