# BallClock
There are 2 modes:

Mode 1 (Cycle Days) 
The first mode takes a single parameter specifying the number of balls and reports the number 
of balls given in the input and the number of days (24-hour periods) which elapse before the 
clock returns to its initial ordering. 
Name in project *getCycleDays()*;

Mode 2 (Clock State) 
The second mode takes two parameters, the number of balls and the number of minutes to run 
for. If the number of minutes is specified, the clock must run to the number of minutes and 
report the state of the tracks at that point in a JSON format.
Name in project *getClockState()*;
