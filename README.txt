Assumptions:
1. This is not a maze generator
2. This is not an autonomous maze solver
3. Movements are one step per request
4. Mazes are read from FileSystem

Known issues:
1. This is an unbounded requests-acceptor
2. North-South goes with "y", and East-West goes with "x": FIXED.
3. Session management is weak as it is un-bounded

Pending:
1. jUnit
2. Non-functional testing needed
3. Functional testing with corner cases needed
4. cache level 2 needed for production

