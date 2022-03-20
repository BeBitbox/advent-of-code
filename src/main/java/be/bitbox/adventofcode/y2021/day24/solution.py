# solution found here https://www.reddit.com/r/adventofcode/comments/rnejv5/2021_day_24_solutions/hpsjfis/
# All credit to https://www.reddit.com/user/4HbQ/

instr, stack = [*open(0)], []

p, q = 99999999999999, 11111111111111

for i in range(14):
    a = int(instr[18*i+5].split()[-1])
    b = int(instr[18*i+15].split()[-1])

    if a > 0: stack+=[(i, b)]; continue
    j, b = stack.pop()

    p -= abs((a+b)*10**(13-[i,j][a>-b]))
    q += abs((a+b)*10**(13-[i,j][a<-b]))

print(p, q)