x = input('give me an integer')#set x to the value the user enters
try:#Try to cast the input
    x = int(x)#if the value the user enters is an interger,cast x into an interger
    if x%2 == 0:#to see if x is even
        print('Even')#if x is even, print 'Even'
    else:
        print('Odd')#if x is odd, print 'Odd'
except ValueError as e:
    print('please input an integer')#if x is not an integer, print a warning