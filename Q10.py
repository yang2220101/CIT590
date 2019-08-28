#Given the following coefficients: 1, 3, 1
#Solve the quadratic equation: ax^2 + bx + c

#Here's a reminder of the quadratic equation formula
#https://en.wikipedia.org/wiki/Quadratic_formula
a,b,c = 1,3,1#set a,b and c to certain value respectively for further calculation
d = b ** 2 - 4 * a * c#we need to use '**'for power calculation
if d < 0:
    print('no solution')
elif d == 0:#use '==' for bool calculation
  print(-b/2*a)
else:
    solution1 = -b + d ** 0.5 / 2 * a
    solution2 = -b - d ** 0.5 / 2 * a#use '**'for power calculation
    print('solution1:',solution1)
    print('solution2:',solution2)#this is the right way to print 2 solutions respectively

