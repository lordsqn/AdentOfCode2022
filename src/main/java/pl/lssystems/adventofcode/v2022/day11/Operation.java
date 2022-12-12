package pl.lssystems.adventofcode.v2022.day11;

public enum Operation {
    ADD('+'), MULTIPLY('*');
    final char sign;

    Operation(char sign) {
        this.sign = sign;
    }

    public static Operation getOperationBySign(char sign) {
        for (Operation operation : Operation.values())
            if (operation.sign == sign)
                return operation;

        return null;
    }

}
