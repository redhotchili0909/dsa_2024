# Markov Text Analysis Using Associative Arrays

In solving the problem of generating text based on a Markov chain, associative arrays were utilized to map each word in the input text to its subsequent possible words, along with their frequencies.

### Original Text:

- **Pangram**: the quick brown fox jumps over the lazy dog. the quick brown fox is quick,
  and the lazy dog is not quick but very lazy. the fox is very quick and jumps
  over the lazy dog frequently.
- **The Hobbit**: In a hole in the ground there lived a hobbit. Not a nasty, dirty, wet hole, filled with the ends of worms and an oozy smell,
  nor yet a dry, bare, sandy hole with nothing in it to sit down on or to eat: it was a hobbit-hole, and that means comfort.
- **Moby Dick**: Call me Ishmael. Some years ago—never mind how long precisely—having little or no money in my purse, and nothing particular to interest me on shore, I thought I would sail about a little and see the watery part of the world. It is a way I have of driving off the spleen and regulating the circulation.

### Markov Results:

- **Pangram**: the fox is not quick brown fox is very lazy. the fox jumps over the lazy dog is quick, and the lazy dog is very lazy. the lazy dog. the fox is not quick and the fox jumps over the lazy dog frequently. the fox is very quick but very quick and jumps over the quick brown fox is very lazy.
- **The Hobbit**: In a hole in it was a dry, bare, sandy hole in it was a nasty, dirty, wet hole, filled with nothing in it to eat: it was a hobbit-hole, and an oozy smell, nor yet a hobbit. Not a dry, bare, sandy hole in it was a hole with nothing in the ends of worms and that means comfort. Not a hole with nothing in it was a nasty, dirty, wet hole, filled with nothing in the ends of worms and an oozy smell, nor yet a hobbit. Not a hole in it to eat: it to sit down on or to eat: it was a hobbit-hole, and an oozy smell, nor yet a hobbit-hole, and an oozy smell, nor yet a nasty, dirty, wet hole, filled with the ends of worms and that means comfort. In a nasty, dirty, wet hole, filled with the ground there lived a nasty, dirty, wet hole, filled with the ground there lived a hobbit.
- **Moby Dick**: Some years ago—never mind how long precisely—having little or no money in my purse, and see the world. Call me on shore, I would sail about a way I thought I have of the world. Call me on shore, I thought I thought I have of driving off the world. Call me Ishmael. It is a little or no money in my purse, and see the world.
