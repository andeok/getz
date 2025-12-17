package kr.getz.fut.formation.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Formation {
    FUTSAL_2_2("2-2", MatchType.FUTSAL, 2),
    FUTSAL_2_1_1("2-1-1", MatchType.FUTSAL, 2),
    FUTSAL_1_2_1("1-2-1", MatchType.FUTSAL, 1),

    SOCCER_3_4_3("3-4-3", MatchType.SOCCER, 3),
    SOCCER_3_3_3_1("3-3-3-1", MatchType.SOCCER, 3),
    SOCCER_3_5_2("3-5-2", MatchType.SOCCER, 3),
    SOCCER_3_4_1_2("3-4-1-2", MatchType.SOCCER, 3),
    SOCCER_3_6_1("3-6-1", MatchType.SOCCER, 3),
    SOCCER_3_4_2_1("3-4-2-1", MatchType.SOCCER, 3),
    SOCCER_3_2_4_1("3-2-4-1", MatchType.SOCCER, 3),

    SOCCER_4_6_0("4-6-0", MatchType.SOCCER, 4),
    SOCCER_4_5_1("4-5-1", MatchType.SOCCER, 4),
    SOCCER_4_4_1_1("4-4-1-1", MatchType.SOCCER, 4),
    SOCCER_4_4_2("4-4-2", MatchType.SOCCER, 4),
    SOCCER_4_3_1_2("4-3-1-2", MatchType.SOCCER, 4),
    SOCCER_4_3_2_1("4-3-2-1", MatchType.SOCCER, 4),
    SOCCER_4_3_3("4-3-3", MatchType.SOCCER, 4),
    SOCCER_4_2_3_1("4-2-3-1", MatchType.SOCCER, 4),
    SOCCER_4_2_2_2("4-2-2-2", MatchType.SOCCER, 4),
    SOCCER_4_1_4_1("4-1-4-1", MatchType.SOCCER, 4),
    SOCCER_4_1_2_3("4-1-2-3", MatchType.SOCCER, 4),

    SOCCER_5_3_2("5-3-2", MatchType.SOCCER, 5),
    SOCCER_5_4_1("5-4-1", MatchType.SOCCER, 5),
    ;

    private final String name;
    private final MatchType matchType;
    private final int defenderCount;

    public enum MatchType {
        SOCCER, FUTSAL
    }

    public static List<Formation> getFormationBy(MatchType matchType) {
        return Arrays.stream(values())
            .filter(formation -> formation.matchType == matchType)
            .collect(Collectors.toList());
    }

    public static List<Formation> getFormationByDefenderCount(MatchType matchType, int count) {
        return Arrays.stream(values())
            .filter(formation -> formation.matchType == matchType && formation.defenderCount == count)
            .collect(Collectors.toList());
    }

}
