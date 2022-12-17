package pl.lssystems.adventofcode.v2022.day16;

import java.util.*;

public class Combination<T> implements Iterator<List<T>> {
    final List<T> originalVector;
    List<T> currentCombination;
    long currentIndex;
    final int lengthN;
    final int lengthK;
    int[] bitVector;
    private int endIndex = 0;

    Combination(Collection<T> set, int combinationLength) {
        this.originalVector = new ArrayList<>(set);
        this.lengthN = set.size();
        this.lengthK = combinationLength;
        this.currentCombination = new ArrayList<>();
        this.bitVector = new int[this.lengthK + 1];

        for(int i = 0; i <= this.lengthK; this.bitVector[i] = i++) {/* Just Iterate over */}

        if (this.lengthN > 0) {
            this.endIndex = 1;
        }

        this.currentIndex = 0L;
    }

    public boolean hasNext() {
        return this.endIndex != 0 && this.lengthK <= this.lengthN;
    }

    public List<T> next() {
        ++this.currentIndex;

        int i;
        for(i = 1; i <= this.lengthK; ++i) {
            int index = this.bitVector[i] - 1;
            if (this.originalVector.size() > 0) {
                setValue(this.currentCombination, i - 1, this.originalVector.get(index));
            }
        }

        this.endIndex = this.lengthK;

        while(this.bitVector[this.endIndex] == this.lengthN - this.lengthK + this.endIndex) {
            --this.endIndex;
            if (this.endIndex == 0) {
                break;
            }
        }

        this.bitVector[this.endIndex]++;

        for(i = this.endIndex + 1; i <= this.lengthK; ++i) {
            this.bitVector[i] = this.bitVector[i - 1] + 1;
        }

        return new ArrayList<>(this.currentCombination);
    }

    private static <T> void setValue(List<T> list, int index, T value) {
        if (index < list.size()) {
            list.set(index, value);
        } else {
            list.add(index, value);
        }
    }

}