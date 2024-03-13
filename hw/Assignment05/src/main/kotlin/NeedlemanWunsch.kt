class NeedlemanWunsch {
    companion object{
        /**
         * Implements the Needleman-Wunsch algorithm for global sequence alignment.
         * This function aligns two sequences by determining the optimal global alignment
         * with the highest possible alignment score, based on the provided scoring scheme.
         *
         * @param seq1 The first sequence to be aligned.
         * @param seq2 The second sequence to be aligned.
         * @param matchScore The score for matching characters (default is 1).
         * @param mismatchPenalty The penalty for mismatching characters (default is -1).
         * @param gapPenalty The penalty for gaps (default is -2).
         * @return A pair of strings representing the aligned sequences.
         */
        fun needlemanWunsch(
            seq1: String,
            seq2: String,
            matchScore: Int = 1,
            mismatchPenalty: Int = -1,
            gapPenalty: Int = -2
        ): Pair<String, String> {
            val m = seq1.length
            val n = seq2.length
            val score = Array(m + 1) { IntArray(n + 1) }
            val traceback = Array(m + 1) { Array(n + 1) { "" } }

            for (i in 0..m) {
                score[i][0] = i * gapPenalty
            }
            for (j in 0..n) {
                score[0][j] = j * gapPenalty
            }

            for (i in 1..m) {
                for (j in 1..n) {
                    val match = score[i - 1][j - 1] + if (seq1[i - 1] == seq2[j - 1]) matchScore else mismatchPenalty
                    val delete = score[i - 1][j] + gapPenalty
                    val insert = score[i][j - 1] + gapPenalty
                    score[i][j] = maxOf(match, delete, insert)
                    traceback[i][j] = when {
                        score[i][j] == match -> "Diagonal"
                        score[i][j] == delete -> "Up"
                        else -> "Left"
                    }
                }
            }

            // Traceback
            var align1 = ""
            var align2 = ""
            var i = m
            var j = n
            while (i > 0 || j > 0) {
                when {
                    i > 0 && j > 0 && traceback[i][j] == "Diagonal" -> {
                        align1 = seq1[i - 1] + align1
                        align2 = seq2[j - 1] + align2
                        i--
                        j--
                    }
                    i > 0 && traceback[i][j] == "Up" -> {
                        align1 = seq1[i - 1] + align1
                        align2 = "-$align2"
                        i--
                    }
                    j > 0 -> { // This now catches both "Left" and any remaining cases where j > 0
                        align1 = "-$align1"
                        align2 = seq2[j - 1] + align2
                        j--
                    }
                }
            }

            return Pair(align1, align2)
        }
    }
}